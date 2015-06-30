package com.depth1.grc.model;

/**
 * @author Team B
 *Risk Assessment object, holds all of the necessary variables to assess risks.
 *
 */
public class RiskAssessment {
	
	private String tentantId;
	private String assessmentId;
	private float severity;
	private String severityDescription;
	private float likelihood;
	private String likelihoodDescription;
	private String matrixRed;
	private String matrixYellow;
	private String matrixLightGreen;
	private String matrixGreen;
	private float vulnerabilty;
	private String risk;
	private float speedOfOnset;
	private float impact;
	private String oppurtunity;
	private String triggerEvent;
	private String riskFactor;
	private String consequence;
	
	//constructor
	RiskAssessment(){
		
		
		
	}
	
	//getters and setters
	public String getTentantId() {
		return tentantId;
	}

	public void setTentantId(String tentantId) {
		this.tentantId = tentantId;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
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

	public float getVulnerabilty() {
		return vulnerabilty;
	}

	public void setVulnerabilty(float vulnerabilty) {
		this.vulnerabilty = vulnerabilty;
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

	public String getOppurtunity() {
		return oppurtunity;
	}

	public void setOppurtunity(String oppurtunity) {
		this.oppurtunity = oppurtunity;
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
	
}
