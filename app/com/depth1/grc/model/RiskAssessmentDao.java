package com.depth1.grc.model;

public interface RiskAssessmentDao {

	void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException;
	boolean updateRiskAssessment() throws DaoException;
	boolean deleteRiskAssessment() throws DaoException;
	void viewRiskAssessment() throws DaoException;
	RiskAssessment findRiskAssessment() throws DaoException;
	void findAll(); //not type void, some list of riskAssessments
}
