/**
 * 
 */
package com.depth1.grc.model;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Bisi Adedokun
 *
 */
public class ControlAssessment {
	
	private long controlAssessmentId;
	private String principle;
	private Set<String> category;
	private Map<Float, String> section; // key is the section number, value is the section text
	private String question;
	private String answer;
	private String reference;
	private String comment;
	

	
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
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}


	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}


	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
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
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}


	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}


	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
