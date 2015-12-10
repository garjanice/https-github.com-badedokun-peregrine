package com.depth1.grc.model;

import java.util.Date; 
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RiskRegister {
	private UUID id;

	private int tenantId;

	private int riskId;

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
	
	private Date impact_date;

	private float score;

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

	public RiskRegister(){
		super();
	}


	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}


	/**
	 * @return the tenantId
	 */
	public int getTenantId() {
		return tenantId;
	}


	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}


	/**
	 * @return the riskId
	 */
	public int getRiskId() {
		return riskId;
	}


	/**
	 * @param riskId the riskId to set
	 */
	public void setRiskId(int riskId) {
		this.riskId = riskId;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}


	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the category_rating
	 */
	public String getCategory_rating() {
		return category_rating;
	}


	/**
	 * @param category_rating the category_rating to set
	 */
	public void setCategory_rating(String category_rating) {
		this.category_rating = category_rating;
	}


	/**
	 * @return the reporting_level
	 */
	public String getReporting_level() {
		return reporting_level;
	}


	/**
	 * @param reporting_level the reporting_level to set
	 */
	public void setReporting_level(String reporting_level) {
		this.reporting_level = reporting_level;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the impact_description
	 */
	public String getImpact_description() {
		return impact_description;
	}


	/**
	 * @param impact_description the impact_description to set
	 */
	public void setImpact_description(String impact_description) {
		this.impact_description = impact_description;
	}


	/**
	 * @return the probability
	 */
	public String getProbability() {
		return probability;
	}


	/**
	 * @param probability the probability to set
	 */
	public void setProbability(String probability) {
		this.probability = probability;
	}


	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}


	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}


	/**
	 * @return the impact
	 */
	public String getImpact() {
		return impact;
	}


	/**
	 * @param impact the impact to set
	 */
	public void setImpact(String impact) {
		this.impact = impact;
	}


	/**
	 * @return the impact_date
	 */
	public Date getImpact_date() {
		return impact_date;
	}


	/**
	 * @param impact_date the impact_date to set
	 */
	public void setImpact_date(Date impact_date) {
		this.impact_date = impact_date;
	}


	/**
	 * @return the score
	 */
	public float getScore() {
		return score;
	}


	/**
	 * @param score the score to set
	 */
	public void setScore(float score) {
		this.score = score;
	}


	/**
	 * @return the resolution
	 */
	public String getResolution() {
		return resolution;
	}


	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}


	/**
	 * @return the target_resolution_date
	 */
	public Date getTarget_resolution_date() {
		return target_resolution_date;
	}


	/**
	 * @param target_resolution_date the target_resolution_date to set
	 */
	public void setTarget_resolution_date(Date target_resolution_date) {
		this.target_resolution_date = target_resolution_date;
	}


	/**
	 * @return the actual_resolution_date
	 */
	public Date getActual_resolution_date() {
		return actual_resolution_date;
	}


	/**
	 * @param actual_resolution_date the actual_resolution_date to set
	 */
	public void setActual_resolution_date(Date actual_resolution_date) {
		this.actual_resolution_date = actual_resolution_date;
	}


	/**
	 * @return the response_type
	 */
	public String getResponse_type() {
		return response_type;
	}


	/**
	 * @param response_type the response_type to set
	 */
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}


	/**
	 * @return the associated_risk
	 */
	public String getAssociated_risk() {
		return associated_risk;
	}


	/**
	 * @param associated_risk the associated_risk to set
	 */
	public void setAssociated_risk(String associated_risk) {
		this.associated_risk = associated_risk;
	}


	/**
	 * @return the associated_issue
	 */
	public String getAssociated_issue() {
		return associated_issue;
	}


	/**
	 * @param associated_issue the associated_issue to set
	 */
	public void setAssociated_issue(String associated_issue) {
		this.associated_issue = associated_issue;
	}


	/**
	 * @return the risk_creator
	 */
	public String getRisk_creator() {
		return risk_creator;
	}


	/**
	 * @param risk_creator the risk_creator to set
	 */
	public void setRisk_creator(String risk_creator) {
		this.risk_creator = risk_creator;
	}


	/**
	 * @return the last_updated_person
	 */
	public String getLast_updated_person() {
		return last_updated_person;
	}


	/**
	 * @param last_updated_person the last_updated_person to set
	 */
	public void setLast_updated_person(String last_updated_person) {
		this.last_updated_person = last_updated_person;
	}


	/**
	 * @return the last_updated_desc
	 */
	public String getLast_updated_desc() {
		return last_updated_desc;
	}


	/**
	 * @param last_updated_desc the last_updated_desc to set
	 */
	public void setLast_updated_desc(String last_updated_desc) {
		this.last_updated_desc = last_updated_desc;
	}


	/**
	 * @return the last_updated
	 */
	public Date getLast_updated() {
		return last_updated;
	}


	/**
	 * @param last_updated the last_updated to set
	 */
	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}


	/**
	 * @return the assumption
	 */
	public String getAssumption() {
		return assumption;
	}


	/**
	 * @param assumption the assumption to set
	 */
	public void setAssumption(String assumption) {
		this.assumption = assumption;
	}


	/**
	 * @return the symptom
	 */
	public String getSymptom() {
		return symptom;
	}


	/**
	 * @param symptom the symptom to set
	 */
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}


	public CassandraRiskRegisterDao getCassandraRiskRegisterDao() {
		return cassandraRiskRegisterDao;
	}

	public void setCassandraRiskRegisterDao(CassandraRiskRegisterDao cassandraRiskRegisterDao) {
		this.cassandraRiskRegisterDao = cassandraRiskRegisterDao;
	}
    public String getSystemDateTime(){ 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		return df.format(Calendar.getInstance().getTime()); 
    }
}
