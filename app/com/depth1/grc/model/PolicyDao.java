package com.depth1.grc.model;

import java.util.UUID;

/**
 * @author Team-A
 *
 */
public interface PolicyDao {
	public void createPolicy(Policy policy) throws DaoException;
	public boolean updatePolicy(UUID policyId) throws DaoException;
	public boolean deletePolicy(UUID policyId) throws DaoException;
	public boolean restorePolicy(UUID policyId) throws DaoException;
	public Policy viewPolicyByName(String policyName) throws DaoException;
	public Policy viewPolicyById(UUID policyId) throws DaoException;
	public Policy viewPolicyByClassification(String policyClassification) throws DaoException;
	public void importPolicy(Policy policy) throws DaoException;

}
