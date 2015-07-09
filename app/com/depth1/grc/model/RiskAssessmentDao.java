package com.depth1.grc.model;

import java.util.List;

public interface RiskAssessmentDao {

	public void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException;
	public boolean updateRiskAssessment() throws DaoException;
	public boolean deleteRiskAssessment() throws DaoException;
	public void viewRiskAssessment() throws DaoException;
	public RiskAssessment findRiskAssessment() throws DaoException;
	public List<RiskAssessment> listRiskAssessment(); 
}
