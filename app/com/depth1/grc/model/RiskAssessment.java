package com.depth1.grc.model;

import java.util.UUID;

/**
 * @author Team B
 * Risk Assessment object, holds all of the necessary variables to assess risks.
 */
public class RiskAssessment {
    /**
     * Parameters associated with the Risk Assessment form
     */
	private int tenantId;
	private UUID assessmentId;
	private float severity;
	private String severityDescription;
	private float likelihood;
	private String likelihoodDescription;
	private String matrixRed;
	private String matrixYellow;
	private String matrixLightGreen;
	private String matrixGreen;
	private float vulnerability;
	private String risk;
	private float speedOfOnset;
	private float impact;
	private String opportunity;
	private String triggerEvent;
	private String riskFactor;
	private String consequence;

    /**
     * Empty Risk Assessment constructor that gets created by the user's input values
     */
	public RiskAssessment(){
		
	}

	/*
	Getters and Setters for all input parameters associated with the Risk Assessment form
	 */

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public UUID getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(UUID assessmentId) {
		this.assessmentId = assessmentId;
	}

	public float getSeverity() {
		return severity;
	}

	public void setSeverity(float severity) {
		this.severity = severity;
	}

	public String getSeverityDescription() {
		return severityDescription;
	}

	public void setSeverityDescription(String severityDescription) {
		this.severityDescription = severityDescription;
	}

	public float getLikelihood() {
		return likelihood;
	}

	public void setLikelihood(float likelihood) {
		this.likelihood = likelihood;
	}

	public String getLikelihoodDescription() {
		return likelihoodDescription;
	}

	public void setLikelihoodDescription(String likelihoodDescription) {
		this.likelihoodDescription = likelihoodDescription;
	}

	public String getMatrixRed() {
		return matrixRed;
	}

	public void setMatrixRed(String matrixRed) {
		this.matrixRed = matrixRed;
	}

	public String getMatrixYellow() {
		return matrixYellow;
	}

	public void setMatrixYellow(String matrixYellow) {
		this.matrixYellow = matrixYellow;
	}

	public String getMatrixLightGreen() {
		return matrixLightGreen;
	}

	public void setMatrixLightGreen(String matrixLightGreen) {
		this.matrixLightGreen = matrixLightGreen;
	}

	public String getMatrixGreen() {
		return matrixGreen;
	}

	public void setMatrixGreen(String matrixGreen) {
		this.matrixGreen = matrixGreen;
	}

	public float getVulnerability() {
		return vulnerability;
	}

	public void setVulnerability(float vulnerability) {
		this.vulnerability = vulnerability;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public float getSpeedOfOnset() {
		return speedOfOnset;
	}

	public void setSpeedOfOnset(float speedOfOnset) {
		this.speedOfOnset = speedOfOnset;
	}

	public float getImpact() {
		return impact;
	}

	public void setImpact(float impact) {
		this.impact = impact;
	}

	public String getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(String oppurtunity) {
		this.opportunity = oppurtunity;
	}

	public String getTriggerEvent() {
		return triggerEvent;
	}

	public void setTriggerEvent(String triggerEvent) {
		this.triggerEvent = triggerEvent;
	}

	public String getRiskFactor() {
		return riskFactor;
	}

	public void setRiskFactor(String riskFactor) {
		this.riskFactor = riskFactor;
	}

	public String getConsequence() {
		return consequence;
	}

	public void setConsequence(String consequence) {
		this.consequence = consequence;
	}

	/**
	 * Add this version of the return Consequence so it is
	 * shorter for the table display in frontRA.html
	 * @return Consequence that is a max of 150 characters
	 * @author Benjamin J Cargile
	 * @version 1.0 -- 9/2/2015
	 */
	public String getShortConsequence(){
		if(consequence.length() > 150)
			return consequence.substring(0, 150);
		return consequence;
	}
	/**
	 * Add this version of the return Risk so it is
	 * shorter for the table display in frontRA.html
	 * @return Risk that is a max of 150 characters
	 * @author Benjamin J Cargile
	 * @version 1.0 -- 9/2/2015
	 */
	public String getShortRisk(){
		if(risk.length() > 150)
			return risk.substring(0, 150);
		return risk;
	}

}
