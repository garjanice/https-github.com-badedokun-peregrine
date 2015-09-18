package com.depth1.grc.model;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Delete.Where;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;
import com.depth1.grc.model.common.Keyspace;
import com.depth1.grc.util.IdProducer;

import play.Logger;
import play.Play;

/**
 * This class implements the Data Access Object pattern (GoF). It provides capability to create, read, update, delete 
 * a Tenant - the typical CRUD functions in any business application.
 * 
 * @author Bisi Adedokun
 * Create Date: 07/10/2015
 */

public class CassandraTenantDao implements TenantDao
{
	
	//select the type of deployment model from the configuration file
	private final static Boolean keyspace = Play.application().configuration().getBoolean("onpremise.deploy.model");
	

	public CassandraTenantDao(){
		super();
	}

	/**
	 * Deletes a tenant from the tenant table
	 * 
	 * @param tenantId ID of the tenant to delete
	 * @return boolean True if the delete is successful, false otherwise
	 * @throws DaoException if error occurs while deleting a tenant from the tenant table
	 */
	
	public boolean deleteTenant(UUID id) throws DaoException {
		boolean del = false;
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Where delete = QueryBuilder.delete()
					.from(Keyspace.valueOf(keyspace), "tenant")
					.where(eq("id", id));							

			dbSession.execute(delete);
			del = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while deleting tenant from the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(dbSession);
		}
		return del;
	}	
	
	
	/**
	 * Creates a new tenant in the tenant table.
	 * 
	 * @param tenant the tenant to create
	 * @throws DaoException if an error occurs while creating a tenant in the tenant table
	 */
	
	public void createTenant(Tenant tenant) throws DaoException {
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement insert = QueryBuilder
					.insertInto(Keyspace.valueOf(keyspace), "tenant")
					.value("id", java.util.UUID.randomUUID())
					.value("tenantid", IdProducer.nextId())
					.value("name", tenant.getName())
					.value("type", tenant.getType())
					.value("address1", tenant.getAddress1())
					.value("address2", tenant.getAddress2())
					.value("city", tenant.getCity())
					.value("zipcode", tenant.getZipcode())
					.value("state", tenant.getState())
					.value("province", tenant.getProvince())
					.value("country", tenant.getCountry())
					.value("contact_person_name", tenant.getContactPersonName())
					.value("contact_person_email", tenant.getContactPersonEmail())
					.value("contact_person_phone", tenant.getContactPersonPhone())
					.value("service_start_date", tenant.getServiceStartDate())
					.value("company_url", tenant.getCompanyUrl())
					.value("phone_number", tenant.getPhoneNumber())
					.value("ipaddress", tenant.getIpaddress())
					.value("email", tenant.getEmail())
					.value("status", tenant.getStatus());					
					dbSession.execute(insert);
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the tenant table ", e);
		} finally {			
			CassandraDaoFactory.close(dbSession);
		}		
	}			
				
	
	/**
	 * List all tenants in the tenant table.
	 * 
	 * @return list of tenants
	 * @throws DaoException if an error occurs while retrieving tenant from the tenant table
	 */
	
	public List<Tenant> listTenant() throws DaoException {
		List<Tenant> list = new ArrayList<>();
		Session dbSession = CassandraDaoFactory.connect();
		
		try {					
			Statement listAll = QueryBuilder.select().all()
					.from(Keyspace.valueOf(keyspace), "tenant");
			
			ResultSet result = dbSession.execute(listAll);
			
			if (result == null) {
				return null;
			}
			
			// get data elements from the Result set

			for (Row row : result.all()) {
				
				Tenant tenant = new Tenant();
				tenant.setId(row.getUUID("id"));
				tenant.setTenantId(row.getLong("tenantid"));
				tenant.setType(row.getString("type"));
				tenant.setAddress1(row.getString("address1"));
				tenant.setAddress2(row.getString("address2"));
				tenant.setCity(row.getString("city"));
				tenant.setZipcode(row.getString("zipcode"));
				tenant.setState(row.getString("state"));
				tenant.setProvince(row.getString("province"));
				tenant.setCountry(row.getString("country"));
				tenant.setContactPersonName(row.getString("contact_person_name"));
				tenant.setContactPersonEmail(row.getString("contact_person_email"));
				tenant.setContactPersonPhone(row.getString("contact_person_phone"));
				tenant.setName(row.getString("name"));
				tenant.setServiceStartDate(row.getString("service_start_date"));
				
				tenant.setCompanyUrl(row.getString("company_url"));
				tenant.setPhoneNumber(row.getString("phone_number"));
				tenant.setIpaddress(row.getString("ipaddress"));
				tenant.setStatus(row.getString("status"));
				tenant.setEmail(row.getString("email"));
				list.add(tenant);
				result.iterator();
			}
			
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving data from the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(dbSession);
		}
		
		return list;
	}
	
	
	/**
	 * Updates tenant information in the tenant table.
	 * 
     * @param tenant the tenant to update
     * @return boolean true if update succeed, false otherwise
     * @throws DaoException if an error occurs while updating tenant from the table
     */
	@Override
	public boolean updateTenant(final Tenant tenant) throws DaoException {
		boolean update = false;
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Update.Assignments updateAssignments = QueryBuilder
					.update(Keyspace.valueOf(keyspace), "tenant")
					.with(set("type", tenant.getType()))
					.and(set("address1", tenant.getAddress1()))
					.and(set("address2", tenant.getAddress2()))
					.and(set("city", tenant.getCity()))
					.and(set("zipcode", tenant.getZipcode()))
					.and(set("state", tenant.getState()))
					.and(set("province", tenant.getProvince()))
					.and(set("country", tenant.getCountry()))
					.and(set("contact_person_name", tenant.getContactPersonName()))
					.and(set("contact_person_email", tenant.getContactPersonEmail()))
					.and(set("contact_person_phone", tenant.getContactPersonPhone()))
					.and(set("company_url", tenant.getCompanyUrl()))
					.and(set("phone_number", tenant.getPhoneNumber()))
					.and(set("email", tenant.getEmail()))
					.and(set("ipaddress", tenant.getIpaddress()))
					.and(set("status", tenant.getStatus()));

			Statement updateDetails = updateAssignments
					.where(eq("id", tenant.getId()));							

			dbSession.execute(updateDetails);
			update = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while updating data in the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(dbSession);
		}
		return update;
	}
	
	/**
	 * Finds a tenant given a tenant Id.
	 * 
	 * @param tenantId the tenant Id to find
	 * @return tenant the tenant profile 
	 * @throws DaoException When error occurs while retrieving a tenant from the tenant table
	 */
	
	@Override
	public Tenant findTenant(int tenantId) throws DaoException {
		Tenant tenant = new Tenant();
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement find = QueryBuilder.select().all()
					.from(Keyspace.valueOf(keyspace), "tenant")
					.where(eq("tenantid", tenantId));

			ResultSet result = dbSession.execute(find);
			if (result == null) {
				return null;
			}
			Row row = result.one();
			
			// get data elements from the Result set
			
			tenant.setId(row.getUUID("id"));
			tenant.setTenantId(row.getLong("tenantid"));
			tenant.setType(row.getString("type"));
			tenant.setAddress1(row.getString("address1"));
			tenant.setAddress2(row.getString("address2"));
			tenant.setCity(row.getString("city"));
			tenant.setZipcode(row.getString("zipcode"));
			tenant.setState(row.getString("state"));
			tenant.setProvince(row.getString("province"));
			tenant.setCountry(row.getString("country"));
			tenant.setContactPersonName(row.getString("contact_person_name"));
			tenant.setContactPersonEmail(row.getString("contact_person_email"));
			tenant.setServiceStartDate(row.getString("service_start_date"));
			tenant.setCompanyUrl(row.getString("company_url"));
			tenant.setPhoneNumber(row.getString("phone_number"));
			tenant.setIpaddress(row.getString("ipaddress"));
			tenant.setStatus(row.getString("status"));
			tenant.setEmail(row.getString("email"));

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving data from the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(dbSession);
		}
		return tenant;
	}
	
}

