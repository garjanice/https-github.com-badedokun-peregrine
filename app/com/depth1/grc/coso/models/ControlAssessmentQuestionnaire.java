/**
 * 
 */
package com.depth1.grc.coso.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The control assessment is an assessment of the 2013 control framework to assist organizations to link
 * their controls to the updated control framework. This assessment is structured in the form of a
 * questionnaire.
 * @author Bisi Adedokun
 *
 */
@Entity
@Table(name = "controlassesmentquestionnaire")
public class ControlAssessmentQuestionnaire {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="questionnaireid")
	private long questionnaireId;
	
	@Column(name="tenantid")
	private long tenantId;
	
	private String answer;
	private String reference;
	private String comment;
	
	@Column(name = "questionnairedate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date questionnaireDate;
	
	@OneToOne
	@JoinColumn(name = "questionid")
	private ControlAssesmentQuestion question;
	
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

	/**
	 * @return the questionnaireId
	 */
	public long getQuestionnaireId() {
		return questionnaireId;
	}

	/**
	 * @param questionnaireId the questionnaireId to set
	 */
	public void setQuestionnaireId(long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	/**
	 * @return the questionnaireDate
	 */
	public Date getQuestionnaireDate() {
		return questionnaireDate;
	}

	/**
	 * @param questionnaireDate the questionnaireDate to set
	 */
	public void setQuestionnaireDate(Date questionnaireDate) {
		this.questionnaireDate = questionnaireDate;
	}

	/**
	 * @return the question
	 */
	public ControlAssesmentQuestion getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(ControlAssesmentQuestion question) {
		this.question = question;
	}

}
