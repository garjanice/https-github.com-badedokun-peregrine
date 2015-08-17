package com.depth1.grc.model;

import java.util.List;

public interface RiskTypeDao {

	public void createRiskType(RiskType riskType) throws DaoException;
	public void deleteRiskType(RiskType riskType) throws DaoException; 
	public void updateRiskType(RiskType riskType) throws DaoException;
	public List<RiskType> listRiskType() throws DaoException;
	
}
