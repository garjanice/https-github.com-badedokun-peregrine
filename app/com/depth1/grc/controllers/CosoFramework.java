
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
import com.depth1.grc.coso.models.ObjectiveSort;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.DepartmentDao;
import com.depth1.grc.views.html.*;
import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;

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
	
	final static Form<Objective> objectiveForm = Form.form(Objective.class);
	static List<Objective> objective;
	static Objective selectedObjective;

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
	
	
	
	/**
	 *  Shows the list of Objectives in the Database on the FrontObjective Page
	 * @param page current page number form pagination
	 * @param view the number of items to show per page
	 * @param order the sorting order of the page
	 * @param query the string to search for in the Objective list
	 * @return Result of the List Page
	 */
	public Result showFrontObjectivePage(int page, int view, String order, String query){
		int size = 0;
						
		try {
			ObjectiveSort objectiveSort = new ObjectiveSort();	
			ObjectiveDao objectives = new JpaObjectiveDao();
			objectives.listObjective();
		   
			if(query.compareTo("")!= 0){
				objective = objectiveSort.filterDataByQuery(objective, query);
				size = (objective).size();
			}
			
			if(size > 0){
				objective = objectiveSort.sortObjective(objective, order);
				objective = objectiveSort.paginateObjective(objective, view, page);
			}
			
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Objective Front ", e);
		}
			
		return ok();
	}
	
	
	/**
	 * Sets the Objective that the user picks from the list of Objective
	 * on the FrontObjective Page.   Uses Ajax and JSON.
	 * @return Result of setting the selected Objective
	 */
	public Result setSelectedObjective() {
		
		JsonNode node = request().body().asJson().get("val");
		
		if(node == null){
		        return badRequest("empty json"); 
		}
		String inputString = node.textValue();
		
		int index = Integer.parseInt(inputString);
				
		selectedObjective = objective.get(index);
		return ok();
	}
	
	
	/**
	 * Shows the ViewObjectives Page with the currently selected Objectives
	 * information.
	 * @return Result of the viewing the Objective Information
	 */
	public Result showViewObjectivePage() {

		return ok(viewObjective.render(selectedObjective));
	}
	
	
	
	/**
	 * Shows the UpdateObjective Page with the Objective Information Populating
	 * the fields.
	 * @return Result of updating the Objective information
	 */
	public Result showUpdateObjectivePage() {
		return ok(updateObjective.render(selectedObjective));
	}	
	
	
	/**
	 * Shows the Objective Creation Page
	 * @return Result of the Objective creation
	 */
	public Result showCreateObjectivePage() {
		
		return ok(createObjective.render());
	}
	
}
