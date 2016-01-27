/**
 * 
 */
package com.depth1.grc.model;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.DriverException;
import com.depth1.grc.db.util.CassandraPoolImpl;
import com.depth1.grc.db.util.DropDownList;
import com.depth1.grc.db.util.DropDownListReader;
import com.depth1.grc.exception.DaoException;
import com.depth1.grc.jpa.models.JpaObjectiveDao;
import com.depth1.grc.jpa.models.ObjectiveDao;

import play.Logger;

/**
 * This class is a Data Access Object factory method and extends an abstract factory method that implements
 * the abstract factory method pattern for creating data access object for a specific type of data store
 * @author Bisi Adedokun
 *
 */
public class CassandraDaoFactory extends DaoFactory {	
	
	/**
	 * @generated
	 */
	public CassandraDaoFactory() {
		
	}
	
	 /**
	 * Creates a connection to the Cassandra database
	 * @return session The session object to connect to the database
	 */
	public static Session getSession() {
		CassandraPoolImpl pool = null;
		Session session = null;
		try {
			pool = new CassandraPoolImpl();
			session = pool.checkOut(); // instead of using: session =
										// pool.create();
		} catch (DriverException e) {
			Logger.error("Error occurred while connecting to the cassandra cluster ", e);
		}
		return session;
	}
	  
	 /**
	 * Closes connection to the Cassandra database
	 * @return session The session object to connect to the database
	 */
	public static void close(Session session) {
		CassandraPoolImpl pool = null;
		try {
			pool = new CassandraPoolImpl();
			pool.checkIn(session);
			pool.expire(session);
		} catch (DriverException e) {
			Logger.error("Error occurred while closing open connection to the cassandra database ", e);
		}

	}
	
	/**
	 * Returns the Policy DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return PolicyDao policy data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	public PolicyDao getPolicyDao() {
		return new CassandraPolicyDao();
	}
	
	/**
	 * Returns the Risk Assessment DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return RiskAssessmentDao risk assessment data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	@Override
	public RiskAssessmentDao getRiskAssessmentDao() throws DaoException {
		return new CassandraRiskAssessmentDao();
	}
	
	/**
	 * Returns the Risk Register DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return RiskRegisterDao risk register data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	public RiskRegisterDao getRiskRegisterDao() {
		return null;
	}
	  	
	/**
	 * Returns the Tenant DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return TenantDao tenant data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	public ObjectiveDao getObjectiveDao() {
		return new JpaObjectiveDao();
	}
	
	
	/**
	 * Returns the Tenant DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return TenantDao tenant data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	public TenantDao getTenantDao() {
		return new CassandraTenantDao();
	}
	
	
	/**
	 * Returns the UserProfile DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return UserProfileDao user profile data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	public UserProfileDao getUserProfileDao() {
		return new CassandraUserProfileDao();
	}
	
	/**
	 * Returns the DropDownlist DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return DropDownList drop down data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	public DropDownList getDropDownList() {
		return new DropDownListReader();
	}

	/**
	 * Returns the Department DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return DepartmentDao department data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */	
	public DepartmentDao getDepartmentDao() {
		return new CassandraDepartmentDao();
	}

}
