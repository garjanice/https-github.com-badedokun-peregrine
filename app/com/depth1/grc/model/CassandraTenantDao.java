package com.depth1.grc.model;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import play.Logger;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class CassandraTenantDao implements TenantDao
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Tenant tenant;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public CassandraTenantDao(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean deleteTenant() {
		// TODO implement me
		return false;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void createTenant(Tenant tenant) throws DaoException {
		Session dbSession = CassandraDaoFactory.connect();
		try {					
			Statement insert = QueryBuilder
					.insertInto("grc", "tenant")
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
					.value("service_start_date", tenant.getServiceStartDate())
					.value("company_url", tenant.getCompanyUrl())
					.value("phone_number", tenant.getPhoneNumber())
					.value("ipaddress", tenant.getIpaddress())
					.value("status", tenant.getStatus());					
					dbSession.execute(insert);
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} finally {			
			CassandraDaoFactory.close(dbSession);
		}		
	}			
				
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void listTenant() {
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean updateTenant() {
		// TODO implement me
		return false;	
	}
	
}

