package com.natixis.appdynamics.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appdynamics.JSON.JSONException;
import com.appdynamics.REST.RESTToolkit;
import com.appdynamics.TypeREST.ADApplication;
import com.appdynamics.TypeREST.ADNode;
import com.natixis.appdynamics.beans.BeanMachineDivers;
import com.natixis.appdynamics.beans.BeanMachineIsa;
import com.natixis.appdynamics.beans.BeanNode;
import com.natixis.appdynamics.beans.BeanTrigrammeDomaineProdappli;
import com.natixis.appdynamics.traitements.GetParamApplication;
import com.natixis.appdynamics.traitements.ReadFromFileMachineDivers;
import com.natixis.appdynamics.traitements.ReadFromFileMachineIsa;
import com.natixis.appdynamics.traitements.ReadFromFileTrigrammeDomaineProdappli;
import com.natixis.appdynamics.traitements.RechercheInfoByMachine;

@WebServlet(description = "Afficher tous les nodes", urlPatterns = { "/MakeInventory" }, loadOnStartup = 1)
public class MakeInventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MakeInventory() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    //Méthode pour mettre dans l'ordre alphabétique l'affichage
    public Map<String, Object> doSort(Map<String, Object> myMap) {
		 Map<String, Object> sortedMap = new TreeMap<String, Object>();
		 sortedMap.putAll(myMap);
		 return sortedMap;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.getWriter().append("Served at: ").append(request.getContextPath());		
		
		//Objet de connexion au controlleur
		RESTToolkit toolkit = new RESTToolkit(GetParamApplication.userApm, GetParamApplication.passwordApm, GetParamApplication.urlApm, "&output=XML");
		
		//ON récupère la liste des applicationName
		java.util.ArrayList<ADApplication> listApp = new java.util.ArrayList<ADApplication> ();
		try {
			listApp = toolkit.getApplications();			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Map qui contiendra la liste des AgentNode et leur object beanjava associé: clef="nom agent" / value="bean attribut agent"
		Map<String, Object> MapNode = new HashMap<String, Object>();
		
		//Map pour enregistrer les AgentNodes pour lesquels, on n'a pas trouvé les infos sur ProdAppli, Domaine, email
		Map<String, Object> MapNodeInfoManquant = new HashMap<String, Object>();
		
		//Chargemement du fichier référentiel IUA/Machine/ServeurApp/Trigramme/Techno dans une Map de bean avec comme clef ServeurApp
		new ReadFromFileMachineIsa();
		Map<String, Object> MapMachineIsa = ReadFromFileMachineIsa.getMapMachineIsa();
		
		//Chargement du fichier pour les serveurs divers (serveurs avec info renseignée en manuel: machine windows ou autre)
		new ReadFromFileMachineDivers();
		Map<String, Object> MapMachineDivers = ReadFromFileMachineDivers.getMachineDivers();
		
		//Chargemement du fichier de correspondance Trigramme/domaine/ProdAppli/Email dans une Map de bean avec comme clef Trigramme
		new ReadFromFileTrigrammeDomaineProdappli();
		Map<String, Object> MapTrigrammeDomaineProdappli = ReadFromFileTrigrammeDomaineProdappli.getMapTrigramme();		
		
		//Iteration sur la liste de applicationName pour récupérer les "AgentNode" pour chaque applicationName
		//Et ensuite on itère sur chaque "AgentNode" pour récupérer les attributs qui nous intéressent.
		Iterator<ADApplication> itrApp = listApp.iterator();
		while (itrApp.hasNext()) {
				ADApplication app = itrApp.next();				
				java.util.ArrayList<ADNode> listNode = new java.util.ArrayList<ADNode> ();		
				try {
					listNode = toolkit.getNodesFromApp(app.name);
				} catch (JSONException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Itération sur "AgentNode" pour récupérer les attributs
				//On créé un bean pour chaque "AgentNode"				
				Iterator<ADNode> itrNode = listNode.iterator();
				while (itrNode.hasNext()) {			
					ADNode node = itrNode.next();
					// Si Machine Agent, on ne traite pas, on passe.		
					if (node.tierName.equals("Machine Agent")) {
						//System.out.println("Machine "+node.machineName+" Agent: "+node.name+" TierName: "+node.tierName);
						continue;
					}					
					BeanNode Node = new BeanNode();					
					Node.setId(node.id);
					Node.setName(node.name);
					Node.setAppName(app.name);					
					Node.setMachineName(node.machineName);
					//On supprime le domaine dans le nom des machines
					CharSequence s = ".";					
					if (Node.getMachineName().contains(s)) {
						String var_machine = Node.getMachineName();
						String[] tab_machine = var_machine.split("\\.");
						String machine = tab_machine[0];						
						Node.setMachineName(machine);						
					}						
					Node.setTierName(node.tierName);
					Node.setMachineOSType(node.machineOSType);
					Node.setAppAgentPresent(node.appAgentPresent);
					Node.setAppAgentVersion(node.appAgentVersion);		
					Node.setTechno(node.type);					
					//Récupérer code le IUA depuis le fichier d'inventaire ISA ou le fichier MachineDivers 
					//On commence par récupérer dans une liste Machine+"-"+ServeurApp depuis notre Map d'inventaire ISA
					Set<Entry<String, Object>> setMachineSrvApp = MapMachineIsa.entrySet();
					Iterator<Entry<String, Object>> i = setMachineSrvApp.iterator();
					// Pour chaque entré de notre inventaire ISA, on regarde si une clef de la MAP correspond au nom de l'agent:
					// Est-ce que serveurApp est contenu dans le nom de l'agent qui peut être de la forme <nomMachine>_srvr ou <nomMachine>.srvr ou .srvr
					// Si trouvé, on récupère alors le code IUA, le trigramme et techno dans le bean correspondant.
					while(i.hasNext()) {
						 Map.Entry coupleKeyValue = (Map.Entry)i.next();
				         String machine_serveurApp = (String) coupleKeyValue.getKey();
				         String[] tab_machine_serveurApp = machine_serveurApp.split("-");
				         String serveurApp = tab_machine_serveurApp[1];				         
				         //On passe en minuscule pour éviter de faire échouer la comparation juste pour une histoire de casse
				         CharSequence serveurApp_minuscule = serveurApp.toLowerCase();
				         if (Node.getName().toLowerCase().contains(serveurApp_minuscule)) {				        
				        	 BeanMachineIsa BeanIua = (BeanMachineIsa) MapMachineIsa.get(machine_serveurApp);
				        	 //On s'assure que c'est l'agentNode sur la bonne machine avant de récupérer les informations				        					     
				        	 if (BeanIua.getMachine().equals(Node.getMachineName())) {
				        		 Node.setCodeIua(BeanIua.getCodeIua());
				        		 String Trigramme = BeanIua.getTrigramme();
				        		 Node.setTrigramme(Trigramme);
				        		 //Si la techno n'est pas renseigné, on récupère le valeur fournie par Appdynamics
				        		 if (!BeanIua.getTechno().isEmpty()) {				        	
				        			 Node.setTechno(BeanIua.getTechno());
				        		 }
				        		 //System.out.println(Node.getMachineName()+","+Node.getName()+","+Node.getTierName()+","+Node.getTrigramme()+","+Node.getTechno()+","+Node.getProdAppli());
				        		 if (Trigramme != null) {		        		
				        			 BeanTrigrammeDomaineProdappli MyBeanTrigrammeDomaineProdappli = (BeanTrigrammeDomaineProdappli) MapTrigrammeDomaineProdappli.get(Trigramme);
				        			 if (MyBeanTrigrammeDomaineProdappli != null) {
				        				 Node.setDomaine(MyBeanTrigrammeDomaineProdappli.getDomaine());
				        				 Node.setProdAppli(MyBeanTrigrammeDomaineProdappli.getProdAppli());
				        				 Node.setEmail(MyBeanTrigrammeDomaineProdappli.getEmail());
				        			 }				        			        		
				        		 }
				        		 break;
				        	}				        					        	 
				         }
					}
										
					//Si le champs ProdAppli est vide, on cherche dans le fichier annexe MachineDivers
					if (Node.getProdAppli().equals("null")) {
						//On fait la recherche avec l'agent node en minuscule et sinon avec la casse normale.
						BeanMachineDivers MyBeanMachineDivers = (BeanMachineDivers) MapMachineDivers.get(Node.getName().toLowerCase());
						if (MyBeanMachineDivers == null) {
							MyBeanMachineDivers = (BeanMachineDivers) MapMachineDivers.get(Node.getName());
						}
						if (MyBeanMachineDivers != null) {
							Node.setProdAppli(MyBeanMachineDivers.getProdAppli());
							Node.setEmail(MyBeanMachineDivers.getEmail());
							Node.setCodeIua(MyBeanMachineDivers.getCodeIua());
							Node.setDomaine(MyBeanMachineDivers.getDomaine());
						} else {
							// On met dans une Map les Nodes avec infos manquants pour ProdAppli
							MapNodeInfoManquant.put(Node.getName(),Node);
						}
					}										
					//System.out.println(Node.getMachineName()+","+Node.getName()+","+Node.getTierName()+","+Node.getTrigramme()+","+Node.getTechno()+","+Node.getProdAppli());
					MapNode.put(Node.getName(), Node);					
			   }
		}
		//Pour les infos manquants (prodAppli, domaine, email), on va essayer de trouver les infos en faisant une correspondance par machine
		RechercheInfoByMachine MyRechercheInfoByMachine = new RechercheInfoByMachine();
		MapNode=MyRechercheInfoByMachine.GetInfo(MapNodeInfoManquant, MapNode);
		
		//Appel de la méthode de tri alphabétique sur la Map		
		Map<String, Object> MapTried = doSort(MapNode);
		
		//On enregistre MapNode et MapNodeInfoManquant au niveau context de l'application
		ServletContext context = this.getServletContext();
		context.setAttribute("MapNode", MapTried);
		context.setAttribute("MapNodeInfoManquant", MapNodeInfoManquant);
		this.getServletContext().getRequestDispatcher("/WEB-INF/MakeInventory.jsp").forward(request, response);		
	}
}
