
package com.depth1.grc.model;

import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.depth1.grc.exception.DaoException;

/**
 * The PolicyDao interface defines the contract that all implementing classes must abide by.
 * It provides API to expose to caller of classes that implement the interface
 * 
 * @author Pooja Purushotham
 *
 */
public interface PolicyDao {
	
	/**
	* Creates a policy
	* 
	* @param policy to create
	* @throws DaoException if error occurs while creating the Policy in the data store
	*/
	public void createPolicy(Policy policy) throws DaoException;
	
	/**
	* Updates a policy
	* 
	* @param policy UUID to update
	* @return true if the policy could be updated successfully, else false
	* @throws DaoException if error occurs while updating the Policy in the data store
	*/
	public boolean updatePolicy(UUID id, Policy policy) throws DaoException;
	
	/**
	* Deletes a policy
	* 
	* @param policy UUID to delete
	* @return true if the policy could be deleted successfully, else false
	* @throws DaoException if error occurs while deleting the Policy in the data store
	*/
	public boolean deletePolicy(UUID id) throws DaoException;
	
	/**
	* Restores a policy
	* 
	* @param policy UUID to restore
	* @return true if the policy could be restored successfully, else false
	* @throws DaoException if error occurs while restoring the Policy in the data store
	*/
	public boolean restorePolicy(UUID id) throws DaoException;
	
	/**
	* View a policy by Policy name
	* 
	* @param policyName to view
	* @return Policy to view
	* @throws DaoException if error occurs while viewing the Policy in the data store
	*/
	public Policy viewPolicyByName(String policyName) throws DaoException;
	
	/**
	* View a policy by Policy id
	* 
	* @param policy UUID to view
	* @return Policy to view
	* @throws DaoException if error occurs while viewing the Policy in the data store
	*/	
	public Policy viewPolicyById(UUID id) throws DaoException;
	
	/**
	* View policies by Policy class
	* 
	* @param policyClassification to view
	* @return Policy to view
	* @throws DaoException if error occurs while viewing the Policies in the data store
	*/
	public Policy viewPolicyByClassification(String policyClassification) throws DaoException;
	
	/**
	* View all Policies
	* 
	* @param void
	* @return List of Policies to view
	* @throws DaoException if error occurs while viewing the Policies in the data store
	*/
	public List<Policy> viewAllPolicy() throws DaoException;
	
	/**
	* View all Deleted Policies
	* 
	* @param void
	* @return List of Policies that are deleted
	* @throws DaoException if error occurs while deleting the Policies in the data store
	*/
	public List<Policy> viewAllDeletedPolicy() throws DaoException;


}
