/**
 * 
 */
package com.depth1.grc.model;

import java.util.List;
import java.util.Map;

/**
 * This class provides overall assessment of a system of internal control 
 * @author Bisi Adedokun
 *
 */
public class OverallControlAssessment {
	private String entityType;
	private String objective;
	private List<String> objectiveType;
	private List<String> riskLevelAcceptance;
	private Map<String, String> componentPresent;
	private Map<String, String> componentFunction;
	private Map<String, String> componentExplanation;
	private String isAllComponentsIntegrated;
	private String isControlEffective;
	private String conclusion;
	

	/**
	 * 
	 */
	public OverallControlAssessment() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the entityType
	 */
	public String getEntityType() {
		return entityType;
	}


	/**
	 * @return the objective
	 */
	public String getObjective() {
		return objective;
	}


	/**
	 * @return the objectiveType
	 */
	public List<String> getObjectiveType() {
		return objectiveType;
	}


	/**
	 * @return the riskLevelAcceptance
	 */
	public List<String> getRiskLevelAcceptance() {
		return riskLevelAcceptance;
	}


	/**
	 * @return the componentPresent
	 */
	public Map<String, String> getComponentPresent() {
		return componentPresent;
	}


	/**
	 * @return the componentFunction
	 */
	public Map<String, String> getComponentFunction() {
		return componentFunction;
	}


	/**
	 * @return the componentExplanation
	 */
	public Map<String, String> getComponentExplanation() {
		return componentExplanation;
	}


	/**
	 * @return the isAllComponentsIntegrated
	 */
	public String getIsAllComponentsIntegrated() {
		return isAllComponentsIntegrated;
	}


	/**
	 * @return the isControlEffective
	 */
	public String getIsControlEffective() {
		return isControlEffective;
	}


	/**
	 * @return the conclusion
	 */
	public String getConclusion() {
		return conclusion;
	}


	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}


	/**
	 * @param objective the objective to set
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}


	/**
	 * @param objectiveType the objectiveType to set
	 */
	public void setObjectiveType(List<String> objectiveType) {
		this.objectiveType = objectiveType;
	}


	/**
	 * @param riskLevelAcceptance the riskLevelAcceptance to set
	 */
	public void setRiskLevelAcceptance(List<String> riskLevelAcceptance) {
		this.riskLevelAcceptance = riskLevelAcceptance;
	}


	/**
	 * @param componentPresent the componentPresent to set
	 */
	public void setComponentPresent(Map<String, String> componentPresent) {
		this.componentPresent = componentPresent;
	}


	/**
	 * @param componentFunction the componentFunction to set
	 */
	public void setComponentFunction(Map<String, String> componentFunction) {
		this.componentFunction = componentFunction;
	}


	/**
	 * @param componentExplanation the componentExplanation to set
	 */
	public void setComponentExplanation(Map<String, String> componentExplanation) {
		this.componentExplanation = componentExplanation;
	}


	/**
	 * @param isAllComponentsIntegrated the isAllComponentsIntegrated to set
	 */
	public void setIsAllComponentsIntegrated(String isAllComponentsIntegrated) {
		this.isAllComponentsIntegrated = isAllComponentsIntegrated;
	}


	/**
	 * @param isControlEffective the isControlEffective to set
	 */
	public void setIsControlEffective(String isControlEffective) {
		this.isControlEffective = isControlEffective;
	}


	/**
	 * @param conclusion the conclusion to set
	 */
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

}
