/**
 * 
 */
package com.depth1.grc.model;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.DriverException;
import com.depth1.grc.db.util.CassandraPoolImpl;
import com.depth1.grc.db.util.DropDownList;
import com.depth1.grc.db.util.DropDownListReader;

import play.Logger;

/**
 * This class is a Data Access Object factory method and extends an abstract factory method that implements
 * the abstract factory method pattern for creating data access object for a specific type of data store
 * @author Bisi Adedokun
 *
 */
public class CassandraDaoFactory extends DaoFactory {	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public CassandraDaoFactory(){
		super();
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
			  session = pool.create();
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
			  pool.expire(session);
		} catch (DriverException e) {
			Logger.error("Error occurred while closing open connection to the cassandra database ", e);
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
	 * @see com.depth1.grc.model.DaoFactory#getTenantDao()
	 */
	public UserProfileDao getUserProfileDao() {
		    
		    return new CassandraUserProfileDao();
		  }
	
	/* (non-Javadoc)
	 * @see com.depth1.grc.model.DaoFactory#getTenantDao()
	 */
	public DropDownList getDropDownList() {
		    
		    return new DropDownListReader();
		  }	

}
