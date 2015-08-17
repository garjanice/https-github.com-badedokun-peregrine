package com.depth1.grc.model;

import java.util.UUID;

import play.data.validation.Constraints.Required;

public class RiskType {

	@Required
	private UUID id;
	
	@Required
	private int	riskId;

	@Required
	private String riskType;

	 /**
     * Empty RiskType constructor that gets created by the user's input values
     */
	public RiskType(){
		
		id = UUID.randomUUID();
		
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getRiskId() {
		return riskId;
	}

	public void setRiskId(int riskId) {
		this.riskId = riskId;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	
}
