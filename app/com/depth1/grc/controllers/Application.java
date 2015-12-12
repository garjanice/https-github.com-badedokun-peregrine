package com.depth1.grc.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
import java.lang.Long;

import org.jsoup.*;

import play.Logger;
import play.data.Form;
import play.data.Form.Field;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.mvc.Security;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.utils.UUIDs;
import com.depth1.grc.db.util.DataException;
import com.depth1.grc.db.util.DropDownList;
import com.depth1.grc.db.util.JpaUtil;
import com.depth1.grc.jpa.models.JpaStrategicObjectiveDao;
import com.depth1.grc.jpa.models.Measure;
import com.depth1.grc.jpa.models.StrategicObjective;
import com.depth1.grc.model.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.PrintPdfRiskAssessment;
import com.depth1.grc.model.RiskAssessment;
import com.depth1.grc.model.RiskAssessmentDao;
import com.depth1.grc.model.RiskAssessmentSort;
import com.depth1.grc.model.RiskAssessment;
import com.depth1.grc.model.RiskAssessmentDao;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.model.UserProfile;
import com.depth1.grc.model.UserProfileDao;
import com.depth1.grc.security.BCrypt;
import com.depth1.grc.util.DateUtility;
import com.depth1.grc.util.IdProducer;
import com.depth1.grc.model.Policy;
import com.depth1.grc.model.PolicyDao;
import com.depth1.grc.model.PolicySort;
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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.Logger;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Application extends Controller {

	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory.getDaoFactory(DaoFactory.CASSANDRA);
	static DaoFactory rdbFactory = DaoFactory.getDaoFactory(DaoFactory.MARIADB);
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
	 * Deletes a Tenant
	 * @param tenantId is the id of the tenant to delete
	 * @return Result of the delete tenant
	 */
	public static Result deleteTenant(long tenantId) {
		boolean deleted = false;
		try {
			TenantDao tenant = cassandraFactory.getTenantDao();
			deleted = tenant.deleteTenant(tenantId);
			if (deleted) {
				Logger.info("Tenant profile deleted successfully.");
			} else {
				Logger.info("Tenant profile not deleted.");
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while deleting a tenant ", e);
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
	 * This method is used as a client to test getting data from the Cassandra
	 * database It displays data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * @return a result in the future in an non-blocking fashion
	 */
	public static Result getCountry() {
		
		try {
			DropDownList dropDown = cassandraFactory.getDropDownList();
			List<String> countries = dropDown.getCountry();
			Collections.sort(countries);
			for (String row : countries) {
				System.out.printf(" %s\n", row);						
			}

		} catch (DataException e) {

		}
		return ok();

	}	

	/**
	 * This method is used as a client to test getting data from the Cassandra
	 * database It displays data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * @return a result in the future in an non-blocking fashion
	 */
	public static Result getState(String countryCode) {
		
		try {
			DropDownList dropDown = cassandraFactory.getDropDownList();
			List<String> states = dropDown.getState(countryCode);
			Collections.sort(states);
			for (String row : states) {
				System.out.printf(" %s\n", row);						
			}

		} catch (DataException e) {

		}
		return ok();

	}

	

	/**
	 * Gets all the tenants in the tenant  table.
	 * 
	 * @return Result of all the tenants in the table
	 */
	public static Result getTenant(long tenantId) {
		try {
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			Tenant list = tenantDao.getTenant(tenantId);
			Map<String, String> phones = list.getPhones();
			String direct = phones.get("Direct");
			String main = phones.get("Main");
			String dateFormat = "yyyy-dd-MM HH:mm:ss";
			Date dateTime = list.getCreateDateUtil();
			String outTimestamp = Messages.get("date.out.timestamp.format");
			DateFormat df = new SimpleDateFormat(outTimestamp);

		} catch (DaoException e) {
			Logger.error("Error occurred while reading a tenant data ", e);
		}

		return redirect("/riskAssessment/1/10/descendingRisk");
	}
	
	/**
	 * Gets all the tenants in the tenant  table.
	 * 
	 * @return Result of all the tenants in the table
	 */
	public static Result getTenant(String name) {
		try {
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			Tenant list = tenantDao.getTenant(name);
			Map<String, String> phones = list.getPhones();
			String direct = phones.get("Direct");
			String main = phones.get("Main");
			String dateFormat = "yyyy-dd-MM HH:mm:ss";
			Date dateTime = list.getCreateDateUtil();
			String outTimestamp = Messages.get("date.out.timestamp.format");
			DateFormat df = new SimpleDateFormat(outTimestamp);				
		} catch (DaoException e) {
			Logger.error("Error occurred while reading a tenant data ", e);
		}

		return ok();
	}
	
	/**
	 * This method is used as a client to test getting data from the Cassandra
	 * database It displays data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * @return a result in the future in an non-blocking fashion
	 */
	public static Result getTimezone() {
		
		try {
			DropDownList dropDown = rdbFactory.getDropDownList();
			List<String> timezones = dropDown.getTimezone();
			//Collections.sort(titles);
			for (String row : timezones) {
				System.out.printf(" %s\n", row);						
			}

		} catch (DataException e) {

		}
		return ok();

	}		
	
	/**
	 * This method is used as a client to test getting data from the Cassandra
	 * database It displays data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * @return a result in the future in an non-blocking fashion
	 */
	public static Result getTitle() {
		
		try {
			DropDownList dropDown = cassandraFactory.getDropDownList();
			List<String> titles = dropDown.getTitle();
			Collections.sort(titles);
			for (String row : titles) {
				System.out.printf(" %s\n", row);						
			}

		} catch (DataException e) {

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
	 * Gets all the tenants in the tenant  table.
	 * 
	 * @return Result of all the tenants in the table
	 */
	public static Result listTenant() {
		try {
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			List<Tenant> tenantList = tenantDao.listTenant();
			for (Tenant list : tenantList) {
				Map<String, String> phones = list.getPhones();
				//String direct = map.get("Direct");
				//String main   = map.get("Main");
				String dateFormat = "yyyy-dd-MM HH:mm:ss";
				Date dateTime = list.getCreateDateUtil();
				String outTimestamp = Messages.get("date.out.timestamp.format");
				DateFormat df = new SimpleDateFormat(outTimestamp);
				Logger.info("Tenant Created On: " +  DateUtility.formatDateFromUuid(dateFormat, dateTime));
				Logger.info("Tenant UUID: " + list.getId());
				Logger.info("Tenant Id: " + list.getTenantId());
				Logger.info("Tenant Name: " + list.getName());
				Logger.info("Tenant Type: " + list.getType());
				Logger.info("Tenant Stret1: " + list.getStreet1());
				Logger.info("Tenant Street2: " + list.getStreet2());
				Logger.info("Tenant City: " + list.getCity());
				Logger.info("Tenant Zipcode: " + list.getZipcode());
				Logger.info("Tenant State: " + list.getState());
				Logger.info("Tenant Province: " + list.getProvince());
				Logger.info("Tenant Country: " + list.getCountry());
				Logger.info("Tenant Latitude: " + list.getLatitude());
				Logger.info("Tenant Longitude: " + list.getLongitude());
				Logger.info("Tenant Contact Person: " + list.getContactPersonName());
				Logger.info("Tenant Contact Person Email: " + list.getContactPersonEmail());
				//Logger.info("Tenant Main Phone:  " + main);
				//Logger.info("Tenant Direct Line:  " + direct);
				Logger.info("Tenant Service Start Date: " + DateUtility.toString(list.getServiceStartDate(), df));
				Logger.info("Tenant Web Address: " + list.getCompanyUrl());
				Logger.info("Tenant IP Address: " + list.getIpaddress());
				Logger.info("Tenant Status: " + list.getStatus());
				
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while reading a tenant data ", e);
		}

		return ok();
	}
	
    /**
	 * Updates a tenant profile
	 * @param tenant Tenant to create
	 * @return Result of the tenant created
	 */
	public static Result updateTenant(Tenant tenant) {
		try {
			TenantDao profile = cassandraFactory.getTenantDao();
			profile.updateTenant(tenant);
		} catch (DaoException e) {
			Logger.error("Error occurred while updating a tenant ", e);
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

			Logger.error("Error occurred while creating tenant ", e);
		}
		return ok();
	}
	
	
	/**
	 * Adds a Tenant to the database from form information
	 * @return a redirect to the main tenant page
	 */
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
	 * Adds a User Profile to the Database
	 * 
	 * @return redirect to the User Profile Front Page
	 */
	public Result addUserProfile() {
		Form<UserProfile> filledUserProfile = userProfileForm.bindFromRequest();
		UserProfile criteria = filledUserProfile.get();
		
		try {
			UserProfileDao userProfileDao = cassandraFactory.getUserProfileDao();
					
			userProfileDao.createUserProfile(criteria);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating user profile criteria ", e);
		}
		return redirect("/userprofile/1/10/descendingName");
	}

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
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			// create policy on DB
			policyDao.createPolicy(criteria);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy ", e);
		}
		if (filledPolicy.field("policybody").value() != null) {
			savePolicyBodyDocument(criteria.getName(), filledPolicy.field("policybody"));
		}
		return redirect("/policy/1/10/descendingName");
	}

	/**
	 * Creates strategic objective using JPA.
	 * 
	 * @return
	 */
	public Result createSO() {
		StrategicObjective objective;
		JpaStrategicObjectiveDao obj;
		try {
			
			objective = new StrategicObjective(18L, "Strategy 2020", "Best Enterprise Risk Management Software");
			 
			Measure measure1 = new Measure(18L, "40% in customer base", objective);
			Measure measure2 = new Measure(18L, "35% increase in revenue", objective);
			Measure measure3 = new Measure(18L, "40% increase in profit", objective);
			Measure measure4 = new Measure(18L, "Minimize software defect by 95% in 2016", objective);
			Measure measure5 = new Measure(18L, "Expand sales to Europe and EMEA regions in 2016", objective);
			Measure measure6 = new Measure(18L, "Expand sales to APAC region in 2017", objective);
			Set<Measure> measureSet = Collections.synchronizedSet(new LinkedHashSet<Measure>());
			measureSet.add(measure1);
			measureSet.add(measure2);
			measureSet.add(measure3);
			measureSet.add(measure4);
			measureSet.add(measure5);
			measureSet.add(measure6);
			obj = new JpaStrategicObjectiveDao();
			obj.createStrategicObjective(objective, measureSet);
			Logger.info("Data Transaction persisted successfully.");
			Logger.info("Strategic Objective ID="+objective.getObjectiveId());
		} catch (Exception e) {
			Logger.error("Error occured while persisting data in the data store.");
		}	
		return ok();
		}
	
	/**
	 * Deletes a policy.
	 * 
	 * @param UUID of policy
	 * @return delete policy page
	 */
	public Result deletePolicy() {
		boolean result = false;
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			result = policyDao.deletePolicy(selectedPolicy.getId());
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Delete Page ", e);
		}		
		return redirect("/policy/1/10/descendingName");
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
	 * Downloads a policy to a file
	 * @param policy name
	 * @return creates a policy file
	 */
	public Result downloadPolicy(String name){
		String filepath = "public/policyDocuments/" + name;
		return ok(new java.io.File(filepath));

	}
	private String getPolicyBodyAsString(String name){
		String policyBody = "";
		try {
			policyBody = new String(Files.readAllBytes(Paths.get("public/policyDocuments/" + name)));
		} catch(IOException e){
				Logger.error("Error reading policy body file into string: ", e);
		}
		return policyBody;
	}
	/**
	 *  Returns a list of tenants in the database
	 * @return JSON object that is a list of Tenants in the database
	 */
	public Result getTenantNames(){
		
		TenantDao tenantDao = null;
		List<Tenant> list = null;
		try {
			tenantDao = cassandraFactory.getTenantDao();
			list =  tenantDao.listTenant();
		} catch (DaoException e) {
			Logger.error(
					"Error reading Tenant IDs ",e);
		}
		
		ObjectNode result = Json.newObject();
		
		for(Tenant tenant: list)
		{
			result.put(tenant.getName(), String.valueOf(tenant.getTenantId()) );
		}
		
	    return ok(result);
	}
	public Result index() {
		// test JPA
		createSO();
		return ok(index.render()); 
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
	 * This method prints data retrieved from the Cassandra database It displays
	 * data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * 
	 */

	public void printState(Session session) {
		/*ResultSetFuture result = getState(session);
		for (Row row : result.getUninterruptibly()) {
			System.out.printf("%s: %s  %s\n", row.getString("country"),
					row.getString("short_name"), row.getString("long_name"));
		}
		session.close();*/

	}
	
	
	/**
	 * Restores a policy 
	 * @param UUID of policy
	 * @return restore policy page
	 */
	public Result restorePolicy() {
		boolean result = false;
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			result = policyDao.restorePolicy(selectedPolicy.getId());
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Restore Page ", e);
		}
		return redirect("/policy/restore/1/10/descendingName");
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
			writer.print(policyBody.value());
			writer.close();
			Logger.info("Policy Body Documented at " + dirString + fileName);
		}
		catch (IOException e) {
			Logger.error("Error while storing the policy body document " + e);
		}
	}		
	
	/**
	 * Policy
	 */
	public Result setSelectedPolicy() {
		
		JsonNode node = request().body().asJson().get("val");
		
		 if(node == null){
		      return badRequest("empty json"); 
		    }
		String inputString = node.textValue();
		int index = Integer.parseInt(inputString);		
		selectedPolicy = policies.get(index);
		return ok();
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
	 * Sets the the User Profile Selected on the Front Page as the selectedUserProfile
	 * 
	 * @return a message that the JSON was received ok
	 */
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
	 * Shows a create policy page 
	 * @param void
	 * @return policy create page
	 */
	public Result showCreatePolicyPage() {

		return ok(createPolicy.render(policyForm));
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
	 * Shows the Tenant Creation Page
	 * @return Result of the tenant creation
	 */
	public Result showCreateTenant() {
		
		return ok(createTenant.render());
	}
	/**
	 * Shows the Create User Profile Page
	 * 
	 * @return User Profile Create Page
	 */
	public Result showCreateUserProfile() {
		
		return ok(createUserProfile.render());
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
	 *  Shows the list of Tenants in the Database on the FrontTenant Page
	 * @param page current page number form pagination
	 * @param view the number of items to show per page
	 * @param order the sorting order of the page
	 * @param query the string to search for in the tenant list
	 * @return Result of the List Page
	 */
	public Result showFrontTenantPage(int page, int view, String order, String query){
		int size = 0;
		
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
	 * Shows the front page of the User Profile
	 * 
	 * @param page current page number for Pagination
	 * @param view current number of items for Pagination
	 * @param order current sorting order for Pagination
	 * @param query any search query used to filter user profile list
	 * @return page to display the front page of the User Profile
	 */
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

	/**
	 * Shows a list of policy - Front page for Policy 
	 * @param void
	 * @return policy list page
	 */
	public Result showPolicyListPage() {

		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			;
			policies = policyDao.viewAllPolicy();
			
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(policyListPage.render(policies, 1));
	}
		
	public Result showPolicyListPageQuery(int page, int view, String order, String query){
		int size = 0;
		try {
			PolicySort policySort = new PolicySort();
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllPolicy();
			size = policies.size();
			if(query.compareTo("")!= 0){
				policies = policySort.filterDataByQuery(policies, query);
				size = policies.size();
				
			}
			if(size > 0){
				policies = policySort.sortPolicy(policies, order);
				policies = policySort.paginatePolicy(policies, view, page);
			}
			
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(policyListPage.render(policies, size));
	}
	
	/**
	 * Shows a restore policy page 
	 * @param void
	 * @return policy restore page
	 */
	public Result showRestorePolicyPage(int page, int view, String order, String query){
		int size = 0;
		try {
			PolicySort policySort = new PolicySort();
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllDeletedPolicy();
			size = policies.size();
			if(query.compareTo("")!= 0){
				policies = policySort.filterDataByQuery(policies, query);
				size = policies.size();
				
			}
			if(size > 0){
				policies = policySort.sortPolicy(policies, order);
				policies = policySort.paginatePolicy(policies, view, page);
			}
			
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(restorePolicy.render(policies, size));
	}
	
	/**
	 * Shows a update policy page 
	 * @param void
	 * @return policy update page
	 */
	public Result showUpdatePolicyPage() {
		
		String policybody = getPolicyBodyAsString(selectedPolicy.getName());
		return ok(updatePolicy.render(selectedPolicy, policybody));
		
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
	 * Shows the UpdateTenant Page with the Tenant Information Populating
	 * the fields.
	 * @return Result of updating the Tenant information
	 */
	public Result showUpdateTenant() {

		return ok(updateTenant.render(selectedTenant));
	}

	/**
	 * Shows a view policy page 
	 * @param void
	 * @return policy view page
	 */
	public Result showViewPolicyPage(UUID id) {		

		PolicyDao policyDao;
		try {
			policyDao = cassandraFactory.getPolicyDao();
			final Policy policy = policyDao.viewPolicyById(id);
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
	 * This method allows users to view Risk Assessments
	 * 
	 * @return view Risk Assessment page
	 */
	public Result showViewRAPage() {

		return ok(viewRA.render(selectedRA));
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
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			// create policy on DB
			policyDao.updatePolicy(selectedPolicy.getId(), criteria);
			if (filledPolicy.field("policybody").value() != null) {
				savePolicyBodyDocument(criteria.getName(), filledPolicy.field("policybody"));
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy ", e);
		} catch (IllegalArgumentException e) {
			Logger.error("Invalid UUID");
			return badRequest("Invalid UUID");
		}

		return redirect("/policy/1/10/descendingName");
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
			Logger.error("Error occurred while updating risk assessment criteria ",	e);
		}

		return redirect("/tenant/1/10/descendingName");
	}
	
	public Result uploadFilePolicyBody(){
		FilePart uploadedFile = request().body().asMultipartFormData().getFile("file");
		if(uploadedFile == null){
		    return badRequest("empty file"); 
		    }
		File file = uploadedFile.getFile();
		String fileName = uploadedFile.getFilename();
		
		return ok();
	}


}
