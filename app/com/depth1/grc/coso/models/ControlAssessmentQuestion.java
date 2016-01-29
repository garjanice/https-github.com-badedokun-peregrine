/**
 * 
 */
package com.depth1.grc.coso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.depth1.grc.util.IdProducer;

/**
 * The control assessment questions is an assessment of the 2013 control framework to assist organizations to link
 * their controls to the updated control framework. 
 * @author Bisi Adedokun
 *
 */

@Entity
@Table(name = "controlassesmentquestion")
public class ControlAssessmentQuestion {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="questionid")
	private long questionId;
	
	@Column(name="question_number")
	private Float questionNumber;
	
	private String question;
	
	@ManyToOne
    @JoinColumn(name="principleid", nullable=false)
    private ControlPrinciple principle;
	
	/**
	 * Produces custom identifier (Id) to use for the entity
	 * 
	 */
	@PrePersist
	public void generateId() {
		questionId = IdProducer.getNextId();
	}

	/**
	 * 
	 */
	public ControlAssessmentQuestion() {
		
	}


	/**
	 * @return the questionId
	 */
	public long getQuestionId() {
		return questionId;
	}


	/**
	 * @return the questionNumber
	 */
	public Float getQuestionNumber() {
		return questionNumber;
	}


	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}


	/**
	 * @return the assessment
	 */
	public ControlPrinciple getPrinciple() {
		return principle;
	}


	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}


	/**
	 * @param questionNumber the questionNumber to set
	 */
	public void setQuestionNumber(Float questionNumber) {
		this.questionNumber = questionNumber;
	}


	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}


	/**
	 * @param assessment the assessment to set
	 */
	public void setPrinciple(ControlPrinciple principle) {
		this.principle = principle;
	}

}
