package com.depth1.grc.model;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.utils.UUIDs;
import com.depth1.grc.exception.DaoException;

/**
 * This class implements the Data Access Object pattern (GoF). It provides capability to create, read, update, delete 
 * a User Profile - the typical CRUD functions in any business application.
 * 
 * @author Pooja Purushotham
 * Create Date: 07/27/2015
 */

//import com.datastax.driver.core.querybuilder.QueryBuilder;
//import com.datastax.driver.core.utils.UUIDs;

import play.Logger;


public class CassandraPolicyDao implements PolicyDao {
	
	/**
	* Creates a policy
	* 
	* @param policy to create
	* @throws DaoException if error occurs while creating the Policy in the data store
	*/
	@Override
	public void createPolicy(Policy policy) throws DaoException {
		try {
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
			Statement insert = QueryBuilder.insertInto("grc", "policy")
					.value("id", UUID.randomUUID()) // TBD
					.value("name", policy.getName())
					.value("author", policy.getAuthor())
					.value("version", policy.getVersion())
					.value("classification", policy.getClassification())
					.value("policyid", policy.getPolicyId())
					.value("tenantid", policy.getTenantId())
					.value("description", policy.getDescription())
					.value("create_date", UUIDs.timeBased())
					.value("effective_date", policy.getEffectiveDate())
					.value("format", policy.getFormat())
					.value("language", policy.getLanguage())
					.value("subject", policy.getSubject())
					.value("source", policy.getSource())
					.value("sunset_date", policy.getSunsetDate())
					.value("category", policy.getCategory())
					.value("reference", policy.getReference())
					.value("legal", policy.getLegalRequirement())
					.value("regulatory", policy.getRegulatory())
					.value("approver", policy.getApprover())
					.value("producing_function", policy.getProducingFunction())
					.value("compliance_category", policy.getComplianceCategory())
					.value("owner", policy.getOwner())
					.value("document_contact", policy.getDocumentContact())
					.value("functional_applicability", policy.getFunctionalApplicability())
					.value("geographic_applicability", policy.getGeographicApplicability())
					.value("issue_date", policy.getOriginalIssueDate())
					.value("last_review_date", policy.getLastReviewDate())
					.value("next_review_date", policy.getNextReviewDate())
					.value("last_updated_date", currentTimestamp)
					.value("is_deleted",false);

			CassandraDaoFactory.getSession().execute(insert);
					Logger.info("Inserted successfully to database");
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} finally {
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
	}
	
	
	/**
	* Deletes a policy
	* 
	* @param policy UUID to delete
	* @return true if the policy could be deleted successfully, else false
	* @throws DaoException if error occurs while deleting the Policy in the data store
	*/
	@Override
	public boolean deletePolicy(UUID id) throws DaoException {
		try {		
			Statement update = QueryBuilder
						.update("grc", "policy")
						.with(QueryBuilder.set("is_deleted", true))
						.where(QueryBuilder.eq("id", id));
			CassandraDaoFactory.getSession().execute(update);
			return true;
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
			return false;
		} finally {			
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}

	}

	/**
	* Get List of all Policies
	* 
	* @param void
	* @return List of Policies
	* @throws DaoException if error occurs while listing the Policies in the data store
	*/
	private List<Policy> getResultList(ResultSet result) throws DaoException
	{
		List<Policy> listPolicy = new ArrayList<>();
		for (Row row : result.all()) {
			Policy policy = new Policy();
			listPolicy.add(setPolicyAttributes(policy, row));
			result.iterator();
		}
		return listPolicy;
	}

	/**
	* Restores a policy
	* 
	* @param id the Id of the policy to restore
	* @return true if the policy could be restored successfully, else false
	* @throws DaoException if error occurs while restoring the Policy from the data store
	*/
	@Override
	public boolean restorePolicy(UUID id) throws DaoException {
		try {
			Statement restore = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("is_deleted", false))
					.where(QueryBuilder.eq("id", id));

			CassandraDaoFactory.getSession().execute(restore);
			return true;
		} catch (DriverException e) {
			Logger.error("Error while restoring", e);
			return false;
		} finally {
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
	}

	/**
	* Updates a policy
	* 
	* @param UUID of policy and policy to update
	* @throws DaoException if error occurs while updating the Policy in the data store
	*/
	@Override
	public boolean updatePolicy(UUID policyId, Policy policy) throws DaoException {
		return true;
	}
	
	/**
	* View all Deleted Policies
	* 
	* @param void
	* @return List of Policies that are deleted
	* @throws DaoException if error occurs while deleting the Policies in the data store
	*/
	@Override
	public List<Policy> viewAllDeletedPolicy() throws DaoException {
        List<Policy> listPolicy = new ArrayList<>();
        try {
            Statement viewAllDeletedPolicy = QueryBuilder
            		.select()
            		.all()
                    .from("grc", "policy")
                    .where(QueryBuilder.eq("is_deleted", true));

            ResultSet result = CassandraDaoFactory.getSession().execute(viewAllDeletedPolicy);
            if (result == null) {
                return null;
            }

            for (Row row : result.all()) {
                Policy policy = new Policy();
		        listPolicy.add(setPolicyAttributes(policy, row));
                result.iterator();
            }
        } catch (DriverException e) {
            Logger.error("Error occurred while retrieving list of Policies from database ", e);
        } finally {
            CassandraDaoFactory.close(CassandraDaoFactory.getSession());
        }

        return listPolicy;
	}

	/**
	* View all Policies
	* 
	* @param void
	* @return List of Policies to view
	* @throws DaoException if error occurs while viewing the Policies in the data store
	*/
	@Override
	public List<Policy> viewAllPolicy() throws DaoException {
        List<Policy> listPolicy = new ArrayList<>();
        try {
            Statement viewAllPolicy = QueryBuilder
            		.select()
            		.all()
                    .from("grc", "policy")
                    .where(QueryBuilder.eq("is_deleted", false));
            ResultSet result = CassandraDaoFactory.getSession().execute(viewAllPolicy);
            if (result == null) {
            	return null;
            }

            for (Row row : result.all()) {
            	Policy policy = new Policy();
            	listPolicy.add(setPolicyAttributes(policy, row));
            	result.iterator();
            }
        } catch (DriverException e) {
        	Logger.error("Error occurred while retrieving list of Policies from database ", e);
        } finally {
        	CassandraDaoFactory.close(CassandraDaoFactory.getSession());
        }

        return listPolicy;
	}

	/**
	* View policies by Policy class
	* 
	* @param policyClassification to view
	* @return Policy to view
	* @throws DaoException if error occurs while viewing the Policies in the data store
	*/
	@Override
	public Policy viewPolicyByClassification(String policyClassification) throws DaoException {
		List<Policy> listPolicy;
		try {
			Statement viewPolicyById = QueryBuilder.select().all().from("grc", "policy").where(QueryBuilder.eq("classification", policyClassification));

			ResultSet result = CassandraDaoFactory.getSession().execute(viewPolicyById);
			if (result == null) {
				return null;
			}
			listPolicy = getResultList(result);
			if(listPolicy.isEmpty())
				return null;
			return listPolicy.get(0);
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving list of Policies from database ", e);
		} finally {
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return null;
	}

	/**
	* View a policy by Policy id
	* 
	* @param policy UUID to view
	* @return Policy to view
	* @throws DaoException if error occurs while viewing the Policy in the data store
	*/	
	@Override
	public Policy viewPolicyById(UUID id) throws DaoException {
		List<Policy> listPolicy;
		try {
			Statement viewPolicyById = QueryBuilder.select().all().from("grc", "policy").where(QueryBuilder.eq("id", id));

			ResultSet result = CassandraDaoFactory.getSession().execute(viewPolicyById);
			if (result == null) {
				return null;
			}
			listPolicy = getResultList(result);
			if(listPolicy.isEmpty())
				return null;
			Policy head = listPolicy.get(0);
			id = head.getId();
			return head;
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving list of Policies from database ", e);
		} finally {
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return null;
	}
	
	/**
	* View a policy by Policy name
	* 
	* @param policyName to view
	* @return Policy to view
	* @throws DaoException if error occurs while viewing the Policy in the data store
	*/
	@Override
	public Policy viewPolicyByName(String policyName) throws DaoException {
		List<Policy> listPolicy;
		try {
			Statement viewPolicyById = QueryBuilder.select().all().from("grc", "policy").where(QueryBuilder.eq("name", policyName));

			ResultSet result = CassandraDaoFactory.getSession().execute(viewPolicyById);
			if (result == null) {
				return null;
			}
			listPolicy = getResultList(result);
			if(listPolicy.isEmpty())
				return null;
			return listPolicy.get(0);
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving list of Policies from database ", e);
		} finally {
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return null;	
	}
	
	/**
	 * Sets policy attributes
	 * 
	 * @param policy the policy attributes to set
	 * @param row the result of a query 
	 * @return policy with the attributes set
	 */
	private Policy setPolicyAttributes(Policy policy, Row row) {
		policy.setId(row.getUUID("id"));
		policy.setName(row.getString("name"));
		policy.setDescription(row.getString("description"));
	    policy.setPolicyId(row.getString("policyid"));
		policy.setAuthor(row.getString("author"));
		policy.setVersion(row.getString("version"));
		policy.setCreationDate(row.getUUID("create_date"));
		policy.setFormat(row.getString("format"));
		policy.setLanguage(row.getString("language"));
		policy.setSubject(row.getString("subject"));
		policy.setSource(row.getString("source"));
		policy.setSunsetDate(row.getTimestamp("sunset_date"));
		policy.setCategory(row.getString("category"));
		policy.setClassification(row.getString("classification"));
		policy.setReference(row.getString("reference"));
		policy.setLegalRequirement(row.getString("legal"));
		policy.setRegulatory(row.getString("regulatory"));
		policy.setApprover(row.getString("approver"));
		policy.setProducingFunction(row.getString("producing_function"));
		policy.setComplianceCategory(row.getString("compliance_category"));
		policy.setOwner(row.getString("owner"));
		policy.setDocumentContact(row.getString("document_contact"));
		policy.setFunctionalApplicability(row.getString("functional_applicability"));
		policy.setGeographicApplicability(row.getString("geographic_applicability"));
		policy.setEffectiveDate(row.getTimestamp("effective_date"));
		policy.setOriginalIssueDate(row.getTimestamp("issue_date"));
		policy.setLastReviewDate(row.getTimestamp("last_review_date"));
		policy.setNextReviewDate(row.getTimestamp("next_review_date"));
		policy.setLastUpdatedDate(row.getTimestamp("last_updated_date"));
		
		return policy;	
	}		

}
