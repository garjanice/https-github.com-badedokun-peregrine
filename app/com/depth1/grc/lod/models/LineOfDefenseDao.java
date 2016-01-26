/**
 * 
 */
package com.depth1.grc.lod.models;

import java.util.List;
import java.util.UUID;

import com.depth1.grc.coso.models.ControlPrinciple;
import com.depth1.grc.exception.DaoException;

/**
 * The Line of Defense interface defines the contract that all implementing classes must abide by.
 * It provides API to expose to caller of classes that implement the interface
 * @author Bisi Adedokun
 *
 */
public interface LineOfDefenseDao {

	/**
	 * Creates a Line of Defense.
	 * 
	 * @param lod Line of Defense to create
	 * @throws DaoException if error occurs while creating the Line of Defense in the data store
	 */
	public void createLineOfDefense(LineOfDefense lod) throws DaoException;
	
	/**
	 * Deletes a Line of Defense.
	 * 
	 * @param lod Line of Defense to delete
	 * @return boolean True if the line of defense was successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a line of defense from the data store
	 */
	public boolean deleteLineOfDefense(String lod) throws DaoException;
	
	
	/**
	 * List Line of Defense in the data store.
	 * 
	 * @return List of line of defense
	 * @throws DaoException if error occurs while reading line of defenses from the data store
	 */
	public List<LineOfDefense> listLineOfDefense() throws DaoException;
	
	/**
	 * Updates a Line of Defense.
	 * 
	 * @param lod Line of defense to update
	 * @return boolean True if the line of defense was successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a line of defense in the data store
	 */
	
	public boolean updateLineOfDefense(LineOfDefense lod) throws DaoException;
	
	/**
	 * Finds a Line of Defense.
	 * 
	 * @param lod Line of defense to find
	 * @return Line of Defense that was found
	 * @throws DaoException if error occurs while finding a line of defense in the data store
	 */
	public LineOfDefense findLineOfDefense(String lod) throws DaoException;
	

	/**
	 * Creates a Line of Defense function.
	 * 
	 * @param lodFunction Line of Defense function to create
	 * @throws DaoException if error occurs while creating the LOD function in the data store
	 */
	public void createLodFunction(LodFunction lodFunction) throws DaoException;
	
	/**
	 * Deletes a Line of Defense function.
	 * 
	 * @param lodFunctionId Line of Defense function Id to delete
	 * @return boolean True if the line of defense function was successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a LOD function from the data store
	 */
	public boolean deleteLodFunction(UUID lodFunctionId) throws DaoException;
	
	
	/**
	 * List Line of Defense function in the data store.
	 * 
	 * @return List of line of defense function
	 * @throws DaoException if error occurs while reading LOD function from the data store
	 */
	public List<LodFunction> listLodFunction() throws DaoException;
	
	/**
	 * Updates a Line of Defense function.
	 * 
	 * @param lodFunction Line of defense function to update
	 * @return boolean True if the LOD function was successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a LOD function in the data store
	 */
	
	public boolean updateLodFunction(LodFunction lodFunction) throws DaoException;
	
	/**
	 * Finds a Line of Defense function
	 * 
	 * @param lodFunctionId Line of defense function to find
	 * @return Line of Defense function that was found
	 * @throws DaoException if error occurs while finding a LOD function in the data store
	 */
	public LodFunction findLodFunction(UUID lodFunctionId) throws DaoException;
	
	/**
	 * Creates a Control Principle.
	 * 
	 * @param controlPrinciple The control principle to create
	 * @throws DaoException if error occurs while creating the control principle in the data store
	 */
	public void createControlPrinciple(ControlPrinciple principle) throws DaoException;
	
	/**
	 * Deletes a Control Principle.
	 * 
	 * @param principleId Control principle Id to delete
	 * @return boolean True if the control principle was successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a control principle from the data store
	 */
	public boolean deleteControlPrinciple(int principleId) throws DaoException;
	
	
	/**
	 * List Control Principles in the data store.
	 * 
	 * @return List control principle
	 * @throws DaoException if error occurs while reading control principle from the data store
	 */
	public List<ControlPrinciple> listControlPrinciple() throws DaoException;
	
	/**
	 * Updates a Control Principle.
	 * 
	 * @param lodFunction Line of defense function to update
	 * @return boolean True if the control principle was successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a control principle in the data store
	 */
	
	public boolean updateControlPrinciple(ControlPrinciple principle) throws DaoException;
	
	/**
	 * Finds a Control Principle
	 * 
	 * @param princileId Control principle Id to find
	 * @return Control Principle that was found
	 * @throws DaoException if error occurs while finding a control principle in the data store
	 */
	public ControlPrinciple findControlPrinciple(int princileId) throws DaoException;
	
		
	
}
