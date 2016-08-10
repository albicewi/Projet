package com.natixis.appdynamics.traitements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.natixis.appdynamics.beans.BeanMachineDivers;

public class ReadFromFileMachineDivers {
	static String machine;
	static String agentNode;
	static String prodAppli;
	static String email;
	static String codeIua;
	static String domaine;
	private static Map<String, Object> mapMachineDivers = new HashMap<String, Object>();
	
	public ReadFromFileMachineDivers() {
		ReadFromFileMachineDivers.readText();
	}
	
	public static Map<String, Object> getMachineDivers() {
		return mapMachineDivers;
	}	
	
	public static void readText() {
		
		String file = GetParamApplication.MachineDivers;	
	
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineVariables = line.split(",");
				if (lineVariables.length == 6) {
					machine = lineVariables[0];
					agentNode = lineVariables[1];
					prodAppli = lineVariables[2];
					email = lineVariables[3];
					codeIua = lineVariables[4];
					domaine = lineVariables[5];
					BeanMachineDivers MyBeanMachineDivers = new BeanMachineDivers (machine,agentNode,prodAppli,email,codeIua,domaine);	    		    	
					mapMachineDivers.put(agentNode, MyBeanMachineDivers);
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
			System.err.println(e.getMessage()); // handle exception
		}
	}	
}
