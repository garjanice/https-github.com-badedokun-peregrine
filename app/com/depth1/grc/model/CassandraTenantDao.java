package com.depth1.grc.model;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Delete.Where;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.utils.UUIDs;
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
	
	public boolean deleteTenant(int tenantId) throws DaoException {
		boolean del = false;
		try {					
			Where delete = QueryBuilder.delete()
					.from(Keyspace.valueOf(keyspace), "tenant")
					.where(eq("tenantid", tenantId));							

			CassandraDaoFactory.getSession().execute(delete);
			del = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while deleting tenant from the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
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
					.value("service_start_date", UUIDs.timeBased())
					.value("create_date", UUIDs.timeBased())
					.value("company_url", tenant.getCompanyUrl())
					.value("phone_number", tenant.getPhoneNumber())
					.value("ipaddress", tenant.getIpaddress())
					.value("status", tenant.getStatus());					
			CassandraDaoFactory.getSession().execute(insert);
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the tenant table ", e);
		} finally {			
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
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
		try {					
			Statement listAll = QueryBuilder.select().all()
					.from(Keyspace.valueOf(keyspace), "tenant");

			ResultSet result = CassandraDaoFactory.getSession().execute(listAll);
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
				tenant.setUuidTime(UUIDs.unixTimestamp(row.getUUID("service_start_date")));
				tenant.setStartDateUtil(new Date(tenant.getUuidTime()));
				tenant.setUuidTime(UUIDs.unixTimestamp(row.getUUID("create_date")));
				tenant.setCreateDateUtil(new Date(tenant.getUuidTime()));
				tenant.setCompanyUrl(row.getString("company_url"));
				tenant.setPhoneNumber(row.getString("phone_nummber"));
				tenant.setIpaddress(row.getString("ipaddress"));
				tenant.setStatus(row.getString("status"));
				list.add(tenant);
				result.iterator();
			}

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving data from the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
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
					.and(set("company_url", tenant.getCompanyUrl()))
					.and(set("phone_number", tenant.getPhoneNumber()))
					.and(set("ipaddress", tenant.getIpaddress()))
					.and(set("status", tenant.getState()));

			Statement updateDetails = updateAssignments
					.where(eq("tenantid", tenant.getTenantId()));							

			CassandraDaoFactory.getSession().execute(updateDetails);
			update = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while updating data in the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
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
		try {					
			Statement find = QueryBuilder.select().all()
					.from(Keyspace.valueOf(keyspace), "tenant")
					.where(eq("tenantid", tenantId));

			ResultSet result = CassandraDaoFactory.getSession().execute(find);
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
			tenant.setUuidTime(UUIDs.unixTimestamp(row.getUUID("service_start_date")));
			tenant.setStartDateUtil(new Date(tenant.getUuidTime()));
			tenant.setUuidTime(UUIDs.unixTimestamp(row.getUUID("create_date")));
			tenant.setCreateDateUtil(new Date(tenant.getUuidTime()));
			tenant.setCompanyUrl(row.getString("company_url"));
			tenant.setPhoneNumber(row.getString("phone_nummber"));
			tenant.setIpaddress(row.getString("ipaddress"));
			tenant.setStatus(row.getString("status"));

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving data from the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return tenant;
	}
	
}

