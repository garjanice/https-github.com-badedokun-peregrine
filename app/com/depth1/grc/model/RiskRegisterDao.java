/**
 * 
 */
package com.depth1.grc.model;

/**
 * @author Bisi Adedokun
 *
 */
public interface RiskRegisterDao {
	
	public abstract void createRiskRegister(Register register) throws DaoException;
	
	public abstract boolean updateRiskRegister() throws DaoException;
	
	public abstract List<Register> listRegister() throws DaoException;
	
	public abstract Register findRegister(int riskId) throws DaoException; 

}
