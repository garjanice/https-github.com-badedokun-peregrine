/**
 * 
 */
package com.depth1.grc.model;

/**
 * This class evaluates deficiencies across the component. Evaluates if any internal control deficiencies
 * or combination of internal control deficiencies when considered across the component, represent a major
 * deficiency.
 * 
 * @author Bisi Adedokun
 *
 */
public class DeficiencyAcrossComponent extends ComponentEvaluation {
	
	private String deficiency;
	private String isComponentPresent;
	private String isComponentFunctioning;
	
	/**
	 * @return the deficiency
	 */
	public String getDeficiency() {
		return deficiency;
	}


	/**
	 * @return the isComponentPresent
	 */
	public String getIsComponentPresent() {
		return isComponentPresent;
	}


	/**
	 * @return the isComponentFunctioning
	 */
	public String getIsComponentFunctioning() {
		return isComponentFunctioning;
	}


	/**
	 * @param deficiency the deficiency to set
	 */
	public void setDeficiency(String deficiency) {
		this.deficiency = deficiency;
	}


	/**
	 * @param isComponentPresent the isComponentPresent to set
	 */
	public void setIsComponentPresent(String isComponentPresent) {
		this.isComponentPresent = isComponentPresent;
	}


	/**
	 * @param isComponentFunctioning the isComponentFunctioning to set
	 */
	public void setIsComponentFunctioning(String isComponentFunctioning) {
		this.isComponentFunctioning = isComponentFunctioning;
	}
	

	/**
	 * 
	 */
	public DeficiencyAcrossComponent() {
		// TODO Auto-generated constructor stub
	}

}
