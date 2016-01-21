/**
 * 
 */
package com.depth1.grc.model;

import java.util.UUID;

/**
 * This class captures risk taxonomy by Line of Defense.
 * @author Bisi Adedokun
 *
 */
public class RiskTaxonomy {

	private UUID riskTaxonomyId;
	private String lineOfDefense;
	private String level1Risk;
	private String level2Risk;

	/**
	 * @return the riskTaxonomyId
	 */
	public UUID getRiskTaxonomyId() {
		return riskTaxonomyId;
	}


	/**
	 * @return the lineOfDefense
	 */
	public String getLineOfDefense() {
		return lineOfDefense;
	}


	/**
	 * @return the level1Risk
	 */
	public String getLevel1Risk() {
		return level1Risk;
	}


	/**
	 * @return the level2Risk
	 */
	public String getLevel2Risk() {
		return level2Risk;
	}


	/**
	 * @param riskTaxonomyId the riskTaxonomyId to set
	 */
	public void setRiskTaxonomyId(UUID riskTaxonomyId) {
		this.riskTaxonomyId = riskTaxonomyId;
	}


	/**
	 * @param lineOfDefense the lineOfDefense to set
	 */
	public void setLineOfDefense(String lineOfDefense) {
		this.lineOfDefense = lineOfDefense;
	}


	/**
	 * @param level1Risk the level1Risk to set
	 */
	public void setLevel1Risk(String level1Risk) {
		this.level1Risk = level1Risk;
	}


	/**
	 * @param level2Risk the level2Risk to set
	 */
	public void setLevel2Risk(String level2Risk) {
		this.level2Risk = level2Risk;
	}


	/**
	 * 
	 */
	public RiskTaxonomy() {
		// TODO Auto-generated constructor stub
	}

}
