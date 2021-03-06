/**
 * 
 */
package com.depth1.grc.coso.models;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.depth1.grc.util.IdProducer;

import play.Logger;


/**
 * This class captures strategic objectives of an entity, including how the objective is measured
 * and any related objectives associated with the strategic objective
 * @author Bisi Adedokun
 *
 */
@Entity
@Table(name = "objective")
@Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
public class Objective {
	
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
	
	@Column(name="objectivetype")
	private String objectiveType;
	
	@Column (name="objectivelevel")
	private String objectiveLevel;		// The level at which the objective is being set such as Entity or Division
	
	@Column (name="objectivesettingbody")
	private String objectiveSettingBody;	// The body responsible for setting the objective such as the Board or Senior Management

	/**
	 * Produces custom identifier (Id) to use for the entity
	 * 
	 */
	//@PrePersist
	public void generateId() {
		//IdProducer.getInstance();
		objectiveId = IdProducer.nextId();
		Logger.info("Id value: "+ objectiveId);
	}	
	
	/**
	 * No argument constructor required by Hibernate
	 */
	public Objective() {
		
	}	
	

	/**
	 * @param tenantId
	 * @param name
	 * @param objective
	 * @param objectiveType
	 * @param objectiveLevel
	 * @param objectiveSettingBody
	 */
	public Objective(long tenantId, String name, String objective, String objectiveType, String objectiveLevel,
			String objectiveSettingBody) {
		this.tenantId = tenantId;
		this.name = name;
		this.objective = objective;
		this.objectiveType = objectiveType;
		this.objectiveLevel = objectiveLevel;
		this.objectiveSettingBody = objectiveSettingBody;
	}


	/**
	 * @return the objectiveType
	 */
	public String getObjectiveType() {
		return objectiveType;
	}

	/**
	 * @return the objectiveLevel
	 */
	public String getObjectiveLevel() {
		return objectiveLevel;
	}

	/**
	 * @param objectiveType the objectiveType to set
	 */
	public void setObjectiveType(String objectiveType) {
		this.objectiveType = objectiveType;
	}

	/**
	 * @param objectiveLevel the objectiveLevel to set
	 */
	public void setObjectiveLevel(String objectiveLevel) {
		this.objectiveLevel = objectiveLevel;
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
	
	 
	@OneToMany(cascade = CascadeType.ALL, mappedBy="objective", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Measure> measures = Collections.synchronizedSet(new LinkedHashSet<Measure>());


	/**
	 * @return the measure
	 */
	public Set<Measure> getMeasure() {
		return measures;
	}

	/**
	 * @return the objectiveSettingBody
	 */
	public String getObjectiveSettingBody() {
		return objectiveSettingBody;
	}

	/**
	 * @param objectiveSettingBody the objectiveSettingBody to set
	 */
	public void setObjectiveSettingBody(String objectiveSettingBody) {
		this.objectiveSettingBody = objectiveSettingBody;
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
