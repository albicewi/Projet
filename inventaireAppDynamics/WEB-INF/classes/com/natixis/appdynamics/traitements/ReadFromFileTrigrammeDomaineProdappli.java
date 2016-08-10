package com.natixis.appdynamics.traitements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import com.natixis.appdynamics.beans.BeanTrigrammeDomaineProdappli;

public class ReadFromFileTrigrammeDomaineProdappli {
	static private String trigramme;
	static private String domaine;
	static private String prodAppli;
	static private String emailProdAppli;	
	static private Map<String, Object> mapTrigramme = new HashMap<String, Object>();
	
	public ReadFromFileTrigrammeDomaineProdappli() {
		ReadFromFileTrigrammeDomaineProdappli.readText();
	}
	
	static public Map<String, Object> getMapTrigramme() {
		return mapTrigramme;
	}
	
	public static void readText() {
		
		String file = GetParamApplication.TrigrammeDomaineProdappli;	
	
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineVariables = line.split(",");
				if (lineVariables.length == 4) {
					trigramme = lineVariables[0];
					domaine = lineVariables[1];
					prodAppli = lineVariables[2];
					emailProdAppli = lineVariables[3]; 	    	
					BeanTrigrammeDomaineProdappli MyBeanTrigrammeMetierDomaine = new BeanTrigrammeDomaineProdappli(trigramme,domaine,prodAppli,emailProdAppli);
					mapTrigramme.put(trigramme, MyBeanTrigrammeMetierDomaine);
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