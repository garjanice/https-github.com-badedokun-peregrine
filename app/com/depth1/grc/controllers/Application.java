package com.depth1.grc.controllers;
import java.util.Collections;
import java.util.Comparator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
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
import com.depth1.grc.views.html.createRA;
import com.depth1.grc.views.html.frontRA;
import com.depth1.grc.views.html.index;
import com.depth1.grc.views.html.updateRA;
import com.depth1.grc.views.html.viewRA;
import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Application extends Controller {

	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory
			.getDaoFactory(DaoFactory.CASSANDRA);
	final static Form<RiskAssessment> rAForm = Form.form(RiskAssessment.class);
	static List<RiskAssessment> riskAssessments;
	static RiskAssessment selectedRA;

	public Result index() {
		// test connection to the cassandra cluster

		/*
		 * CassandraPoolImpl con = new CassandraPoolImpl(); Session session =
		 * con.create(); System.out.println();
		 * System.out.println("Test client to display state");
		 * System.out.println("============================");
		 * printState(session); session.close();
		 */
		
		//test data for various behaviors (methods) of UserProfile
		
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
		String workPhone = "732-593-3518";
		String mobilePhone = "732-874-7610";
		String work = "Work";
		String mobile = "Mobile";
		Map<String, String> phones = new HashMap<String, String>();
		phones.put(work, workPhone);
		phones.put(mobile, mobilePhone);		
		user.setPhones(phones);
		user.setStatus("Active");
		Calendar calendar = Calendar.getInstance();
	    java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
		user.setCreatedate(timestamp);
		Logger.info("user profile populated ");
		Logger.info("username = " + user.getUsername() + " password = " + user.getPassword());
		
		//createUserProfile(user);
		updateUserProfile(user);
		
		//String username = "badedokun@acenonyx.com";
		//boolean deleted = false;
		//deleteUserProfile(username);
		//listProfile();
		getUserProfile(user.getUsername(), user.getLname());
		//getUserProfile(user.getId());
		
		Logger.info("create user invoked successfully ");

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
				Logger.info("user firsname =  " + list.getFname());
				Logger.info("user lastname =  " + list.getLname());
				Logger.info("user title =  " + list.getTitle());
				Logger.info("user email =  " + list.getEmail());
				Logger.info("user language =  " + list.getLanguage());
				Logger.info("user locale =  " + list.getLocale());
				Logger.info("user timezone =  " + list.getTimeZone());
			}
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
			
			//Logger.info("user profile create date: " +  format.format(time));
			Logger.info("user profile create on: " +  DateUtil(dateFormat, dateTime));
			Logger.info("user Salutation =  " + user.getSalutation());
			Logger.info("user firsname =  " + user.getFname());
			Logger.info("user p firstname =  " + user.getPfname());
			Logger.info("user m initials =  " + user.getMinitial());
			Logger.info("user lastname =  " + user.getLname());
			Logger.info("user title =  " + user.getTitle());
			Logger.info("user email =  " + user.getEmail());
			Logger.info("user work phone =  " + work);
			Logger.info("user mobile phone =  " + mobile);
			Logger.info("Password =  " + user.getPassword());
			Logger.info("latitude =  " + user.getLatitude());
			Logger.info("Longitude =  " + user.getLongitude());
			Logger.info(user.getStreet1() + " " + user.getStreet2() + " " + user.getCity() + ", " + user.getState() + " " + user.getZipcode());

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

			Logger.info("User Id =  " + user.getId());
			Logger.info("user firsname =  " + user.getFname());
			Logger.info("user lastname =  " + user.getLname());
			Logger.info("user email =  " + user.getEmail());
			Logger.info("Password =  " + user.getPassword());
			Logger.info("latitude =  " + user.getLatitude());
			Logger.info("Longitude =  " + user.getLongitude());
			Logger.info(user.getStreet1() + " " + user.getStreet2() + " " + user.getCity() + ", " + user.getState() + " " + user.getZipcode());

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
