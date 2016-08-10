package com.natixis.appdynamics.traitements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class GetParamApplication {
	public static final String MACHINE_DIVERS="MachineDivers";	
	public static final String MACHINE_ISA="MachineIsa";
	public static final String TRIGRAMME_DOMAINE_PRODAPPLI="TrigrammeDomaineProdappli";
	public static final String URL_APM="urlApm";
	public static final String USER_APM="userApm";
	public static final String PASSWORD_APM="passwordApm";
	public static final String URL_MAKE_INVENTORY="urlMakeInventory";
	public static final String INTERVAL_ACTUALISATION_HEURE="intervalActualisationHeure";
	public static final String SCRIPT_SHELL="scriptShell";
	public static final String TIME_TASK_SHELL="heureLancementScriptShell";
	public static final String URI_INFO_LICENSE="uriInfoLicense";
	public static final String FILE_CONF="fileConf";	
		
	public static String MachineDivers;
	public static String MachineIsa;
	public static String TrigrammeDomaineProdappli;
	public static String urlApm;
	public static String userApm;
	public static String passwordApm;
	public static String urlMakeInventory;
	public static int intervalActualisationHeure;
	public static String scriptShell;
	public static String heureLancementScriptShell;
	public static String uriInfoLicense;
	public static String fileConfig;
	
	public static Map<String, String> mapParamAppli = new HashMap<String, String>();
	
	//Constructeur
	public GetParamApplication() {
		try {
			GetParamApplication.getProperties();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static void getProperties() throws FileNotFoundException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			fileConfig = (String) envContext.lookup(FILE_CONF);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
		
		File configFile = new File(fileConfig);
		Properties configParm = new Properties();
		try {
			FileInputStream istream = new FileInputStream(configFile);			
			configParm.load(istream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MachineDivers = configParm.getProperty(MACHINE_DIVERS);
		MachineIsa = configParm.getProperty(MACHINE_ISA);
		TrigrammeDomaineProdappli = configParm.getProperty(TRIGRAMME_DOMAINE_PRODAPPLI);
		urlApm = configParm.getProperty(URL_APM);
		userApm = configParm.getProperty(USER_APM);
		passwordApm = configParm.getProperty(PASSWORD_APM);
		urlMakeInventory = configParm.getProperty(URL_MAKE_INVENTORY);
		intervalActualisationHeure = Integer.valueOf(configParm.getProperty(INTERVAL_ACTUALISATION_HEURE));
		scriptShell = configParm.getProperty(SCRIPT_SHELL);
		heureLancementScriptShell = configParm.getProperty(TIME_TASK_SHELL);
		uriInfoLicense = configParm.getProperty(URI_INFO_LICENSE);
						
		// on met dans une Map pour passer en attribut de la servlet AfficheParam
		for (final String name: configParm.stringPropertyNames())
			mapParamAppli.put(name, configParm.getProperty(name));
	}  
}
