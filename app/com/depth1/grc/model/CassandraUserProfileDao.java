/**
 * 
 */
package com.depth1.grc.model;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Assignment;
import com.datastax.driver.core.querybuilder.Delete.Where;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.utils.UUIDs;
import com.depth1.grc.db.util.DataReaderUtil;
import com.depth1.grc.exception.DaoException;
import com.depth1.grc.model.common.Keyspace;
import com.depth1.grc.security.BCrypt;
import com.depth1.grc.util.DateUtility;

import play.Logger;
import play.Play;

/**
 * This class implements the Data Access Object pattern (GoF). It provides capability to create, read, update, delete 
 * a User Profile - the typical CRUD functions in any business application.
 * 
 * @author Bisi Adedokun
 * Create Date: 07/27/2015
 */

public class CassandraUserProfileDao implements UserProfileDao {
	
	//select the type of deployment model from the configuration file
	private final static Boolean keyspace = Play.application().configuration().getBoolean("onpremise.deploy.model");

	
	/**
	 * Authenticates a user with username and password. Username is email address.
	 * @param username user name of the user to authenticate
	 * @param password password of the user to authenticate
	 * @return login credentials of the authenticated user
	 * @throws DaoException if errors occurs while authenticating a user
	 */
	@Override
	public Login authenticate(String username, String password) throws DaoException {
		Login login = new Login();
		try {
			Statement find = QueryBuilder.select().all()
					.from(Keyspace.valueOf(keyspace), "userauth")
					.where(eq("username", username));
			ResultSet result = CassandraDaoFactory.getSession().execute(find);
			if (result == null) {
				return null;
			}
			Row row = result.one();

			// get data elements from the Result set
			if (BCrypt.checkpw(password, row.getString("hash"))) {
				login.setUsername(row.getString("username"));
			} else {
				Logger.error("Hash value does not match");
				return null;
			}
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving data from the userauth table ", e);
		} finally {
			// close the connection to the database();

			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return login;
	}
	
	/**
	* Creates a user authentication profile.
	* 
	* @param user user profile to create
	* @throws DaoException if error occurs while creating the User in the data store
	*/
	//@Override
	private void createUserAuth(UserProfile user) throws DaoException {
					
		try {
			if (user.getUsername()!= null && user.getPassword() != null && user.getUsername().length() < 100) {
			Statement insert = QueryBuilder
					.insertInto(Keyspace.valueOf(keyspace), "userauth")
					.value("id", user.getUserProfileId())
					.value("tenantid", user.getTenantId())
					.value("fname", user.getFirstName())
					.value("lname", user.getLastName())
					.value("username", user.getUsername())
					.value("hash", BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)))
					.value("createdate", UUIDs.timeBased()); //Timestamp.valueOf(LocalDateTime.now())	
					CassandraDaoFactory.getSession().execute(insert);
			}		
			
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting user profile in the user profile table ", e);
		} finally {
			//close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
	}	

