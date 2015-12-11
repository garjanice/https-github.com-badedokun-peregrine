package com.depth1.grc.model;

import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;

/**
 * The Procedure Dao interface defines the contract that all implementing classes must abide by.
 * It provides API to expose to caller of classes that implement the interface
 * 
 * @author Nilima
 *
 */
public interface ProcedureDao {
	public void createProcedure(Procedure procedure) throws DaoException;
	public boolean updateProcedure(Procedure procedure) throws DaoException;
	public boolean restoreProcedure(String procedureId) throws DaoException;
	public Procedure viewProcedureByName(String procedureName) throws DaoException;
	public Procedure viewProcedureById(UUID id) throws DaoException;
	public List<Procedure> viewAllProcedure() throws DaoException;
	public List<Procedure> viewAllDeletedProcedure() throws DaoException;
	public List<Procedure> listProcedure() throws DaoException;
	public boolean deleteProcedure(Procedure procedure) throws DaoException;

	
}
