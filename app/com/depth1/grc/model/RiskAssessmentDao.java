package com.depth1.grc.model;

import java.util.List;

public interface RiskAssessmentDao {

<<<<<<< HEAD
	public void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException;
	public boolean updateRiskAssessment() throws DaoException;
	public boolean deleteRiskAssessment() throws DaoException;
	public void viewRiskAssessment() throws DaoException;
	public RiskAssessment findRiskAssessment() throws DaoException;
	public List<RiskAssessment> listRiskAssessment(); 
}
=======
    void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException;
    boolean updateRiskAssessment(final RiskAssessment riskAssessment) throws DaoException;
    boolean deleteRiskAssessment(int riskAssessmentId) throws DaoException;
    void viewRiskAssessment(int riskAssessmentId) throws DaoException;
    RiskAssessment findRiskAssessment(int riskAssessmentId) throws DaoException;
    List<RiskAssessment> findAll() throws DaoException; //not type void, some list of riskAssessments
}
>>>>>>> origin/Team-B
