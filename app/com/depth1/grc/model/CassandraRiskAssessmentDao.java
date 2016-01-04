package com.depth1.grc.model;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;
import com.depth1.grc.exception.DaoException;

import play.Logger;

public class CassandraRiskAssessmentDao implements RiskAssessmentDao {

    /**
     * This method is called when the user clicks on the 'Create' button.
     * Assigns the inputted fields to a new Risk Assessment form.
     * @param riskAssessment form
     * @throws DaoException error if insertion to database fails.
     */
	@Override
	//not complete
	public void createRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
		try {					
			Statement insert = QueryBuilder
					.insertInto("grc", "riskassessment")
					.value("id", UUID.randomUUID()) //need to change this to not be random
					.value("tenantid", riskAssessment.getTenantId()) 
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
			CassandraDaoFactory.getSession().execute(insert);
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} finally {			
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}		
	}

    /**
     * This method updates the selected Risk Assessment with newly inputted data fields by the user.
     * Replaces old Risk Assessment with the new.
     * @param riskAssessment form is passed in to be updated.
     * @return true if updated successfully.
     * @throws DaoException error if update failed
     */
	@Override
	public boolean updateRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
        boolean update = false;
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
                    .where(eq("id", riskAssessment.getAssessmentId()));

            CassandraDaoFactory.getSession().execute(updateDetails);
            update = true;
        } catch (DriverException e) {
            Logger.error("Error occurred while attempting to update Risk Assessment ", e);
        } finally {
            CassandraDaoFactory.close(CassandraDaoFactory.getSession());
        }
        return update;
	}

    /**
     * This method is called when the 'Delete' button is clicked and prompts the user if they want to
     * delete the selected Risk Assessment.
     * @param riskAssessment to be deleted.
     * @return true if the deletion was successful.
     * @throws DaoException error if deletion failed.
     */
	@Override
	public boolean deleteRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
        boolean del = false;
        try {
            Delete.Where delete = QueryBuilder.delete().from("grc", "riskassessment")
                    .where(eq("id", riskAssessment.getAssessmentId()));
            CassandraDaoFactory.getSession().execute(delete);
            del = true;
        } catch (DriverException e) {
            Logger.error("Error occurred while attempting to delete Risk Assessment", e);
        } finally {
            CassandraDaoFactory.close(CassandraDaoFactory.getSession());
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

    /**
     * Lists all of the Risk Assessments on the front-end UI
     * @return List containing all Risk Assessments
     * @throws DaoException error if unable to retrieve list of Risk Assessment
     */
	@Override
	public List<RiskAssessment> listRiskAssessment() throws DaoException {
        List<RiskAssessment> listRA = new ArrayList<>();
        try {
            Statement listAllRA = QueryBuilder.select().all()
                    .from("grc", "riskassessment");

            ResultSet result = CassandraDaoFactory.getSession().execute(listAllRA);
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
            CassandraDaoFactory.close(CassandraDaoFactory.getSession());
        }

        return listRA;
	}
	
	
	/**
     * Lists all of the Risk Assessments on the front-end UI
     * @return List containing all Risk Assessments
     * @throws DaoException error if unable to retrieve list of Risk Assessment
     */
	@Override
	public List<RiskAssessment> listRiskAssessmentPagination(int numberOfItems, int page) throws DaoException {
        List<RiskAssessment> listRA = new ArrayList<>();
        List<RiskAssessment> listRAShort = new ArrayList<>();
        try {
            Statement listAllRA = QueryBuilder.select().all()
                    .from("grc", "riskassessment");
            ResultSet result = CassandraDaoFactory.getSession().execute(listAllRA);

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
            CassandraDaoFactory.close(CassandraDaoFactory.getSession());
        }
        int start = numberOfItems * (page - 1);
        int end = numberOfItems * (page) - 1;
        for(int x= start; x <= end && x < listRA.size(); x++){
        	listRAShort.add(listRA.get(x));
        }
        
        return listRAShort;
	}

}
