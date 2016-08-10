package com.natixis.appdynamics.traitements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.natixis.appdynamics.beans.BeanNode;

public class MakeCalculByEntity {
		
	public Map<String, Integer> calculByDomaine(Map<String, BeanNode> MapNode) {
		Map<String, Integer> mapSommeByDomaine = new HashMap<String, Integer>();
		Integer bgc = 0;
		Integer epg = 0;
		Integer sfs = 0;
		Integer tsv = 0;
		Integer domaineNull = 0;
		mapSommeByDomaine.put("domaineNull",0);
		
		//Boucle pour calcul par domaine
		for (BeanNode value : MapNode.values()) {
			String domaine = value.getDomaine();
			//System.out.println("Domaine = " + value.getDomaine());
		    switch(domaine) {
		    	case "bgc":
		    		bgc++;
		    		mapSommeByDomaine.put(domaine, bgc);
		    		break;
		    	
		    	case "epg":
		    		epg++;
		    		mapSommeByDomaine.put(domaine, epg);
		    		break;
		    	
		    	case "sfs":
		    		sfs++;
		    		mapSommeByDomaine.put(domaine, sfs);
		    		break;
		    		
		    	case "tsv":
		    		tsv++;
		    		mapSommeByDomaine.put(domaine,tsv);
		    		break;
		    		
		    	case "null":
		    		domaineNull++;
		    		mapSommeByDomaine.put("domaineNull",domaineNull);
		    		break;
		    	
		    	default:
		    		System.out.println("Domaine "+domaine+" n'existe pas.");		    		
		    }
		  
		}
		//System.out.println("Somme: "+bgc+","+epg+","+sfs+","+tsv+","+domaineNull);
		mapSommeByDomaine.put("total",MapNode.size());
		return mapSommeByDomaine;		
	}
	
	public Map<String, Map<String, ArrayList>> calculByProdAppli(Map<String, BeanNode> MapNode) {
		String[] listDomaine = {"bgc","epg","sfs","tsv"};
		Map<String, ArrayList> mapBgc = new HashMap<String, ArrayList>();
		Map<String, ArrayList> mapEpg = new HashMap<String, ArrayList>();
		Map<String, ArrayList> mapSfs = new HashMap<String, ArrayList>();
		Map<String, ArrayList> mapTsv = new HashMap<String, ArrayList>();
		Map<String, ArrayList> mapDomaineNull = new HashMap<String, ArrayList>();
		Map<String, Map<String, ArrayList>> mapSommeByProdAppli = new HashMap<String, Map<String, ArrayList>>();
		
		for (BeanNode value : MapNode.values()) {
			String domaine = value.getDomaine();
			String prodAppli = value.getProdAppli();
			String nodeAgent = value.getName();
			if (domaine.equals("bgc")) {
					if (mapBgc.get(prodAppli) == null) mapBgc.put(prodAppli, new ArrayList<String>());
				mapBgc.get(prodAppli).add(nodeAgent);				
			}
			if (domaine.equals("epg")) {
					if (mapEpg.get(prodAppli) == null) mapEpg.put(prodAppli, new ArrayList<String>());
				mapEpg.get(prodAppli).add(nodeAgent);				
			}
			if (domaine.equals("sfs")) {
					if (mapSfs.get(prodAppli) == null) mapSfs.put(prodAppli, new ArrayList<String>());
				mapSfs.get(prodAppli).add(nodeAgent);				
			}
			if (domaine.equals("tsv")) {
					if (mapTsv.get(prodAppli) == null) mapTsv.put(prodAppli, new ArrayList<String>());
				mapTsv.get(prodAppli).add(nodeAgent);				
			}
			if (domaine.equals("null")) {
				if (mapDomaineNull.get(prodAppli) == null) mapDomaineNull.put(prodAppli, new ArrayList<String>());
			mapDomaineNull.get(prodAppli).add(nodeAgent);				
			}
			
		}
			
		//System.out.println("Somme EPG: "+mapEpg.get("prd-assurances").size());		
		//Boucle pour calcul par équipe et domaine comme clef de la map
		mapSommeByProdAppli.put("bgc", mapBgc);
		mapSommeByProdAppli.put("epg", mapEpg);
		mapSommeByProdAppli.put("sfs", mapSfs);
		mapSommeByProdAppli.put("tsv", mapTsv);
		mapSommeByProdAppli.put("domaineNull", mapDomaineNull);		
		return mapSommeByProdAppli;		
	}
}
