/**
 * 
 */
package com.depth1.grc.jpa.models;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * This class captures strategic objectives of an entity, including how the objective is measured
 * and any related objectives associated with the strategic objective
 * @author Bisi Adedokun
 *
 */
@Entity
@Table(name = "strategicobjective")
@Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
public class StrategicObjective {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private long objectiveId;
	
	@Column(name="tenantid")
	private long tenantId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="objective")
	private String objective;
	
	

	/**
	 * No argument constructor required by Hibernate
	 */
	public StrategicObjective() {
		
	}	
	
	/**
	 * @param objectiveId
	 * @param tenantId
	 * @param name
	 * @param objective
	 */
	public StrategicObjective(long tenantId, String name, String objective) {
		this.tenantId = tenantId;
		this.name = name;
		this.objective = objective;
	}



	/**
	 * @return the objectiveId
	 */
	
	public long getObjectiveId() {
		return objectiveId;
	}
	/**
	 * @param objectiveId the objectiveId to set
	 */
	public void setObjectiveId(long objectiveId) {
		this.objectiveId = objectiveId;
	}
	/**
	 * @return the tenantId
	 */
	public long getTenantId() {
		return tenantId;
	}
	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
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
	 * @return the objective
	 */
	public String getObjective() {
		return objective;
	}
	/**
	 * @param objective the objective to set
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}
	
	 
	@OneToMany(cascade = CascadeType.ALL, mappedBy="objective", orphanRemoval = true)
    private Set<Measure> measures = Collections.synchronizedSet(new LinkedHashSet<Measure>());


	/**
	 * @return the measure
	 */
	public Set<Measure> getMeasure() {
		return measures;
	}

	/**
	 * @param measure the measure to set
	 */
	public void setMeasure(Set<Measure> measure) {
		this.measures = measure;
	}
	
	
	/**
	 * Adds measures to the set
	 * @param measure the measure to add
	 */
	public void addMeasure(Measure measure) {
		this.measures.add(measure);
		if (measure.getObjective() != this) {
			measure.setObjective(this);
		}
	}

	/**
	 * Remove measures from the set
	 * @param measure the measure to remove
	 */
	public void removeMeasure(Measure measure) {
		measure.setObjective(null);
		this.measures.remove(measure);
	}	

}
