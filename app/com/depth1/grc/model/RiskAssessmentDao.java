package com.depth1.grc.model;

import java.util.List;

/**
 * Interface for Risk Assessment DAO's.
 */
public interface RiskAssessmentDao {

    /**
     * Create a new Risk Assessment
     * @param riskAssessment form with inputted data from user
     * @throws DaoException if failed to create Risk Assessment
     */
	public void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

    /**
     * Update selected Risk Assessment
     * @param riskAssessment to be updated
     * @return updated Risk Assessment and deletes the old Risk Assessment
     * @throws DaoException if update failed
     */
	public boolean updateRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

    /**
     * Delete selected Risk Assessment
     * @param riskAssessment to be deleted
     * @return true if deletion was successful
     * @throws DaoException error if deletion failed
     */
	public boolean deleteRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

    /**
     * List all of the Risk Assessments in the database
     * @return a list containing all Risk Assessments
     * @throws DaoException if failed to retrieve list of Risk Assessments
     */
	public List<RiskAssessment> listRiskAssessment() throws DaoException;
}