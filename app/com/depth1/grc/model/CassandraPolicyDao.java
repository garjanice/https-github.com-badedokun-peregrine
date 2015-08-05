package com.depth1.grc.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.joda.time.LocalDateTime;

import play.Logger;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Assignment;
import com.datastax.driver.core.utils.UUIDs;

public class CassandraPolicyDao implements PolicyDao {
	@Override
	public void createPolicy(Policy policy) throws DaoException {
		Session dbSession = CassandraDaoFactory.connect();
		try {
			Statement insert = QueryBuilder.insertInto("grc", "policy")
					.value("id", UUID.randomUUID()) // TBD
					.value("name", policy.getName())
					.value("author", policy.getAuthor())
					.value("version", policy.getVersion())
					.value("classification", policy.getClassification())
					.value("policyId", policy.getPolicyId())
					.value("tenanid", policy.getTenantId())
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
					.value("last_updated_date", UUIDs.timeBased())
					.value("is_deleted",false);

					dbSession.execute(insert);
					Logger.info("Inserted successfully to database");
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} finally {
			CassandraDaoFactory.close(dbSession);
		}
	}

	@Override
	public boolean updatePolicy(UUID policyId, Policy policy) throws DaoException {
		boolean flag = false;
		Session dbSession = CassandraDaoFactory.connect();
		try {
			Statement update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("name", policy.getName()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("author", policy.getAuthor()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("version", policy.getVersion()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
					
					//.value("tenanid", policy.getTenantId())
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("description", policy.getDescription()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("effective_date", policy.getEffectiveDate()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("format", policy.getFormat()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("language", policy.getLanguage()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("subject", policy.getSubject()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("source", policy.getSource()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("sunset_date", policy.getSunsetDate()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("category", policy.getCategory()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("reference", policy.getReference()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("legal", policy.getLegalRequirement()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("regulatory", policy.getRegulatory()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("approver", policy.getApprover()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("producing_function", policy.getProducingFunction()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("compliance_category", policy.getComplianceCategory()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("owner", policy.getOwner()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("document_contact", policy.getDocumentContact()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("functional_applicability", policy.getFunctionalApplicability()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("geographic_applicability", policy.getGeographicApplicability()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("issue_date", policy.getOriginalIssueDate()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("last_review_date", policy.getLastReviewDate()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("next_review_date", policy.getNextReviewDate()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
			
			update = QueryBuilder.update("grc", "policy")
					.with(QueryBuilder.set("last_updated_date", UUIDs.timeBased()))
					.where(QueryBuilder.eq("id", policyId));
			dbSession.execute(update);
					
			Logger.info("Inserted successfully to database");
			flag = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} finally {
			CassandraDaoFactory.close(dbSession);
		}
		return flag;
	}

	@Override
	public boolean deletePolicy(String policyId) throws DaoException {
		// TODO Auto-generated method stub
		Session dbSession = CassandraDaoFactory.connect();
		try {		
			Statement update = QueryBuilder
						.update("grc", "policy")
						.with(QueryBuilder.set("is_deleted", true))
						.where(QueryBuilder.eq("id", UUID.fromString(policyId)));
					dbSession.execute(update);
					return true;
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
			return false;
		} finally {			
			CassandraDaoFactory.close(dbSession);
		}

	}

	@Override
	public boolean restorePolicy(String policyId) throws DaoException {
		Session dbSession = CassandraDaoFactory.connect();
		try{
			Statement restore = QueryBuilder
							.update("grc","policy")
							.with(QueryBuilder.set("is_deleted", false))
							.where(QueryBuilder.eq("id", UUID.fromString(policyId)));
			dbSession.execute(restore);
			return true;
		} catch (DriverException e){
			Logger.error("Error while restoring",e);
			return false;
		} finally{
			CassandraDaoFactory.close(dbSession);
		}
	}

	private List<Policy> getResultList(ResultSet result) throws DaoException
	{
		List<Policy> listPolicy = new ArrayList<>();
		for (Row row : result.all()) {
			Policy policy = new Policy();
			policy.setId(row.getUUID("id"));
			policy.setName(row.getString("name"));
			policy.setDescription(row.getString("description"));
			policy.setAuthor(row.getString("author"));
			policy.setVersion(row.getString("version"));
			policy.setCreationDate(row.getUUID("create_date"));
			policy.setFormat(row.getString("format"));
			policy.setLanguage(row.getString("language"));
			policy.setSubject(row.getString("subject"));
			policy.setSource(row.getString("source"));
			policy.setSunsetDate(row.getUUID("sunset_date"));
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
			policy.setEffectiveDate(row.getUUID("effective_date"));
			policy.setOriginalIssueDate(row.getUUID("issue_date"));
			policy.setLastReviewDate(row.getUUID("last_review_date"));
			policy.setNextReviewDate(row.getUUID("next_review_date"));
			policy.setLastUpdatedDate(row.getUUID("last_updated_date"));
			listPolicy.add(policy);
			result.iterator();
		}
		return listPolicy;
	}
	
	@Override
	public Policy viewPolicyByName(String policyName) throws DaoException {
		List<Policy> listPolicy;
		Session dbSession = CassandraDaoFactory.connect();
		try {
			Statement viewPolicyById = QueryBuilder.select().all().from("grc", "policy").where(QueryBuilder.eq("name", policyName));

			ResultSet result = dbSession.execute(viewPolicyById);
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
			CassandraDaoFactory.close(dbSession);
		}
		return null;	
	}

	@Override
	public Policy viewPolicyById(String policyId) throws DaoException {
		List<Policy> listPolicy;
		Session dbSession = CassandraDaoFactory.connect();
		try {
			Statement viewPolicyById = QueryBuilder.select().all().from("grc", "policy").where(QueryBuilder.eq("policyId", policyId));

			ResultSet result = dbSession.execute(viewPolicyById);
			if (result == null) {
				return null;
			}
			listPolicy = getResultList(result);
			if(listPolicy.isEmpty())
				return null;
			Policy head = listPolicy.get(0);
			policyId = head.getPolicyId().toString();
			return head;
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving list of Policies from database ", e);
		} finally {
			CassandraDaoFactory.close(dbSession);
		}
		return null;
	}

	@Override
	public Policy viewPolicyByClassification(String policyClassification) throws DaoException {
		List<Policy> listPolicy;
		Session dbSession = CassandraDaoFactory.connect();
		try {
			Statement viewPolicyById = QueryBuilder.select().all().from("grc", "policy").where(QueryBuilder.eq("classification", policyClassification));

			ResultSet result = dbSession.execute(viewPolicyById);
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
			CassandraDaoFactory.close(dbSession);
		}
		return null;
	}

	@Override
	public void importPolicy(Policy policy) throws DaoException {
		// TODO Auto-generated method stub
		createPolicy(policy);
	}

	@Override
	public List<Policy> viewAllPolicy() throws DaoException {
        List<Policy> listPolicy = new ArrayList<>();
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Statement viewAllPolicy = QueryBuilder
            		.select()
            		.all()
                    .from("grc", "policy")
                    .where(QueryBuilder.eq("is_deleted", false));
            ResultSet result = dbSession.execute(viewAllPolicy);
            if (result == null) {
                return null;
            }

            for (Row row : result.all()) {
                Policy policy = new Policy();
                policy.setId(row.getUUID("id"));
                policy.setName(row.getString("name"));
                policy.setPolicyId(row.getString("policyId"));
                policy.setDescription(row.getString("description"));
                policy.setAuthor(row.getString("author"));
                policy.setVersion(row.getString("version"));
                policy.setCreationDate(row.getUUID("create_date"));
                policy.setFormat(row.getString("format"));
                policy.setLanguage(row.getString("language"));
                policy.setSubject(row.getString("subject"));
                policy.setSource(row.getString("source"));
                policy.setSunsetDate(row.getUUID("sunset_date"));
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
                policy.setEffectiveDate(row.getUUID("effective_date"));
                policy.setOriginalIssueDate(row.getUUID("issue_date"));
                policy.setLastReviewDate(row.getUUID("last_review_date"));
                policy.setNextReviewDate(row.getUUID("next_review_date"));
                policy.setLastUpdatedDate(row.getUUID("last_updated_date"));
							
		        listPolicy.add(policy);
                result.iterator();
            }
        } catch (DriverException e) {
            Logger.error("Error occurred while retrieving list of Policies from database ", e);
        } finally {
            CassandraDaoFactory.close(dbSession);
        }

        return listPolicy;
	}
	
	@Override
	public List<Policy> viewAllDeletedPolicy() throws DaoException {
        List<Policy> listPolicy = new ArrayList<>();
        Session dbSession = CassandraDaoFactory.connect();
        try {
            Statement viewAllDeletedPolicy = QueryBuilder
            		.select()
            		.all()
                    .from("grc", "policy")
                    .where(QueryBuilder.eq("is_deleted", true));

            ResultSet result = dbSession.execute(viewAllDeletedPolicy);
            if (result == null) {
                return null;
            }

            for (Row row : result.all()) {
                Policy policy = new Policy();
                policy.setId(row.getUUID("id"));
                policy.setName(row.getString("name"));
                policy.setPolicyId(row.getString("policyId"));
                policy.setDescription(row.getString("description"));
                policy.setAuthor(row.getString("author"));
                policy.setVersion(row.getString("version"));
                policy.setCreationDate(row.getUUID("create_date"));
                policy.setFormat(row.getString("format"));
                policy.setLanguage(row.getString("language"));
                policy.setSubject(row.getString("subject"));
                policy.setSource(row.getString("source"));
                policy.setSunsetDate(row.getUUID("sunset_date"));
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
                policy.setEffectiveDate(row.getUUID("effective_date"));
                policy.setOriginalIssueDate(row.getUUID("issue_date"));
                policy.setLastReviewDate(row.getUUID("last_review_date"));
                policy.setNextReviewDate(row.getUUID("next_review_date"));
                policy.setLastUpdatedDate(row.getUUID("last_updated_date"));
							
		        listPolicy.add(policy);
                result.iterator();
            }
        } catch (DriverException e) {
            Logger.error("Error occurred while retrieving list of Policies from database ", e);
        } finally {
            CassandraDaoFactory.close(dbSession);
        }

        return listPolicy;
	}

}
