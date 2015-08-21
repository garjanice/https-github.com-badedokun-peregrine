package com.depth1.grc.controllers;

import java.util.List;
import java.util.UUID;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.*;

import play.Logger;
import play.data.Form;
import play.data.Form.Field;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Security;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.depth1.grc.db.util.CassandraPoolImpl;
import com.depth1.grc.model.CassandraDaoFactory;
import com.depth1.grc.model.CassandraPolicyDao;
import com.depth1.grc.model.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Policy;
import com.depth1.grc.model.PolicyDao;
import com.depth1.grc.model.RiskAssessment;
import com.depth1.grc.model.RiskAssessmentDao;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.views.html.*;
import com.fasterxml.jackson.databind.JsonNode;

@Security.Authenticated(Secured.class)
public class Application extends Controller {

	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory.getDaoFactory(DaoFactory.CASSANDRA);
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
		 * con.create(); System.out.println(); System.out.println(
		 * "Test client to display state");
		 * System.out.println("============================");
		 * printState(session); session.close();
		 */

		// gets the list of previous RA, this code will be moved from the index
		// to RA page method later

		return ok(index.render());
	}

	/**
	 * This method is used as a client to test getting data from the Cassandra
	 * database It displays data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * @return a result in the future in an non-blocking fashion
tart */
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
			System.out.printf("%s: %s  %s\n", row.getString("country"), row.getString("short_name"),
					row.getString("long_name"));
		}
		session.close();

	}

	/**
	 * @param tenant
	 *            The tenant to create
	 * @return Result of the tenant creation
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
	 * @return the result of the RAC creation
	 */
	public Result addRiskAssessment() {
		Form<RiskAssessment> filledRA = rAForm.bindFromRequest();
		RiskAssessment criteria = filledRA.get();
		System.out.println("Here it is: " + criteria.getLikelihoodDescription());
		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory.getRiskAssessmentDao();
			riskAssessmentDao.createRiskAssessment(criteria);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating risk assessment criteria ", e);
		}

		return redirect("/riskAssessment");
	}

    /**
     * Action method for the 'Delete' button. Deletes selected Risk Assessment
     * @return
     */
	public Result deleteRiskAssessment() {
		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory
					.getRiskAssessmentDao();
			riskAssessmentDao.deleteRiskAssessment(selectedRA);
		} catch (DaoException e) {
			Logger.error(
					"Error occurred while deleting risk assessment criteria ",
					e);
		}

		return redirect("/riskAssessment");
	}
	
	public Result setSelectedRA() {
		JsonNode node = request().body().asJson().get("val");
		 if(node == null){
		        return badRequest("empty json"); 
		    }
		String inputString = node.textValue();
		//System.out.println(inputString);
		int index = Integer.parseInt(inputString);
		selectedRA = riskAssessments.get(index);
		return ok();
	}
	
	/**
	 * Shows the front page of the Risk Assessment UI
	 * 
	 * @return to the main page
	 */
	public Result showFrontRAPage() {

		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory.getRiskAssessmentDao();
			riskAssessments = riskAssessmentDao.listRiskAssessment();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating risk assessment criteria ", e);
		}

		return ok(frontRA.render(riskAssessments));
	}

	/**
	 * This method shows the create Risk Assessment page if the 'Create' button
	 * is clicked
	 * 
	 * @return create Risk Assessment page
	 */
	public Result showCreateRAPage() {

		return ok(createRA.render(rAForm));
	}

	/**
	 * This method allows users to view Risk Assessments
	 * 
	 * @return view Risk Assessment page
	 */
	public Result showViewRAPage() {

		return ok(viewRA.render(selectedRA));
	}

	/**
	 * This method allows users to update selected Risk Assessments
	 * 
	 * @return update Risk Assessment page
	 */
	public Result showUpdateRAPage() {

		return ok(updateRA.render(selectedRA));
	}

	// remove this later
	public Result showDeleteRAPage() {

		return TODO;
	}

	/**
	 * @param Policy
	 *
	 * @return
	 */
	public Result createPolicy() {
		// create form object from the request
		Form<Policy> filledPolicy = policyForm.bindFromRequest();
		// check for required and validate input fields
		// TODO : Validate input fields for Date and Strings
		if (filledPolicy.hasErrors()) {
			Logger.error("The error in the form are " + filledPolicy.errorsAsJson());
			return badRequest(filledPolicy.errorsAsJson());
		}
		// Bind policy object with the form object
		Policy criteria = filledPolicy.get();
		System.out.println("Here it is: " + criteria.getDescription());
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			// create policy on DB
			policyDao.createPolicy(criteria);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy ", e);
		}
		if (filledPolicy.field("policy-body").value() != null) {
			savePolicyBodyDocument(criteria.getName(), filledPolicy.field("policy-body"));
		}
		return redirect("/policy");
	}
