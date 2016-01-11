package com.depth1.grc.model;

import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;

/**
 * The Procedure interface defines the contract that all implementing classes must abide by.
 * It provides API to expose to caller of classes that implement the interface
 * 
 * @author Nilima Shinde
 *
 */
public interface ProcedureDao {
	
	/**
	 * Creates a procedure.
	 * 
	 * @param procedure procedure to create
	 * @throws DaoException if error occurs while creating the Procedure in the data store
	 */
	public void createProcedure(Procedure procedure) throws DaoException;
	/**
	 * Updates a procedure.
	 * 
	 * @param procedure procedure to update
	 * @return boolean True if the procedure is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a procedure in the data store
	 */
	public boolean updateProcedure(Procedure procedure) throws DaoException;
	/**
	* Restores a procedure.
	* 
	* @param procedure UUID to restore
	* @return true if the procedure could be restored successfully, else false
	* @throws DaoException if error occurs while restoring the procedure in the data store
	*/
	public boolean restoreProcedure(String procedureId) throws DaoException;
	/**
	* View a procedure by Procedure name
	* 
	* @param procedureName to view
	* @return Procedure to view
	* @throws DaoException if error occurs while viewing the Procedure in the data store
	*/
	public Procedure viewProcedureByName(String procedureName) throws DaoException;
	/**
	* View a procedure by Procedure id
	* 
	* @param procedure UUID to view
	* @return Procedure to view
	* @throws DaoException if error occurs while viewing the Procedure in the data store
	*/	
	public Procedure viewProcedureById(UUID id) throws DaoException;
	/**
	* View all Procedures
	* 
	* @param void
	* @return List of Procedures to view
	* @throws DaoException if error occurs while viewing the Procedures in the data store
	*/
	public List<Procedure> viewAllProcedure() throws DaoException;
	/**
	* View all Deleted Procedures
	* 
	* @param void
	* @return List of Procedures that are deleted
	* @throws DaoException if error occurs while deleting the Procedures in the data store
	*/
	public List<Procedure> viewAllDeletedProcedure() throws DaoException;
	/**
     * Lists all of the Procedures in the database
     * @return a list containing all Procedures
     * @throws DaoException if failed to retrieve list of Procedures
     */
	public List<Procedure> listProcedure() throws DaoException;
	/**
	 * Deletes a procedure.
	 * 
	 * @param procedure procedure to delete
	 * @return boolean True if the procedure is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a procedure from the data store
	 */
	public boolean deleteProcedure(Procedure procedure) throws DaoException;

	
}
