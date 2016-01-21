/**
 * 
 */
package com.depth1.grc.model;

/**
 * This class links existing internal control structure to the 17 COSO
 * principles by conducting an evaluation of the components and identify gaps that may exist.
 * 
 * @author Bisi Adedokun
 *
 */
public class ComponentEvaluation extends Evaluation {
	
	private String isControlFunctioning;
	private String isControlPresent;
	
	
	/**
	 * 
	 */
	public ComponentEvaluation() {
		
	}
	
	/**
	 * @return the isControlFunctioning
	 */
	public String getIsControlFunctioning() {
		return isControlFunctioning;
	}
	/**
	 * @return the isControlPresent
	 */
	public String getIsControlPresent() {
		return isControlPresent;
	}
	/**
	 * @param isControlFunctioning the isControlFunctioning to set
	 */
	public void setIsControlFunctioning(String isControlFunctioning) {
		this.isControlFunctioning = isControlFunctioning;
	}
	/**
	 * @param isControlPresent the isControlPresent to set
	 */
	public void setIsControlPresent(String isControlPresent) {
		this.isControlPresent = isControlPresent;
	}

}
