package com.depth1.grc.model;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import play.Logger;

public class CassandraRiskAssessmentDao implements RiskAssessmentDao {

	@Override
	public void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement insert = QueryBuilder
					.insertInto("grc", "riskAssessment")
					.value("tentantId", riskAssessment.getTentantId())
					.value("assessmentId", riskAssessment.getAssessmentId())
					.value("severity", riskAssessment.getSeverity())
					.value("severityDescription", riskAssessment.getSeverityDescription())
					.value("likelihood", riskAssessment.getLikelihood())
					.value("likelihoodDescription", riskAssessment.getLikelihoodDescription())
					.value("matrixRed", riskAssessment.getMatrixRed())
					.value("matrixYellow", riskAssessment.getMatrixYellow())
					.value("matrixLightGreen", riskAssessment.getMatrixLightGreen())
					.value("matrixGreen", riskAssessment.getMatrixGreen())
					.value("vulnerabilty", riskAssessment.getVulnerabilty())
					.value("risk", riskAssessment.getRisk())
					.value("speedOfOnset", riskAssessment.getSpeedOfOnset())
					.value("impact", riskAssessment.getImpact())
					.value("oppurtunity", riskAssessment.getOppurtunity())
					.value("triggerEvent", riskAssessment.getTriggerEvent())
					.value("riskFactor", riskAssessment.getRiskFactor())	
					.value("consequence", riskAssessment.getConsequence());					
					dbSession.execute(insert);
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} finally {			
			CassandraDaoFactory.close(dbSession);
		}		
	}			
	

	@Override
	public boolean updateRiskAssessment() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRiskAssessment() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void viewRiskAssessment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RiskAssessment findRiskAssessment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findAll() {
		// TODO Auto-generated method stub
		return;
	}

}
