package com.depth1.grc.controllers;
import java.util.Collections;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.*;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.depth1.grc.model.DaoException;
import com.depth1.grc.model.DaoFactory;

import com.depth1.grc.model.PrintPdfRiskAssessment;
import com.depth1.grc.model.RiskAssessment;
import com.depth1.grc.model.RiskAssessmentDao;
import com.depth1.grc.model.RiskAssessmentSort;


import com.depth1.grc.db.util.CassandraPoolImpl;
import com.depth1.grc.model.CassandraDaoFactory;
import com.depth1.grc.model.CassandraPolicyDao;
import com.depth1.grc.model.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Policy;
import com.depth1.grc.model.PolicyDao;

import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.model.UserProfile;
import com.depth1.grc.model.UserProfileDao;
import com.depth1.grc.util.DateUtility;

import com.depth1.grc.views.html.*;

import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.data.Form;
import play.data.Form.Field;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Security;

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
	 * @param tenant The tenant to create
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
	 * Creates a user profile
	 * @param user User to create
	 * @return Result of the user created
	 */
	public static Result createUserProfile(UserProfile user) {
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			profile.createUserProfile(user);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating a user ", e);
		}

		return ok();
	}	
	
	/**
	 * Creates a user profile
	 * @param user User to create
	 * @return Result of the user created
	 */
	public static Result deleteUserProfile(String username) {
		boolean deleted = false;
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			deleted = profile.deleteUserProfile(username);
			if (deleted) {
				Logger.info("User profile deleted successfully.");
			} else {
				Logger.info("User profile not deleted.");
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while deleting a user ", e);
		}

		return ok();
	}		
	
	/**
	 * Creates a user profile
	 * @param user User to create
	 * @return Result of the user created
	 */
	public static Result updateUserProfile(UserProfile user) {
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			profile.updateUserProfile(user);
		} catch (DaoException e) {
			Logger.error("Error occurred while updating a user ", e);
		}

		return ok();
	}	
	
	/**
	 * Gets all the users in the user profile table.
	 * 
	 * @return Result of all the users in the table
	 */
	public static Result listProfile() {
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			List<UserProfile> userList = profile.listUserProfile();
			for (UserProfile list : userList) {
				// TODO
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while creating a user ", e);
		}

		return ok();
	}	
	
	
	/**
	 * Gets a user that matches search criteria of username and lastname.
	 * 
	 * @return Result of all the users in the table
	 */
	public static Result getUserProfile(String username, String lastname) {
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			UserProfile user = profile.findUserProfile(username, lastname);
			// TODO

		} catch (DaoException e) {
			Logger.error("Error occurred while creating a user ", e);
		}

		return ok();
	}	
	
	/**
	 * Gets a user that matches search criteria of username and lastname.
	 * 
	 * @return Result of all the users in the table
	 */
	public static Result getUserProfile(UUID userId) {
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			UserProfile user = profile.findUserProfile(userId);
			// TODO

		} catch (DaoException e) {
			Logger.error("Error occurred while creating a user ", e);
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

		return redirect("/riskAssessment/1/10/descendingRisk");
	}
	/**
	 * @param RiskAssessment
	 *            The RA criteria to update
	 * @return the result of the RAC creation
	 */
	public Result updateRiskAssessment() {
		Form<RiskAssessment> filledRA = rAForm.bindFromRequest();
		RiskAssessment criteria = filledRA.get();
		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory
					.getRiskAssessmentDao();
			riskAssessmentDao.updateRiskAssessment(criteria);
		} catch (DaoException e) {
			Logger.error(
					"Error occurred while updating risk assessment criteria ",
					e);
		}

		return redirect("/riskAssessment/1/10/descendingRisk");
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


		return redirect("/riskAssessment/1/10/descendingRisk");
	}
	
	public Result setSelectedRA() {
		RiskAssessmentSort riskAssessmentUtil = new RiskAssessmentSort();
		JsonNode node = request().body().asJson().get("val");
		
		 if(node == null){
		      return badRequest("empty json"); 
		    }
		String inputString = node.textValue();
		int index = Integer.parseInt(inputString);
		int size = 0;
		
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


		return ok(frontRA.render(riskAssessments, riskAssessments.size()));
	}
	
	
	/**
	 *  Pagination for RiskAssessment
	 */
	
	
	public Result showFrontRAPageQuery(int page, int view, String order, String query){
		int size = 0;
		RiskAssessmentSort riskAssessmentSort = new RiskAssessmentSort();
		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory.getRiskAssessmentDao();
			riskAssessments = riskAssessmentDao.listRiskAssessment();
			size = riskAssessments.size();
			//riskAssessments = riskAssessmentDao.listRiskAssessmentPagination(view, page );
			if(query.compareTo("")!= 0){
				riskAssessments = riskAssessmentSort.filterDataByQuery(riskAssessments, query);
				size = riskAssessments.size();
				
			}
			if(size > 0){
				riskAssessments = riskAssessmentSort.sortRiskAssessment(riskAssessments, order);
				riskAssessments = riskAssessmentSort.paginateRiskAssessment(riskAssessments, view, page);
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while creating risk assessment criteria ", e);
		}

		return ok(frontRA.render(riskAssessments, size));
	}
	
	/**
	 * This method shows the create Risk Assessment page if the 'Create' button
	 * is clicked
	 * 
	 * @return create Risk Assessment page
	 */
	public Result showCreateRAPage() {

		return ok(createRA.render());

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

	/**
	 * This method allows users to print selected Risk Assessments
	 * 
	 * @return update Risk Assessment page
	 */
	public Result printRA() {
		PrintPdfRiskAssessment pdf = new PrintPdfRiskAssessment();
		pdf.printRiskAssessment(selectedRA);
		return redirect("/assets/pdf/RA.pdf");
	}

	/**
	 * Policy
	 */
	
	/**
	 * Creates a policy 
	 * @param void
	 * @return Result of the policy created
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
	
	/**
	 * Saves the policy body document to a file on the server file system
	 * @param file name of policy to be saved, text-area field of policy body
	 * @return void
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

	/**
	 * Updates a policy 
	 * @param void
	 * @return Result of the policy updated
	 */
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
		
	/**
	 * Deletes a policy 
	 * @param UUID of policy
	 * @return delete policy page
	 */
	public Result deletePolicy(UUID id) {
		//Logger.error("correct");
		//call cassandra policy dao
		boolean result = false;
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			result = policyDao.deletePolicy(id);
			//System.out.println("COMPLETED result = " + result );
		} catch (DaoException e) {
			System.out.println("ERROR OCCURED");
			Logger.error("Error occurred while creating Policy Delete Page ", e);
		}		
		return ok(deletePolicy.render(policies));
	}
	
	/**
	 * Restores a policy 
	 * @param UUID of policy
	 * @return restore policy page
	 */
	public Result restorePolicy(UUID id) {
		//Logger.error("correct");
		//call cassandra policy dao
		boolean result = false;
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			result = policyDao.restorePolicy(id);
			//System.out.println("COMPLETED result = " + result );
		} catch (DaoException e) {
			System.out.println("ERROR OCCURED");
			Logger.error("Error occurred while creating Policy Restore Page ", e);
		}
		return ok(restorePolicy.render(policies));
	}
	
	/**
	 * Shows a list of policy - Front page for Policy 
	 * @param void
	 * @return policy list page
	 */
	public Result showPolicyListPage() {

		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllPolicy();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(policyListPage.render(policies));
	}

	/**
	 * Shows a create policy page 
	 * @param void
	 * @return policy create page
	 */
	public Result showCreatePolicyPage() {

		return ok(createPolicy.render(policyForm));
	}

	/**
	 * Shows a create policy editor page 
	 * @param void
	 * @return policy editor page
	 */
	public Result showCreatePolicyEditorPage() {

		// return ok(viewPolicy.render(selectedPolicy));
		return ok();
	}

	/**
	 * Shows a view policy page 
	 * @param void
	 * @return policy view page
	 */
	public Result showViewPolicyPage(UUID id) {		
		//String filepath = "documents/test.pdf";
		//return ok(new java.io.File(filepath));

		PolicyDao policyDao;
		try {
			policyDao = cassandraFactory.getPolicyDao();
			final Policy policy = policyDao.viewPolicyById(id);
			//File policyBodyFile = new java.io.File("public/policyDocuments/"+policy.getName());
			String policyBody = "";
			try{policyBody = new String(Files.readAllBytes(Paths.get("public/policyDocuments/" + policy.getName())));}
			catch(IOException e){
				Logger.error("Error reading policy body file into string: ", e);
			}
			return ok(viewPolicy.render(policy, policyBody));
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page: ", e);
		}
		return ok();
	}

	/**
	 * Shows a update policy page 
	 * @param void
	 * @return policy update page
	 */
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

	/**
	 * Shows a delete policy page 
	 * @param void
	 * @return policy delete page
	 */
	public Result showDeletePolicyPage() {
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllPolicy();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(deletePolicy.render(policies));
		
	}
	
	/**
	 * Shows a restore policy page 
	 * @param void
	 * @return policy restore page
	 */
	public Result showRestorePolicyPage(){
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllDeletedPolicy();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(restorePolicy.render(policies));
	}

	/**
	 * Downloads a policy to a file
	 * @param policy name
	 * @return creates a policy file
	 */
	public Result downloadPolicy(String name){
		String filepath = "public/policyDocuments/" + name;
		return ok(new java.io.File(filepath));

	}

}