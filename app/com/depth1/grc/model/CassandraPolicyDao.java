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
import com.datastax.driver.core.utils.UUIDs;

public class CassandraPolicyDao implements PolicyDao {
	@Override
	public void createPolicy(Policy policy) throws DaoException {
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement insert = QueryBuilder
					.insertInto("grc", "policy")
					.value("id", UUID.randomUUID()) //TBD
					.value("tenantid", policy.getTenantId())
					.value("name", policy.getName())
					.value("policyId", policy.getPolicyId())
					.value("description", policy.getDescription())
					.value("author", policy.getAuthor())
					.value("version", policy.getVersion())
					.value("create_date", UUIDs.timeBased())
					.value("effective_date", policy.getEffectiveDate())
					.value("format", policy.getFormat())
					.value("language", policy.getLanguage())
					.value("subject", policy.getSubject())
					.value("source", policy.getSource())
					.value("sunset_date", policy.getSunsetDate())
					.value("category", policy.getCategory())
					.value("classification", policy.getClassification())
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
					.value("last_updated_date", UUIDs.timeBased());
					dbSession.execute(insert);
					Logger.info("Inserted successfully to database");
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} finally {			
			CassandraDaoFactory.close(dbSession);
		}		
	}

	@Override
	public boolean updatePolicy(UUID policyId) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePolicy(UUID policyId) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean restorePolicy(UUID policyId) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Policy viewPolicyByName(String policyName) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Policy viewPolicyById(UUID policyId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Policy viewPolicyByClassification(String policyClassification)
			throws DaoException {
		// TODO Auto-generated method stub
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
            Statement viewAllPolicy = QueryBuilder.select().all()
                    .from("grc", "policy");

            ResultSet result = dbSession.execute(viewAllPolicy);
            if (result == null) {
                return null;
            }

            for (Row row : result.all()) {
                Policy policy = new Policy();
                policy.setId(row.getUUID("id"));
                policy.setName(row.getString("name"));
                policy.setName(row.getString("policyId"));
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
