/**
 * 
 */
package com.depth1.grc.jpa.models;

/**
 * This class is the business object for creating, reading, updating, and deleting (CRUD)
 * Line of Defense
 * 
 * @author Bisi Adedokun
 *
 */
public class LineOfDefense {
	private int lodId;
	private String description;
	private String lod;
	
	/**
	 * @return the lodId
	 */
	public int getLodId() {
		return lodId;
	}
	/**
	 * @param lodId the lodId to set
	 */
	public void setLodId(int lodId) {
		this.lodId = lodId;
	}	

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the lod
	 */
	public String getLod() {
		return lod;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param lod the lod to set
	 */
	public void setLod(String lod) {
		this.lod = lod;
	}	

}
