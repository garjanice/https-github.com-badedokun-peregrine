package com.depth1.grc.controllers;

import play.*;
import play.mvc.*;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.depth1.grc.db.util.CassandraPoolImpl;
import com.depth1.grc.model.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.RiskRegisterDao;
import com.depth1.grc.model.Register;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.views.html.createRR;
import com.depth1.grc.views.html.index;
import com.depth1.grc.views.html.frontRR;


public class Application extends Controller {
	
	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory.getDaoFactory(DaoFactory.CASSANDRA);
	final static Form<Register> rRForm=Form.form(Register.class);
	static List<Register> registers;

 public Result index() {
    	// test connection to the cassandra cluster
    	//CassandraPoolImpl con = new CassandraPoolImpl();
    	//Session session = con.create();
    	//System.out.println();
    	//System.out.println("Trying to get the information in DataBase");
    	//printState(session);
    	//session.close();
   	    return ok(index.render("Database Created Successfully"));
    }
    
    
    /**
     * This method is used as a client to test getting data from the Cassandra database
     * It displays data it reads from the database on the console
     * @param session The established session to the cluster
     * @return Returns a result in the future in an non-blocking fashion
     */
  //public ResultSetFuture getRegister(Session session) {    	
    //	Statement query = QueryBuilder.select().all().from("grc", "riskregister");
    	//return session.executeAsync(query);
   //}

    /**
     * This method prints data retrieved from the Cassandra database
     * It displays data it reads from the database on the console
     * @param session The established session to the cluster
     * 
     */
   
    //public void printState(Session session) {
      //  ResultSetFuture result = getRegister(session);
       // for (Row row : result.getUninterruptibly()) {
    	//System.out.printf( "%s: %s  %s\n",
    		//row.getString("id"),
    		//row.getString("me"),
    	//	row.getString("long_name"));
    	//}
    	//session.close();

    //}
    
	/**
	 * @param tenant The tenant to create
	 * @return Result the result of the tenant creation
	 */
	public static Result createTenant(Tenant tenant) {
		try {
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			tenantDao.createTenant(tenant);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating tenant ", e);
		}

		return ok();
	}

	public Result addRisk() {
    	
		Form<Register> risky=rRForm.bindFromRequest();
    	if(risky.hasErrors()){
    		flash("Error","Please Correct the Form");
    		return badRequest(createRR.render(rRForm));
    	}
    	Register register=risky.get();
		try {
			 	RiskRegisterDao riskregisterDao = cassandraFactory.getRiskRegisterDao();
			 	riskregisterDao.createRiskRegister(register);
		} catch(DaoException e) {
			Logger.error("Error occures while creating Risk Register", e);
		}
		System.out.println("The data is retrieved into the database successfully!");
   	 return redirect("/register");
	}
    	
	public Result showFrontRRPage() {
		
		try {
		 	RiskRegisterDao riskregisterDao = cassandraFactory.getRiskRegisterDao();
		 	registers=riskregisterDao.listRegister();
	} catch(DaoException e) {
		Logger.error("Error occures while creating Risk Register", e);
	}		
		return ok(frontRR.render(registers));
	}
	
	public Result showCreateRRPage() {

		return ok(createRR.render(rRForm));
	}

	
	public Result showViewRRPage() {

		return TODO;
	}

	public Result showUpdateRRPage() {

		return TODO;
	}

}
