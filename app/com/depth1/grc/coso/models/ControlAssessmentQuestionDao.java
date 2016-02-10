/**
 * 
 */
package com.depth1.grc.coso.models;

import java.util.List;

import com.depth1.grc.exception.DaoException;

/**
 * The Control Assessment Question and Questionnaire interface defines the contract that all 
 * implementing classes must abide by. It provides API to expose to caller of classes that implement
 * the interface.
 * @author Bisi Adedokun
 *
 */
public interface ControlAssessmentQuestionDao {
	
	/**
	 * Creates a control assessment question.
	 * 
	 * @param question the question to create
	 * @throws DaoException if error occurs while creating a control assessment question in the data store
	 */
	public void createControlAssessmentQuestion(ControlAssessmentQuestion question) throws DaoException;
	
	/**
	 * Deletes a control assessment question.
	 * 
	 * @param questionId the control assessment question ID to delete
	 * @param tenantId the tenant ID of the tenant whose control assessment to delete
	 * @return true if the Control Assessment Question is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a control assessment question from the data store
	 */
	public boolean deleteControlAssessmentQuestion(long questionId, long tenantId) throws DaoException;
	
	
	/**
	 * Finds a Control Assessment question.
	 * 
	 * @param questionId the control assessment ID to find
	 * @param tenantId the tenant ID of the tenant whose control assessment to find
	 * @return Control Assessment question that was found
	 * @throws DaoException if error occurs while finding control assessment question in the data store
	 */
	public ControlAssessmentQuestion getControlAssessmentQuestion(long questionId, long tenantId) throws DaoException;
	
	/**
	 * Lists control assessment question in the data store.
	 * 
	 * @param tenantId the tenant ID of the tenant whose control assessment question to find
	 * @return List of control assessment questions
	 * @throws DaoException if error occurs while reading control assessment question from the data store
	 */
	public List<ControlAssessmentQuestion> listControlAssessmentQuestion(long tenantId) throws DaoException;
	
	/**
	 * Lists control assessment question.
	 * 
	 * @param questionId the control assessment question ID to find
	 * @param tenantId the tenant ID of the tenant whose control assessment question to find
	 * @return List of Control Assessment questions that were found
	 * @throws DaoException if error occurs while finding control assessment questions in the data store
	 */
	public List<ControlAssessmentQuestion> listControlAssessmentQuestion(long questionId, long tenantId) throws DaoException;	
		
	/**
	 * Updates Control Assessment question.
	 * 
	 * @param question the control assessment question to update
	 * @return true if the control assessment question is updated successfully, false otherwise
	 * @throws DaoException if error occurs while updating a control assessment question in the data store
	 */
	public boolean updateControlAssessmentQuestion(ControlAssessmentQuestion question) throws DaoException; 
	
	/**
	 * Creates a control assessment questionnaire.
	 * 
	 * @param questionnaire the control assessment questionnaire to create
	 * @throws DaoException if error occurs while creating the Control Assessment questionnaire in the data store
	 */
	public void createControlAssessmentQuestionnaire(ControlAssessmentQuestionnaire questionnaire) throws DaoException;
	
	/**
	 * Deletes a control assessment questionnaire.
	 * 
	 * @param questionnaireId the control assessment questionnaire ID to delete
	 * @param tenantId the tenant ID of the tenant whose control assessment questionnaire to delete
	 * @return true if the Control Assessment questionnaire is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a control assessment questionnaire from the data store
	 */
	public boolean deleteControlAssessmentQuestionnaire(long questionnaireId, long tenantId) throws DaoException;
	
	
	/**
	 * Finds a Control Assessment questionnaire.
	 * 
	 * @param questionnaireId the control assessment questionnaire ID to find
	 * @param tenantId the tenant ID of the tenant whose control assessment questionnaire to find
	 * @return Control Assessment questionnaire that was found
	 * @throws DaoException if error occurs while finding control assessment questionnaire in the data store
	 */
	public ControlAssessmentQuestionnaire getControlAssessmentQuestionnaire(long questionnaireId, long tenantId) throws DaoException;
	
	/**
	 * Lists control assessment questionnaire in the data store.
	 * 
	 * @param tenantId the tenant ID of the tenant whose control assessment to find
	 * @return List of Control Assessment questionnaire
	 * @throws DaoException if error occurs while reading control assessment questionnaire from the data store
	 */
	public List<ControlAssessmentQuestionnaire> listControlAssessmentQuestionnaire(long tenantId) throws DaoException;
	
	/**
	 * Lists control assessment questionnaire.
	 * 
	 * @param questionnaireId the control assessment questionnaire ID to find
	 * @param tenantId the tenant ID of the tenant whose control assessment questionnaire to find
	 * @return List of Control Assessment questionnaire that were found
	 * @throws DaoException if error occurs while finding control assessment questionnaire in the data store
	 */
	public List<ControlAssessmentQuestionnaire> listControlAssessmentQuestionnaire(long questionnaireId, long tenantId) throws DaoException;	
		
	/**
	 * Updates Control Assessment questionnaire.
	 * 
	 * @param questionnaire the control assessment questionnaire to update
	 * @return true if the control assessment questionnaire is update successfully, false otherwise
	 * @throws DaoException if error occurs while updating a control assessment questionnaire in the data store
	 */
	public boolean updateControlAssessmentQuestionnaire(ControlAssessmentQuestionnaire questionnaire) throws DaoException; 	
	
}
