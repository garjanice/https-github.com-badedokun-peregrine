/**
 * 
 */
package com.depth1.grc.coso.models;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * This class provides a summary of control deficiencies across the entity or organization
 * @author Bisi Adedokun
 *
 */
public class SummaryOfDeficiency {
	
	private UUID deficiencyId;
	private String component;
	private String principle;
	private String deficiencyDescription;
	private String deficiencySeverity;
	private String isDeficiencyMajor;
	private String owner;
	private String remediationPlan;
	private Date remediationPlanDate;
	private String impact;
	private Map<String, String> relatedDeficiency;
	private String lodFunction;

	/**
	 * 
	 */
	public SummaryOfDeficiency() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the deficiencyId
	 */
	public UUID getDeficiencyId() {
		return deficiencyId;
	}

	/**
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * @return the principle
	 */
	public String getPrinciple() {
		return principle;
	}

	/**
	 * @return the deficiencyDescription
	 */
	public String getDeficiencyDescription() {
		return deficiencyDescription;
	}

	/**
	 * @return the deficiencySeverity
	 */
	public String getDeficiencySeverity() {
		return deficiencySeverity;
	}

	/**
	 * @return the isDeficiencyMajor
	 */
	public String getIsDeficiencyMajor() {
		return isDeficiencyMajor;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @return the remediationPlan
	 */
	public String getRemediationPlan() {
		return remediationPlan;
	}

	/**
	 * @return the remediationPlanDate
	 */
	public Date getRemediationPlanDate() {
		return remediationPlanDate;
	}

	/**
	 * @return the impact
	 */
	public String getImpact() {
		return impact;
	}

	/**
	 * @return the relatedDeficiency
	 */
	public Map<String, String> getRelatedDeficiency() {
		return relatedDeficiency;
	}

	/**
	 * @param deficiencyId the deficiencyId to set
	 */
	public void setDeficiencyId(UUID deficiencyId) {
		this.deficiencyId = deficiencyId;
	}

	/**
	 * @param component the component to set
	 */
	public void setComponent(String component) {
		this.component = component;
	}

	/**
	 * @param principle the principle to set
	 */
	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	/**
	 * @param deficiencyDescription the deficiencyDescription to set
	 */
	public void setDeficiencyDescription(String deficiencyDescription) {
		this.deficiencyDescription = deficiencyDescription;
	}

	/**
	 * @param deficiencySeverity the deficiencySeverity to set
	 */
	public void setDeficiencySeverity(String deficiencySeverity) {
		this.deficiencySeverity = deficiencySeverity;
	}

	/**
	 * @param isDeficiencyMajor the isDeficiencyMajor to set
	 */
	public void setIsDeficiencyMajor(String isDeficiencyMajor) {
		this.isDeficiencyMajor = isDeficiencyMajor;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @param remediationPlan the remediationPlan to set
	 */
	public void setRemediationPlan(String remediationPlan) {
		this.remediationPlan = remediationPlan;
	}

	/**
	 * @param remediationPlanDate the remediationPlanDate to set
	 */
	public void setRemediationPlanDate(Date remediationPlanDate) {
		this.remediationPlanDate = remediationPlanDate;
	}

	/**
	 * @param impact the impact to set
	 */
	public void setImpact(String impact) {
		this.impact = impact;
	}

	/**
	 * @param relatedDeficiency the relatedDeficiency to set
	 */
	public void setRelatedDeficiency(Map<String, String> relatedDeficiency) {
		this.relatedDeficiency = relatedDeficiency;
	}

	/**
	 * @return the lodFunction
	 */
	public String getLodFunction() {
		return lodFunction;
	}

	/**
	 * @param lodFunction the lodFunction to set
	 */
	public void setLodFunction(String lodFunction) {
		this.lodFunction = lodFunction;
	}

}
