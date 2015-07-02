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
					.insertInto("grc", "riskassessment")
					.value("tenantid", riskAssessment.getTenantId())
					.value("assessmentid", riskAssessment.getAssessmentId())
					.value("severity", riskAssessment.getSeverity())
					.value("severity_description", riskAssessment.getSeverityDescription())
					.value("likelihood", riskAssessment.getLikelihood())
					.value("likelihood_description", riskAssessment.getLikelihoodDescription())
					.value("red", riskAssessment.getMatrixRed())
					.value("yellow", riskAssessment.getMatrixYellow())
					.value("light_green", riskAssessment.getMatrixLightGreen())
					.value("green", riskAssessment.getMatrixGreen())
					.value("vulnerabilty", riskAssessment.getVulnerabilty())
					.value("risk", riskAssessment.getRisk())
					.value("onset_speed", riskAssessment.getSpeedOfOnset())
					.value("impact", riskAssessment.getImpact())
					.value("oppurtunity", riskAssessment.getOppurtunity())
					.value("trigger_event", riskAssessment.getTriggerEvent())
					.value("risk_factor", riskAssessment.getRiskFactor())	
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
