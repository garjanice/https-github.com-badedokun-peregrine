package com.depth1.grc.model;

import java.util.List;
import com.depth1.grc.exception.DaoException;

public interface RiskRegisterDao {
	
	public void createRiskRegister(RiskRegister register) throws DaoException;
	
	public boolean updateRiskRegister(RiskRegister register) throws DaoException;
	
	public List<RiskRegister> listRegister() throws DaoException;
	
	public RiskRegister findRegister(int riskId) throws DaoException;
	
	public boolean deleteRiskRegister(RiskRegister register) throws DaoException;

}
