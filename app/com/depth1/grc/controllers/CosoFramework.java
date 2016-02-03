
package com.depth1.grc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.depth1.grc.coso.models.JpaObjectiveDao;
import com.depth1.grc.coso.models.Measure;
import com.depth1.grc.coso.models.Objective;
import com.depth1.grc.coso.models.ObjectiveDao;
import com.depth1.grc.exception.DaoException;
import com.depth1.grc.exception.DataException;
import com.depth1.grc.model.DaoFactory;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * This controller class provides operations to support the COSO framework for frontend use.
 * 
 * @author Bisi Adedokun
 * @version Revision 1.0
 * Created 12/29/2015
 *
 */
public class CosoFramework extends Controller {
	
	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory.getDaoFactory(DaoFactory.CASSANDRA);
	static DaoFactory rdbFactory = DaoFactory.getDaoFactory(DaoFactory.MARIADB);	

	/**
	 * 
	 */
	public CosoFramework() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Creates Strategic objective and its corresponding measures.
	 * 
	 * @param objective the strategic objective to update
	 * @param measure a set containing the measures for the strategic objective
	 * @throws DaoException if error occurs while updating a strategic objective in the data store
	 */
	public Result createObjective(Objective objective, Set<Measure> measure)  
			 throws DaoException {

		try {
			ObjectiveDao obj = new JpaObjectiveDao();
			obj.createObjective(objective, measure);
		} catch (DataException e) {
			Logger.error("Error occured while creating strategic objective", e);
		}
		return ok();
	}
	
	/**
	 * Deletes a strategic objective.
	 * 
	 * @param objectiveId strategic objective ID to delete
	 * @throws DaoException if error occurs while deleting a strategic objective from the data store
	 */
	public Result deleteObjective(long objectiveId, long tenantId) throws DaoException {

		try {
			ObjectiveDao obj = new JpaObjectiveDao();
			obj.deleteObjective(objectiveId, tenantId);
		} catch (DataException e) {
			Logger.error("Error occured while deleting strategic objective", e);
		}
		
		return ok();
	}
	
	/**
	 * List measures in the data store.
	 * 
	 * @return list of measures
	 * @throws DaoException if error occurs while reading measures from the data store
	 */
	
	public Result listMeasure(long tenantId) throws DaoException {
		@SuppressWarnings("unused")
		List<Measure> list = new ArrayList<>();
		try {
			ObjectiveDao obj = new JpaObjectiveDao();
			list = obj.listMeasure(tenantId);
		} catch (DataException e) {
			Logger.error("Error occured while reading measures", e);
		}
		
		return ok();		
	}	
	
	/**
	 * List objectives in the data store.
	 * 
	 * @return list of strategic objectives
	 * @throws DaoException if error occurs while reading strategic objective from the data store
	 */
	
	public Result listObjective() throws DaoException {
		@SuppressWarnings("unused")
		List<Objective> list = new ArrayList<>();
		try {
			ObjectiveDao obj = new JpaObjectiveDao();
			list = obj.listObjective();
		} catch (DataException e) {
			Logger.error("Error occured while reading strategic objective", e);
		}
		
		return ok();		
	}	
	
	/**
	 * List objectives in the data store.
	 * 
	 * @return list of strategic objectives
	 * @throws DaoException if error occurs while reading strategic objective from the data store
	 */
	
	public Result listObjective(long tenantId) throws DaoException {
		@SuppressWarnings("unused")
		List<Objective> list = new ArrayList<>();
		try {
			ObjectiveDao obj = new JpaObjectiveDao();
			list = obj.listObjective(tenantId);
		} catch (DataException e) {
			Logger.error("Error occured while reading strategic objective", e);
		}
		
		return ok();		
	}
	
	/**
	 * Lists objectives in the data store
	 * 
	 * @param name strategic objective name to find
	 * @param tenantId the tenant ID of the tenant to find
	 * @return List of Strategic objective that were found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	public Result listObjective(String name, long tenantId) throws DaoException {	
		@SuppressWarnings("unused")
		List<Objective> list = new ArrayList<>();
		try {
			ObjectiveDao obj = new JpaObjectiveDao();
			list = obj.listObjective(name, tenantId);
		} catch (DataException e) {
			Logger.error("Error occured while reading strategic objective", e);
		}
		
		return ok();
	}
	
	/**
	 * Updates Objective and its corresponding measures.
	 * @param measure a map <k,v> where k is the measureId and v is the measure value to update
	 * @param objective the strategic objective to update
	 * @throws DaoException if error occurs while updating a strategic objective in the data store
	 */
	public Result updateObjective(Map<Long, Measure> measure, Objective objective)  
			 throws DaoException {

		try {
			ObjectiveDao obj = new JpaObjectiveDao();
			obj.updateObjective(objective);
			obj.updateMeasure(measure, objective);
		} catch (DataException e) {
			Logger.error("Error occured while updating strategic objective", e);
		}
		return ok();
	}	

}
