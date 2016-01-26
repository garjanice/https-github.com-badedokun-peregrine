/**
 * 
 */
package com.depth1.grc.coso.models;

import java.util.List;

import com.depth1.grc.exception.DaoException;

/**
 * The Control Assessment Questionnaire interface defines the contract that all implementing classes must abide by
 * It provides API to expose to caller of classes that implement the interface.
 * @author Bisi Adedokun
 *
 */
public interface ControlAssessmentQuestionnaireDao {
	
	/**
	 * Creates a control assessment.
	 * 
	 * @param assessment the control assessment to create
	 * @throws DaoException if error occurs while creating the Control Assessment in the data store
	 */
	public void createControlAssessment(ControlPrinciple principle) throws DaoException;
	
	/**
	 * Deletes a control assessment.
	 * 
	 * @param assessmentId the control assessment ID to delete
	 * @param tenantId the tenant ID of the tenant whose control assessment to delete
	 * @return true if the Control Assessment is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a control assessment from the data store
	 */
	public boolean deleteControlAssessment(long assessmentId, long tenantId) throws DaoException;
	
	
	/**
	 * Finds a Control Assessment.
	 * 
	 * @param assessmentId the control assessment ID to find
	 * @param tenantId the tenant ID of the tenant whose control assessment to find
	 * @return Control Assessment that was found
	 * @throws DaoException if error occurs while finding control assessment in the data store
	 */
	public ControlPrinciple getControlAssessment(long assessmentId, long tenantId) throws DaoException;
	
	/**
	 * Finds a Control Assessment.
	 * 
	 * @param assessmentId the control assessment ID to find
	 * @return Control Assessment that was found
	 * @throws DaoException if error occurs while finding a control assessment in the data store
	 */
	public ControlPrinciple getControlAssessment(long assessmentId) throws DaoException;	

	
	/**
	 * Lists control assessment in the data store.
	 * 
	 * @param tenantId the tenant ID of the tenant whose control assessment to find
	 * @return List list of strategic objectives
	 * @throws DaoException if error occurs while reading control assessment from the data store
	 */
	public List<ControlPrinciple> listControlAssessment(long tenantId) throws DaoException;
	
	/**
	 * Lists control assessment.
	 * 
	 * @param assessmentId the control assessment ID to find
	 * @param tenantId the tenant ID of the tenant whose control assessment to find
	 * @return List of Control Assessment that were found
	 * @throws DaoException if error occurs while finding control assessment in the data store
	 */
	public List<ControlPrinciple> listControlAssessment(long assessmentId, long tenantId) throws DaoException;	
		
	/**
	 * Updates Control Assessment.
	 * 
	 * @param control the control assessment to update
	 * @return true if the control assessment is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a control assessment in the data store
	 */
	public boolean updateControlAssessment(ControlPrinciple control) throws DaoException; 	
	

}
