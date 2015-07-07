package com.depth1.grc.model;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.sun.rowset.internal.Row;
import jdk.nashorn.internal.objects.annotations.Where;
import play.Logger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CassandraRiskAssessmentDao implements RiskAssessmentDao {

	@Override
	//not complete
	public void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement insert = QueryBuilder
					.insertInto("grc", "riskAssessment")
					.value("id", UUID.randomUUID()) //need to change this to not be random
					// .value("tenantid", fillThis) needs to be some random text?
					// .value("assessmentid", fillThis) needs to be some random int
					.value("severity", riskAssessment.getSeverity())
					.value("severity_description", riskAssessment.getSeverityDescription())
					.value("likelihood", riskAssessment.getLikelihood())
					.value("likelihood_description", riskAssessment.getLikelihoodDescription())
					.value("red", riskAssessment.getMatrixRed())
					.value("yellow", riskAssessment.getMatrixYellow())
					.value("light_green", riskAssessment.getMatrixLightGreen())
					.value("green", riskAssessment.getMatrixGreen())
					.value("vulnerability", riskAssessment.getVulnerability())
					.value("risk", riskAssessment.getRisk())
					.value("onset_speed", riskAssessment.getSpeedOfOnset())
					.value("impact", riskAssessment.getImpact())
					.value("opportunity", riskAssessment.getOpportunity())
					.value("trigger_event", riskAssessment.getTriggerEvent())
					.value("risk_factor", riskAssessment.getRiskFactor())	
					.value("consequence", riskAssessment.getConsequence());					
					dbSession.execute(insert);
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting risk assessment into database ", e);
		} finally {			
			CassandraDaoFactory.close(dbSession);
		}		
	}		

	@Override
	public boolean updateRiskAssessment(final RiskAssessment riskAssessment)
    throws DaoException {
        boolean update = false;
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Update.Assignments updateAssignments = QueryBuilder
                    .update("grc", "riskAssessment")
                    .with(set("riskAssessmentId", riskAssessment.getAssessmentId()))
                    .and(set("severity", riskAssessment.getSeverity()))
                    .and(set("severityDescription", riskAssessment.getSeverityDescription()))
                    .and(set("likelihood", riskAssessment.getLikelihood()))
                    .and(set("likelihoodDescription", riskAssessment.getLikelihoodDescription()))
                    .and(set("matrixRed", riskAssessment.getMatrixRed()))
                    .and(set("matrixYellow", riskAssessment.getMatrixYellow()))
                    .and(set("matrixLightGreen", riskAssessment.getMatrixLightGreen()))
                    .and(set("matrixGreen", riskAssessment.getMatrixGreen()))
                    .and(set("vulnerability", riskAssessment.getVulnerability()))
                    .and(set("risk", riskAssessment.getRisk()))
                    .and(set("speedOfOnset", riskAssessment.getSpeedOfOnset()))
                    .and(set("impact", riskAssessment.getImpact()))
                    .and(set("opportunity", riskAssessment.getOpportunity()))
                    .and(set("triggerEvent", riskAssessment.getTriggerEvent()))
                    .add(set("riskFactor", riskAssessment.getRiskFactor()))
                    .add(set("consequence", riskAssessment.getConsequence()));

            Statement updateDetails = updateAssignments
                    .where(eq("riskAssessmentId", riskAssessment.getAssessmentId()));

            dbSession.execute(updateDetails);
            update = true;
        } catch (DriverException e) {
            Logger.error("Error occurred while updating risk assessment in database", e);
        } finally {
            //dbSession.close();
            CassandraDaoFactory.close(dbSession);
        }
        return update;
	}

	@Override
	public boolean deleteRiskAssessment(int riskAssessmentId) throws DaoException {
        boolean del = false;
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Where delete = QueryBuilder.delete()
                    .from("grc", "riskAssessment")
                    .where(eq("riskAssessmentId", riskAssessmentId));

            dbSession.execute(delete);
            del = true;
        } catch (DriverException e) {
            Logger.error("Error occurred while deleting risk assessment from database ", e);
        } finally {
            //dbSession.close();
            CassandraDaoFactory.close(dbSession);
        }
        return del;
	}

	@Override
	public void viewRiskAssessment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RiskAssessment findRiskAssessment(int riskAssessmentId) {
        RiskAssessment riskAssessment = new RiskAssessment();
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Statement find = QueryBuilder.select().all()
                    .from("grc", "riskAssessment")
                    .where(eq("riskAssessmentId", riskAssessmentId));
            ResultSet result = dbSession.execute(find);
            if (result == null) {
                return null;
            }
            Row row = result.one();

            // get data elements from the Result set

            riskAssessment.setAssessmentId(row.getString("riskAssessmentId"));
            riskAssessment.setSeverity(row.getFloat("severity"));
            riskAssessment.setSeverityDescription(row.getString("severityDescription"));
            riskAssessment.setLikelihood(row.getFloat("likelihood"));
            riskAssessment.setLikelihoodDescription(row.getString("likelihoodDescription"));
            riskAssessment.setMatrixRed(row.getString("matrixRed"));
            riskAssessment.setMatrixYellow(row.getString("matrixYellow"));
            riskAssessment.setMatrixLightGreen(row.getString("matrixLightGreen"));
            riskAssessment.setMatrixGreen(row.getString("matrixGreen"));
            riskAssessment.setVulnerability(row.getFloat("vulnerability"));
            riskAssessment.setRisk(row.getString("risk"));
            riskAssessment.setSpeedOfOnset(row.getFloat("speedOfOnset"));
            riskAssessment.setImpact(row.getFloat("impact"));
            riskAssessment.setOpportunity(row.getString("opportunity"));
            riskAssessment.setTriggerEvent(row.getString("triggerEvent"));
            riskAssessment.setRiskFactor(row.getString("riskFactor"));
            riskAssessment.setConsequence(row.getString("consequence"));

        } catch (DriverException e) {
            Logger.error("Error occurred while retrieving risk assessment from database.", e);
        } finally {
            //dbSession.close();
            CassandraDaoFactory.close(dbSession);
        }
        return riskAssessment;
	}

	@Override
	public List<RiskAssessment> findAll() throws DaoException {
        List<RiskAssessment> listOfRA = new ArrayList<>();
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Statement listAll = QueryBuilder.select().all()
                    .from("grc", "riskAssessment");

            ResultSet result = dbSession.execute(listAll);
            if (result == null) {
                return null;
            }

            // get all data elements from the Result set

            for (Row row : result.all()) {
                RiskAssessment riskAssessment = new RiskAssessment();
                riskAssessment.setAssessmentId(row.getString("riskAssessmentId"));
                riskAssessment.setSeverity(row.getFloat("severity"));
                riskAssessment.setSeverityDescription(row.getString("severityDescription"));
                riskAssessment.setLikelihood(row.getFloat("likelihood"));
                riskAssessment.setLikelihoodDescription(row.getString("likelihoodDescription"));
                riskAssessment.setMatrixRed(row.getString("matrixRed"));
                riskAssessment.setMatrixYellow(row.getString("matrixYellow"));
                riskAssessment.setMatrixLightGreen(row.getString("matrixLightGreen"));
                riskAssessment.setMatrixGreen(row.getString("matrixGreen"));
                riskAssessment.setVulnerability(row.getFloat("vulnerability"));
                riskAssessment.setRisk(row.getString("risk"));
                riskAssessment.setSpeedOfOnset(row.getFloat("speedOfOnset"));
                riskAssessment.setImpact(row.getFloat("impact"));
                riskAssessment.setOpportunity(row.getString("opportunity"));
                riskAssessment.setTriggerEvent(row.getString("triggerEvent"));
                riskAssessment.setRiskFactor(row.getString("riskFactor"));
                riskAssessment.setConsequence(row.getString("consequence"));
                listOfRA.add(riskAssessment);
                result.iterator();
            }
        } catch (DriverException e) {
            Logger.error("Error occurred while retrieving all risk assessment from database ", e);
        } finally {
            //dbSession.close();
            CassandraDaoFactory.close(dbSession);
        }
        return listOfRA;
	}

}
