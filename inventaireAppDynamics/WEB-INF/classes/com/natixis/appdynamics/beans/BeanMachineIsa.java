package com.natixis.appdynamics.beans;
import java.io.Serializable;

public class BeanMachineIsa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String machine;
	private String serveurApp;
	private String codeIua;	
	private String trigramme;
	private String techno;
	
	public BeanMachineIsa (String machine, String serveurApp, String codeIua, String trigramme, String techno) {
		this.machine = machine;
		this.serveurApp = serveurApp;
		this.codeIua = codeIua;
		this.trigramme = trigramme;
		this.techno = techno;
	}
	
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	public String getServeurApp() {
		return serveurApp;
	}
	public void setServeurApp(String serveurApp) {
		this.serveurApp = serveurApp;
	}	
	public String getCodeIua() {
		return codeIua;
	}
	public void setCodeIua(String codeIua) {
		this.codeIua = codeIua;
	}
	public String getTrigramme() {
		return trigramme;
	}
	public void setTrigramme(String trigramme) {
		this.trigramme = trigramme;
	}

	public String getTechno() {
		return techno;
	}

	public void setTechno(String techno) {
		this.techno = techno;
	}

}
