package com.depth1.grc.controllers;

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
import com.depth1.grc.model.RiskAssessment;
import com.depth1.grc.model.RiskAssessmentDao;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.views.html.createRA;
import com.depth1.grc.views.html.index;

public class Application extends Controller {

	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory
			.getDaoFactory(DaoFactory.CASSANDRA);
	final static Form<RiskAssessment> rAForm = Form.form(RiskAssessment.class);

	public Result index() {
		// test connection to the cassandra cluster

		/*
		 * CassandraPoolImpl con = new CassandraPoolImpl(); Session session =
		 * con.create(); System.out.println();
		 * System.out.println("Test client to display state");
		 * System.out.println("============================");
		 * printState(session); session.close();
		 */

		return ok(index.render()); // change to main page
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

		return ok("Risk Assessment Created");
	}

	public Result showCreateRAPage() {

		return ok(createRA.render(rAForm));
	}

	public Result showViewRAPage() {

		return TODO;
	}

	public Result showUpdateRAPage() {

		return TODO;
	}

	public Result showDeleteRAPage() {

		return TODO;

	}
}
