/**
 * 
 */
package com.depth1.grc.jpa.models;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.depth1.grc.db.util.DataStoreException;
import com.depth1.grc.db.util.JpaUtil;
import com.depth1.grc.model.DaoException;

import play.Logger;

/**
 * This class provides CRUD capabilities for Strategic Objective.
 * 
 * @author Bisi Adedokun
 *
 */
public class JpaStrategicObjectiveDao implements StrategicObjectiveDao {
	EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	/**
	 * 
	 */
	public JpaStrategicObjectiveDao() {
		entityManager = entityManagerFactory.createEntityManager();
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
		
		try {
			JpaUtil.beginTransaction(entityManager);
			objective.setMeasure(measureSet);
			entityManager.persist(objective);
			// using Java 8 forEach to iterate over the Set object
			measureSet.forEach(measures->entityManager.persist(measures));
			JpaUtil.comitTransaction(entityManager);
			
		} catch (DataStoreException e) {
			Logger.error("Error occurred while saving data in Strategic Objective tables ", e);
			JpaUtil.rollbackTransaction(entityManager);
		} finally {
			JpaUtil.closeTransaction(entityManager);
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
		int deletedCount = 0;
		try {
			JpaUtil.beginTransaction(entityManager);
			StrategicObjective strategicObjective = getStrategicObjective(objectiveId);
			if (strategicObjective != null) {
				entityManager.remove(strategicObjective);
				++deletedCount;
			}
			JpaUtil.comitTransaction(entityManager);
			
		} catch (DataStoreException e) {
			Logger.error("Error occurred while deleting data in Strategic Objective tables ", e);
			JpaUtil.rollbackTransaction(entityManager);
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		
		return deletedCount > 0 ? true : false;
	}
	
	/**
	 * Deletes a strategic objective.
	 * 
	 * @param objectiveId strategic objective ID to delete
	 * @return boolean True if the Strategic Objective is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a strategic objective from the data store
	 */
	//@Override
	public boolean deleteStrategicObjectiveMeasure(long objectiveId) throws DaoException {
		int deletedCount = 0;
		try {
			JpaUtil.beginTransaction(entityManager);
			StrategicObjective strategicObjective = getStrategicObjective(objectiveId);   
			strategicObjective = (StrategicObjective) entityManager.createQuery(
			        "from StrategicObjective s " +
			                "join fetch s.name " +
			                "where s.id = :objectiveId", StrategicObjective.class)
			        .setParameter("objectiveId", objectiveId)
			        .getSingleResult();
			Logger.info("QUERY SUCCESSFUL ");
			strategicObjective.removeMeasure(strategicObjective.getMeasure().iterator().next());
			Logger.info("REMOVE ERROR ");
			++deletedCount;
			/*if (strategicObjective != null) {
				entityManager.remove(strategicObjective);
				++deletedCount;
			}*/
			JpaUtil.comitTransaction(entityManager);
			
		} catch (DataStoreException e) {
			Logger.error("Error occurred while deleting data in Strategic Objective tables ", e);
			JpaUtil.rollbackTransaction(entityManager);
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		
		return deletedCount > 0 ? true : false;
	}	

	/**
	 * List strategic objective in the data store.
	 * 
	 * @return List list of strategic objectives
	 * @throws DaoException if error occurs while reading strategic objective from the data store
	 */
	@Override
	public List<StrategicObjective> listStrategicObjective() throws DaoException {
		List<StrategicObjective> resultList = null;
		try {
			JpaUtil.beginTransaction(entityManager);
			TypedQuery<StrategicObjective> query = entityManager.createQuery("from StrategicObjective", StrategicObjective.class);
			resultList = query.getResultList();
			JpaUtil.closeTransaction(entityManager);
		} catch (DataStoreException e) {
			Logger.error("Error occurred while retrieving data from Strategic Objective table ", e);
		}
		return resultList;
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
		int updatedCount = 0;
		try {
			JpaUtil.beginTransaction(entityManager);
			StrategicObjective strategicObjective = getStrategicObjective(objective.getObjectiveId());
			if (strategicObjective != null) {
				strategicObjective.setName(objective.getName());
				strategicObjective.setMeasure(objective.getMeasure());
				strategicObjective.setObjective(objective.getObjective());
				++updatedCount;
			}
			JpaUtil.comitTransaction(entityManager);
			
		} catch (DataStoreException e) {
			Logger.error("Error occurred while updating data in Strategic Objective tables ", e);
			JpaUtil.rollbackTransaction(entityManager);
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		
		return updatedCount > 0 ? true : false;
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
		
		return entityManager.find(StrategicObjective.class, objectiveId);
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
		TypedQuery<StrategicObjective> query = null;
		try {
			JpaUtil.beginTransaction(entityManager);
			query = entityManager.createQuery("from StrategicObjective s WHERE s.name = :name", StrategicObjective.class);
			query.setParameter("name", name);
		} catch (DataStoreException e) {
			Logger.error("Error occurred while retrieving data in Strategic Objective tables ", e);			
			return null;
		}
		return query.getSingleResult();
	}
	
	/**
	 * Lists strategic objectives.
	 * 
	 * @param name strategic objective name to find
	 * @return List of Strategic objective that were found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	@Override
	public List<StrategicObjective> listStrategicObjective(String name) throws DaoException {
		TypedQuery<StrategicObjective> query = null;
		try {
			JpaUtil.beginTransaction(entityManager);
			query = entityManager.createQuery("from StrategicObjective s WHERE s.name = :name", StrategicObjective.class);
			query.setParameter("name", name);
		} catch (DataStoreException e) {
			Logger.error("Error occurred while deleting data in Strategic Objective tables ", e);			
			return null;
		}
		return query.getResultList();
	}	

}
