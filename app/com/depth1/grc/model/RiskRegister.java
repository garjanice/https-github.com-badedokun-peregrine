package com.depth1.grc.model;

import java.util.Date;

/**
 * @author Team C
 *Risk Register object, holds all of the necessary variables to assess risks.
 *NOTE: copied from Risk Assessment, have not yet changed variables
 */
public class RiskRegister {
	
	private int tenantId;
	private String riskCategoryRating;
	private int riskId;
	private String riskName;
	private String riskOwner;
	private String riskStatus;
	private String riskReportingLevel;
	private String description;
	private String impactDescription;
	private String riskProbability;
	private String riskPriority;
	private String riskImpact;
	private String resolutionMitigation;
	private Date targetResolutionDate;
	private Date actualResolutionDate;
	private String responseType;
	private String associatedRisk;
	private String associatedIssue;
	private String riskCreator;
	private String riskLastUpdatedPerson;
	private String riskLastUpdatedDescription;
	private Date dateLastUpdated;
	private String riskAssumptions;
	private String riskSymptoms;
	
	//constructor
	public RiskRegister(){
		
	}
	
	//getters and setters
	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getRiskCategoryRating() {
		return riskCategoryRating;
	}
	
	public void setRiskCategoryRating(String tenantId) {
		this.riskCategoryRating = riskCategoryRating;
	}
	
	public int getRiskId() {
		return riskId;
	}
	
	public void setRiskID(int riskId) {
		this.riskId= riskId;
	}
	
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	
	public String getRiskOwner() {
		return riskOwner;
	}
	public void setRiskOwner(String riskOwner) {
		this.riskOwner = riskOwner;
	}
	
	public String getRiskStatus() {
		return riskStatus;
	}
	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}
	
	public String getRiskReportingLevel() {
		return riskReportingLevel;
	}
	public void setRiskReportingLevel(String riskReportingLevel) {
		this.riskReportingLevel = riskReportingLevel;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImpactDescription() {
		return impactDescription;
	}
	
	public void setImpactDescription(String impactDescription) {
		this.impactDescription=impactDescription;
	}
	
	public String getRiskProbability() {
		return riskProbability;
	}
	public void setRiskProbability(String riskProbability) {
		this.riskProbability = riskProbability;
	}
	
	public String getRiskPriority() {
		return riskPriority;
	}
	public void setRiskPriority(String riskPriorty) {
		this.riskPriority = riskPriority;
	}
	
	public String getRiskImpact() {
		return riskImpact;
	}
	public void setRiskImpact(String riskImpact) {
		this.riskImpact= riskImpact;
	}
	
	public String getResolutionMitigation() {
		return resolutionMitigation;
	}
	public void setResolutionMitigation(String resolutionMitigation) {
		this.resolutionMitigation = resolutionMitigation;
	}
	
	public Date getTargetResolutionDate() {
		return targetResolutionDate;
	}
	
	public void setTargetResolutionDate(Date targetResolutionDate) {
		this.targetResolutionDate = targetResolutionDate;
	}
	
	public Date getActualResolutionDate() {
		return actualResolutionDate;
	}
	
	public void setActualResolutionDate(Date actualResolutionDate) {
		this.actualResolutionDate = actualResolutionDate;
	}
	
	public String getResponseType(){
		return responseType;
	}
	
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	
	public String getAssociatedRisk(){
		return associatedRisk;
	}
	public void setAssociatedRisk(String associatedRisk) {
		this.associatedRisk = associatedRisk;
	}
	
	public String getAssociatedIssue(){
		return associatedIssue;
	}
	
	public void setAssociatedIssue(String associatedIssue) {
		this.associatedIssue = associatedIssue;
	}
	
	public String getRiskCreator(){
		return riskCreator;
	}
	public void setRiskCreator(String riskCreator) {
		this.riskCreator = riskCreator;
	}
	public String getRiskLastUpdatedPerson(){
		return riskLastUpdatedPerson;
	}
	
	public void setRiskLastUpdatedPerson(String riskLastUpdatedPerson) {
		this.riskLastUpdatedPerson = riskLastUpdatedPerson;
	}
	public String getRiskLastUpdatedDescription(){
		return riskLastUpdatedDescription;
	}
	public void setRiskLastUpdatedDescription(String riskLastUpdatedDescription) {
		this.riskLastUpdatedDescription = riskLastUpdatedDescription;
	}
	
	public Date getDateLastUpdated(){
		return dateLastUpdated;
	}
	public void setDateLastUpdated(Date dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}
	public String getRiskAssumptions(){
		return riskAssumptions;
	}
	public void setRiskAssumptions(String riskAssumptions) {
		this.riskAssumptions = riskAssumptions;
	}
	
	public String getRiskSymptoms(){
		return riskSymptoms;
	}	
	
	public void setRiskSymptoms(String riskSymptoms) {
		this.riskSymptoms = riskSymptoms;
	}
	
	//other methods
	//TODO - to be implemented later
	public int findRiskId(){
		return 0;
	}
	
	
}
