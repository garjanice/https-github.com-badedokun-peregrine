/**
 * 
 */
package com.depth1.grc.jpa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class 
 * @author badedokun
 *
 */

@Entity
@Table(name = "relatedobjective")
public class RelatedObjective extends Objective {
	
	@Column (name = "type")
	private String objectiveType;

	/**
	 * No argument constructor
	 */
	public RelatedObjective() {
		
	}

	/**
	 * @param tenantId
	 * @param name
	 * @param objective
	 */
	public RelatedObjective(long tenantId, String name, String objective) {
		super(tenantId, name, objective);
		// TODO Auto-generated constructor stub
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

}
