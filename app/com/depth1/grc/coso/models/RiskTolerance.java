/**
 * 
 */
package com.depth1.grc.coso.models;

import javax.persistence.Entity;

/**
 * 
 * @author Bisi Adedokun
 *
 */
@Entity (name = "risktolerance")
public class RiskTolerance {
	
	private long toleranceId;
	private long tenantId;
	private String tolerance;
	
	/**
	 * 
	 */
	public RiskTolerance() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the missionId
	 */
	public long getToleranceId() {
		return toleranceId;
	}

	/**
	 * @return the tenantId
	 */
	public long getTenantId() {
		return tenantId;
	}

	/**
	 * @return the tolerance
	 */
	public String getTolerance() {
		return tolerance;
	}

	/**
	 * @param tolerance the tolerance to set
	 */
	public void setTolerance(String tolerance) {
		this.tolerance = tolerance;
	}

}
