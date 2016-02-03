/**
 * 
 */
package com.depth1.grc.coso.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.depth1.grc.db.util.JpaUtil;
import com.depth1.grc.exception.DaoException;
import com.depth1.grc.exception.DataStoreException;
import com.depth1.grc.model.RdbDaoFactory;

import play.Logger;

/**
 * This class is used to set different objectives: Strategic, Operations, Reporting or Compliance.
 * It provides CRUD capabilities for setting different type of objectives.
 * 
 * @author Bisi Adedokun
 *
 */
public class JpaObjectiveDao implements ObjectiveDao {
	EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	/**
	 * 
	 */
	public JpaObjectiveDao() {
		EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();		
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
	public void createObjective(Objective objective, Set<Measure> measureSet) throws DaoException {
		
		try {
			JpaUtil.beginTransaction(entityManager);
			objective.setMeasure(measureSet);
			entityManager.persist(objective);
			// using Java 8 forEach to iterate over the Set object
			
			if (!(measureSet.isEmpty()))
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
	 * @param objectiveId the strategic objective ID to delete
	 * @param tenantId the tenant ID of the tenant who set the strategic objective
	 * @return boolean True if the Strategic Objective is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a strategic objective from the data store
	 */
	@Override
	public boolean deleteObjective(long objectiveId, long tenantId) throws DaoException {
		int deletedCount = 0;
		try {
			JpaUtil.beginTransaction(entityManager);
			Objective objective = getObjective(objectiveId, tenantId);
			if (objective != null) {
				entityManager.remove(objective);
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
	public boolean deleteObjectiveMeasure(long objectiveId) throws DaoException {
		int deletedCount = 0;
		try {
			JpaUtil.beginTransaction(entityManager);
			Objective objective = getObjective(objectiveId);   
			objective = (Objective) entityManager.createQuery(
			        "from Objective s " +
			                "join fetch s.name " +
			                "where s.id = :objectiveId", Objective.class)
			        .setParameter("objectiveId", objectiveId)
			        .getSingleResult();
			Logger.info("QUERY SUCCESSFUL ");
			objective.removeMeasure(objective.getMeasure().iterator().next());
			Logger.info("REMOVE ERROR ");
			++deletedCount;

			JpaUtil.comitTransaction(entityManager);
		} catch (DataStoreException e) {
			Logger.error("Error occurred while deleting data in Objective tables ", e);
			JpaUtil.rollbackTransaction(entityManager);
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		
		return deletedCount > 0 ? true : false;
	}	

	/**
	 * Finds Measures with a given strategic objective.
	 * 
	 * @param objectiveid strategic objective id to find
	 * @return Set of Strategic objective that were found
	 * @throws DaoException if error occurs while finding measures in the data store
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Objective> getMeasure(long objectiveId) throws DaoException {
		Query query = null;
		List<Objective> resultList = null;
		try {
			JpaUtil.beginTransaction(entityManager);
			query = entityManager.createNativeQuery("select a.objectiveid, a.objective, a.name, b.measure from objective a left outer join measure b on a.objectiveid = b.objectiveid where a.objectiveid = :objectiveid", Objective.class);
			query.setParameter("objectiveid", objectiveId);
			resultList = query.getResultList();
			
		} catch (DataStoreException e) {
			Logger.error("Error occurred while retrieving data in Measure table ", e);
			JpaUtil.rollbackTransaction(entityManager);
			return null;
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		Set<Objective> measureSet = new LinkedHashSet<Objective>((List<Objective>)resultList);
		return measureSet;
	}

	/**
	 * Finds a strategic objective.
	 * 
	 * @param objectiveId strategic objective ID to find
	 * @return Strategic objective that was found
	 * @throws DaoException if error occurs while finding a objective in the data store
	 */
	@Override
	public Objective getObjective(long objectiveId) throws DaoException {
		
		return entityManager.find(Objective.class, objectiveId);
	}

	/**
	 * Finds a strategic objective.
	 * 
	 * @param objectiveId strategic objective Id to find
	 * @param tenantId the tenant Id of the tenant who set the strategic objective
	 * @return Strategic objective that was found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	@Override
	public Objective getObjective(long objectiveId, long tenantId) throws DaoException {
		TypedQuery<Objective> query = null;
		try {
			JpaUtil.beginTransaction(entityManager);
			query = entityManager.createQuery("from Objective s WHERE s.name = :name AND s.tenantid = :tenantid", Objective.class);
			query.setParameter("objectiveid", objectiveId);
			query.setParameter("tenantid", tenantId);
		} catch (DataStoreException e) {
			Logger.error("Error occurred while retrieving data in Objective tables ", e);
			JpaUtil.rollbackTransaction(entityManager);
			return null;
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		return query.getSingleResult();
	}
	
	/**
	 * Finds a strategic objective.
	 * 
	 * @param name strategic objective name to find
	 * @return Strategic objective that was found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	@Override
	public Objective getObjective(String name) throws DaoException {
		TypedQuery<Objective> query = null;
		try {
			JpaUtil.beginTransaction(entityManager);
			query = entityManager.createQuery("from Objective s WHERE s.name = :name", Objective.class);
			query.setParameter("name", name);
		} catch (DataStoreException e) {
			Logger.error("Error occurred while retrieving data in Objective tables ", e);
			JpaUtil.rollbackTransaction(entityManager);
			return null;
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		return query.getSingleResult();
	}	
	
	/**
	 * List measures in the data store.
	 * 
	 * @param tenantId the tenant ID of the tenant whose measures to list
	 * @return list of measures
	 * @throws DaoException if error occurs while reading measures from the data store
	 */
	@Override
	public List<Measure> listMeasure(long tenantId) throws DaoException {
		List<Measure> resultList = null;
		try {
			JpaUtil.beginTransaction(entityManager);
			TypedQuery<Measure> query = entityManager.createQuery("from Measure WHERE s.tenantid = :tenantid", Measure.class);
			query.setParameter("tenantid", tenantId);
			resultList = query.getResultList();
		} catch (DataStoreException e) {
			Logger.error("Error occurred while retrieving data from Measure table ", e);
			JpaUtil.rollbackTransaction(entityManager);
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		return resultList;
	}
	
	/**
	 * List strategic objective in the data store.
	 * 
	 * @return list of strategic objectives
	 * @throws DaoException if error occurs while reading objective from the data store
	 */
	@Override
	public List<Objective> listObjective() throws DaoException {
		List<Objective> resultList = null;
		try {
			JpaUtil.beginTransaction(entityManager);
			TypedQuery<Objective> query = entityManager.createQuery("from Objective", Objective.class);
			resultList = query.getResultList();
			
		} catch (DataStoreException e) {
			Logger.error("Error occurred while retrieving data from Objective table ", e);
			JpaUtil.rollbackTransaction(entityManager);
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		return resultList;
	}	
	
	/**
	 * List strategic objective in the data store.
	 * 
	 * @param tenantId the tenant ID of the tenant who set the strategic objectives
	 * @return list of strategic objectives
	 * @throws DaoException if error occurs while reading objective from the data store
	 */
	@Override
	public List<Objective> listObjective(long tenantId) throws DaoException {
		List<Objective> resultList = null;
		try {
			JpaUtil.beginTransaction(entityManager);
			TypedQuery<Objective> query = entityManager.createQuery("from Objective WHERE s.tenantid = :tenantid", Objective.class);
			query.setParameter("tenantid", tenantId);
			resultList = query.getResultList();
		} catch (DataStoreException e) {
			Logger.error("Error occurred while retrieving data from Objective table ", e);
			JpaUtil.rollbackTransaction(entityManager);
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		return resultList;
	}
	
	/**
	 * Lists strategic objectives.
	 * 
	 * @param name strategic objective name to find
	 * @param tenantId the tenant ID of the tenant to find
	 * @return List of Strategic objective that were found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	@Override
	public List<Objective> listObjective(String name, long tenantId) throws DaoException {
		TypedQuery<Objective> query = null;
		try {
			JpaUtil.beginTransaction(entityManager);
			query = entityManager.createQuery("from Objective s WHERE s.name = :name AND s.tenantid = :tenantid", Objective.class);
			query.setParameter("name", name);
			query.setParameter("tenantid", tenantId);
		} catch (DataStoreException e) {
			Logger.error("Error occurred while deleting data in Objective tables ", e);	
			JpaUtil.rollbackTransaction(entityManager);
			return null;
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		return query.getResultList();
	}	
	
	/**
	 * Updates measures corresponding to a given strategic objectives.
	 * 
	 * @param measure the measures in a map container to update
	 * @param objective the strategic objective that the measures belongs to
	 * @return true if update was successful, false otherwise
	 * @throws DaoException if error occurs while getting states from the state table
	 */
	@Override
	public boolean updateMeasure(Map<Long, Measure> measure, Objective objective) throws DaoException {
		
		String update = "UPDATE measure set measure = ? WHERE measureid = ? AND objectiveid = ? AND tenantid = ? ";
		PreparedStatement updateMeasure = null;
		boolean success = false;
		try {

			updateMeasure = RdbDaoFactory.getSession().prepareStatement(update);
			for (Map.Entry<Long, Measure> e: measure.entrySet()) {
				updateMeasure.setString(1, e.getValue().getMeasure());
				updateMeasure.setLong(2, e.getValue().getMeasureId());
				updateMeasure.setLong(3, objective.getObjectiveId());
				updateMeasure.setLong(4, objective.getTenantId());
				updateMeasure.executeUpdate();
			}
			success = true;
		} catch (DataStoreException e) {
			Logger.error("Error occurred while updating data in the measure table ", e);
		} catch (SQLException e) {
			Logger.error("Error occurred while updating data in the measure table ", e);
		} finally {
			// close the connection to the database();
			RdbDaoFactory.close(RdbDaoFactory.getSession());
		}
		return success;
		
	}	
	
	/**
	 * Updates Strategic objective.
	 * 
	 * @param objective The strategic objective to update
	 * @return True if the strategic objective is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a strategic objective in the data store
	 */
	@Override
	public boolean updateObjective(Objective obj) throws DaoException {
		int updatedCount = 0;
		try {
			//JpaUtil.beginTransaction(entityManager);
			Objective objective = getObjective(obj.getObjectiveId(), obj.getTenantId());
			if (objective != null) {
				objective.setName(obj.getName());
				objective.setObjective(obj.getObjective());
				++updatedCount;
			}
			JpaUtil.comitTransaction(entityManager);
			
		} catch (DataStoreException e) {
			Logger.error("Error occurred while updating data in Objective tables ", e);
			JpaUtil.rollbackTransaction(entityManager);
		} finally {
			JpaUtil.closeTransaction(entityManager);
		}
		
		return updatedCount > 0 ? true : false;
	}	

}
