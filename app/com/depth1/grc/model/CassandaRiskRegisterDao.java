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

public class CassandraRiskRegisterDao implements RiskRegisterDao {

	public Register register;
	
	//private final static Boolean keyspace = Play.application().configuration().getBoolean("onpremise.deploy.model");
		
	public void createRiskRegister(Register register) throws DaoException{
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement insert = QueryBuilder
					.insertInto("grc", "riskregister")
					.value("id",UUID.randomUUID())
					//.value("tenantId", register.getTenantId())
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
					.value("impact_date",register.getimpactdate())
					.value("score",register.getscore())
					.value("resolution",register.getresolution())
					.value("target_resoltuion_date",register.gettargetresolutiondate())
					.value("actual_resolution_date",register.getactualresolutiondate())
					.value("response_type",register.getresponsetype())
					.value("associated_risk",register.getassociatedrisk())
					.value("associated_issue",register.getassociatedissue())
					.value("risk_creator",register.getriskcreator())
					.value("assumption",register.getassumption())
					.value("symptom",register.getSymptom());
					dbSession.execute(insert);
		} 
		
		catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} 
		
		finally {			
			CassandraDaoFactory.close(dbSession);
		}		
		
}		
	
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
		//register.setTenantId(row.getInt("tenantId"));
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
		register.setimpactdate(row.getDate("impact_date"));
		register.setscore(row.getFloat("score"));
		register.settargetresolutiondate(row.getDate("target_resolution_date"));
		register.setactualresolutiondate(row.getDate("actual_resolution_date"));
		register.setresponsetype(row.getString("response_type"));
		register.setassociatedrisk(row.getString("associated_risk"));
		register.setassociatedissue(row.getString("associated_issue"));
		register.setriskcreator(row.getString("risk_creator"));
		register.setassumption(row.getString("assumption"));
		register.setSymptom(row.getString("symptom"));
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
	public boolean updateRiskRegister(Register register){
		boolean update= false;
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Update.Assignments updateAssignments = QueryBuilder
					.update("grc", "riskregister")
					.with(set("tenantId",register.getTenantId()))
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
					.and(set("target_resolution_date",register.gettargetresolutiondate()))
					.and(set("actual_resolution_date",register.getactualresolutiondate()))
					.and(set("response_type",register.getresponsetype()))
					.and(set("associated_risk",register.getassociatedrisk()))
					.and(set("associated_issue",register.getassociatedissue()))
					.and(set("risk_creator",register.getriskcreator()))
					.and(set("last_updated_person",register.getlastupdatedperson()))
					.and(set("last_updated_desc",register.getlastupdateddesc()))
					.and(set("last_updated",register.getlastupdated()))
					.and(set("assumption",register.getassumption()))
					.and(set("symptom",register.getSymptom()));
					
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
	
	//public boolean viewRiskRegister(){
		
		//return false;
	//}
	
	
	public Register findRegister(int riskId){
		Register register=new Register();
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement find = QueryBuilder.select().all()
					.from("grc", "riskregister")
					.where(eq("riskid", register.getriskId()));

			ResultSet result = dbSession.execute(find);
			if (result == null) {
				return null;
			}
			Row row = result.one();
			
			register.setid(row.getUUID("id"));
			//register.setriskId(row.getInt("riskId"));
			//register.setTenantId(row.getInt("tenantId"));
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
			register.setimpactdate(row.getDate("impact_date"));
			register.setscore(row.getFloat("score"));
			register.settargetresolutiondate(row.getDate("target_resolution_date"));
			register.setactualresolutiondate(row.getDate("actual_resolution_date"));
			register.setresponsetype(row.getString("response_type"));
			register.setassociatedrisk(row.getString("associated_risk"));
			register.setassociatedissue(row.getString("associated_issue"));
			register.setriskcreator(row.getString("risk_creator"));
			//register.setlastupdatedperson(row.getString("last_updated_person"));
			//register.setlastupdateddesc(row.getString("last_updated_desc"));
			//register.setlastupdated(row.getDate("last_updated"));
			register.setassumption(row.getString("assumption"));
			register.setSymptom(row.getString("symptom"));
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

