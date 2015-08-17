package com.depth1.grc.model;

import java.util.List;

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
     * @throws DaoException if update failed
     */
	public void updateRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

    /**
     * Deletes selected Risk Assessment
     * @param riskAssessment to be deleted
     * @throws DaoException error if deletion failed
     */
	public void deleteRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

    /**
     * Lists all of the Risk Assessments in the database
     * @return a list containing all Risk Assessments
     * @throws DaoException if failed to retrieve list of Risk Assessments
     */
	public List<RiskAssessment> listRiskAssessment() throws DaoException;
}