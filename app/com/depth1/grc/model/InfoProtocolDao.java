package com.depth1.grc.model;

import java.util.List;

/**
 * Interface for Risk Assessment DAO's.
 */
public interface InfoProtocolDao {

    /**
     * Creates a new Risk Assessment
     * @param riskAssessment form with inputted data from user
     * @throws DaoException if failed to create Risk Assessment
     */
	public void createInfoProtocol(InfoProtocol infoProtocol) throws DaoException;

    /**
     * Updates selected Risk Assessment
     * @param riskAssessment to be updated
     * @return updated Risk Assessment and deletes the old Risk Assessment
     * @throws DaoException if update failed
     */
	public boolean updateInfoProtocol(InfoProtocol infoProtocol) throws DaoException;

    /**
     * Deletes selected Risk Assessment
     * @param riskAssessment to be deleted
     * @return true if deletion was successful
     * @throws DaoException error if deletion failed
     */
	public boolean deleteInfoProtocol(InfoProtocol infoProtocol) throws DaoException;

    /**
     * Lists all of the Risk Assessments in the database
     * @return a list containing all Risk Assessments
     * @throws DaoException if failed to retrieve list of Risk Assessments
     */
	public List<InfoProtocol> listInfoProtocol() throws DaoException;
}