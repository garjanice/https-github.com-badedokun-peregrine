package com.depth1.grc.model;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;
import play.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

public class CassandraRiskAssessmentDao implements RiskAssessmentDao {

    /**
     * Creates new Risk Assessment
     * Assigns the inputted fields to a new Risk Assessment form.
     * @param riskAssessment form
     * @throws DaoException error if insertion to database fails.
     */
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

    /**
     * Updates the selected Risk Assessment with newly inputted data fields by the user.
     * Replaces old Risk Assessment with the new.
     * @param riskAssessment form is passed in to be updated.
     * @return true if updated successfully.
     * @throws DaoException error if update failed
     */
	@Override
	public boolean updateRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
        boolean update = false;
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Update.Assignments updateRA = QueryBuilder
                    .update("grc", "riskassessment")
                    .with(set("risk", riskAssessment.getRisk()))
                    .and(set("severity", riskAssessment.getSeverity()))
                    .and(set("severity_description", riskAssessment.getSeverityDescription()))
                    .and(set("likelihood", riskAssessment.getLikelihood()))
                    .and(set("likelihood_description", riskAssessment.getLikelihoodDescription()))
                    .and(set("red", riskAssessment.getMatrixRed()))
                    .and(set("yellow", riskAssessment.getMatrixYellow()))
                    .and(set("light_green", riskAssessment.getMatrixLightGreen()))
                    .and(set("green", riskAssessment.getMatrixGreen())) 
                    .and(set("vulnerability", riskAssessment.getVulnerability()))
                    .and(set("onset_speed", riskAssessment.getSpeedOfOnset()))
                    .and(set("impact", riskAssessment.getImpact()))
                    .and(set("opportunity", riskAssessment.getOpportunity()))
                    .and(set("trigger_event", riskAssessment.getTriggerEvent()))
                    .and(set("risk_factor", riskAssessment.getRiskFactor()))
                    .and(set("consequence", riskAssessment.getConsequence()));

            Statement updateDetails = updateRA
                    .where(eq("assessmentid", riskAssessment.getAssessmentId()));

            dbSession.execute(updateDetails);
            update = true;
        } catch (DriverException e) {
            Logger.error("Error occurred while attempting to update Risk Assessment ", e);
        } finally {
            CassandraDaoFactory.close(dbSession);
        }
        return update;
	}

    /**
     * Prompts the user if they want to delete the selected Risk Assessment.
     * @param riskAssessment to be deleted.
     * @return true if the deletion was successful.
     * @throws DaoException error if deletion failed.
     */
	@Override
	public boolean deleteRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
        boolean del = false;
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Delete.Where delete = QueryBuilder.delete().from("grc", "riskassessment")
                    .where(eq("id", riskAssessment.getAssessmentId()));
            dbSession.execute(delete);
            del = true;
        } catch (DriverException e) {
            Logger.error("Error occurred while attempting to delete Risk Assessment", e);
        } finally {
            CassandraDaoFactory.close(dbSession);
        }
        return del;
	}

    /**
     * Lists all of the Risk Assessments on the front-end UI
     * @return List containing all Risk Assessments
     * @throws DaoException error if unable to retrieve list of Risk Assessment
     */
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
