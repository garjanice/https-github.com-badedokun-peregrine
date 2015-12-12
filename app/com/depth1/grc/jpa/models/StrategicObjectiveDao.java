/**
 * 
 */
package com.depth1.grc.jpa.models;

import java.util.List;
import java.util.Set;

import com.depth1.grc.model.DaoException;

/**
 * The Strategic Objective interface that defines the contract that all implementing classes must abide by
 * It provides API to expose to caller of classes that implement the interface
 * @author Bisi Adedokun
 *
 */
public interface StrategicObjectiveDao {
	
	/**
	 * Creates a strategic objective.
	 * 
	 * @param objective strategic objective to create
	 * @throws DaoException if error occurs while creating the Strategic Objective in the data store
	 */
	public void createStrategicObjective(StrategicObjective objective, Set<Measure> measureSet) throws DaoException;
	
	/**
	 * Deletes a strategic objective.
	 * 
	 * @param objectiveId strategic objective ID to delete
	 * @return boolean True if the Strategic Objective is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a strategic objective from the data store
	 */
	public boolean deleteStrategicObjective(long objectiveId) throws DaoException;
	
	
	/**
	 * List strategic objective in the data store.
	 * 
	 * @return List list of strategic objectives
	 * @throws DaoException if error occurs while reading strategic objective from the data store
	 */
	public List<StrategicObjective> listStrategicObjective() throws DaoException;
	
	/**
	 * Updates Strategic objective.
	 * 
	 * @param objective The strategic objective to update
	 * @return boolean True if the strategic objective is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a strategic objective in the data store
	 */
	
	public boolean updateStrategicObjective(StrategicObjective objective) throws DaoException;
	
	/**
	 * Finds a strategic objective.
	 * 
	 * @param objectiveId strategic objective ID to find
	 * @return Strategic objective that was found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	
	public StrategicObjective getStrategicObjective(long objectiveId) throws DaoException;
	/**
	 * Finds a strategic objective.
	 * 
	 * @param name strategic objective name to find
	 * @return Strategic objective that was found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	public StrategicObjective getStrategicObjective(String name) throws DaoException;
		

}
