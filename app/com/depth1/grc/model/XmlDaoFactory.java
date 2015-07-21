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
	@Override
	public PolicyDao getPolicyDao() {
		    
		return new CassandraPolicyDao();
	}
	
	/* (non-Javadoc)
	 * @see com.depth1.grc.model.DaoFactory#getRiskAssesmentDao()
	 */
<<<<<<< HEAD
=======

>>>>>>> ae63f32bcf04bfaabf8330deea14f06142062780
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

