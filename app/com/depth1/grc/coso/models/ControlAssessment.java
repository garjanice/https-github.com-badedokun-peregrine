/**
 * 
 */
package com.depth1.grc.coso.models;

import java.util.Map;
import java.util.Set;

/**
 * The control assessment is an assessment of the 2013 control framework to assist organizations to link
 * their controls to the updated control framework. 
 * @author Bisi Adedokun
 *
 */
public class ControlAssessment {
	
	private long controlAssessmentId;
	private String principle;
	private Set<String> category;
	private Map<Float, String> section; // key is the section number, value is the section text
	private String question;
	/**
	 * 
	 */
	public ControlAssessment() {
		
	}


	/**
	 * @return the controlId
	 */
	public long getControlAssessmentId() {
		return controlAssessmentId;
	}


	/**
	 * @return the section
	 */
	public Map<Float, String> getSection() {
		return section;
	}


	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}


	/**
	 * @param controlId the controlId to set
	 */
	public void setControlAssessmentId(long controlId) {
		this.controlAssessmentId = controlId;
	}


	/**
	 * @param section the section to set
	 */
	public void setSection(Map<Float, String> section) {
		this.section = section;
	}


	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
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
	 * @return the category
	 */
	public Set<String> getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Set<String> category) {
		this.category = category;
	}

}
