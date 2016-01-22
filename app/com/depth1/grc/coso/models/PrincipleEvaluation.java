/**
 * 
 */
package com.depth1.grc.coso.models;

import java.util.Map;

/**
 * This class links existing internal control structure to the 17 COSO
 * principles by conducting an evaluation of the 17 principles and identify gaps that may exist.
 * 
 * @author Bisi Adedokun
 *
 */
public class PrincipleEvaluation extends Evaluation {
	
	private String summaryOfControl;
	private String isPrinciplePresent;
	private String isPrincipleFunctioning;
	private String isPrincipleEffective;
	private String deficiencyWithinPrinciple;
	private Map<String, String> focusPoint;
	private Map<String, String> relatedFocusPoint;

	/**
	 * 
	 */
	public PrincipleEvaluation() {
		
	}

	/**
	 * @return the summaryOfControl
	 */
	public String getSummaryOfControl() {
		return summaryOfControl;
	}

	/**
	 * @return the isPrinciplePresent
	 */
	public String getIsPrinciplePresent() {
		return isPrinciplePresent;
	}

	/**
	 * @return the isPrincipleFunctioning
	 */
	public String getIsPrincipleFunctioning() {
		return isPrincipleFunctioning;
	}

	/**
	 * @return the isPrincipleEffective
	 */
	public String getIsPrincipleEffective() {
		return isPrincipleEffective;
	}

	/**
	 * @return the deficiencyWithinPrinciple
	 */
	public String getDeficiencyWithinPrinciple() {
		return deficiencyWithinPrinciple;
	}

	/**
	 * @return the focusPoint
	 */
	public Map<String, String> getFocusPoint() {
		return focusPoint;
	}

	/**
	 * @return the relatedFocusPoint
	 */
	public Map<String, String> getRelatedFocusPoint() {
		return relatedFocusPoint;
	}

	/**
	 * @param summaryOfControl the summaryOfControl to set
	 */
	public void setSummaryOfControl(String summaryOfControl) {
		this.summaryOfControl = summaryOfControl;
	}

	/**
	 * @param isPrinciplePresent the isPrinciplePresent to set
	 */
	public void setIsPrinciplePresent(String isPrinciplePresent) {
		this.isPrinciplePresent = isPrinciplePresent;
	}

	/**
	 * @param isPrincipleFunctioning the isPrincipleFunctioning to set
	 */
	public void setIsPrincipleFunctioning(String isPrincipleFunctioning) {
		this.isPrincipleFunctioning = isPrincipleFunctioning;
	}

	/**
	 * @param isPrincipleEffective the isPrincipleEffective to set
	 */
	public void setIsPrincipleEffective(String isPrincipleEffective) {
		this.isPrincipleEffective = isPrincipleEffective;
	}

	/**
	 * @param deficiencyWithinPrinciple the deficiencyWithinPrinciple to set
	 */
	public void setDeficiencyWithinPrinciple(String deficiencyWithinPrinciple) {
		this.deficiencyWithinPrinciple = deficiencyWithinPrinciple;
	}

	/**
	 * @param focusPoint the focusPoint to set
	 */
	public void setFocusPoint(Map<String, String> focusPoint) {
		this.focusPoint = focusPoint;
	}

	/**
	 * @param relatedFocusPoint the relatedFocusPoint to set
	 */
	public void setRelatedFocusPoint(Map<String, String> relatedFocusPoint) {
		this.relatedFocusPoint = relatedFocusPoint;
	}

}
