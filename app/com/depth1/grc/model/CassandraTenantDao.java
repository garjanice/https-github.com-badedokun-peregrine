package com.depth1.grc.model;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Assignment;
import com.datastax.driver.core.querybuilder.Delete.Where;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.utils.UUIDs;
import com.depth1.grc.db.util.DataReaderUtil;
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
	
	public boolean deleteTenant(long tenantId) throws DaoException {
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
					.value("street1", tenant.getStreet1())
					.value("street2", tenant.getStreet2())
					.value("city", tenant.getCity())
					.value("zipcode", tenant.getZipcode())
					.value("state", tenant.getState())
					.value("province", tenant.getProvince())
					.value("country", tenant.getCountry())
					.value("latitude", tenant.getLatitude())
					.value("longitude", tenant.getLongitude())
					.value("phones", tenant.getPhones())
					.value("contact_person_name", tenant.getContactPersonName())
					.value("contact_person_email", tenant.getContactPersonEmail())
					.value("service_start_date", tenant.getServiceStartDate())
					.value("createdate", UUIDs.timeBased())
					.value("companyurl", tenant.getCompanyUrl())
					.value("contact_person_phones", tenant.getContactPersonPhones())
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
		String table = "tenant";
		try {
			ResultSetFuture results = DataReaderUtil.getAll(table);
			if (results == null) {
				return null;
			}

			// get data elements from the Result set

			for (Row row : results.getUninterruptibly()) {
				Tenant tenant = new Tenant();
				tenant.setId(row.getUUID("id"));
				tenant.setTenantId(row.getLong("tenantid"));
				tenant.setName(row.getString("name"));
				tenant.setType(row.getString("type"));
				tenant.setStreet1(row.getString("street1"));
				tenant.setStreet2(row.getString("street2"));
				tenant.setCity(row.getString("city"));
				tenant.setZipcode(row.getString("zipcode"));
				tenant.setState(row.getString("state"));
				tenant.setProvince(row.getString("province"));
				tenant.setCountry(row.getString("country"));
				tenant.setLatitude(row.getString("latitude"));
				tenant.setLongitude(row.getString("longitude"));
				tenant.setContactPersonName(row.getString("contact_person_name"));
				tenant.setContactPersonEmail(row.getString("contact_person_email"));
				tenant.setServiceStartDate(new Timestamp(row.getDate("service_start_date").getTime()));
				tenant.setUuidTime(UUIDs.unixTimestamp(row.getUUID("createdate")));
				tenant.setCreateDateUtil(new Date(tenant.getUuidTime()));
				tenant.setCompanyUrl(row.getString("companyurl"));
				tenant.setIpaddress(row.getString("ipaddress"));
				tenant.setStatus(row.getString("status"));
				list.add(tenant);
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
		Assignment phones = QueryBuilder.putAll("phones", tenant.getPhones());
		Assignment contactPhones = QueryBuilder.putAll("contact_person_phones", tenant.getContactPersonPhones());
		try {					
			Update.Assignments updateAssignments = QueryBuilder
					.update(Keyspace.valueOf(keyspace), "tenant")
					.with(set("type", tenant.getType()))
					.and(phones)
					.and(contactPhones)
					.and(set("name", tenant.getName()))
					.and(set("street1", tenant.getStreet1()))
					.and(set("street2", tenant.getStreet2()))
					.and(set("city", tenant.getCity()))
					.and(set("zipcode", tenant.getZipcode()))
					.and(set("state", tenant.getState()))
					.and(set("province", tenant.getProvince()))
					.and(set("country", tenant.getCountry()))
					.and(set("latitude", tenant.getLatitude()))
					.and(set("longitude", tenant.getLongitude()))
					.and(set("contact_person_name", tenant.getContactPersonName()))
					.and(set("contact_person_email", tenant.getContactPersonEmail()))
					.and(set("companyurl", tenant.getCompanyUrl()))
					.and(set("ipaddress", tenant.getIpaddress()))
					.and(set("status", tenant.getStatus()));
			Statement updateDetails = updateAssignments
					.where(eq("tenantid", tenant.getTenantId()))
					.and(eq("id", tenant.getId()));							

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
	public Tenant getTenant(long tenantId) throws DaoException {
		Tenant tenant = new Tenant();
		try {					
			ResultSetFuture result = getOneTenant(tenantId);
			if (result == null) {
				return null;
			}
						
			// get data elements from the Result set
			for (Row row : result.getUninterruptibly()) {
				tenant.setId(row.getUUID("id"));
				tenant.setTenantId(row.getLong("tenantid"));
				tenant.setName(row.getString("name"));
				tenant.setType(row.getString("type"));
				tenant.setStreet1(row.getString("street1"));
				tenant.setStreet2(row.getString("street2"));
				tenant.setCity(row.getString("city"));
				tenant.setZipcode(row.getString("zipcode"));
				tenant.setState(row.getString("state"));
				tenant.setProvince(row.getString("province"));
				tenant.setCountry(row.getString("country"));
				tenant.setLatitude(row.getString("latitude"));
				tenant.setLongitude(row.getString("longitude"));
				tenant.setPhones(row.getMap("phones", String.class, String.class));
				tenant.setContactPersonName(row.getString("contact_person_name"));
				tenant.setContactPersonEmail(row.getString("contact_person_email"));
				tenant.setContactPersonPhones(row.getMap("contact_person_phones", String.class, String.class));
				tenant.setServiceStartDate(new Timestamp(row.getDate("service_start_date").getTime()));
				tenant.setUuidTime(UUIDs.unixTimestamp(row.getUUID("createdate")));
				tenant.setCreateDateUtil(new Date(tenant.getUuidTime()));
				tenant.setCompanyUrl(row.getString("companyurl"));
				tenant.setIpaddress(row.getString("ipaddress"));
				tenant.setStatus(row.getString("status"));
			}

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving data from the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		
		return tenant;
	}
	
	/**
	 * Finds a tenant given a tenant name.
	 * 
	 * @param name the tenant name to find
	 * @return tenant the tenant profile 
	 * @throws DaoException When error occurs while retrieving a tenant from the tenant table
	 */
	
	@Override
	public Tenant getTenant(String name) throws DaoException {
		Tenant tenant = new Tenant();
		try {					
			ResultSetFuture result = getOneTenant(name);
			if (result == null) {
				return null;
			}
						
			// get data elements from the Result set
			for (Row row : result.getUninterruptibly()) {
				tenant.setId(row.getUUID("id"));
				tenant.setTenantId(row.getLong("tenantid"));
				tenant.setName(row.getString("name"));
				tenant.setType(row.getString("type"));
				tenant.setStreet1(row.getString("street1"));
				tenant.setStreet2(row.getString("street2"));
				tenant.setCity(row.getString("city"));
				tenant.setZipcode(row.getString("zipcode"));
				tenant.setState(row.getString("state"));
				tenant.setProvince(row.getString("province"));
				tenant.setCountry(row.getString("country"));
				tenant.setLatitude(row.getString("latitude"));
				tenant.setLongitude(row.getString("longitude"));
				tenant.setPhones(row.getMap("phones", String.class, String.class));
				tenant.setContactPersonName(row.getString("contact_person_name"));
				tenant.setContactPersonEmail(row.getString("contact_person_email"));
				tenant.setContactPersonPhones(row.getMap("contact_person_phones", String.class, String.class));
				tenant.setServiceStartDate(new Timestamp(row.getDate("service_start_date").getTime()));
				tenant.setUuidTime(UUIDs.unixTimestamp(row.getUUID("createdate")));
				tenant.setCreateDateUtil(new Date(tenant.getUuidTime()));
				tenant.setCompanyUrl(row.getString("companyurl"));
				tenant.setIpaddress(row.getString("ipaddress"));
				tenant.setStatus(row.getString("status"));
			}

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving data from the tenant table ", e);
		} finally {
			//dbSession.close();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		
		return tenant;
	}	
	
	/**
	 * Get a tenant profile that matches the given criteria of tenantId
	 * 
	 * @return a row that matches the tenant profile
	 * @throws DaoException if error occurs while getting tenant profiles from the tenant table
	 */
	private ResultSetFuture getOneTenant(long tenantId) {
		Select.Where select = null;
		try {
			select = QueryBuilder.select().all().from(Keyspace.valueOf(keyspace), "tenant")
					.where(eq("tenantid", tenantId));

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving a tenant profile from the tenant table ", e);
		} finally {
			// close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return CassandraDaoFactory.getSession().executeAsync(select);

	}
	
	/**
	 * Get a tenant profile that matches the given criteria of name
	 * 
	 * @return a row that matches the user profile
	 * @throws DaoException if error occurs while getting user profiles from the user profile table
	 */
	private ResultSetFuture getOneTenant(String name) {
		Select.Where select = null;
		try {
			select = QueryBuilder.select().all().from(Keyspace.valueOf(keyspace), "tenant")
					.where(eq("name", name));

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving a tenant profile from the tenant table ", e);
		} finally {
			// close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return CassandraDaoFactory.getSession().executeAsync(select);

	}		
	
}

