package com.depth1.grc.model;

import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;

/**
 * @author Team-A
 *
 */
public interface PolicyDao {
	public void createPolicy(Policy policy) throws DaoException;
	public boolean updatePolicy(UUID id, Policy policy) throws DaoException;
	public boolean deletePolicy(String policyId) throws DaoException;
	public boolean restorePolicy(String policyId) throws DaoException;
	public Policy viewPolicyByName(String policyName) throws DaoException;
	public Policy viewPolicyById(UUID id) throws DaoException;
	public Policy viewPolicyByClassification(String policyClassification) throws DaoException;
	public List<Policy> viewAllPolicy() throws DaoException;
	public List<Policy> viewAllDeletedPolicy() throws DaoException;
}
