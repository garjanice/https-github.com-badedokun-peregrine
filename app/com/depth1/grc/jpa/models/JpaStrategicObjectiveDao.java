/**
 * 
 */
package com.depth1.grc.jpa.models;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.depth1.grc.db.util.DataStoreException;
import com.depth1.grc.db.util.JpaUtil;
import com.depth1.grc.model.DaoException;

import play.Logger;

/**
 * @author badedokun
 *
 */
public class JpaStrategicObjectiveDao implements StrategicObjectiveDao {

	/**
	 * 
	 */
	public JpaStrategicObjectiveDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a strategic objective.
	 * 
	 * @param objective strategic objective to create
	 * @param measureSet a Set containing measures
	 * @throws DaoException if error occurs while creating the Strategic Objective in the data store
	 */
	@Override
	public void createStrategicObjective(StrategicObjective objective, Set<Measure> measureSet) throws DaoException {
		EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			JpaUtil.beginTransaction(entityManager);
			objective.setMeasure(measureSet);
			entityManager.persist(objective);
			// using Java 8 forEach to iterate over the Set object
			measureSet.forEach(measures->entityManager.persist(measures));
			JpaUtil.comitTransaction(entityManager);
			JpaUtil.closeTransaction(entityManager);
		} catch (DataStoreException e) {
			Logger.error("Error occurred while saving data in Strategic Objective tables ", e);
			JpaUtil.rollbackTransaction(entityManager);
		}

	}

	/**
	 * Deletes a strategic objective.
	 * 
	 * @param objectiveId strategic objective ID to delete
	 * @return boolean True if the Strategic Objective is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a strategic objective from the data store
	 */
	@Override
	public boolean deleteStrategicObjective(long objectiveId) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * List strategic objective in the data store.
	 * 
	 * @return List list of strategic objectives
	 * @throws DaoException if error occurs while reading strategic objective from the data store
	 */
	@Override
	public List<StrategicObjective> listStrategicObjective() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Updates Strategic objective.
	 * 
	 * @param objective The strategic objective to update
	 * @return boolean True if the strategic objective is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a strategic objective in the data store
	 */
	@Override
	public boolean updateStrategicObjective(StrategicObjective objective) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Finds a strategic objective.
	 * 
	 * @param objectiveId strategic objective ID to find
	 * @return Strategic objective that was found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	@Override
	public StrategicObjective getStrategicObjective(long objectiveId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Finds a strategic objective.
	 * 
	 * @param name strategic objective name to find
	 * @return Strategic objective that was found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	@Override
	public StrategicObjective getStrategicObjective(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
