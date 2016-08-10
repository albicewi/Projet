package com.natixis.appdynamics.servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.natixis.appdynamics.beans.BeanMachineDivers;
import com.natixis.appdynamics.beans.BeanNode;
import com.natixis.appdynamics.traitements.ReadFromFileMachineDivers;
import com.natixis.appdynamics.traitements.RechercheInfoByMachine;
import com.natixis.appdynamics.traitements.UpdateFileMachineDivers;

@WebServlet("/UpdateInfoNode")
public class UpdateInfoNode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateInfoNode() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());		
		this.getServletContext().getRequestDispatcher("/WEB-INF/UpdateInfoNode.jsp").forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer les data passé au post
		String machine = request.getParameter("machine");
		String agentNode = request.getParameter("agentNode");
		String prodAppli = request.getParameter("prodAppli");
		String domaine = request.getParameter("domaine");
		String codeIua = request.getParameter("codeIua");
		String email = request.getParameter("email");		
		
		// Mettre à jour BeanNode
		// On commence par récupérer MapNode qui est visible au niveau scope Context.
		ServletContext context = this.getServletContext();
		Map<String, Object> MyMapNode = (Map<String, Object>) context.getAttribute("MapNode");
		
		//On récupère le bean qui qui correspond à AgentNode
		BeanNode MyBeanNode = (BeanNode) MyMapNode.get(agentNode);
		//System.out.println("Node: "+MyBeanNode);
		//On met à jour avec les infos fournies par l'utilisateur.
		MyBeanNode.setProdAppli(prodAppli);
		MyBeanNode.setDomaine(domaine);
		MyBeanNode.setCodeIua(codeIua);
		MyBeanNode.setEmail(email);		
		
	    //Récupération des données dans un bean 	
	  	BeanMachineDivers MyBeanMachineDivers = new BeanMachineDivers(machine,agentNode,prodAppli,email,codeIua,domaine);
	  	
	    //Mettre à jour la MAP mapMachineDivers
	  	Map<String, Object> mapMachineDivers = ReadFromFileMachineDivers.getMachineDivers();	  	
	  	mapMachineDivers.put(agentNode, MyBeanMachineDivers);
	  	
	  	//Mettre à jour le fichier MachineDivers à partir de mapMachineDivers
	  	UpdateFileMachineDivers.update(mapMachineDivers);
	  	
	  	//Voir si les infos peuvent compléter d'autres nodes	  	
	  	Map<String, Object> MyMapNodeInfoManquant = (Map<String, Object>) context.getAttribute("MapNodeInfoManquant");
	  	RechercheInfoByMachine MyRechercheInfoByMachine = new RechercheInfoByMachine();
		MyMapNode=MyRechercheInfoByMachine.GetInfo(MyMapNodeInfoManquant, MyMapNode);
	  	
	  	//Affichage dans la JSP UpdateInfoNode.jsp
	  	request.setAttribute("MyBeanMachineDivers", MyBeanMachineDivers);
	  	this.getServletContext().getRequestDispatcher("/WEB-INF/UpdateInfoNode.jsp").forward(request, response);				
	}
}
