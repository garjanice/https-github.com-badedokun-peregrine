package com.depth1.grc.model;

import java.util.UUID;
import java.sql.Timestamp;
import java.util.Date;
import com.depth1.grc.util.DateUtility;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import play.data.validation.Constraints.Required;
import play.data.format.*;

/**
 * This class is the business object for creating, updating, deleting, viewing, and finding a procedure.
 * @author Nilima Shinde
 *
 */
public class Procedure {
	
	private UUID id;			//unique
	private int tenantId;
	private String policyId;
	@Required
	private String name;
	private String procedureId;
	private String description;
	@Required
	private String version;
	@Required
	private String author;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	private Date createDate;
	private String format;
	private String language;
	private String subject;
	private String reference;
	private String approver;
	private String owner;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	//@DateFormat("MM-dd-yyyy")
	private Date lastUpdatedDate;
	
	//Constructor
	public Procedure(){
		
	}
	
	//Setters and getters	
	
	/**
	 * @param tenantid the tenantId to set
	 */
	public void setTenantId(int tenantId){
		this.tenantId = tenantId;
	}
	/**
	 * @return the tenantId
	 */		
	public int getTenantId(){
		return tenantId;
	}
	/**
	 * @param policyid the policyId to set
	 */
	public void setPolicyId(String policyId){
		this.policyId = policyId;
	}
	/**
	 * @return the policyId
	 */			
	public String getPolicyId(){
		return policyId;
	}
	/**
	 * @param id the id to set
	 */	
	public void setId(UUID id){
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public UUID getId(){
		return id;
	}
	/**
	 * @param name the name to set
	 */		
	public void setName(String name){
		this.name = name;
	}
	/**
	 * @return the name
	 */		
	public String getName(){
		return name;
	}
	/**
	 * @param procedureid the procedureId to set
	 */
	public void setProcedureId(String procedureId){
		this.procedureId = procedureId;
	}
	/**
	 * @return the procedureId
	 */			
	public String getProcedureId(){
		return procedureId;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description){
		this.description = description;
	}
	/**
	 * @return the description
	 */		
	public String getDescription(){
		return description;
	}
	/**
	 * @param author the author to set
	 */		
	public void setAuthor(String author){
		this.author = author;
	}
	/**
	 * @return the author
	 */		
	public String getAuthor(){
		return author;
	}
	/**
	 * @param version the version to set
	 */				
	public void setVersion(String version){
		this.version = version;
	}
	/**
	 * @return the version
	 */			
	public String getVersion(){
		return version;
	}
	/**
	 * @param createDate the createDate to set
	 */			
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * @return the createDate
	 */			
	public Date getCreateDate(){
		return createDate;
	}
	/**
	 * @param format the format to set
	 */			
	public void setFormat(String format){
		this.format = format;
	}
	/**
	 * @return the format
	 */			
	public String getFormat(){
		return format;
	}
	/**
	 * @param language the language to set
	 */			
	public void setLanguage(String language){
		this.language = language;
	}
	/**
	 * @return the language
	 */		
	public String getLanguage(){
		return language;
	}
	/**
	 * @param subject the subject to set
	 */			
	public void setSubject(String subject){
		this.subject = subject;
	}
	/**
	 * @return the subject
	 */			
	public String getSubject(){
		return subject;
	}
	/**
	 * @param reference the reference to set
	 */		
	public void setReference(String reference){
		this.reference = reference;
	}
	/**
	 * @return the reference
	 */		
	public String getReference(){
		return reference;
	}
	/**
	 * @param approver the approver to set
	 */			
	public void setApprover(String approver){
		this.approver = approver;
	}
	/**
	 * @return the approver
	 */			
	public String getApprover(){
		return approver;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String portfolioOwner){
		this.owner = portfolioOwner;
	}
	/**
	 * @return the owner
	 */
	public String getOwner(){
		return owner;
	}
	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */	
	public void setLastUpdatedDate(Date lastUpdatedDate){
		this.lastUpdatedDate = lastUpdatedDate;
	}
	/**
	 * @return the lastUpdatedDate
	 */
	public Date getLastUpdatedDate(){
		return lastUpdatedDate;
	}
	public String getLastUpdatedDateString(){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return DateUtility.toString(lastUpdatedDate, df);
	}
	public void setLastUpdatedDateString(String date){
		try{
			lastUpdatedDate = DateUtility.toTimestamp(date, "MM/dd/yyyy");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	}


