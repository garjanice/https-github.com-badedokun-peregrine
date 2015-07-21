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
import com.datastax.driver.core.querybuilder.Delete.Where;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;
//import com.depth1.grc.model.common.Keyspace;
import com.depth1.grc.model.Register;

import play.Logger;
import play.Play;
/**
 * This class is used to implementation of the exposed methods declared in RiskRegisterDao interface, 
 * achieves basic data processing. Add, view, update and find specified data to and from DB.
 * @throws DaoException if a failed file input-output exception, a faulty db connection occurs
 * @throws DriverException if a unsuccessful pool connection occurs 
 */
public class CassandraRiskRegisterDao implements RiskRegisterDao {

	public Register register;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//private final static Boolean keyspace = Play.application().configuration().getBoolean("onpremise.deploy.model");
	
	public CassandraRiskRegisterDao() {
		super();
	}
	
	/**
	 * This method registers new risk and inserts a record into the Keyspace called "grc", riskregister table.
	 * @param register This object to obtain attributes to map column items
	 * @throws InvalidQueryException if a mismatched type stored in a predefined data type column
	 */	
	public void createRiskRegister(Register register) throws DaoException{
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement insert = QueryBuilder
					.insertInto("grc", "riskregister")
					.value("id",UUID.randomUUID())
					//.value("tenantId", register.gettenantId())
					//.value("riskID",register.getriskId())
					.value("name",register.getname())
					.value("owner",register.getowner())
					.value("status",register.getstatus())
					.value("category_rating",register.getcategory_rating())
					.value("reporting_level",register.getreporting_level())
					.value("description",register.getdescription())
					.value("impact_description",register.getimpact_description())
					.value("probability",register.getprobability())
					.value("priority",register.getpriority())
					.value("impact",register.getimpact())
					//.value("impact_date",register.getimpact_date())
					.value("impact_date",register.getSystemDateTime())
					.value("score",register.getscore())
					.value("resolution",register.getresolution())
					//.value("target_resolution_date",register.gettarget_resolution_date())
					.value("target_resolution_date",sdf.format(register.gettarget_resolution_date()))
					//.value("actual_resolution_date",register.getactual_resolution_date())
					.value("response_type",register.getresponse_type())
					.value("associated_risk",register.getassociated_risk())
					.value("associated_issue",register.getassociated_issue())
					.value("risk_creator",register.getrisk_creator())
					.value("assumption",register.getassumption())
					.value("symptom",register.getsymptom());
					dbSession.execute(insert);
		} 
		
		catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} 
		
		finally {			
			CassandraDaoFactory.close(dbSession);
		}		
		
}		
	
	/**
	 * This method lists all the registered risks stored in the Keyspace called "grc", riskregister table.
	 * 
	 */
	public List<Register> listRegister() throws DaoException {
		List<Register> list = new ArrayList<>();
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement listAll = QueryBuilder.select().all()
					.from("grc", "riskregister");

			ResultSet result = dbSession.execute(listAll);
			if (result == null) {
				return null;
			}
			
			for (Row row : result.all()) {
				Register register=new Register();
				register.setid(row.getUUID("id"));
				//register.setriskId(row.getInt("riskId"));
				//register.settenantId(row.getInt("tenantId"));
				register.setname(row.getString("name"));
				register.setowner(row.getString("owner"));
				register.setstatus(row.getString("status"));
				register.setcategory_rating(row.getString("category_rating"));
				register.setreporting_level(row.getString("reporting_level"));
				register.setdescription(row.getString("description"));
				register.setimpact_description(row.getString("impact_description"));
				register.setprobability(row.getString("probability"));
				register.setpriority(row.getString("priority"));
				register.setimpact(row.getString("impact"));
				//register.setimpact_date(row.getDate("impact_date"));
				register.setscore(row.getFloat("score"));
				//register.settarget_resolution_date(row.getDate("target_resolution_date"));
				//register.setactual_resolution_date(row.getDate("actual_resolution_date"));
				register.setresponse_type(row.getString("response_type"));
				register.setassociated_risk(row.getString("associated_risk"));
				register.setassociated_issue(row.getString("associated_issue"));
				register.setrisk_creator(row.getString("risk_creator"));
				register.setassumption(row.getString("assumption"));
				register.setsymptom(row.getString("symptom"));
				list.add(register);
				result.iterator();
				}
	        }
		catch (DriverException e) {
			Logger.error("Error occurred while retrieving from database ", e);
		} 
	
		finally {			
			CassandraDaoFactory.close(dbSession);
			}
		return list;
	}
	
	/**
	 * This method update an existed specified risk stored in the Keyspace called "grc", riskregister table.
	 * @param register This object to obtain attributes to map column items
	 */
	public boolean updateRiskRegister(Register register){
		boolean update= false;
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Update.Assignments updateAssignments = QueryBuilder
					.update("grc", "riskregister")
					.with(set("tenantId",register.gettenantId()))
					.and(set("name", register.getname()))
					.and(set("owner",register.getowner()))
					.and(set("status",register.getstatus()))
					.and(set("category_rating",register.getcategory_rating()))
					.and(set("reporting_level",register.getreporting_level()))
					.and(set("description",register.getdescription()))
					.and(set("impact_description",register.getimpact_description()))
					.and(set("probability",register.getprobability()))
					.and(set("priority",register.getpriority()))
					.and(set("impact",register.getimpact()))
					.and(set("score",register.getscore()))
					.and(set("resolution",register.getresolution()))
					//.and(set("target_resolution_date",register.gettarget_resolution_date()))
					//.and(set("actual_resolution_date",register.getactual_resolution_date()))
					.and(set("response_type",register.getresponse_type()))
					.and(set("associated_risk",register.getassociated_risk()))
					.and(set("associated_issue",register.getassociated_issue()))
					.and(set("risk_creator",register.getrisk_creator()))
					.and(set("last_updated_person",register.getlast_updated_person()))
					.and(set("last_updated_desc",register.getlast_updated_desc()))
					.and(set("last_updated",register.getlast_updated()))
					.and(set("assumption",register.getassumption()))
					.and(set("symptom",register.getsymptom()));
					
					Statement updateDetails = updateAssignments
					.where(eq("riskId", register.getriskId()));							

			dbSession.execute(updateDetails);
			update = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while updating data in the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(dbSession);
		}
		return update;
	}
	
	
	public Register findRegister(int riskId){
		Register register=new Register();
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement find = QueryBuilder.select().all()
					.from("grc", "riskregister")
					.where(eq("riskId", register.getriskId()));

			ResultSet result = dbSession.execute(find);
			if (result == null) {
				return null;
			}
			Row row = result.one();
			
			register.setid(row.getUUID("id"));
			//register.setriskId(row.getInt("riskId"));
			//register.settenantId(row.getInt("tenantId"));
			register.setname(row.getString("name"));
			register.setowner(row.getString("owner"));
			register.setstatus(row.getString("status"));
			register.setcategory_rating(row.getString("category_rating"));
			register.setreporting_level(row.getString("reporting_level"));
			register.setdescription(row.getString("description"));
			register.setimpact_description(row.getString("impact_description"));
			register.setprobability(row.getString("probability"));
			register.setpriority(row.getString("priority"));
			register.setimpact(row.getString("impact"));
			//register.setimpact_date(row.getDate("impact_date"));
			register.setscore(row.getFloat("score"));
			//register.settargetresolutiondate(row.getDate("target_resolution_date"));
			//register.setactualresolutiondate(row.getDate("actual_resolution_date"));
			register.setresponse_type(row.getString("response_type"));
			register.setassociated_risk(row.getString("associated_risk"));
			register.setassociated_issue(row.getString("associated_issue"));
			register.setrisk_creator(row.getString("risk_creator"));
			//register.setlast_updated_person(row.getString("last_updated_person"));
			//register.setlast_updated_desc(row.getString("last_updated_desc"));
			//register.setlast_updated(row.getDate("last_updated"));
			register.setassumption(row.getString("assumption"));
			register.setsymptom(row.getString("symptom"));
	}
		catch (DriverException e) {
			Logger.error("Error occurred while retrieving data from the risk register table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(dbSession);
		}
		return register;
	}
}

