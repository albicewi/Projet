package com.natixis.appdynamics.beans;
import java.io.Serializable;

public class BeanTrigrammeDomaineProdappli implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String trigramme;
	private String domaine;
	private String prodAppli;
	private String email;
	
	public BeanTrigrammeDomaineProdappli (String trigramme, String domaine, String prodAppli, String email) {
		this.trigramme = trigramme;
		this.domaine =  domaine;
		this.prodAppli = prodAppli;
		this.email = email;
	}
		
	public String getTrigramme() {
		return trigramme;
	}
	public void setTrigramme(String trigramme) {
		this.trigramme = trigramme;
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
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

}
