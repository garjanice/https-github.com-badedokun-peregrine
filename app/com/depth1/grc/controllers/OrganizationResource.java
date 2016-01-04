
package com.depth1.grc.controllers;

import java.util.List;
import java.util.UUID;

import com.depth1.grc.exception.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Department;
import com.depth1.grc.model.DepartmentDao;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.model.TenantSort;
import com.depth1.grc.model.UserProfile;
import com.depth1.grc.model.UserProfileDao;
import com.depth1.grc.model.UserProfileSort;
import com.depth1.grc.views.html.createTenant;
import com.depth1.grc.views.html.createUserProfile;
import com.depth1.grc.views.html.frontTenant;
import com.depth1.grc.views.html.frontUserProfile;
import com.depth1.grc.views.html.updateTenant;
import com.depth1.grc.views.html.viewTenant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author badedokun
 *
 */
public class OrganizationResource extends Controller {
	
	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory.getDaoFactory(DaoFactory.CASSANDRA);
	static DaoFactory rdbFactory = DaoFactory.getDaoFactory(DaoFactory.MARIADB);	

	static List<UserProfile> userProfiles;
	static UserProfile selectedUserProfile;
	final static Form<UserProfile> userProfileForm = Form.form(UserProfile.class);
	static List<Tenant> tenants;
	final static Form<Tenant> tenantForm = Form.form(Tenant.class);
	static Tenant selectedTenant;

	/**
	 * 
	 */
	public OrganizationResource() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a user profile
	 * @param user User to create
	 * @return Result of the user created
	 */
	public static Result createDepartment(Department dept) {
		try {
			DepartmentDao department = cassandraFactory.getDepartmentDao();
			department.createDepartment(dept);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating a department ", e);
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
	 * Gets all the users in the user profile table.
	 * 
	 * @return Result of all the users in the table
	 */
	public static Result findUserProfile(String username, String lastName, long tenantId) {
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			UserProfile user = profile.findUserProfile(username, lastName, tenantId);
			// TODO - use the user variable to extract relevant information to display
			
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
	public static Result findUserProfile(UUID id) {
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			UserProfile user = profile.findUserProfile(id);
			// TODO - use the user variable to extract relevant information to display			
			
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
	 * Gets a user that matches user ID search criteria.
	 * 
	 * @return Result of all the users in the table
	 */
	public static Result getUserProfile(UUID userId) {
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			UserProfile user = profile.findUserProfile(userId);
		} catch (DaoException e) {
			Logger.error("Error occurred while finding a user ", e);
		}
	
		return ok();
	}

	/**
	 * Gets a user that matches search criteria of username and lastname.
	 * 
	 * @return Result of all the users in the table
	 */
	public static Result getUserProfile(String username, String lastname, long tenantId) {
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			UserProfile user = profile.findUserProfile(username, lastname, tenantId);
			// TODO - use the user variable to extract relevant information to display
			
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
	public static Result deleteUserProfile(String username, long tenantId) {
		boolean deleted = false;
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			deleted = profile.deleteUserProfile(username, tenantId);
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
	 * Gets all the tenants in the tenant  table.
	 * 
	 * @return Result of all the tenants in the table
	 */
	public static Result getTenant(long tenantId) {
		try {
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			Tenant list = tenantDao.getTenant(tenantId);
			// TODO - use the list variable to extract relevant information to display
	
		} catch (DaoException e) {
			Logger.error("Error occurred while reading a tenant data ", e);
		}
	
		return redirect("/riskAssessment/1/10/descendingRisk");
	}

	/**
	 * Gets all the users in the user profile table.
	 * 
	 * @return Result of all the users in the table
	 */
	public static Result listProfile(long tenantId) {
		try {
			UserProfileDao profile = cassandraFactory.getUserProfileDao();
			List<UserProfile> userList = profile.listUserProfile(tenantId);
			userList.forEach(results-> {
				// TODO - use the results variable to extract relevant information to display
				
			});
			
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
				// TODO - use the list variable to extract relevant information to display				
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
	 * Gets all the tenants in the tenant  table.
	 * 
	 * @return Result of all the tenants in the table
	 */
	public static Result getTenant(String name) {
		try {
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			// TODO - extract information to display using the tenantDao variable		
		} catch (DaoException e) {
			Logger.error("Error occurred while reading a tenant data ", e);
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
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			tenantDao.createTenant(criteria);
		} catch (Exception e) {
			Logger.error("Error occurred while creating risk assessment criteria ",	e);
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
	 * Deletes the selected Tenant from the database.  Uses Ajax and JSON.
	 * Shows the FrontTenant Page.
	 * @return Result of the deleting the Tenant.
	 */
	public Result deleteTenant() {
		try {
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			tenantDao.deleteTenant(selectedTenant.getTenantId());
		} catch (DaoException e) {
			Logger.error("Error occurred while deleting tenant ", e);
		}

		return redirect("/tenant/1/10/descendingName");
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
			Logger.error("Error reading Tenant IDs ", e);
		}
		
		ObjectNode result = Json.newObject();

		for(Tenant tenant: list)
		{
			result.put(tenant.getName(), String.valueOf(tenant.getTenantId()) );
		}

		return ok(result);
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
	public Result showFrontUserProfile(int page, int view, String order, String query, long tenantId){
		int size = 0;
		try {
			UserProfileDao userProfileDao = cassandraFactory.getUserProfileDao();
			UserProfileSort userProfileSort = new UserProfileSort();
			userProfiles = userProfileDao.listUserProfile(tenantId);
			
			size = OrganizationResource.userProfiles.size();
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
			TenantDao tenantDao = cassandraFactory.getTenantDao();
			tenantDao.updateTenant(criteria);
			
		} catch (DaoException e) {
			Logger.error("Error occurred while updating risk assessment criteria ",	e);
		}

		return redirect("/tenant/1/10/descendingName");
	}
	
	/**
	 * Shows the UpdateTenant Page with the Tenant Information Populating
	 * the fields.
	 * @return Result of updating the Tenant information
	 */
	public Result showUpdateTenant() {
		return ok(updateTenant.render(selectedTenant));
	}	

}
