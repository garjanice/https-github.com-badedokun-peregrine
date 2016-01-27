package com.depth1.grc.model;

import java.sql.Connection;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.DriverException;
import com.depth1.grc.db.util.CassandraPoolImpl;
import com.depth1.grc.db.util.DropDownList;
import com.depth1.grc.db.util.DropDownListReader;
import com.depth1.grc.db.util.RdbPoolImpl;
import com.depth1.grc.exception.DaoException;
import com.depth1.grc.jpa.models.JpaObjectiveDao;
import com.depth1.grc.jpa.models.ObjectiveDao;

import play.Logger;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class RdbDaoFactory extends DaoFactory
{
	/**
	 * @generated
	 */
	public RdbDaoFactory(){
		super();
	}
	
	 /**
	 * Creates a connection to the MariaDB database
	 * @return session The connection object to connect to the database
	 */
	public static Connection getSession() {
		  RdbPoolImpl pool = null;
		  Connection session = null;
		  try {
			  pool = new RdbPoolImpl();
			  session = pool.checkOut();
			  //session = pool.create();
		} catch (DriverException e) {
			Logger.error("Error occurred while connecting to the MariaDB cluster ", e);
		} 
		  return session;
	  }	
	
	 /**
	 * Closes connection to the MariaDB database
	 * @return session The connection object to connect to the database
	 */
	public static void close(Connection session) {
		  RdbPoolImpl pool = null;		  
		  try {
			  pool = new RdbPoolImpl();
			  pool.checkIn(session);
			  pool.expire(session);
		} catch (DriverException e) {
			Logger.error("Error occurred while closing open connection to the MariaDB database ", e);
		} 
		  
	  }	
	
	/* (non-Javadoc)
	 * @see com.depth1.grc.model.DaoFactory#getPolicyDao()
	 */
	public PolicyDao getPolicyDao() {
		    
		    return null;
		  }
	
	/* (non-Javadoc)
	 * @see com.depth1.grc.model.DaoFactory#getRiskAssesmentDao()
	 */
	@Override
	public RiskAssessmentDao getRiskAssessmentDao() throws DaoException {
		// TODO Auto-generated method stub
		return new CassandraRiskAssessmentDao();
	}
	
	/* (non-Javadoc)
	 * @see com.depth1.grc.model.DaoFactory#getPolicyDao()
	 */
	public RiskRegisterDao getRiskRegisterDao() {
		    
		    return null;
		  }
	  	
	
	/* (non-Javadoc)
	 * @see com.depth1.grc.model.DaoFactory#getTenantDao()
	 */
	public TenantDao getTenantDao() {
		    
		    return new CassandraTenantDao();
		  }
	
	
	/* (non-Javadoc)
	 * @see com.depth1.grc.model.DaoFactory#getObjectiveDao()
	 */
	public ObjectiveDao getObjective() {
		    
		    return new JpaObjectiveDao();
		  }
	
	
	
	/* (non-Javadoc)
	 * @see com.depth1.grc.model.DaoFactory#getUserProfileDao()
	 */
	public UserProfileDao getUserProfileDao() {

		return new CassandraUserProfileDao();
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
	
	/* (non-Javadoc)
	 * @see com.depth1.grc.model.DaoFactory#getDropDownList()
	 */
	public DropDownList getDropDownList() {
		    
		    return new DropDownListReader();
		  }

	@Override
	public ObjectiveDao getObjectiveDao() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}		

	
}

