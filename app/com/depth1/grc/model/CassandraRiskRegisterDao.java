package com.depth1.grc.model;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Delete.Where;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;

import play.Logger;
import play.Play;
/**
 * This class is used to implement methods declared in RiskRegisterDao interface. 
 * Achieves basic data processing: Add, view, update and find specified data to and from DB.
 */
public class CassandraRiskRegisterDao implements RiskRegisterDao {

	public RiskRegister register;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//private final static Boolean keyspace = Play.application().configuration().getBoolean("onpremise.deploy.model");
	
	public CassandraRiskRegisterDao() {
		super();
	}
	
	/**
	 * Register new risk and insert a record into "grc" Keyspace, riskregister table.
	 * @param register This object to obtain attributes to map column items
	 * @throws DaoException if insertion to database failed
         * @throws DriverException if a unsuccessful pool connection occurs 
	 */	
	public void createRiskRegister(RiskRegister register) throws DaoException{
		
		try {					
			Statement insert = QueryBuilder
					.insertInto("grc", "riskregister")
					.value("id",UUID.randomUUID())
					//.value("tenantId", register.gettenantId())
					//.value("riskID",register.getriskId())
					.value("name",register.getName())
					.value("owner",register.getOwner())
					.value("status",register.getStatus())
					.value("category_rating",register.getCategory_rating())
					.value("reporting_level",register.getReporting_level())
					.value("description",register.getDescription())
					.value("impact_description",register.getImpact_description())
					.value("probability",register.getProbability())
					.value("priority",register.getPriority())
					.value("impact",register.getImpact())
					//.value("impact_date",register.getimpact_date())
					.value("impact_date",register.getSystemDateTime())
					.value("score",register.getScore())
					.value("resolution",register.getResolution())
					//.value("target_resolution_date",register.gettarget_resolution_date())
					.value("target_resolution_date",sdf.format(register.getTarget_resolution_date()))
					//.value("actual_resolution_date",register.getactual_resolution_date())
					.value("response_type",register.getResponse_type())
					.value("associated_risk",register.getAssociated_risk())
					.value("associated_issue",register.getAssociated_issue())
					.value("risk_creator",register.getRisk_creator())
					.value("assumption",register.getAssumption())
					.value("symptom",register.getSymptom());
			CassandraDaoFactory.getSession().execute(insert);
		} 
		
		catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} 
		
