package com.natixis.appdynamics.traitements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.natixis.appdynamics.beans.BeanNode;


public class RechercheInfoByMachine {		
	
	public Map<String, Object> GetInfo(Map<String, Object> myMapNodeInfoManquant, Map<String, Object> myMapNode) {
		
		List<Object> myListBeanNodeInfoManquant=new ArrayList<Object>(myMapNodeInfoManquant.values());		
		List<Object> myListBeanNode=new ArrayList<Object>(myMapNode.values());
		
		Iterator<Object> it1 = myListBeanNodeInfoManquant.iterator();
	    while(it1.hasNext()) {
	    	BeanNode myBeanNodeInfoManquant = (BeanNode) it1.next();
	    	String myMachine = myBeanNodeInfoManquant.getMachineName();	    	
	    	Iterator<Object> it2 = myListBeanNode.iterator();
	    	// On cherche dans la liste des Nodes existant, une machine identique à celui de notre node
	    	// Si cela correspond, on récupère les infos prodAppli, domaine et email
	    	while(it2.hasNext()) {
	    		BeanNode myBeanNode = (BeanNode) it2.next();	    		
	    		if (myMachine.equals(myBeanNode.getMachineName()) && (!myBeanNode.getProdAppli().equals("null"))) {
	    			myBeanNodeInfoManquant.setProdAppli(myBeanNode.getProdAppli());
	    			if (!myBeanNode.getDomaine().equals("null")) {
	    				myBeanNodeInfoManquant.setDomaine(myBeanNode.getDomaine());	    				
	    			}
	    			if (!myBeanNode.getEmail().equals("null")) {
	    				myBeanNodeInfoManquant.setEmail(myBeanNode.getEmail());	    				
	    			}
	    			//System.out.println(myBeanNodeInfoManquant.getName()+":"+myBeanNodeInfoManquant.getMachineName()+":"+myBeanNodeInfoManquant.getProdAppli());
	    			myMapNode.put(myBeanNodeInfoManquant.getName(), myBeanNodeInfoManquant);
	    			break;
	    		}	    			
	    	}	    			
	    }
		return myMapNode;
	}
}
