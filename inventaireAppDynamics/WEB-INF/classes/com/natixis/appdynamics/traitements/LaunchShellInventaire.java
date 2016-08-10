package com.natixis.appdynamics.traitements;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LaunchShellInventaire {
	String command = GetParamApplication.scriptShell;	
	StringBuffer output = new StringBuffer();
	
	//Constructeur
	public LaunchShellInventaire() {
		this.ExecCommand(command);		
	}	
	
	public void ExecCommand(String command) {		
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";			
		    while ((line = reader.readLine())!= null) {
		    	output.append(line + "\n");
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(output);
	}
}
