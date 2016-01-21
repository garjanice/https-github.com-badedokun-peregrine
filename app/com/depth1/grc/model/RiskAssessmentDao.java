package com.depth1.grc.model;

import java.util.List;

import com.depth1.grc.exception.DaoException;

/**
 * Interface for Risk Assessment DAO's.
 */
public interface RiskAssessmentDao {

    /**
     * Creates a new Risk Assessment
     * @param riskAssessment form with inputted data from user
     * @throws DaoException if failed to create Risk Assessment
     */
	public void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

    /**
     * Updates selected Risk Assessment
     * @param riskAssessment to be updated
     * @return updated Risk Assessment and deletes the old Risk Assessment
     * @throws DaoException if update failed
     */
	public boolean updateRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

    /**
     * Deletes selected Risk Assessment
     * @param riskAssessment to be deleted
     * @return true if deletion was successful
     * @throws DaoException error if deletion failed
     */
	public boolean deleteRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

    /**
     * Views selected Risk Assessment
     * @return void
     * @throws DaoException if viewing failed
     */
//	public void viewRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

	public RiskAssessment findRiskAssessment() throws DaoException;

    /**
     * Lists all of the Risk Assessments in the database
     * @return a list containing all Risk Assessments
     * @throws DaoException if failed to retrieve list of Risk Assessments
     */
	public List<RiskAssessment> listRiskAssessment() throws DaoException;

	
	/**
     * Lists some of the Risk Assessments in the database
     * @return a list containing all Risk Assessments
     * @throws DaoException if failed to retrieve list of Risk Assessments
     */
	public List<RiskAssessment> listRiskAssessmentPagination(int numberOfItems, int page) throws DaoException;

}