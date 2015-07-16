package com.depth1.grc.model;

import java.util.Date; 
import java.util.List;
import java.util.UUID;

public class Register {

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

	public Register(){
		super();
	}


	public UUID getid(){
		return id;
	}
	
	public void setid(UUID id){
		this.id=id;
	}
	
	public int gettenantId() {
		return tenantId;
	}

	public void settenantId(int tenantID) {
		this.tenantId=tenantId;
	}
	public int getriskId() {
		return riskId;
	}

	public void setriskId(int riskId) {
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
	
	public Date getimpact_date(){
		return impact_date;
	}
	
	public void setimpact_date(Date impact_date){
		this.impact_date=impact_date;
	}
	
	public float getscore() {
		return score;
	}

	public void setscore(float score) {
		this.score=score;
	}

	public String getresolution() {
		return resolution;
	}

	public void setresolution(String resolution) {
		this.resolution=resolution;
	}

	public Date gettarget_resolution_date() {
		return target_resolution_date;
	}

	public void settarget_resolution_date(Date target_resolution_date) {
		this.target_resolution_date=target_resolution_date;
	}

	public Date getactual_resolution_date() {
		return actual_resolution_date;
	}

	public void setactual_resolution_date(Date actual_resolution_date) {
		this.actual_resolution_date=actual_resolution_date;
	}

	public String getresponse_type() {
		return response_type;
	}

	public void setresponse_type(String response_type) {
		this.response_type=response_type;
	}

	public String getassociated_risk() {
		return associated_risk;
	}

	public void setassociated_risk(String associated_risk) {
		this.associated_risk=associated_risk;
	}

	public String getassociated_issue() {
		return associated_issue;
	}

	public void setassociated_issue(String associated_issue) {
		this.associated_issue=associated_issue;
	}

	public String getrisk_creator() {
		return risk_creator;
	}

	public void setrisk_creator(String risk_creator) {
		this.risk_creator=risk_creator;
	}

	public String getlast_updated_person() {
		return last_updated_person;
	}

	public void setlast_updated_person(String last_updated_person) {
		this.last_updated_person=last_updated_person;
	}

	public String getlast_updated_desc() {
		return last_updated_desc;
	}

	public void setlast_updated_desc(String last_updated_desc) {
		this.last_updated_desc=last_updated_desc;
	}

	public Date getlast_updated() {
		return last_updated;
	}

	public void setlast_updated(Date last_updated) {
		this.last_updated=last_updated;
	}

	public String getassumption() {
		return assumption;
	}

	public void setassumption(String assumption) {
		this.assumption=assumption;
	}

	public String getsymptom() {
		return symptom;
	}

	public void setsymptom(String symptom) {
		this.symptom=symptom;
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
