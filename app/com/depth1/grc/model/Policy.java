package com.depth1.grc.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.datastax.driver.core.utils.UUIDs;
import com.depth1.grc.model.common.DateFormat;
import com.depth1.grc.util.DateUtility;

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
	
	private UUID creationDate;
	private long uuidTime;
	private Date createDateUtil;
	
	private String format;
	private String language;
	private String subject;
	private String source;
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
	private Timestamp sunsetDate;
	
	private Timestamp effectiveDate;
	
	private Timestamp issueDate;
	
	private Timestamp lastReviewDate;
	
	private Timestamp nextReviewDate;
	
	private Timestamp lastUpdatedDate;
	
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
			
	public void setEffectiveDate(Timestamp effectiveDate){
		this.effectiveDate = effectiveDate;
	}
			
	public Timestamp getEffectiveDate(){
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
			
	public void setSunsetDate(Timestamp sunsetDate){
		this.sunsetDate = sunsetDate;
	}
			
	public Timestamp getSunsetDate(){
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
	
	public void setOriginalIssueDate(Timestamp originalIssueDate){
		this.issueDate = originalIssueDate;
	}
	
	public Timestamp getOriginalIssueDate(){
		return issueDate;
	}
	
	public void setLastReviewDate(Timestamp lastReviewDate){
		this.lastReviewDate = lastReviewDate;
	}
	
	public Timestamp getLastReviewDate(){
		return lastReviewDate;
	}
	
	public void setNextReviewDate(Timestamp nextReviewDate){
		this.nextReviewDate = nextReviewDate;
	}
	
	public Timestamp getNextReviewDate(){
		return nextReviewDate;
	}
	
	public void setRegulatory(String regulatory){
		this.regulatory = regulatory;
	} 
	
	public String getRegulatory(){
		return regulatory;
	}
	
	public void setLastUpdatedDate(Timestamp lastUpdatedDate){
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	public Timestamp getLastUpdatedDate(){
		return lastUpdatedDate;
	}
	//TODO: update the getLastUpdatedDate to current date for a new policy

	/**
	 * @return the uuidTime
	 */
	public long getUuidTime() {
		return uuidTime;
	}

	/**
	 * @param uuidTime the uuidTime to set
	 */
	public void setUuidTime(long uuidTime) {
		this.uuidTime = uuidTime;
	}

	/**
	 * @return the createDateUtil
	 */
	public Date getCreateDateUtil() {
		return createDateUtil;
	}

	/**
	 * @param createDateUtil the createDateUtil to set
	 */
	public void setCreateDateUtil(Date createDateUtil) {
		this.createDateUtil = createDateUtil;
	}
	
	public String getCreationDateString() {	
		uuidTime = UUIDs.unixTimestamp(creationDate);
		createDateUtil = new Date(uuidTime);
		return DateUtility.formatDateFromUuid("MM/dd/yyyy",createDateUtil );
	}
	public String getSunsetDateString(){
		java.text.DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return DateUtility.toString(sunsetDate, df);
	}
	public void setSunsetDateString(String date){
		try{
			sunsetDate = DateUtility.toTimestamp(date, "MM/dd/yyyy");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getLastUpdatedDateString(){
		java.text.DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return DateUtility.toString(lastUpdatedDate, df);
	}
	public String getEffectiveDateString(){
		java.text.DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return DateUtility.toString(effectiveDate, df);
	}
	public void setEffectiveDateString(String date){
		try{
			effectiveDate = DateUtility.toTimestamp(date, "MM/dd/yyyy");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getIssueDateString(){
		java.text.DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return DateUtility.toString(issueDate, df);
	}
	public void setIssueDateString(String date){
		try{
			issueDate = DateUtility.toTimestamp(date, "MM/dd/yyyy");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getLastReviewDateString(){
		java.text.DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return DateUtility.toString(lastReviewDate, df);
	}
	public void setLastReviewDateString(String date){
		try{
			lastReviewDate = DateUtility.toTimestamp(date, "MM/dd/yyyy");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getNextReviewDateString(){
		java.text.DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return DateUtility.toString(nextReviewDate, df);
	}
	public void setNextReviewDateString(String date){
		try{
			nextReviewDate = DateUtility.toTimestamp(date, "MM/dd/yyyy");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
