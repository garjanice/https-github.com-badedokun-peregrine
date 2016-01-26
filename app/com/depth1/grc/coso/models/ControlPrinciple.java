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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * This class is the business object for creating, reading, updating, and deleting (CRUD)
 * Control Principle
 * @author Bisi Adedokun
 *
 */
@Entity
@Table(name = "controlprinciple")
public class ControlPrinciple {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="principleid")
	private int principleId;
	
	private String component;
	
	@Column (name = "principlenumber")
	private int principleNumber;
	
	private String principle;
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="principle", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ControlAssesmentQuestion> questions = Collections.synchronizedSet(new LinkedHashSet<ControlAssesmentQuestion>());
	
	
	/**
	 * 
	 */
	public ControlPrinciple() {
		
	}
	
	/**
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}
	/**
	 * @param component the component to set
	 */
	public void setComponent(String component) {
		this.component = component;
	}
	/**
	 * @return the principle
	 */
	public String getPrinciple() {
		return principle;
	}
	/**
	 * @param principle the principle to set
	 */
	public void setPrinciple(String principle) {
		this.principle = principle;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * @return the principleId
	 */
	public int getPrincipleId() {
		return principleId;
	}
	/**
	 * @param principleid the principleId to set
	 */
	public void setPrincipleid(int principleId) {
		this.principleId = principleId;
	}
	/**
	 * @return the principleNumber
	 */
	public int getPrincipleNumber() {
		return principleNumber;
	}
	/**
	 * @param principleNumber the principleNumber to set
	 */
	public void setPrincipleNumber(int principleNumber) {
		this.principleNumber = principleNumber;
	}
	

}
