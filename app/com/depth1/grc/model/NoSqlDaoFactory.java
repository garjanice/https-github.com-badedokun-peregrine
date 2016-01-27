package com.depth1.grc.model;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public abstract class NoSqlDaoFactory extends DaoFactory
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public NoSqlDaoFactory(){
		super();
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
	public RiskAssessmentDao getRiskAssessmentDao() {
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
	 * @see com.depth1.grc.model.DaoFactory#getTenantDao()
	 */
	public UserProfileDao getUserProfileDao() {

		return new CassandraUserProfileDao();
	}	
	

}

