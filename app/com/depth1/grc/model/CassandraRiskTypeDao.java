package com.depth1.grc.model;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;

import play.Logger;

public class CassandraRiskTypeDao implements RiskTypeDao {

	@Override
	public void createRiskType(RiskType riskType) throws DaoException {
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement insert = QueryBuilder
					.insertInto("grc", "risktype")
					.value("id", UUID.randomUUID())
					.value("risk_id", riskType.getRiskId())
					.value("risk_type", riskType.getRiskType());
					dbSession.execute(insert);
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} finally {			
			CassandraDaoFactory.close(dbSession);
		}		
	}
		
	@Override
	public void updateRiskType(RiskType riskType) throws DaoException {
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Update.Assignments updateRA = QueryBuilder
                    .update("grc", "riskType")
                    .with(set("risk_id", riskType.getRiskId()))
                    .and(set("risk_type", riskType.getRiskType()));

            Statement updateDetails = updateRA
                    .where(eq("assessmentid", riskType.getId()));

            dbSession.execute(updateDetails);
        } catch (DriverException e) {
            Logger.error("Error occurred while attempting to update Risk Assessment ", e);
        } finally {
            CassandraDaoFactory.close(dbSession);
        }
	}
		

	
	@Override
	public void deleteRiskType(RiskType riskType) throws DaoException {
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Delete.Where delete = QueryBuilder.delete().from("grc", "risktype")
                    .where(eq("id", riskType.getId()));
            dbSession.execute(delete);
        } catch (DriverException e) {
            Logger.error("Error occurred while attempting to delete Risk Assessment", e);
        } finally {
            CassandraDaoFactory.close(dbSession);
        }
	}
		

	@Override
	public List<RiskType> listRiskType() throws DaoException {
        List<RiskType> listRT = new ArrayList<>();
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Statement listAllRT = QueryBuilder.select().all()
                    .from("grc", "riskType");

            ResultSet result = dbSession.execute(listAllRT);
            if (result == null) {
                return null;
            }

            for (Row row : result.all()) {
                RiskType riskType = new RiskType();
                riskType.setId(row.getUUID("id"));
                riskType.setRiskId(row.getInt("riskId"));
                riskType.setRiskType(row.getString("risk_type"));
                listRT.add(riskType);
                result.iterator();
            }
        } catch (DriverException e) {
            Logger.error("Error occurred while retrieving list of Risk Assessments from database ", e);
        } finally {
            CassandraDaoFactory.close(dbSession);
        }

        return listRT;
	}

}
