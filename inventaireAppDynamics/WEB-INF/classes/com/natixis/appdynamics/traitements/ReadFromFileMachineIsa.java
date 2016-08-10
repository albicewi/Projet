package com.natixis.appdynamics.traitements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.natixis.appdynamics.beans.BeanMachineIsa;

public class ReadFromFileMachineIsa {
	static String machine;
	static String serveurApp;
	static String codeIua;
	static String trigramme;
	static String techno;
	static private Map<String, Object> mapMachineIsa = new HashMap<String, Object>();
		
	public ReadFromFileMachineIsa() {
		ReadFromFileMachineIsa.readText();		
	}
	
	public static Map<String, Object> getMapMachineIsa() {	
		return mapMachineIsa;
	}
			
	public static void readText() {
		
		String file = GetParamApplication.MachineIsa;		
	
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line=null;
			while ((line = br.readLine()) != null) {
				String[] lineVariables = line.split(",");				
				if (lineVariables.length == 5) {
					machine = lineVariables[0];	    	
					serveurApp = lineVariables[1];
					codeIua = lineVariables[2];
					trigramme = lineVariables[3];
					techno = lineVariables[4].replace("jbo", "jboss").replace("was","websphere");				
					BeanMachineIsa MyBeanIuaSrvrHost = new BeanMachineIsa(machine,serveurApp,codeIua,trigramme,techno);
					//On associe pour Key machine + serveurApp pour gérer les cas de nom d'instance identiques sur plusieurs machines.
					mapMachineIsa.put(machine+"-"+serveurApp, MyBeanIuaSrvrHost);
				}
				else {
					String [] tab_file = file.split("\\"+"\\");
					String fileName=tab_file[tab_file.length-1];
					System.out.println("ERROR: Fichier "+fileName+", nombre de champs incorrect pour la ligne suivante: "+line);
				}
			}
		} 
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		
	}	
}
