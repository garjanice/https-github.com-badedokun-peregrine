package com.depth1.grc.model;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class XmlDaoFactory extends DaoFactory
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public XmlDaoFactory(){
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
	public RiskAssessmentDao getRiskAssesmentDao() {
		    
		    return null;
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