	/**
	* Creates a user profile.
	* 
	* @param user user profile to create
	* @throws DaoException if error occurs while creating the User in the data store
	*/
	@Override
	public void createUserProfile(UserProfile user) throws DaoException {

		UUID id = java.util.UUID.randomUUID();
		user.setUserProfileId(id);
		try {
			Statement insert = QueryBuilder
					.insertInto(Keyspace.valueOf(keyspace), "userprofile")
					.value("id", user.getUserProfileId())
					.value("tenantid", user.getTenantId())
					.value("fname", user.getFirstName())
					.value("pfname", user.getPreferredFirstName())
					.value("minitial", user.getMiddleInitial())
					.value("lname", user.getLastName())
					.value("title", user.getTitle())
					.value("salutation", user.getSalutation())
					.value("username", user.getUsername())
					.value("password", user.getPassword())
					.value("email", user.getEmail())
					.value("gender", user.getGender())
					.value("street1", user.getStreet1())
					.value("street2", user.getStreet2())
					.value("city", user.getCity())
					.value("zipcode", user.getZipcode())
					.value("state", user.getState())
					.value("province", user.getProvince())
					.value("country", user.getCountry())
					.value("phones", user.getPhones())
					.value("lineofdefense", user.getLineOfDefense())
					.value("createdate", user.getCreateDate()) //Timestamp.valueOf(LocalDateTime.now())
					.value("latitude", user.getLatitude())
					.value("longitude", user.getLongitude())
					.value("timezone", user.getTimeZone())
					.value("language", user.getLanguage())
					.value("locale", user.getLocale())
					.value("deptid", user.getDeptId())
					.value("deptname", user.getDeptName())
					.value("lodfunctionid", user.getLodFunctionId())
					.value("lodfunction", user.getLodFunction())
					.value("status", user.getStatus());	
					CassandraDaoFactory.getSession().execute(insert);
					createUserAuth(user); // create user login credentials
					
			
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting user profile in the user profile table ", e);
		} finally {
			//close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}

	}


	/**
	 * Deletes a user profile from the user profile table
	 * 
	 * @param username username of the user to delete
	 * @return boolean True if the delete is successful, false otherwise
	 * @throws DaoException if error occurs while deleting a user profile from the user profile table
	 */
	@Override
	public boolean deleteUserProfile(String username, long tenantId) throws DaoException {
		boolean del = false;
		try {					
			Where delete = QueryBuilder.delete()
					.from(Keyspace.valueOf(keyspace), "userprofile")
					.where(eq("username", username))
					.and(eq("tenantid", tenantId));

			CassandraDaoFactory.getSession().execute(delete);
			del = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while deleting user profile from the user profile table ", e);
		} finally {
			//close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return del;
	}
	
	/**
	 * Finds a user profile.
	 * 
	 * @param username login username of the user
	 * @param lastname last name of the user
	 * @return UserProfile user profile that was found
	 * @throws DaoException if error occurs while finding a user profile in the data store
	 */
	@Override
	public UserProfile findUserProfile(String username, String lastname, long tenantId) throws DaoException {
		UserProfile user = null;
		try {					
			
			ResultSetFuture result = getOneUserProfile(username, lastname, tenantId);
			if (result == null) {
				return null;
			}
			// get data elements from the Result set
			for (Row row : result.getUninterruptibly()) {
				user = new UserProfile();
				user = setUserAttributes(user, row);								
			}
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving user profile data from the user profile table ", e);
		} finally {
			// close the connection to the database
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		
		return user;
	}	
		
	
	/**
	 * Finds a user profile.
	 * 
	 * @param userId user profile ID to find
	 * @return UserProfile user profile that was found
	 * @throws DaoException if error occurs while finding a user profile in the data store
	 */
	@Override
	public UserProfile findUserProfile(UUID userId) throws DaoException {
		UserProfile user = null;
		try {					
			
			ResultSetFuture result = getOneUserProfile(userId);
			if (result == null) {
				return null;
			}
			// get data elements from the Result set
			for (Row row : result.getUninterruptibly()) {
				user = new UserProfile();
				user = setUserAttributes(user, row);								
			}

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving user profile data from the user profile table ", e);
		} finally {
			// close the connection to the database
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		
		return user;
	}
	
	/**
	 * Get a user profile that matches the given criteria of username and lastname
	 * 
	 * @return a row that matches the user profile
	 * @throws DaoException if error occurs while getting user profiles from the user profile table
	 */
	private ResultSetFuture getOneUserProfile(String username, String lastname, long tenantId) {
		Select.Where select = null;
		try {
		 select = QueryBuilder.select()
				.all()
				.from(Keyspace.valueOf(keyspace), "userprofile")
				.where(eq("username", username))
				.and(eq("lname", lastname))
				.and(eq("tenantid", tenantId));
				
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving a user profile from the user profile table ", e);
		} finally {
			// close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return CassandraDaoFactory.getSession().executeAsync(select);
		
	}
	
	/**
	 * Gets a user profile that matches the given criteria of userId
	 * 
	 * @return a row that matches the user profile
	 * @throws DaoException if error occurs while getting user profiles from the user profile table
	 */
	private ResultSetFuture getOneUserProfile(UUID userId) {
		Select.Where select = null;
		try {
		 select = QueryBuilder.select()
				.all()
				.from(Keyspace.valueOf(keyspace), "userprofile")
				.where(eq("id", userId));				
				
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving a user profile from the user profile table ", e);
		} finally {
			// close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return CassandraDaoFactory.getSession().executeAsync(select);
		
	}	
	
	/**
	 * List user profiles in the data store.
	 * 
	 * @return List list of user profiles
	 * @throws DaoException if error occurs while reading user profiles from the data store
	 */
	@Override
	public List<UserProfile> listUserProfile(long tenantId) throws DaoException {
		List<UserProfile> list = new ArrayList<>();
		String table = "userprofile";
		try {								
			ResultSetFuture results = DataReaderUtil.getAll(table, tenantId);
			if (results == null) {
				return null;
			}
			// get data elements from the Result set
			for (Row row : results.getUninterruptibly()) {
				UserProfile user = new UserProfile();
				list.add(setUserAttributes(user, row));
			}
		} catch (DriverException e) {
			Logger.error("Error occurred while getting user profile data from the user profile table ", e);
		} finally {
			//close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		
		return list;
	}	
	
	
	/**
	 * Sets user profile attributes
	 * 
	 * @param user the user profile attributes to set
	 * @param row the result of a query 
	 * @return user profile with the attributes set
	 */
	private UserProfile setUserAttributes(UserProfile user, Row row) {
		user.setUserProfileId(row.getUUID("id"));
		user.setTenantId(row.getLong("tenantid"));
		user.setLastName(row.getString("lname"));
		user.setFirstName(row.getString("fname"));
		user.setPreferredFirstName(row.getString("pfname"));
		user.setMiddleInitial(row.getString("minitial"));
		user.setTitle(row.getString("title"));
		user.setSalutation(row.getString("salutation"));
		user.setEmail(row.getString("email"));
		user.setPhones(row.getMap("phones", String.class, String.class));
		user.setPassword(row.getString("password"));
		user.setGender(row.getString("gender"));
		user.setStreet1(row.getString("street1"));
		user.setStreet2(row.getString("street2"));
		user.setCity(row.getString("city"));
		user.setZipcode(row.getString("zipcode"));
		user.setState(row.getString("state"));
		user.setProvince(row.getString("province"));
		user.setCountry(row.getString("country"));				
		user.setLineOfDefense(row.getString("lineofdefense"));
		user.setLatitude(row.getString("latitude"));
		user.setLongitude(row.getString("longitude"));
		user.setTimeZone(row.getString("timezone"));
		user.setLanguage(row.getString("language"));
		user.setLocale(row.getString("locale"));
		user.setStatus(row.getString("status"));
		user.setDeptId(row.getUUID("deptid"));
		user.setDeptName(row.getString("deptname"));
		user.setLodFunctionId(row.getInt("lodfunctionid"));
		user.setLodFunction(row.getString("lodfunction"));
		Date date = DateUtility.convertTimeuuid(row.getUUID("createdate"));
		user.setCreateDate(date);
		
		return user;	
	}
	
	/**
	 * Updates a user profile.
	 * 
	 * @param user user profile to update
	 * @return boolean True if the user profile is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a user profile in the data store
	 */
	
	private void updateUserAuth(final UserProfile user) throws DaoException {
		//boolean update = false;		
		try {
			Update.Assignments updateAssignments = QueryBuilder
					.update(Keyspace.valueOf(keyspace), "userauth")					
					.with(set("hash", BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12))))
					.and(set("lname", user.getLastName()))
					.and(set("fname", user.getFirstName()));
					Statement updateDetails = updateAssignments
					.where(eq("username", user.getUsername()));
			CassandraDaoFactory.getSession().execute(updateDetails);			
			//update = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while updating data in the user profile table ", e);
		} finally {
			//close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		//return update;
	}	
	
	public boolean updateUserProfile(final UserProfile user) throws DaoException {
		boolean update = false;		
		try {
			Assignment phones = QueryBuilder.putAll("phones", user.getPhones());
			Update.Assignments updateAssignments = QueryBuilder
					.update(Keyspace.valueOf(keyspace), "userprofile")					
					.with(set("password", user.getPassword()))
					.and(phones)
					.and(set("email", user.getEmail()))
					.and(set("title", user.getTitle()))
					.and(set("salutation", user.getSalutation()))
					.and(set("gender", user.getGender()))
					.and(set("street1", user.getStreet1()))
					.and(set("street2", user.getStreet2()))
					.and(set("city", user.getCity()))
					.and(set("zipcode", user.getZipcode()))
					.and(set("state", user.getState()))
					.and(set("province", user.getProvince()))
					.and(set("country", user.getCountry()))
					.and(set("lineofdefense", user.getLineOfDefense()))
					.and(set("latitude", user.getLatitude()))
					.and(set("longitude", user.getLongitude()))					
					.and(set("locale", user.getLocale()))
					.and(set("language", user.getLanguage()))
					.and(set("timezone", user.getTimeZone()))
					.and(set("deptid", user.getDeptId()))
					.and(set("deptname", user.getDeptName()))
					.and(set("lodfunctionid", user.getLodFunctionId()))
					.and(set("lodfunction", user.getLodFunction()));
			Statement updateDetails = updateAssignments
					.where(eq("username", user.getUsername()))
					.and(eq("lname", user.getLastName()))
					.and(eq("fname", user.getFirstName()))
					.and(eq("tenantid", user.getTenantId()));
			CassandraDaoFactory.getSession().execute(updateDetails);
			updateUserAuth(user);
			update = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while updating data in the user profile table ", e);
		} finally {
			//close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return update;
	}

}
