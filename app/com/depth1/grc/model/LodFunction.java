/**
 * 
 */
package com.depth1.grc.model;

/**
 * This class is the business object for creating, reading, updating, and deleting (CRUD)
 * Line of Defense Functions
 * @author Bisi Adedokun
 *
 */
public class LodFunction {
	private int lodFunctionId;
	private String lod;
	private String lodFunction;
	
	/**
	 * @return the id
	 */
	public int getLodFunctionId() {
		return lodFunctionId;
	}
	/**
	 * @param id the id to set
	 */
	public void setLodFunctionId(int id) {
		this.lodFunctionId = id;
	}
	/**
	 * @return the lod
	 */
	public String getLod() {
		return lod;
	}
	/**
	 * @param lod the lod to set
	 */
	public void setLod(String lod) {
		this.lod = lod;
	}
	/**
	 * @return the lodFunction
	 */
	public String getLodFunction() {
		return lodFunction;
	}
	/**
	 * @param lodFunction the lodFunction to set
	 */
	public void setLodFunction(String lodFunction) {
		this.lodFunction = lodFunction;
	}

}
