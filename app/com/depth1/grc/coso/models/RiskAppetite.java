/**
 * 
 */
package com.depth1.grc.coso.models;

import javax.persistence.Entity;

/**
 * @author badedokun
 *
 */
@Entity (name = "riskappetite")
public class RiskAppetite {
	private long appetiteId;
	private long tenantId;
	private String appetite;

	/**
	 * 
	 */
	public RiskAppetite() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the appetiteId
	 */
	public long getAppetiteId() {
		return appetiteId;
	}

	/**
	 * @return the tenantId
	 */
	public long getTenantId() {
		return tenantId;
	}

	/**
	 * @return the appetite
	 */
	public String getAppetite() {
		return appetite;
	}
	
	/**
	 * @param appetite the appetite to set
	 */
	public void setAppetite(String appetite) {
		this.appetite = appetite;
	}	

}
