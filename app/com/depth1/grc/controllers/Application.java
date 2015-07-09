package com.depth1.grc.controllers;

import play.*;
import play.mvc.*;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.depth1.grc.db.util.CassandraPoolImpl;
import com.depth1.grc.model.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.views.html.*;
import com.depth1.grc.model.RiskRegister;
import com.depth1.grc.model.RiskAssessmentDao;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.views.html.createRR;
import com.depth1.grc.views.html.index;


public class Application extends Controller {
	
	final static play.data.Form<RiskRegister> rRForm = play.data.Form.form(RiskRegister.class);
	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory.getDaoFactory(DaoFactory.CASSANDRA);


    public Result index() {
    	// test connection to the cassandra cluster
    	CassandraPoolImpl con = new CassandraPoolImpl();
    	Session session = con.create();
    	System.out.println();
    	System.out.println("Test client to display state");
    	System.out.println("============================");
    	printState(session);
    	session.close();
   	
        return ok(index.render("Welcome to Depth1Grc"));
    }
    
    
    /**
     * This method is used as a client to test getting data from the Cassandra database
     * It displays data it reads from the database on the console
     * @param session The established session to the cluster
     * @return Returns a result in the future in an non-blocking fashion
     */
    public ResultSetFuture getState(Session session) {    	
    	Statement query = QueryBuilder.select().all().from("member", "state");
    	return session.executeAsync(query);

    }

    /**
     * This method prints data retrieved from the Cassandra database
     * It displays data it reads from the database on the console
     * @param session The established session to the cluster
     * 
     */
   
    public void printState(Session session) {
        ResultSetFuture result = getState(session);
        for (Row row : result.getUninterruptibly()) {
    	System.out.printf( "%s: %s  %s\n",
    		row.getString("country"),
    		row.getString("short_name"),
    		row.getString("long_name"));
    	}
    	session.close();

    }
    
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

	
	public Result showCreateRRPage() {

		return ok(createRR.render(rRForm));
	}

	public Result showViewRRPage() {

		return TODO;
	}

	public Result showUpdateRRPage() {

		return TODO;
	}

	public Result showDeleteRRPage() {

		return TODO;

	}
	
}
	
