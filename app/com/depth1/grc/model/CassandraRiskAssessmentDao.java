package com.depth1.grc.model;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import play.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

public class CassandraRiskAssessmentDao implements RiskAssessmentDao {

	@Override
	//not complete
	public void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement insert = QueryBuilder
					.insertInto("grc", "riskassessment")
					.value("id", UUID.randomUUID()) //need to change this to not be random
					// .value("tenantid", fillThis) needs to be some random text?
					// .value("assessmentid", fillThis) needs to be some random int
					.value("risk", riskAssessment.getRisk())
					.value("severity", riskAssessment.getSeverity())
					.value("severity_description", riskAssessment.getSeverityDescription())
					.value("likelihood", riskAssessment.getLikelihood())
					.value("likelihood_description", riskAssessment.getLikelihoodDescription())
					.value("red", riskAssessment.getMatrixRed())
					.value("yellow", riskAssessment.getMatrixYellow())
					.value("light_green", riskAssessment.getMatrixLightGreen())
					.value("green", riskAssessment.getMatrixGreen())
					.value("vulnerability", riskAssessment.getVulnerability())
					.value("onset_speed", riskAssessment.getSpeedOfOnset())
					.value("impact", riskAssessment.getImpact())
					.value("opportunity", riskAssessment.getOpportunity())
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
	public boolean deleteRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
        boolean del = false;
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Delete.Where delete = QueryBuilder.delete().from("grc", "riskassessment")
                    .where(eq("assessmentid", riskAssessment.getAssessmentId()));
            dbSession.execute(delete);
            del = true;
        } catch (DriverException e) {
            Logger.error("Error occurred while attempting to delete Risk Assessment", e);
        } finally {
            CassandraDaoFactory.close(dbSession);
        }
        return del;
	}

	/*
    Shelving the viewRA() function for now; unsure if needed due to unnecessary process.
    Leaving uncommented just in case.
     */
//    @Override
//    public void viewRiskAssessment(RiskAssessment riskAssessment) {
//        Session dbSession = CassandraDaoFactory.connect();
//        try {
//            Statement viewRA = QueryBuilder.select().from("grc", "riskassessment")
//                    .where(eq("assessmentId", riskAssessment.getAssessmentId()));
//            ResultSet result = dbSession.execute(viewRA);
//            Row row = result.one();
//
//            riskAssessment.setAssessmentId(row.getUUID("id"));
//            riskAssessment.setRisk(row.getString("risk"));
//            riskAssessment.setSeverity(row.getFloat("severity"));
//            riskAssessment.setSeverityDescription(row.getString("severity_description"));
//            riskAssessment.setLikelihood(row.getFloat("likelihood"));
//            riskAssessment.setLikelihoodDescription(row.getString("likelihood_description"));
//            riskAssessment.setMatrixRed(row.getString("red"));
//            riskAssessment.setMatrixYellow(row.getString("yellow"));
//            riskAssessment.setMatrixLightGreen(row.getString("light_green"));
//            riskAssessment.setMatrixGreen(row.getString("green"));
//            riskAssessment.setVulnerability(row.getFloat("vulnerability"));
//            riskAssessment.setRisk(row.getString("risk"));
//            riskAssessment.setSpeedOfOnset(row.getFloat("onset_speed"));
//            riskAssessment.setImpact(row.getFloat("impact"));
//            riskAssessment.setOpportunity(row.getString("opportunity"));
//            riskAssessment.setTriggerEvent(row.getString("trigger_event"));
//            riskAssessment.setRiskFactor(row.getString("risk_factor"));
//            riskAssessment.setConsequence(row.getString("consequence"));
//        } catch (DriverException e) {
//            Logger.error("Error occurred while attempting to view Risk Assessment ", e);
//        } finally {
//            CassandraDaoFactory.close(dbSession);
//        }
//    }

	@Override
	public RiskAssessment findRiskAssessment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RiskAssessment> listRiskAssessment() throws DaoException {
        List<RiskAssessment> listRA = new ArrayList<>();
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Statement listAllRA = QueryBuilder.select().all()
                    .from("grc", "riskassessment");

            ResultSet result = dbSession.execute(listAllRA);
            if (result == null) {
                return null;
            }

            for (Row row : result.all()) {
                RiskAssessment riskAssessment = new RiskAssessment();
                riskAssessment.setAssessmentId(row.getUUID("id"));
                riskAssessment.setRisk(row.getString("risk"));
                riskAssessment.setSeverity(row.getFloat("severity"));
                riskAssessment.setSeverityDescription(row.getString("severity_description"));
                riskAssessment.setLikelihood(row.getFloat("likelihood"));
                riskAssessment.setLikelihoodDescription(row.getString("likelihood_description"));
                riskAssessment.setMatrixRed(row.getString("red"));
                riskAssessment.setMatrixYellow(row.getString("yellow"));
                riskAssessment.setMatrixLightGreen(row.getString("light_green"));
                riskAssessment.setMatrixGreen(row.getString("green"));
                riskAssessment.setVulnerability(row.getFloat("vulnerability"));
                riskAssessment.setRisk(row.getString("risk"));
                riskAssessment.setSpeedOfOnset(row.getFloat("onset_speed"));
                riskAssessment.setImpact(row.getFloat("impact"));
                riskAssessment.setOpportunity(row.getString("opportunity"));
                riskAssessment.setTriggerEvent(row.getString("trigger_event"));
                riskAssessment.setRiskFactor(row.getString("risk_factor"));
                riskAssessment.setConsequence(row.getString("consequence"));
                listRA.add(riskAssessment);
                result.iterator();
            }
        } catch (DriverException e) {
            Logger.error("Error occurred while retrieving list of Risk Assessments from database ", e);
        } finally {
            CassandraDaoFactory.close(dbSession);
        }

        return listRA;
	}

}
