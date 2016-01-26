/**
 * 
 */
package com.depth1.grc.coso.models;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.depth1.grc.exception.DaoException;

/**
 * The Strategic Objective interface that defines the contract that all implementing classes must abide by
 * It provides API to expose to caller of classes that implement the interface
 * @author Bisi Adedokun
 *
 */
public interface ObjectiveDao {
	
	/**
	 * Creates a strategic objective.
	 * 
	 * @param objective strategic objective to create
	 * @throws DaoException if error occurs while creating the Strategic Objective in the data store
	 */
	public void createObjective(Objective objective, Set<Measure> measureSet) throws DaoException;
	
	/**
	 * Deletes a strategic objective.
	 * 
	 * @param objectiveId the strategic objective ID to delete
	 * @param tenantId the tenant ID of the tenant who set the strategic objective
	 * @return true if the Strategic Objective is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a strategic objective from the data store
	 */
	public boolean deleteObjective(long objectiveId, long tenantId) throws DaoException;
	
	
	/**
	 * Finds Measures with a given strategic objective.
	 * 
	 * @param objectiveid strategic objective id to find
	 * @return Set of Strategic objective that were found
	 * @throws DaoException if error occurs while finding measures in the data store
	 */
	
	public Set<Objective> getMeasure(long objectiveId) throws DaoException;
	
	/**
	 * Finds a strategic objective.
	 * 
	 * @param objectiveId strategic objective ID to find
	 * @return Strategic objective that was found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	
	public Objective getObjective(long objectiveId) throws DaoException;	
	
	/**
	 * Finds a strategic objective.
	 * 
	 * @param objectiveId the strategic objective Id to find
	 * @param tenantId the tenant Id of the tenant who set the strategic objective
	 * @return Strategic objective that was found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	public Objective getObjective(long objectiveId, long tenantId) throws DaoException;
	
	/**
	 * Finds a strategic objective.
	 * 
	 * @param name strategic objective name to find
	 * @return Strategic objective that was found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	public Objective getObjective(String name) throws DaoException;
	
	/**
	 * List measures in the data store.
	 * 
	 * @param tenantId the tenant ID of the tenant whose measures to list
	 * @return list of measures
	 * @throws DaoException if error occurs while reading measures from the data store
	 */
	public List<Measure> listMeasure(long tenantId) throws DaoException;	
	
	/**
	 * List strategic objective in the data store.
	 * 
	 * @param tenantId the tenant ID of the tenant who set the strategic objectives
	 * @return List list of strategic objectives
	 * @throws DaoException if error occurs while reading strategic objective from the data store
	 */
	public List<Objective> listObjective(long tenantId) throws DaoException;
	
	
	/**
	 * Lists strategic objectives.
	 * 
	 * @param name the strategic objective name to find
	 * @param tenantId the tenant ID of the tenant to find
	 * @return List of Strategic objective that were found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	public List<Objective> listObjective(String name, long tenantId) throws DaoException;	
		
	/**
	 * Updates measures corresponding to a given strategic objectives.
	 * @param measure the measures in a map container to update
	 * @param objective the strategic objective that the measures belongs to
	 * @return true if update was successful, false otherwise
	 * @throws DaoException if error occurs while getting states from the state table
	 */
	public boolean updateMeasure(Map<Long, Measure> measure, Objective objective) throws DaoException;
	
	/**
	 * Updates Strategic objective.
	 * 
	 * @param objective The strategic objective to update
	 * @return boolean True if the strategic objective is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a strategic objective in the data store
	 */
	
	public boolean updateObjective(Objective objective) throws DaoException; 
}
