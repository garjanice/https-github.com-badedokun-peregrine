package com.depth1.grc.model;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * This is the base class that links existing internal control structure to the 17 COSO
 * principles by conducting an evaluation of the 17 principles and identify gaps that may exist.
 * 
 * @author Bisi Adedokun
 *
 */
public class Evaluation {

	// common attributes
	private UUID evaluationId;
	private String component;
	private String principle;	
	private String deficiencyNumber;
	private String explanation;
	private String isDeficiencyMajor;
	private String compensatingControl;
	private String lodFunction;
	private String reviewDocument;
	private Date controlLastPerformed;
	private Date controlLastReviewed;
	private Map<String, String> controlReference;
	private Map<String, String> deficiencyDescription;
	private Map<String, String> relatedDeficiency;	
	
	/**
	 * 
	 */
	public Evaluation() {
		
	}	

	/**
	 * @return the controlLastPerformed
	 */
	public Date getControlLastPerformed() {
		return controlLastPerformed;
	}

	/**
	 * @return the controlLastReviewed
	 */
	public Date getControlLastReviewed() {
		return controlLastReviewed;
	}

	/**
	 * @return the controlReference
	 */
	public Map<String, String> getControlReference() {
		return controlReference;
	}

	/**
	 * @return the reviewDocument
	 */
	public String getReviewDocument() {
		return reviewDocument;
	}

	/**
	 * @param deficiencyDescription the deficiencyDescription to set
	 */
	public void setDeficiencyDescription(Map<String, String> deficiencyDescription) {
		this.deficiencyDescription = deficiencyDescription;
	}

	/**
	 * @param controlLastPerformed the controlLastPerformed to set
	 */
	public void setControlLastPerformed(Date controlLastPerformed) {
		this.controlLastPerformed = controlLastPerformed;
	}

	/**
	 * @param controlLastReviewed the controlLastReviewed to set
	 */
	public void setControlLastReviewed(Date controlLastReviewed) {
		this.controlLastReviewed = controlLastReviewed;
	}

	/**
	 * @param controlReference the controlReference to set
	 */
	public void setControlReference(Map<String, String> controlReference) {
		this.controlReference = controlReference;
	}

	/**
	 * @param reviewDocument the reviewDocument to set
	 */
	public void setReviewDocument(String reviewDocument) {
		this.reviewDocument = reviewDocument;
	}

	/**
	 * @return the deficiencyDescription
	 */
	public Map<String, String> getDeficiencyDescription() {
		return deficiencyDescription;
	}

	/**
	 * @return the evaluationId
	 */
	public UUID getEvaluationId() {
		return evaluationId;
	}

	/**
	 * @return the principle
	 */
	public String getPrinciple() {
		return principle;
	}

	/**
	 * @return the deficiencyNumber
	 */
	public String getDeficiencyNumber() {
		return deficiencyNumber;
	}

	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * @return the isDeficiencyMajor
	 */
	public String getIsDeficiencyMajor() {
		return isDeficiencyMajor;
	}

	/**
	 * @return the compensatingControl
	 */
	public String getCompensatingControl() {
		return compensatingControl;
	}

	/**
	 * @return the relatedDeficiency
	 */
	public Map<String, String> getRelatedDeficiency() {
		return relatedDeficiency;
	}

	/**
	 * @return the lodFunction
	 */
	public String getLodFunction() {
		return lodFunction;
	}

	/**
	 * @param evaluationId
	 *            the evaluationId to set
	 */
	public void setEvaluationId(UUID evaluationId) {
		this.evaluationId = evaluationId;
	}

	/**
	 * @param principle
	 *            the principle to set
	 */
	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	/**
	 * @param deficiencyNumber
	 *            the deficiencyNumber to set
	 */
	public void setDeficiencyNumber(String deficiencyNumber) {
		this.deficiencyNumber = deficiencyNumber;
	}

	/**
	 * @param explanation
	 *            the explanation to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * @param isDeficiencyMajor
	 *            the isDeficiencyMajor to set
	 */
	public void setIsDeficiencyMajor(String isDeficiencyMajor) {
		this.isDeficiencyMajor = isDeficiencyMajor;
	}

	/**
	 * @param compensatingControl
	 *            the compensatingControl to set
	 */
	public void setCompensatingControl(String compensatingControl) {
		this.compensatingControl = compensatingControl;
	}

	/**
	 * @param relatedDeficiency
	 *            the relatedDeficiency to set
	 */
	public void setRelatedDeficiency(Map<String, String> relatedDeficiency) {
		this.relatedDeficiency = relatedDeficiency;
	}

	/**
	 * @param lodFunction
	 *            the lodFunction to set
	 */
	public void setLodFunction(String lodFunction) {
		this.lodFunction = lodFunction;
	}

	/**
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * @param component the component to set
	 */
	public void setComponent(String component) {
		this.component = component;
	}

}
