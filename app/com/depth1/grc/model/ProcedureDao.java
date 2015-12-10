package com.depth1.grc.model;

import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;

/**
 * @author Team-A
 *
 */
public interface ProcedureDao {
	public void createProcedure(Procedure procedure) throws DaoException;
	public boolean updateProcedure(Procedure procedure) throws DaoException;
	//public boolean deleteProcedure(String procedureId) throws DaoException;
	public boolean restoreProcedure(String procedureId) throws DaoException;
	public Procedure viewProcedureByName(String procedureName) throws DaoException;
	public Procedure viewProcedureById(UUID id) throws DaoException;
	public List<Procedure> viewAllProcedure() throws DaoException;
	public List<Procedure> viewAllDeletedProcedure() throws DaoException;
	public List<Procedure> listProcedure() throws DaoException;
	public boolean deleteProcedure(Procedure procedure) throws DaoException;

	
	/**
     * Lists some of the Risk Assessments in the database
     * @return a list containing all Risk Assessments
     * @throws DaoException if failed to retrieve list of Risk Assessments
     */
	/*public List<Procedure> listProcedurePagination(int numberOfItems, int page) throws DaoException;
	*/
}
