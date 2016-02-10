/**
 * 
 */
package com.depth1.grc.coso.models;

import javax.persistence.Entity;

/**
 * @author badedokun
 *
 */

@Entity (name = "mission")
public class Mission {
	private long missionId;
	private long tenantId;
	private String mission;

	/**
	 * 
	 */
	public Mission() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the missionId
	 */
	public long getMissionId() {
		return missionId;
	}

	/**
	 * @return the tenantId
	 */
	public long getTenantId() {
		return tenantId;
	}

	/**
	 * @return the mission
	 */
	public String getMission() {
		return mission;
	}

	/**
	 * @param mission the mission to set
	 */
	public void setMission(String mission) {
		this.mission = mission;
	}

}
