//////////////////////////////////////////////////////////////////
// Policy.java	-	Data Model for Policy						//
// ver 1.0														//
// Team-A														//
// Platform: Win 8.1 Pro, Lenovo Y40-70, JDK 1.8.0_u45 64bit	//
//				Scala 2.11.6, Play 2.4.2						//
//////////////////////////////////////////////////////////////////

/*
 * Description:
 * --------------------------------------------------
 * This is the data model for Policy.
 * Attributes are made private because Play Framework
 * will automatically generate getters and setters.
 * 
 * Maintanance History:
 * --------------------------------------------------
 * ver 1.0: Jul 7, 2015
 * - First version.
 * 
 */

package com.depth1.grc.model;

import java.util.UUID;
import java.sql.Date;

public class Policy {
	private int tenantId;
	private UUID policyId;			//unique
	private String name;
	private String description;
	private String version;
	private String author;
	private Date creationDate;
	private String format;
	private String language;
	private String subject;
	private String source;
	private Date sunsetDate;
	private String category;
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
	private Date effectiveDate;
	private Date originalIssueDate;
	private Date lastReviewDate;
	private Date nextReviewDate;
	private Date lastModifiedDate;
	
	//Constructor(s)
		//--------------
		
		public Policy(){
			tenantId = 0;
		}
		
		//Setters and getters
		//-------------------
		
		public void setTenantId(int tenantId){
			this.tenantId = tenantId;
		}
		
		public int getTenantId(){
			return tenantId;
		}
		
		public void setPolicyId(UUID id){
			this.policyId = id;
		}
		
		public UUID getPolicyId(){
			return policyId;
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public String getName(){
			return name;
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
		
		public void setCreationDate(Date creationDate){
			this.creationDate = creationDate;
		}
		
		public Date getCreationDate(){
			return creationDate;
		}
		
		public void setEffectiveDate(Date effectiveDate){
			this.effectiveDate = effectiveDate;
		}
		
		public Date getEffectiveDate(){
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
		
		public void setSunsetDate(Date sunsetDate){
			this.sunsetDate = sunsetDate;
		}
		
		public Date getSunsetDate(){
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
		
		public String getCompliaceCategory(){
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
		
		public void setOriginalIssueDate(Date originalIssueDate){
			this.originalIssueDate = originalIssueDate;
		}
		
		public Date getOriginalIssueDate(){
			return originalIssueDate;
		}
		
		public void setLastReviewDate(Date lastReviewDate){
			this.lastReviewDate = lastReviewDate;
		}
		
		public Date getLastReviewDate(){
			return lastReviewDate;
		}
		
		public void setNextReviewDate(Date nextReviewDate){
			this.nextReviewDate = nextReviewDate;
		}
		
		public Date getNextReviewDate(){
			return nextReviewDate;
		}
		
		public void setRegulatory(String regulatory){
			this.regulatory = regulatory;
		} 
		
		public String getRegulatory(){
			return regulatory;
		}
		
		public void setLastModifiedDate(Date lastModifiedDate){
			this.lastModifiedDate = lastModifiedDate;
		}
		
		public Date getLastModifiedDate(){
			return lastModifiedDate;
		}

}
