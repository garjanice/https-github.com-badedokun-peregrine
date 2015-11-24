/**
 * 
 */
package com.depth1.grc.model;

/**
 * This class is the business object for creating, reading, updating, and deleting (CRUD)
 * Line of Defense
 * 
 * @author Bisi Adedokun
 *
 */
public class LineOfDefense {
	private String description;
	private String lod;

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
