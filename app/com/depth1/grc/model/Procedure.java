package com.depth1.grc.model;

import java.util.UUID;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.depth1.grc.util.DateUtility;

import com.depth1.grc.model.common.DateFormat;

import play.data.validation.Constraints.Required;
import play.data.format.*;

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
	private Timestamp creationDate;
	private String format;
	private String language;
	private String subject;
	private String reference;
	private String approver;
	private String owner;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	@DateFormat("MM-dd-yyyy")
	private Timestamp lastUpdatedDate;
	
	//Constructor
	public Procedure(){
		
	}
	
	//Setters and getters	
	public void setTenantId(int tenantId){
		this.tenantId = tenantId;
	}
			
	public int getTenantId(){
		return tenantId;
	}
	
	public void setPolicyId(String policyId){
		this.policyId = policyId;
	}
			
	public String getPolicyId(){
		return policyId;
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

	public void setProcedureId(String procedureId){
		this.procedureId = procedureId;
	}
			
	public String getProcedureId(){
		return procedureId;
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
			
	public void setCreationDate(Timestamp creationDate){
		this.creationDate = creationDate;
	}
			
	public Timestamp getCreationDate(){
		return creationDate;
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
			
	public void setReference(String reference){
		this.reference = reference;
	}
			
	public String getReference(){
		return reference;
	}
			
	public void setApprover(String approver){
		this.approver = approver;
	}
		
	public String getApprover(){
		return approver;
	}
	
	
	
	public void setOwner(String portfolioOwner){
		this.owner = portfolioOwner;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public void setLastUpdatedDate(Timestamp lastUpdatedDate){
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	public Timestamp getLastUpdatedDate(){
		return lastUpdatedDate;
	}
	
	
	//TODO: update the getLastUpdatedDate to current date for a new procedure


	}


