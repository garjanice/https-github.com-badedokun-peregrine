package com.depth1.grc.model;

import java.util.List;

public interface RiskAssessmentDao {

	void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException;
	boolean updateRiskAssessment(int riskAssessmentId) throws DaoException;
	boolean deleteRiskAssessment(int riskAssessmentId) throws DaoException;
	void viewRiskAssessment(int riskAssessmentId) throws DaoException;
	RiskAssessment findRiskAssessment(int riskAssessmentId) throws DaoException;
	List<RiskAssessment> findAll(); //not type void, some list of riskAssessments
}
