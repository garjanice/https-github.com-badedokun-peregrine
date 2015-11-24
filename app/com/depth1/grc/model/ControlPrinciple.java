/**
 * 
 */
package com.depth1.grc.model;

/**
 * This class is the business object for creating, reading, updating, and deleting (CRUD)
 * Control Principle
 * @author Bisi Adedokun
 *
 */
public class ControlPrinciple {
	
	private int principleId;
	private String component;
	private String principle;
	private String role;
	
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
	

}
