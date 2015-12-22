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
 * @author badedokun
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
	private long tenantdId;
	
	@Column(name="measure")
	private String measure;

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
	public Measure(long tenantdId, String measure, StrategicObjective objective) {
		this.tenantdId = tenantdId;
		this.measure = measure;
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
	public long getTenantdId() {
		return tenantdId;
	}

	/**
	 * @param tenantdId the tenantdId to set
	 */
	public void setTenantdId(long tenantdId) {
		this.tenantdId = tenantdId;
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
    private StrategicObjective objective;

	/**
	 * @return the objective
	 */
	public StrategicObjective getObjective() {
		return objective;
	}
	

	/**
	 * @param objective the objective to set
	 */
	public void setObjective(StrategicObjective objective) {
		this.objective = objective;
		if (!objective.getMeasure().contains(this)) {
			objective.getMeasure().add(this);
		}
	}

}
