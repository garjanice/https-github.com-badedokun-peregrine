/**
 * 
 */
package com.depth1.grc.coso.models;

/**
 * The control assessment is an assessment of the 2013 control framework to assist organizations to link
 * their controls to the updated control framework. This assessment is structured in the form of a
 * questionnaire.
 * @author Bisi Adedokun
 *
 */
public class ControlAssessmentQuestionnaire extends ControlAssessment {

	private long tenantId;
	private String answer;
	private String reference;
	private String comment;
	
	/**
	 * 
	 */
	public ControlAssessmentQuestionnaire() {
		
	}

	/**
	 * @return the tenantId
	 */
	public long getTenantId() {
		return tenantId;
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
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
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

}
