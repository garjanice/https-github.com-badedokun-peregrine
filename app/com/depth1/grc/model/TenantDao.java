package com.depth1.grc.model;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.depth1.grc.exception.DaoException;

/**
 * The Tenant interface that defines the contract that all implementing classes must abide by
 * It provides API to expose to caller of classes that implement the interface
 */
public  interface TenantDao 
{
	
	/**
	 * Creates a tenant.
	 * 
	 * @param tenant tenant to create
	 * @throws DaoException if error occurs while creating the Tenant in the data store
	 */
	public void createTenant(Tenant tenant) throws DaoException;
	
	/**
	 * Deletes a tenant.
	 * 
	 * @param tenantId tenant ID to delete
	 * @return boolean True if the Tenant is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a tenant from the data store
	 */
	public boolean deleteTenant(long tenantId) throws DaoException;
	
	
	/**
	 * List tenants in the data store.
	 * 
	 * @return List list of tenants
	 * @throws DaoException if error occurs while reading tenants from the data store
	 */
	public List<Tenant> listTenant() throws DaoException;
	
	/**
	 * Gets tenants information into the cache.
	 * 
	 * @return Map of tenants
	 * @throws DaoException if error occurs while reading tenants from the data store
	 */
	public Map<String, Long> cacheTenant() throws DaoException;	
	
	/**
	 * Updates Tenant.
	 * 
	 * @param tenant The tenant to update
	 * @return boolean True if the tenant is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a tenant in the data store
	 */
	
	public boolean updateTenant(Tenant tenant) throws DaoException;
	
	/**
	 * Finds a tenant.
	 * 
	 * @param tenantId tenant ID to find
	 * @return Tenant tenant that was found
	 * @throws DaoException if error occurs while finding a tenant in the data store
	 */
	public Tenant getTenant(long tenantId) throws DaoException;
	/**
	 * Finds a tenant.
	 * 
	 * @param name tenant name to find
	 * @return Tenant tenant that was found
	 * @throws DaoException if error occurs while finding a tenant in the data store
	 */
	public Tenant getTenant(String name) throws DaoException;
	
	/**
	 * Finds a tenant.
	 * 
	 * @param name tenant name to find
	 * @return true if a tenant if found, false otherwise
	 * @throws DaoException if error occurs while finding a tenant in the data store
	 */
	public boolean findTenant(String name) throws DaoException;	
	
}

