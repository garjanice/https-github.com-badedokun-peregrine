/**
 * 
 */
package com.depth1.grc.model;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Delete.Where;
import com.depth1.grc.model.common.Keyspace;

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
		

	/* (non-Javadoc)
	 * @see com.depth1.grc.model.UserProfileDao#createUserProfile(com.depth1.grc.model.UserProfile)
	 */
	@Override
	public void createUserProfile(UserProfile user) throws DaoException {
		// TODO Auto-generated method stub

	}

	/**
	 * Deletes a user profile from the user profile table
	 * 
	 * @param userId ID of the user to delete
	 * @return boolean True if the delete is successful, false otherwise
	 * @throws DaoException if error occurs while deleting a user profile from the user profile table
	 */
	@Override
	public boolean deleteUserProfile(long userId) throws DaoException {
		boolean del = false;
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Where delete = QueryBuilder.delete()
					.from(Keyspace.valueOf(keyspace), "userprofile")
					.where(eq("userprofileid", userId));							

			dbSession.execute(delete);
			del = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while deleting user profile from the user profile table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(dbSession);
		}
		return del;
	}

	/* (non-Javadoc)
	 * @see com.depth1.grc.model.UserProfileDao#listUserProfile()
	 */
	@Override
	public List<UserProfile> listUserProfile() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.depth1.grc.model.UserProfileDao#updateUserProfile(com.depth1.grc.model.UserProfile)
	 */
	@Override
	public boolean updateUserProfile(UserProfile user) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.depth1.grc.model.UserProfileDao#findUserProfile(long)
	 */
	@Override
	public UserProfile findUserProfile(long userId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Authenticates a user with username and password. Username is email address.
	 * @return user authenticated user object
	 * @throws DaoException if errors occurs while authenticating a user
	 */
	@Override
	public Login authenticate(String username, String password) throws DaoException {
		Login login = new Login();
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement find = QueryBuilder.select().all()
					.from(Keyspace.valueOf(keyspace), "userprofile")
					.where(eq("username", username))
					.and(eq("password", password));

			ResultSet result = dbSession.execute(find);
			if (result == null) {
				return null;
			}
			Row row = result.one();
			
			// get data elements from the Result set
			
			login.setUsername(row.getString("username"));
			login.setPassword(row.getString("password"));
			

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving data from the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(dbSession);
		}
		return login;
	}	

}
