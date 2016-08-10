package com.natixis.appdynamics.beans;
import java.io.Serializable;

public class BeanMachineDivers implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String machine;
	private String agentNode;
	private String prodAppli;
	private String email;
	private String codeIua;
	private String domaine;
			
	public BeanMachineDivers (String machine, String agentNode, String prodAppli, String email, String codeIua, String domaine) {
		this.machine = machine;
		this.agentNode = agentNode;
		this.prodAppli= prodAppli;
		this.email = email;
		this.codeIua = codeIua;
		this.domaine = domaine; 
	}
	
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	public String getAgentNode() {
		return agentNode;
	}
	public void setAgentNode(String agentNode) {
		this.agentNode = agentNode;
	}	
	public String getProdAppli() {
		return prodAppli;
	}
	public void setProdAppli(String prodAppli) {
		this.prodAppli = prodAppli;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public String getCodeIua() {
		return codeIua;
	}
	public void setCodeIua(String codeIua) {
		this.codeIua = codeIua;
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}	
}
