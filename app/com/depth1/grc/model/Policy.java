package com.depth1.grc.model;

import java.util.UUID;

import com.depth1.grc.model.common.DateFormat;

import play.data.validation.Constraints.Required;
import play.data.format.*;

public class Policy {
	
	private UUID id;			//unique
	private int tenantId;
	@Required
	private String name;
	private String policyId;
	private String description;
	@Required
	private String version;
	@Required
	private String author;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	private UUID creationDate;
	private String format;
	private String language;
	private String subject;
	private String source;
	//@Required
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	private UUID sunsetDate;
	private String category;
	@Required
	private String classification;
	private String reference;
	private String legalRequirement;
	private String regulatory;
	private String approver;
	private String producingFunction;
	private String complianceCategory;
	private String owner;
	private String documentContact;
	private String functionalApplicability;
	private String geographicApplicability;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	@DateFormat("MM-dd-yyyy")
	private UUID effectiveDate;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	@DateFormat("MM-dd-yyyy")
	private UUID issueDate;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	@DateFormat("MM-dd-yyyy")
	private UUID lastReviewDate;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	@DateFormat("MM-dd-yyyy")
	private UUID nextReviewDate;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	@DateFormat("MM-dd-yyyy")
	private UUID lastUpdatedDate;
	
	//Constructor
	public Policy(){
		
	}
	
	//Setters and getters	
	public void setTenantId(int tenantId){
		this.tenantId = tenantId;
	}
			
	public int getTenantId(){
		return tenantId;
	}
			
	public void setId(UUID id){
		this.id = id;
	}
			
	public UUID getId(){
		return id;
	}
			
	public void setName(String name){
		this.name = name;
	}
			
	public String getName(){
		return name;
	}

	public void setPolicyId(String policyId){
		this.policyId = policyId;
	}
			
	public String getPolicyId(){
		return policyId;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
		
	public String getDescription(){
		return description;
	}
			
	public void setAuthor(String author){
		this.author = author;
	}
			
	public String getAuthor(){
		return author;
	}
			
	public void setVersion(String version){
		this.version = version;
	}
			
	public String getVersion(){
		return version;
	}
			
	public void setCreationDate(UUID creationDate){
		this.creationDate = creationDate;
	}
			
	public UUID getCreationDate(){
		return creationDate;
	}
			
	public void setEffectiveDate(UUID effectiveDate){
		this.effectiveDate = effectiveDate;
	}
			
	public UUID getEffectiveDate(){
		return effectiveDate;
	}
			
	public void setFormat(String format){
		this.format = format;
	}
			
	public String getFormat(){
		return format;
	}
			
	public void setLanguage(String language){
		this.language = language;
	}
			
	public String getLanguage(){
		return language;
	}
			
	public void setSubject(String subject){
		this.subject = subject;
	}
			
	public String getSubject(){
		return subject;
	}
			
	public void setSource(String source){
		this.source = source;
	}
			
	public String getSource(){
		return source;
	}
			
	public void setSunsetDate(UUID sunsetDate){
		this.sunsetDate = sunsetDate;
	}
			
	public UUID getSunsetDate(){
		return sunsetDate;
	}
			
	public void setCategory(String category){
		this.category = category;
	}
			
	public String getCategory(){
		return category;
	}
			
	public void setClassification(String classification){
		this.classification = classification;
	}
			
	public String getClassification(){
		return classification;
	}
			
	public void setReference(String reference){
		this.reference = reference;
	}
			
	public String getReference(){
		return reference;
	}
			
	public void setLegalRequirement(String legalRequirement){
		this.legalRequirement = legalRequirement;
	}
			
	public String getLegalRequirement(){
		return legalRequirement;
	}
			
	public void setApprover(String approver){
		this.approver = approver;
	}
		
	public String getApprover(){
		return approver;
	}
	
	public void setProducingFunction(String producingFunction){
		this.producingFunction = producingFunction;
	}
	
	public String getProducingFunction(){
		return producingFunction;
	}
	
	public void setComplianceCategory(String complianceCategory){
		this.complianceCategory = complianceCategory;
	}
	
	public String getComplianceCategory(){
		return complianceCategory;
	}
	
	public void setOwner(String portfolioOwner){
		this.owner = portfolioOwner;
	}
	
	public String getOwner(){
		return owner;
	}

	public void setDocumentContact(String documentContact){
		this.documentContact = documentContact;
	}
	
	public String getDocumentContact(){
		return documentContact;
	}
	
	public void setFunctionalApplicability(String functionalApplicability){
		this.functionalApplicability = functionalApplicability;
	}
	
	public String getFunctionalApplicability(){
		return functionalApplicability;
	}
	
	public void setGeographicApplicability(String geographicApplicability){
		this.geographicApplicability = geographicApplicability;
	}
	
	public String getGeographicApplicability(){
		return geographicApplicability;
	}
	
	public void setOriginalIssueDate(UUID originalIssueDate){
		this.issueDate = originalIssueDate;
	}
	
	public UUID getOriginalIssueDate(){
		return issueDate;
	}
	
	public void setLastReviewDate(UUID lastReviewDate){
		this.lastReviewDate = lastReviewDate;
	}
	
	public UUID getLastReviewDate(){
		return lastReviewDate;
	}
	
	public void setNextReviewDate(UUID nextReviewDate){
		this.nextReviewDate = nextReviewDate;
	}
	
	public UUID getNextReviewDate(){
		return nextReviewDate;
	}
	
	public void setRegulatory(String regulatory){
		this.regulatory = regulatory;
	} 
	
	public String getRegulatory(){
		return regulatory;
	}
	
	public void setLastUpdatedDate(UUID lastUpdatedDate){
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	public UUID getLastUpdatedDate(){
		return lastUpdatedDate;
	}
	//TODO: update the getLastUpdatedDate to current date for a new policy

}
