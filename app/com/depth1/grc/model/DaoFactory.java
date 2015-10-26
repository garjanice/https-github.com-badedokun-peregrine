/**
 * 
 */
package com.depth1.grc.model;

/**
 * 
 * An Abstract factory pattern for Data Access Object (DAO)
 * @author Bisi Adedokun
 *
 */
public abstract class DaoFactory {
	
	/**
	 * No argument constructor
	 * 
	 */
	public DaoFactory(){
		super();
	}
	
	 // List of DAO types supported by the factory
	  public static final int CASSANDRA = 1;
	  public static final int MYSQL = 2;
	  public static final int ORACLE = 3;
	  public static final int XML = 4;

		/**
		 * Returns the Policy DAO.
		 * 
		 * <p>This abstract method is implemented by the subclass
		 * @return PolicyDao policy data access object interface
		 * @throws DaoException if errors occurs while retrieving data from the data store
		 */

	
	public abstract PolicyDao getPolicyDao() throws DaoException; 
	
	/**
	 * Returns the Risk Assessment DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return RiskAssessmentDao risk assessment data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	
	public abstract RiskAssessmentDao getRiskAssessmentDao() throws DaoException;
	
	/**
	 * Returns the Risk Register DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return RiskRegisterDao risk register data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	
	public abstract RiskRegisterDao getRiskRegisterDao() throws DaoException;
	
	/**
	 * Returns the Tenant DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return TenantDao tenant data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	
	public abstract TenantDao getTenantDao() throws DaoException;
	
	
	/**
	 * Returns the UserProfile DAO.
	 * 
	 * <p>This abstract method is implemented by the subclass
	 * @return UserProfileDao user profile data access object interface
	 * @throws DaoException if errors occurs while retrieving data from the data store
	 */
	
	public abstract UserProfileDao getUserProfileDao() throws DaoException;	
	
	/**
	 * Selects the appropriate DAO factory to use based on data store option the application 
	 * plans to use
	 * @param whichfactory factory to use
	 * @return dao factory method to use based on the data store option selected
	 * 
	 */
	  public static DaoFactory getDaoFactory(
		      int whichFactory) {
		  
		    switch (whichFactory) {
		      case CASSANDRA: 
		          return new CassandraDaoFactory();
		      case MYSQL: 
		          return new NoSqlDaoFactory();       
		      case ORACLE: 
		          return new NoSqlDaoFactory();      
		      case XML: 
		          return new XmlDaoFactory();
		      
		      default: 
		          return null;
		    }
		  }

}
