package com.natixis.appdynamics.beans;
import java.io.Serializable;

public class BeanNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String machineName;
	private String tierName;			
	private String machineOSType;
	private String appAgentPresent;
	private String appAgentVersion;
	private String appName;
	private String codeIua="null";
	private String trigramme;
	private String prodAppli="null";
	private String domaine="null";
	private String email="null";
	private String techno;
	
	public BeanNode() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName.toLowerCase();
	}
	public String getTierName() {
		return tierName;
	}
	public void setTierName(String tierName) {
		this.tierName = tierName;
	}
	public String getMachineOSType() {
		return machineOSType;
	}
	public void setMachineOSType(String machineOSType) {
		this.machineOSType = machineOSType.toLowerCase();
	}
	public String getAppAgentPresent() {
		return appAgentPresent;
	}
	public void setAppAgentPresent(String appAgentPresent) {
		this.appAgentPresent = appAgentPresent;
	}
	public String getAppAgentVersion() {
		return appAgentVersion;
	}
	public void setAppAgentVersion(String appAgentVersion) {
		this.appAgentVersion = appAgentVersion;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getCodeIua() {
		return codeIua;
	}
	public void setCodeIua(String codeIua) {
		this.codeIua = codeIua.toLowerCase();
	}
	public String getTrigramme() {
		return trigramme;
	}
	public void setTrigramme(String trigramme) {
		this.trigramme = trigramme;
	}
	public String getProdAppli() {
		return prodAppli;
	}
	public void setProdAppli(String prodAppli) {
		this.prodAppli = prodAppli.toLowerCase();
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine.toLowerCase();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}
	public String getTechno() {
		return techno;
	}
	public void setTechno(String techno) {
		this.techno=techno;		
	}

}
