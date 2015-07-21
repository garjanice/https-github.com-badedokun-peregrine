package com.depth1.grc.model;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class RdbDaoFactory extends DaoFactory
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public RdbDaoFactory(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.depth1.grc.model.DaoFactory#getPolicyDao()
	 */
	@Override
	public PolicyDao getPolicyDao() {
		    
		return new CassandraPolicyDao();
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

}

