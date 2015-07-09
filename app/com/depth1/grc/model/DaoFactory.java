/**
 * 
 */
package com.depth1.grc.model;

/**
 * @author Bisi Adedokun
 * An Abstract factory pattern for DAO
 *
 */
public abstract class DaoFactory {
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
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
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public abstract PolicyDao getPolicyDao() throws DaoException; 
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public abstract RiskAssessmentDao getRiskAssessmentDao() throws DaoException;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public abstract RiskRegisterDao getRiskRegisterDao() throws DaoException;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public abstract TenantDao getTenantDao() throws DaoException;
	
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
