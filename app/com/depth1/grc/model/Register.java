package com.depth1.grc.model;

import java.util.Date; 
import java.util.List;

public class Register {

	private String id;

	private String tenantId;

	private String riskId;

	private String name;

	private String owner;

	private String status;

	private String category_rating;

	private String reporting_level;

	private String description;

	private String impact_description;

	private String probability;

	private String priority;

	private String impact;

	private String score;

	private String resolution;

	private Date   target_resolution_date;

	private Date   actual_resolution_date;

	private String response_type;

	private String associated_risk;

	private String associated_issue;

	private String risk_creator;

	private String last_updated_person;

	private String last_updated_desc;

	private Date   last_updated;

	private String assumption;

	private String symptom;


	public CassandraRiskRegisterDao cassandraRiskRegisterDao;

	public Register(){
		super();
	}

	protected Register findriskid(){
		//This is for ViewRegisterPage to View risk using the riskId
		return null;
	}

	public String getid(){
		return id;
	}
	
	public String getTenantId() {
		return tenantId;
	}

	public String getriskId() {
		return riskId;
	}

	public void setriskId(String riskId) {
		this.riskId=riskId;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name=name;
	}

	public String getowner() {
		return owner;
	}

	public void setowner(String owner) {
		this.owner=owner;
	}

	public String getstatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status=status;
	}

	public String getcategory_rating() {
		return category_rating;
	}

	public void setcategory_rating(String category_rating) {
		this.category_rating=category_rating;
	}

	public String getreporting_level() {
		return reporting_level;
	}

	public void setreporting_level(String reporting_level) {
		this.reporting_level=reporting_level;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description=description;
	}

	public String getimpact_description() {
		return impact_description;
	}

	public void setimpact_description(String impact_description) {
		this.impact_description=impact_description;
	}

	public String getprobability() {
		return probability;
	}	

	public void setprobability(String probability) {
		this.probability=probability;
	}

	public String getpriority() {
		return priority;
	}

	public void setpriority(String priority) {
		this.priority=priority;
	}

	public String getimpact() {
		return impact;
	}

	public void setimpact(String impact) {
		this.impact=impact;
	}

	public String getscore() {
		return score;
	}

	public void setscore(String score) {
		this.score=score;
	}

	public String getresolution() {
		return resolution;
	}

	public void setresolution(String resolution) {
		this.resolution=resolution;
	}

	public Date gettargetresolutiondate() {
		return target_resolution_date;
	}

	public void settargetresolutiondate(Date target_resolution_date) {
		this.target_resolution_date=target_resolution_date;
	}

	public Date getactualresolutiondate() {
		return actual_resolution_date;
	}

	public void setactualresolutiondate(Date actual_resolution_date) {
		this.actual_resolution_date=actual_resolution_date;
	}

	public String getresponsetype() {
		return response_type;
	}

	public void setresponsetype(String response_type) {
		this.response_type=response_type;
	}

	public String getassociatedrisk() {
		return associated_risk;
	}

	public void setassociatedrisk(String associated_risk) {
		this.associated_risk=associated_risk;
	}

	public String getassociatedissue() {
		return associated_issue;
	}

	public void setassociatedissue(String associated_issue) {
		this.associated_issue=associated_issue;
	}

	public String getriskcreator() {
		return risk_creator;
	}

	public void setriskcreator(String risk_creator) {
		this.risk_creator=risk_creator;
	}

	public String getlastupdatedperson() {
		return last_updated_person;
	}

	public void setlastupdatedperson(String last_updated_person) {
		this.last_updated_person=last_updated_person;
	}

	public String getlastupdateddesc() {
		return last_updated_desc;
	}

	public void setlastupdateddesc(String last_updated_desc) {
		this.last_updated_desc=last_updated_desc;
	}

	public Date getlastupdated() {
		return last_updated;
	}

	public void setlastupdated(Date last_updated) {
		this.last_updated=last_updated;
	}

	public String getassumption() {
		return assumption;
	}

	public void setassumption(String assumption) {
		this.assumption=assumption;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom=symptom;
	}

	public CassandraRiskRegisterDao getCassandraRiskRegisterDao() {
		return cassandraRiskRegisterDao;
	}

	public void setCassandraRiskRegisterDao(CassandraRiskRegisterDao cassandraRiskRegisterDao) {
		this.cassandraRiskRegisterDao = cassandraRiskRegisterDao;
	}

}
