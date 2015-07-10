package com.depth1.grc.model;

import java.util.List;

/**
 * The Tenant interface that defines the contract that all implementing classes have to abide by
 * It provides API to expose to caller of classes that implement the interface
 */
public  interface TenantDao 
{
	
	/**
	 * Creates a Tenant
	 * @param tenant The Tenant to create
	 * @throws DaoException When error occurs while creating the Tenant in the data store
	 */
	public void createTenant(Tenant tenant) throws DaoException;
	
	/**
	 * Deletes a Tenant
	 * @param tenantId The tenant ID to delete
	 * @return boolean True if the Tenant is successfully deleted, false otherwise
	 * @throws DaoException When error occurs while deleting a tenant from the data store
	 */
	public boolean deleteTenant(int tenantId) throws DaoException;
	
	
	/**
	 * List Tenants in the data store
	 * @return List List of tenants
	 * @throws DaoException When error occurs while reading tenants from the data store
	 */
	public List<Tenant> listTenant() throws DaoException;
	
	/**
	 * Updates Tenant
	 * @param tenant The tenant to update
	 * @return boolean True if the tenant is successfully updated, false otherwise
	 * @throws DaoException When error occurs while updating a tenant in the data store
	 */
	
	public boolean updateTenant(Tenant tenant) throws DaoException;
	
	/**
	 * Finds a Tenant
	 * @param tenantId The tenant ID to find
	 * @return Tenant The tenant that was found
	 * @throws DaoException When error occurs while finding a tenant in the data store
	 */
	public Tenant findTenant(int tenantId) throws DaoException;
	
	
}