/*	
	public Result importPolicyBody() throws DaoException {
		MultipartFormData body = request().body().asMultipartFormData();
		MultipartFormData.FilePart picture = body.getFile("picture");
		if (picture != null) {
	        String fileName = picture.getFilename();
	        String contentType = picture.getContentType();
	        File file = picture.getFile();
	        return ok("File uploaded");
	    } else {
	        flash("error", "Missing file");
	        return badRequest();
	    }
	}
*/
	
	private void savePolicyBodyDocument(String fileName, Field policyBody) {
		try {
			//TODO: Replace the path with path on server for file storage
			String dirString = "public/policyDocuments/";
			Path dirPath = Paths.get(dirString);
			if(Files.notExists(dirPath)) {
				Files.createDirectory(dirPath);				
			}
			Path filePath = Paths.get(dirString + fileName);
			File policyDoc = filePath.toFile();
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(policyDoc)),true); 
			//flushing the buffer after file-write 
			writer.print(Jsoup.parse(policyBody.value()).text());
			writer.close();
			Logger.info("Policy Body Documented at " + dirString + fileName);
		}
		catch (IOException e) {
			Logger.error("Error while storing the policy body document " + e);
		}
	}

	public Result updatePolicy() {
		// create form object from the request
		Form<Policy> filledPolicy = policyForm.bindFromRequest();
		// check for required and validate input fields
		// TODO : Validate input fields for Date and Strings
		if (filledPolicy.hasErrors()) {
			Logger.error("The error in the form are " + filledPolicy.errorsAsJson());
			return badRequest(filledPolicy.errorsAsJson());
		}
		// Bind policy object with the form object
		Policy criteria = filledPolicy.get();
		System.out.println("Here it is: " + criteria.getDescription());
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			// create policy on DB
			policyDao.updatePolicy(criteria.getId(), criteria);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy ", e);
		} catch (IllegalArgumentException e) {
			Logger.error("Invalid UUID");
			return badRequest("Invalid UUID");
		}

		return redirect("/policy");
	}
		
	public Result deletePolicy(String policyId) {
		//Logger.error("correct");
		//call cassandra policy dao
		boolean result = false;
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			result = policyDao.deletePolicy(policyId);
			//System.out.println("COMPLETED result = " + result );
		} catch (DaoException e) {
			System.out.println("ERROR OCCURED");
			Logger.error("Error occurred while creating Policy Front Page ", e);
	}
		
		return ok(deletePolicy.render(policies));
		
		//return TODO;
	}
	public Result restorePolicy(String policyId) {
		//Logger.error("correct");
		//call cassandra policy dao
		boolean result = false;
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			result = policyDao.restorePolicy(policyId);
			//System.out.println("COMPLETED result = " + result );
		} catch (DaoException e) {
			System.out.println("ERROR OCCURED");
			Logger.error("Error occurred while creating Policy Front Page ", e);
	}
		
		return ok(restorePolicy.render(policies));
		
		//return TODO;
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

		// return ok(viewPolicy.render(selectedPolicy));
		return ok();
	}

	public Result showViewPolicyPage() {		
		//String filepath = selectedPolicy.filePath;
		
       	//File file = new java.io.File(source);
		//return ok(file);
		String filepath = "documents/test.pdf";
		
		//response.removeHeader("Content-Disposition");
		return ok(new java.io.File(filepath));
		//return ok(viewPolicy.render(selectedPolicy));
		//return ok();
	}

	public Result showUpdatePolicyPage(UUID id) {
		// return ok(updatePolicy.render(selectedPolicy));
		PolicyDao policyDao;
		try {
			policyDao = cassandraFactory.getPolicyDao();
			final Policy policy = policyDao.viewPolicyById(id);
			Form<Policy> filledForm = policyForm.fill(policy);
			return ok(updatePolicy.render(filledForm));
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}
		return ok();
	}

	// remove this later, we may not have a specific delete policy page
	public Result showDeletePolicyPage() {
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllPolicy();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(deletePolicy.render(policies));
		
	}
	
	public Result showRestorePolicyPage(){
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllDeletedPolicy();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(restorePolicy.render(policies));
	}

}
