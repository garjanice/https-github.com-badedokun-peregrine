package com.depth1.grc.model;


import com.depth1.grc.db.util.DropDownList;
import com.depth1.grc.db.util.DropDownListReader;
import com.depth1.grc.exception.DaoException;


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
	 * @see com.depth1.grc.model.DaoFactory#getTenantDao()
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
	 * @see com.depth1.grc.model.DaoFactory#getTenantDao()
	 */
	public DropDownList getDropDownList() {
		    
		    return new DropDownListReader();
		  }

	@Override
	public ProcedureDao getProcedureDao() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}		
	

}