		finally {			
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}		
		
}		
	
	/**
	 * List all the registered risks stored in "grc" Keyspace, riskregister table.
	 * @return the list of all registered risks stored in riskregister table
	 * @throws DaoException if demonstration of risks list failed
         * @throws DriverException if a unsuccessful pool connection occurs  
	 */
	public List<RiskRegister> listRegister() throws DaoException {
		
		List<RiskRegister> list = new ArrayList<>();
		
		try {
			Statement listAll = QueryBuilder.select().all()
					.from("grc", "riskregister");
			
			ResultSet result = CassandraDaoFactory.getSession().execute(listAll);
			if (result == null) {
				
				return null;
			}
			
			for (Row row : result.all()) {
				RiskRegister register=new RiskRegister();
				register.setId(row.getUUID("id"));
				//Note: this next line was commented out for some reason
				//register.setriskId(row.getInt("riskId"));
				//register.settenantId(row.getInt("tenantId"));
				register.setName(row.getString("name"));
				register.setOwner(row.getString("owner"));
				register.setStatus(row.getString("status"));
				register.setCategory_rating(row.getString("category_rating"));
				register.setReporting_level(row.getString("reporting_level"));
				register.setDescription(row.getString("description"));
				register.setImpact_description(row.getString("impact_description"));
				register.setProbability(row.getString("probability"));
				register.setPriority(row.getString("priority"));
				register.setImpact(row.getString("impact"));
				//register.setimpact_date(row.getDate("impact_date"));
				register.setScore(row.getFloat("score"));
				//register.settarget_resolution_date(row.getDate("target_resolution_date"));
				//register.setactual_resolution_date(row.getDate("actual_resolution_date"));
				register.setResponse_type(row.getString("response_type"));
				register.setAssociated_risk(row.getString("associated_risk"));
				register.setAssociated_issue(row.getString("associated_issue"));
				register.setRisk_creator(row.getString("risk_creator"));
				register.setAssumption(row.getString("assumption"));
				register.setSymptom(row.getString("symptom"));
				list.add(register);
				result.iterator();
				}
	        }
		catch (DriverException e) {
			Logger.error("Error occurred while retrieving from database ", e);
		} 
	
		finally {			
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
			}
		return list;
	}
	
	/**
	 * Update an existed specified risk stored in "grc" Keyspace, riskregister table.
	 * @param register This object to obtain attributes to map column items
	 * @return the success or failure status of updating a record in DB
	 * @throws DaoException if update failed
         * @throws DriverException if a unsuccessful pool connection occurs
	 */
	public boolean updateRiskRegister(RiskRegister register){
		boolean update= false;
		
		try {					
			Update.Assignments updateAssignments = QueryBuilder
					.update("grc", "riskregister")
					.with(set("tenantId",register.getTenantId()))
					.and(set("name", register.getName()))
					.and(set("owner",register.getOwner()))
					.and(set("status",register.getStatus()))
					.and(set("category_rating",register.getCategory_rating()))
					.and(set("reporting_level",register.getReporting_level()))
					.and(set("description",register.getDescription()))
					.and(set("impact_description",register.getImpact_description()))
					.and(set("probability",register.getProbability()))
					.and(set("priority",register.getPriority()))
					.and(set("impact",register.getImpact()))
					.and(set("score",register.getScore()))
					.and(set("resolution",register.getResolution()))
					//.and(set("target_resolution_date",register.gettarget_resolution_date()))
					//.and(set("actual_resolution_date",register.getactual_resolution_date()))
					.and(set("response_type",register.getResponse_type()))
					.and(set("associated_risk",register.getAssociated_risk()))
					.and(set("associated_issue",register.getAssociated_issue()))
					.and(set("risk_creator",register.getRisk_creator()))
					.and(set("last_updated_person",register.getLast_updated_person()))
					.and(set("last_updated_desc",register.getLast_updated_desc()))
					.and(set("last_updated",register.getLast_updated()))
					.and(set("assumption",register.getAssumption()))
					.and(set("symptom",register.getSymptom()));
					
					Statement updateDetails = updateAssignments
					.where(eq("id", register.getId()));							

			CassandraDaoFactory.getSession().execute(updateDetails);
			update = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while updating data in the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return update;
	}
	
	/**
	 * Query a specified risk in the Keyspace "grc", table "riskregister" by risk ID.
	 * @param riskId the primary key ID to query to get corresponding risk record.
	 * @return the Register object stored in riskregister table
	 * @throws DaoException if a finding a specified risk failed
         * @throws DriverException if a unsuccessful pool connection occurs
	 */
	public RiskRegister findRegister(int riskId){
		RiskRegister register=new RiskRegister();
		
		try {					
			Statement find = QueryBuilder.select().all()
					.from("grc", "riskregister")
					.where(eq("riskId", riskId));

			ResultSet result = CassandraDaoFactory.getSession().execute(find);
			if (result == null) {
				return null;
			}
			Row row = result.one();
			
			register.setId(row.getUUID("id"));
			//register.setriskId(row.getInt("riskId"));
			//register.settenantId(row.getInt("tenantId"));
			register.setName(row.getString("name"));
			register.setOwner(row.getString("owner"));
			register.setStatus(row.getString("status"));
			register.setCategory_rating(row.getString("category_rating"));
			register.setReporting_level(row.getString("reporting_level"));
			register.setDescription(row.getString("description"));
			register.setImpact_description(row.getString("impact_description"));
			register.setProbability(row.getString("probability"));
			register.setPriority(row.getString("priority"));
			register.setImpact(row.getString("impact"));
			//register.setimpact_date(row.getDate("impact_date"));
			register.setScore(row.getFloat("score"));
			//register.settargetresolutiondate(row.getDate("target_resolution_date"));
			//register.setactualresolutiondate(row.getDate("actual_resolution_date"));
			register.setResponse_type(row.getString("response_type"));
			register.setAssociated_risk(row.getString("associated_risk"));
			register.setAssociated_issue(row.getString("associated_issue"));
			register.setRisk_creator(row.getString("risk_creator"));
			//register.setlast_updated_person(row.getString("last_updated_person"));
			//register.setlast_updated_desc(row.getString("last_updated_desc"));
			//register.setlast_updated(row.getDate("last_updated"));
			register.setAssumption(row.getString("assumption"));
			register.setSymptom(row.getString("symptom"));
	}
		catch (DriverException e) {
			Logger.error("Error occurred while retrieving data from the risk register table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return register;
	}
	
	public boolean deleteRiskRegister(RiskRegister register) throws DaoException {
        boolean del = false;
        try {
            Delete.Where delete = QueryBuilder.delete().from("grc", "riskassessment")
                    .where(eq("id", register.getId()));
            CassandraDaoFactory.getSession().execute(delete);
            del = true;
        } catch (DriverException e) {
            Logger.error("Error occurred while attempting to delete Risk Assessment", e);
        } finally {
            CassandraDaoFactory.close(CassandraDaoFactory.getSession());
        }
        return del;
	}
}