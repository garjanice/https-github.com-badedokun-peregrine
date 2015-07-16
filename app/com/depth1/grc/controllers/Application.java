package com.depth1.grc.controllers;

import java.util.List;

import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.depth1.grc.db.util.CassandraPoolImpl;
import com.depth1.grc.model.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Policy;
import com.depth1.grc.model.PolicyDao;
import com.depth1.grc.model.RiskAssessment;
import com.depth1.grc.model.RiskAssessmentDao;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.views.html.*;

public class Application extends Controller {

	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory
			.getDaoFactory(DaoFactory.CASSANDRA);
	final static Form<RiskAssessment> rAForm = Form.form(RiskAssessment.class);
	static List<RiskAssessment> riskAssessments;
	static RiskAssessment selectedRA;
	
	final static Form<Policy> policyForm = Form.form(Policy.class);
	static List<Policy> policies;
	static Policy selectedPolicy;

	public Result index() {
		// test connection to the cassandra cluster

		/*
		 * CassandraPoolImpl con = new CassandraPoolImpl(); Session session =
		 * con.create(); System.out.println();
		 * System.out.println("Test client to display state");
		 * System.out.println("============================");
		 * printState(session); session.close();
		 */
		
		//gets the list of previous RA, this code will be moved from the index to RA page method later

		return ok(index.render()); 
	}	

	/**
	 * This method is used as a client to test getting data from the Cassandra
	 * database It displays data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * @return Returns a result in the future in an non-blocking fashion
	 */
	public ResultSetFuture getState(Session session) {
		Statement query = QueryBuilder.select().all().from("member", "state");
		return session.executeAsync(query);

	}

	/**
	 * This method prints data retrieved from the Cassandra database It displays
	 * data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * 
	 */

	public void printState(Session session) {
		ResultSetFuture result = getState(session);
		for (Row row : result.getUninterruptibly()) {
			System.out.printf("%s: %s  %s\n", row.getString("country"),
					row.getString("short_name"), row.getString("long_name"));
		}
		session.close();

	}

	/**
	 * @param tenant
	 *            The tenant to create
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

	/**
	 * @param RiskAssessment
	 *            The RA criteria to create
	 * @return Result the result of the RAC creation
	 */
	public Result addRiskAssessment() {
		Form<RiskAssessment> filledRA = rAForm.bindFromRequest();
		RiskAssessment criteria = filledRA.get();
		System.out
				.println("Here it is: " + criteria.getLikelihoodDescription());
		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory
					.getRiskAssessmentDao();
			riskAssessmentDao.createRiskAssessment(criteria);
		} catch (DaoException e) {
			Logger.error(
					"Error occurred while creating risk assessment criteria ",
					e);
		}

		return redirect("/riskAssessment");
	}
	
	public Result deleteRiskAssessment() {
	
		return TODO;
	}
	
	public Result showFrontRAPage() {

		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory
					.getRiskAssessmentDao();
			riskAssessments = riskAssessmentDao.listRiskAssessment();
		} catch (DaoException e) {
			Logger.error(
					"Error occurred while creating risk assessment criteria ",
					e);
		}

		return ok(frontRA.render(riskAssessments)); // change to main page
	}

	public Result showCreateRAPage() {

		return ok(createRA.render(rAForm));
	}

	public Result showViewRAPage() {

		return ok(viewRA.render(selectedRA));
	}

	public Result showUpdateRAPage() {

		return ok(updateRA.render(selectedRA));
	}

	//remove this later
	public Result showDeleteRAPage() {

		return TODO;
	}

	/**
	 * @param Policy
	 *
	 * @return 
	 */
	public Result createPolicy() {
		//create form object from the request
		Form<Policy> filledPolicy = policyForm.bindFromRequest();
		//check for required and validate input fields
		//TODO : Validate input fields for Date and Strings
		if (filledPolicy.hasErrors()) {
			 Logger.error("The error in the form are " + filledPolicy.errorsAsJson());
			  return badRequest(filledPolicy.errorsAsJson());
		}
		//Bind policy object with the form object
		Policy criteria = filledPolicy.get();
		System.out.println("Here it is: " + criteria.getDescription());
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			//create policy on DB
			policyDao.createPolicy(criteria);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy ", e);
		}

		return redirect("/policy");
	}
	
	public Result deletePolicy() {
	
		return TODO;
	}
	
	public Result showPolicyListPage() {

		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllPolicy();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(policyListPage.render(policies));
	}

	public Result showCreatePolicyPage() {

		return ok(createPolicy.render(policyForm));
	}
	
	public Result showCreatePolicyEditorPage() {

//		return ok(viewPolicy.render(selectedPolicy));
		return ok();
	}


	public Result showViewPolicyPage() {

//		return ok(viewPolicy.render(selectedPolicy));
		return ok();
	}

	public Result showUpdatePolicyPage() {

//		return ok(updatePolicy.render(selectedPolicy));
		return ok();
	}

	//remove this later, we may not have a specific delete policy page
	public Result showDeletePolicyPage() {

		return ok();
	}

	
}
