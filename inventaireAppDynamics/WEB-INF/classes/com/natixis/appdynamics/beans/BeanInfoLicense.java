package com.natixis.appdynamics.beans;
import java.io.Serializable;

public class BeanInfoLicense implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String agentType;
	private int avgUnitsAllowed;
	private int maxUnitsUsed;		
		
	public BeanInfoLicense (String agentType, int avgUnitsAllowed, int maxUnitsUsed) {
		this.setAgentType(agentType);
		this.setAvgUnitsAllowed(avgUnitsAllowed);
		this.setMaxUnitsUsed(maxUnitsUsed);		
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	
	public int getAvgUnitsAllowed() {
		return avgUnitsAllowed;
	}

	public void setAvgUnitsAllowed(int avgUnitsAllowed) {
		this.avgUnitsAllowed = avgUnitsAllowed;
	}
	
	public int getMaxUnitsUsed() {
		return maxUnitsUsed;
	}

	public void setMaxUnitsUsed(int maxUnitsUsed) {
		this.maxUnitsUsed = maxUnitsUsed;
	}
		
}
