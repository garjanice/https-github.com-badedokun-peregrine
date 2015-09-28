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
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.utils.UUIDs;
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
import com.depth1.grc.util.DateUtility;
import com.depth1.grc.views.html.createRA;
import com.depth1.grc.views.html.frontRA;
import com.depth1.grc.views.html.index;
import com.depth1.grc.views.html.updateRA;
import com.depth1.grc.views.html.viewRA;
import com.fasterxml.jackson.databind.JsonNode;

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
	final static Form<RiskAssessment> rAForm = Form.form(RiskAssessment.class);
	static List<RiskAssessment> riskAssessments;
	static RiskAssessment selectedRA;

	public Result index() {
		// user profile test data
		UserProfile user = new UserProfile();
		//user.setId(java.util.UUID.fromString("8b170dad-2ddf-4ab1-9509-ee7370a4f9f6"));
		user.setId(java.util.UUID.randomUUID());
		user.setFname("Adebisi");
		user.setLname("Adedokun");
		user.setMinitial("BA");
		user.setPfname("Bisi");
		user.setTitle("Chief Architect");
		user.setSalutation("Mr.");
		user.setUsername("badedokun@acenonyx.com");
		user.setPassword("Core2512Java");
		user.setEmail("badedokun@acenonyx.com");		
		user.setGender("Male");
		user.setLineofdefense("Active");
		user.setLanguage("en_US");
		user.setLocale("en_US");
		user.setTimeZone("GMT-5");
		user.setStreet1("56 Wellington Rd");
		user.setStreet2("Suite 2");
		user.setCity("East Brunswick");
		user.setState("NJ");
		user.setProvince("  ");
		user.setZipcode("08816");
		user.setCountry("USA");
		user.setLongitude("-74.416855");
		user.setLatitude("40.455281");
		String workPhone = "201-593-3518";
		String mobilePhone = "732-874-7610";
		String work = "Work";
		String mobile = "Mobile";
		Map<String, String> phones = new HashMap<String, String>();
		phones.put(work, workPhone);
		phones.put(mobile, mobilePhone);		
		user.setPhones(phones);
		user.setStatus("Active");

		Logger.info("user profile populated ");
		Logger.info("username = " + user.getUsername() + " password = " + user.getPassword());
		
		createUserProfile(user);
		
		// Tenant test data
		Tenant tenant = new Tenant();
		String value = "09/27/2015";
		String format = Messages.get("date.in.date.format");
		Logger.info("The date format is: " + format);
		try {
			Timestamp date = DateUtility.toTimestamp(value, format);
			tenant.setServiceStartDate(date);
			Logger.info("service start date set to: " + date);
			
		} catch (ParseException p) {

		}
		tenant.setId(java.util.UUID.randomUUID());
		tenant.setName("Acenonyx");
		tenant.setType("Software");
		tenant.setContactPersonName("Jerry Skidmore");
		tenant.setContactPersonEmail("jskidmore@acenonyx.com");
		tenant.setCompanyUrl("http://www.acenonyx.com");
		tenant.setStreet1("56 Wellington Rd");
		tenant.setStreet2("Suite 2");
		tenant.setCity("East Brunswick");
		tenant.setState("NJ");
		tenant.setProvince("  ");
		tenant.setZipcode("08816");
		tenant.setCountry("USA");
		tenant.setLongitude("-74.416855");
		tenant.setLatitude("40.455281");
		String mainPhone = "732-651-7610";
		String directLine = "732-874-7610";
		String main = "Main";
		String direct = "Direct";
		Map<String, String> bphones = new HashMap<String, String>();
		Map<String, String> cphones = new HashMap<String, String>();
		bphones.put(main, mainPhone);
		bphones.put(direct, directLine);		
		tenant.setPhones(bphones);
		cphones.put(main, mainPhone);
		cphones.put(direct, directLine);
		tenant.setStatus("Active");
		tenant.setContactPersonPhones(cphones);
		tenant.setIpaddress("10.25.3.5");
		
		Logger.info("tenant profile populated ");
		Logger.info("tenant name = " + tenant.getName() + " type = " + tenant.getType());
		long tenantId = 1443395694601L;
		createTenant(tenant);
		//listTenant();
		getTenant(tenantId);

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
	 * @param tenant The tenant to create
	 * @return Result of the tenant creation
	 */
	public static Result createTenant(Tenant tenant) {
		try {
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			tenantDao.createTenant(tenant);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating tenant ", e);
		} catch (ParseException p) {
			Logger.error("Error occurred while parsing date format", p);
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
	 * Gets all the tenants in the tenant  table.
	 * 
	 * @return Result of all the tenants in the table
	 */
	public static Result getTenant(long tenantId) {
		try {
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			Tenant list = tenantDao.getTenant(tenantId);
			Map<String, String> phones = list.getPhones();
			// String direct = map.get("Direct");
			// String main = map.get("Main");
			String dateFormat = "yyyy-dd-MM HH:mm:ss";
			Date dateTime = list.getCreateDateUtil();
			String outTimestamp = Messages.get("date.out.timestamp.format");
			DateFormat df = new SimpleDateFormat(outTimestamp);
			Logger.info("Tenant Created On: " + DateUtility.formatDateFromUuid(dateFormat, dateTime));
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
			// Logger.info("Tenant Main Phone: " + main);
			// Logger.info("Tenant Direct Line: " + direct);
			Logger.info("Tenant Service Start Date: " + DateUtility.toString(list.getServiceStartDate(), df));
			Logger.info("Tenant Web Address: " + list.getCompanyUrl());
			Logger.info("Tenant IP Address: " + list.getIpaddress());
			Logger.info("Tenant Status: " + list.getStatus());
				

		} catch (DaoException e) {
			Logger.error("Error occurred while reading a tenant data ", e);
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

}
