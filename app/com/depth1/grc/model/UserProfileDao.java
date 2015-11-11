/**
 * 
 */
package com.depth1.grc.model;

import java.util.List;
import java.util.UUID;

/**
 * The UserProfile interface defines the contract that all implementing classes must abide by.
 * It provides API to expose to caller of classes that implement the interface
 * 
 * @author Bisi Adedokun
 *
 */
public interface UserProfileDao {
	
	/**
	 * Creates a user profile.
	 * 
	 * @param user user profile to create
	 * @throws DaoException if error occurs while creating the User in the data store
	 */
	public void createUserProfile(UserProfile user) throws DaoException;
	
	/**
	 * Deletes a user profile.
	 * 
	 * @param userId user profile ID to delete
	 * @return boolean True if the user profile is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a user profile from the data store
	 */
	public boolean deleteUserProfile(String username) throws DaoException;
	
	
	/**
	 * List user profiles in the data store.
	 * 
	 * @return List list of user profiles
	 * @throws DaoException if error occurs while reading user profiles from the data store
	 */
	public List<UserProfile> listUserProfile() throws DaoException;
	
	/**
	 * Updates a user profile.
	 * 
	 * @param user user profile to update
	 * @return boolean True if the user profile is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a user profile in the data store
	 */
	
	public boolean updateUserProfile(UserProfile user) throws DaoException;
	
	/**
	 * Finds a user profile.
	 * 
	 * @param userId user profile ID to find
	 * @return UserProfile user profile that was found
	 * @throws DaoException if error occurs while finding a user profile in the data store
	 */
	public UserProfile findUserProfile(UUID userId) throws DaoException;
	
	/**
	 * Finds a user profile.
	 * 
	 * @param username login username of the user
	 * @param lastname last name of the user
	 * @return UserProfile user profile that was found
	 * @throws DaoException if error occurs while finding a user profile in the data store
	 */
	public UserProfile findUserProfile(String username, String lastname) throws DaoException;	
	
	/**
	 * Authenticates a user with username and password. Username is email address.
	 * 
	 * @return Login login credentials of an authenticated user
	 * @throws DaoException if errors occurs while authenticating a user
	 */
	public Login authenticate(String username, String password) throws DaoException;
	
	

}
