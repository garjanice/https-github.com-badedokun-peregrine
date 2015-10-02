package com.depth1.grc.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

import play.Logger;
import play.data.Form;
import play.data.Form.Field;
import play.mvc.Controller;
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
import com.depth1.grc.model.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Policy;
import com.depth1.grc.model.PolicyDao;
import com.depth1.grc.model.PolicyUtil;
import com.depth1.grc.model.PrintPdfRiskAssessment;
import com.depth1.grc.model.RiskAssessment;
import com.depth1.grc.model.RiskAssessmentDao;
import com.depth1.grc.model.RiskAssessmentSort;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.model.TenantSort;
import com.depth1.grc.model.UserProfile;
import com.depth1.grc.model.UserProfileDao;
import com.depth1.grc.model.UserProfileSort;
import com.depth1.grc.views.html.*;
import com.fasterxml.jackson.databind.JsonNode;

@Security.Authenticated(Secured.class)
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
	
	static List<Tenant> tenants;
	static Tenant selectedTenant;
	final static Form<Tenant> tenantForm = Form.form(Tenant.class);
	
	static List<UserProfile> userProfiles;
	static UserProfile selectedUserProfile;
	final static Form<UserProfile> userProfileForm = Form.form(UserProfile.class);
	
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
	 * @return a result in the future in an non-blocking fashion
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
	 * @param RiskAssessment
	 *            The RA criteria to create
	 * @return the result of the RAC creation
	 */
	public Result addRiskAssessment() {
		Form<RiskAssessment> filledRA = rAForm.bindFromRequest();
		RiskAssessment criteria = filledRA.get();
		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory
					.getRiskAssessmentDao();
			riskAssessmentDao.createRiskAssessment(criteria);
		} catch (DaoException e) {
			Logger.error(
					"Error occurred while creating risk assessment criteria ",
					e);
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
		RiskAssessmentSort riskAssessmentUtil = new RiskAssessmentSort();
		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory.getRiskAssessmentDao();
			riskAssessments = riskAssessmentDao.listRiskAssessment();
			size = riskAssessments.size();
			//riskAssessments = riskAssessmentDao.listRiskAssessmentPagination(view, page );
			if(query.compareTo("")!= 0){
				riskAssessments = riskAssessmentUtil.filterDataByQuery(riskAssessments, query);
				size = riskAssessments.size();
				
			}
			if(size > 0){
				riskAssessments = riskAssessmentUtil.sortRiskAssessment(riskAssessments, order);
				riskAssessments = riskAssessmentUtil.paginateRiskAssessment(riskAssessments, view, page);
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
	 * This method allows users to update selected Risk Assessments
	 * 
	 * @return update Risk Assessment page
	 */
	public Result printRA() {
		PrintPdfRiskAssessment pdf = new PrintPdfRiskAssessment();
		pdf.printRiskAssessment(selectedRA);
		return redirect("/assets/pdf/RA.pdf");
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
	
	public Result showPolicyListPage(int page, int view, String order, String query){
		int policySize = 0;
		PolicyUtil policyUtil = new PolicyUtil();
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllPolicy();
			policySize = policies.size();
			if(query.compareTo("")!= 0){
				policies = policyUtil.filterDataByQuery(policies, query);
				policySize = policies.size();
				
			}
			if(policySize > 0){
				policies = policyUtil.sortPolicy(policies, order);
				policies = policyUtil.paginatePolicy(policies, view, page);
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		    
		}

		return ok(policyListPage.render(policies, policySize));
	}

	public Result showCreatePolicyPage() {

		return ok(createPolicy.render(policyForm));
	}

	public Result showCreatePolicyEditorPage() {

		// return ok(viewPolicy.render(selectedPolicy));
		return ok();
	}

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

	public Result showUpdatePolicyPage(UUID id) {
		// return ok(updatePolicy.render(selectedPolicy));
		PolicyDao policyDao;
		try {
			policyDao = cassandraFactory.getPolicyDao();
			final Policy policy = policyDao.viewPolicyById(id);
			Form<Policy> filledForm = policyForm.fill(policy);
			return ok(updatePolicy.render(policy));
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

	public Result downloadPolicy(String name){
		String filepath = "public/policyDocuments/" + name;
		return ok(new java.io.File(filepath));

	}
	
	
	
	public Result addTenant() {
		Form<Tenant> filledTenant = tenantForm.bindFromRequest();
		Tenant criteria = filledTenant.get();
		try {
			TenantDao tenantDao = cassandraFactory
					.getTenantDao();
			tenantDao.createTenant(criteria);
		} catch (Exception e) {
			Logger.error(
					"Error occurred while creating risk assessment criteria ",
					e);
		}

		return redirect("/tenant/1/10/descendingName");
	}
	
	
	/**
	 * Shows the Tenant Creation Page
	 * @return Result of the tenant creation
	 */
	public Result showCreateTenant() {
		
		return ok(createTenant.render());
	}
	
	/**
	 *  Shows the list of Tenants in the Database on the FrontTenant Page
	 * @param page current page number form pagination
	 * @param view the number of items to show per page
	 * @param order the sorting order of the page
	 * @param query the string to search for in the tenant list
	 * @return Result of the List Page
	 */
	public Result showFrontTenantPage(int page, int view, String order, String query){
		int size = 0;
		
		//RiskAssessmentUtil riskAssessmentUtil = new RiskAssessmentUtil();
		try {
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			TenantSort tenantSort = new TenantSort();
			tenants = tenantDao.listTenant();
			
			size = tenants.size();
			if(query.compareTo("")!= 0){
				tenants = tenantSort.filterDataByQuery(tenants, query);
				size = tenants.size();
				
			}
			if(size > 0){
				tenants = tenantSort.sortTenant(tenants, order);
				tenants = tenantSort.paginateTenants(tenants, view, page);
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while creating risk assessment criteria ", e);
		}
			
		return ok(frontTenant.render(tenants, size));
	}
	/**
	 * Sets the Tenant that the user picks from the list of Tenants
	 * on the FrontTenant Page.   Uses Ajax and JSON.
	 * @return Result of setting the selected Tenant
	 */
	public Result setSelectedTenant() {
		
		JsonNode node = request().body().asJson().get("val");
		
		if(node == null){
		        return badRequest("empty json"); 
		}
		String inputString = node.textValue();
		
		int index = Integer.parseInt(inputString);
		
		
		selectedTenant = tenants.get(index);
		return ok();
	}
	/**
	 * Shows the UpdateTenant Page with the Tenant Information Populating
	 * the fields.
	 * @return Result of updating the Tenant information
	 */
	public Result showUpdateTenant() {

		return ok(updateTenant.render(selectedTenant));
	}
	/**
	 * Shows the ViewTenant Page with the currently selected Tenants
	 * information.
	 * @return Result of the viewing the Tenant Information
	 */
	public Result showViewTenant() {

		return ok(viewTenant.render(selectedTenant));
	}
	/**
	 * Updates the selected Tenants information and then displays
	 * the list of Tenants on the FrontTenant Page
	 * @return Result of updating the Tenant
	 */
	public Result updateTenant() {
		Form<Tenant> filledTenant = tenantForm.bindFromRequest();
		Tenant criteria = filledTenant.get();
		criteria.setTenantId(selectedTenant.getTenantId());
		criteria.setId(selectedTenant.getId());
		try {
			TenantDao tenantDao = cassandraFactory
					.getTenantDao();
			tenantDao.updateTenant(criteria);
			
		} catch (DaoException e) {
			Logger.error(
					"Error occurred while updating risk assessment criteria ",
					e);
		}

		return redirect("/tenant/1/10/descendingName");
	}
	/**
	 * Deletes the selected Tenant from the database.  Uses Ajax and JSON.
	 * Shows the FrontTenant Page.
	 * @return Result of the deleting the Tenant.
	 */
	public Result deleteTenant() {
		try {
			TenantDao tenantDao = cassandraFactory
					.getTenantDao();
			tenantDao.deleteTenant(selectedTenant.getTenantId());
		} catch (DaoException e) {
			Logger.error(
					"Error occurred while deleting tenant ",
					e);
		}

		return redirect("/tenant/1/10/descendingName");
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
			
		} catch (DaoException e) {
			Logger.error("Error occurred while creating a user ", e);
		}

		return ok();
	}	
	
	
	/**
	 * Date utility to format date retrieved from timeuuid
	 * 
	 * @param dateFormat desired date format
	 * @param date date retrieved from the database
	 * @return a string representative of the date
	 */
	public static String DateUtil (String dateFormat, Date date) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date dateTime = date;
		return format.format(dateTime);
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
			//SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");
			String dateFormat = "yyyy-dd-MM HH:mm:ss";
			Date dateTime = user.getDateUtil();
			Map<String, String> map = user.getPhones();
			String work   = map.get("Work");
			String mobile = map.get("Mobile");
			
		
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

			
		} catch (DaoException e) {
			Logger.error("Error occurred while creating a user ", e);
		}

		return ok();
	}		
	
	public Result showFrontUserProfile(int page, int view, String order, String query){
		int size = 0;
		try {
			UserProfileDao userProfileDao = cassandraFactory.getUserProfileDao();
			UserProfileSort userProfileSort = new UserProfileSort();
			userProfiles = userProfileDao.listUserProfile();
			
			size = userProfiles.size();
			if(query.compareTo("")!= 0){
				userProfiles = userProfileSort.filterDataByQuery(userProfiles, query);
				size = userProfiles.size();
				
			}
			if(size > 0){
				userProfiles = userProfileSort.sortUserProfile(userProfiles, order);
				userProfiles = userProfileSort.paginateUserProfiles(userProfiles, view, page);
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while creating risk assessment criteria ", e);
		}
			
		return ok(frontUserProfile.render(userProfiles, size));
	}
	
	public Result setSelectedUserProfile() {
		
		JsonNode node = request().body().asJson().get("val");
		
		if(node == null){
		        return badRequest("empty json"); 
		}
		String inputString = node.textValue();
		
		int index = Integer.parseInt(inputString);
		
		selectedUserProfile = userProfiles.get(index);
		return ok();
	}
	
	public Result showCreateUserProfile() {
		
		return ok(createUserProfile.render());
	}
	
	public Result addUserProfile() {
		Form<UserProfile> filledUserProfile = userProfileForm.bindFromRequest();
		UserProfile criteria = filledUserProfile.get();
		try {
			UserProfileDao userProfileDao = cassandraFactory.getUserProfileDao();
					
			userProfileDao.createUserProfile(criteria);
		} catch (DaoException e) {
			Logger.error(
					"Error occurred while creating risk assessment criteria ",
					e);
		}

		return redirect("/userprofile/1/10/descendingName");
	}
	
}
