/**
 * 
 */
package com.depth1.grc.model;

/**
 * @author Bisi Adedokun
 *
 */
public interface RiskRegisterDao {
	
	public void createRiskRegister(Register register) throws DaoException;
	
	public boolean updateRiskRegister() throws DaoException;
	
	//public boolean viewRiskRegister() throws DaoException;
	
	public List<Register> listRegister throws DaoException;
	
	public Register findRegister(int riskId) throws DaoException; 

}
