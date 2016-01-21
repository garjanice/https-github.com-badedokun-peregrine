/**
 * 
 */
package com.depth1.grc.jpa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class captures how an objective that is set by an entity is measured for meeting the goals of the 
 * set objective.
 * @author Bisi Adedokun
 *
 */
@Entity
@Table(name = "measure")
public class Measure {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="measureid")
	private long measureId;
	
	@Column(name="tenantId")
	private long tenantId;
	
	@Column(name="measure")
	private String measure;
	
	@Column(name="objectivetype")
	private String objectiveType;
	
	/**
	 * 
	 */
	public Measure() {
		
	}

	/**
	 * @param measureId
	 * @param tenantdId
	 * @param measure
	 * @param objective
	 */
	public Measure(long tenantdId, String measure, String objectiveType, Objective objective) {
		this.tenantId = tenantdId;
		this.measure = measure;
		this.objectiveType = objectiveType;
		this.objective = objective;
	}

	/**
	 * @return the measureId
	 */
	public long getMeasureId() {
		return measureId;
	}

	/**
	 * @param measureId the measureId to set
	 */
	public void setMeasureId(long measureId) {
		this.measureId = measureId;
	}

	/**
	 * @return the tenantdId
	 */
	public long getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantdId the tenantdId to set
	 */
	public void setTenantId(long tenantdId) {
		this.tenantId = tenantdId;
	}

	/**
	 * @return the measure
	 */
	public String getMeasure() {
		return measure;
	}

	/**
	 * @param measure the measure to set
	 */
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
	@ManyToOne
    @JoinColumn(name="objectiveid", nullable=false)
    private Objective objective;

	/**
	 * @return the objective
	 */
	public Objective getObjective() {
		return objective;
	}
	
	/**
	 * @return the objectiveType
	 */
	public String getObjectiveType() {
		return objectiveType;
	}

	/**
	 * @param objectiveType the objectiveType to set
	 */
	public void setObjectiveType(String objectiveType) {
		this.objectiveType = objectiveType;
	}
	
	

	/**
	 * @param objective the objective to set
	 */
	public void setObjective(Objective objective) {
		this.objective = objective;
		if (!objective.getMeasure().contains(this)) {
			objective.getMeasure().add(this);
		}
	}

}
