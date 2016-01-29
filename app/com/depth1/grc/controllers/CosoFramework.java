
package com.depth1.grc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.depth1.grc.exception.DaoException;
import com.depth1.grc.exception.DataException;
import com.depth1.grc.jpa.models.JpaObjectiveDao;
import com.depth1.grc.jpa.models.Measure;
import com.depth1.grc.jpa.models.Objective;
import com.depth1.grc.jpa.models.ObjectiveDao;
import com.depth1.grc.jpa.models.ObjectiveSort;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.PrintPdfRiskAssessment;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.views.html.createObjective;
import com.depth1.grc.views.html.frontObjective;
import com.depth1.grc.views.html.updateObjective;
import com.depth1.grc.views.html.viewObjective;

import com.depth1.grc.views.html.viewTenant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;


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
	public Result createStrategicObjective(Objective objective, Set<Measure> measure)  
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
	public Result deleteStrategicObjective(long objectiveId, long tenantId) throws DaoException {

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
	 * List strategic objective in the data store.
	 * 
	 * @return list of strategic objectives
	 * @throws DaoException if error occurs while reading strategic objective from the data store
	 */
	
	public Result listStrategicObjective(long tenantId) throws DaoException {
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
	 * Lists strategic objectives.
	 * 
	 * @param name strategic objective name to find
	 * @param tenantId the tenant ID of the tenant to find
	 * @return List of Strategic objective that were found
	 * @throws DaoException if error occurs while finding a strategic objective in the data store
	 */
	public Result listStrategicObjective(String name, long tenantId) throws DaoException {	
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
	 * Updates Strategic objective and its corresponding measures.
	 * @param measure a map <k,v> where k is the measureId and v is the measure value to update
	 * @param objective the strategic objective to update
	 * @throws DaoException if error occurs while updating a strategic objective in the data store
	 */
	public Result updateStrategicObjective(Map<Long, Measure> measure, Objective objective)  
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
			ObjectiveDao objectiveDao = cassandraFactory.getObjectiveDao();
			objective = objectiveDao.listObjective();
			
			size = objective.size();
			if(query.compareTo("")!= 0){
				objective = objectiveSort.filterDataByQuery(objective, query);
				size = objective.size();
				
			}
			if(size > 0){
				objective = objectiveSort.sortObjective(objective, order);
				objective = objectiveSort.paginateObjective(objective, view, page);
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Objective Front ", e);
		}
			
		return ok(frontObjective.render(objective, size));
	}
	
	
	/**
	 * Shows the ViewObjectives Page with the currently selected Objectives
	 * information.
	 * @return Result of the viewing the Objective Information
	 */
	public Result showViewObjective() {

		return ok(viewObjective.render(selectedObjective));
	}
	
	/**
	 * Updates the selected Objective information and then displays
	 * the list of Objective on the FrontObjective Page
	 * @return Result of updating the Objective
	 */
	public Result updateObjective() {
		Form<Objective> filledObjective = objectiveForm.bindFromRequest();
		Objective criteria = filledObjective.get();
		criteria.setObjectiveId(selectedObjective.getTenantId());
		criteria.setObjectiveId(selectedObjective.getObjectiveId());
		try {
			ObjectiveDao objectiveDao = cassandraFactory.getObjectiveDao();
			objectiveDao.updateObjective(criteria);
			
		} catch (DaoException e) {
			Logger.error("Error occurred while updating objectives ",	e);
		}

		return redirect("/objective/1/10/descendingName");
	}
	
	/**
	 * Shows the UpdateObjective Page with the Objective Information Populating
	 * the fields.
	 * @return Result of updating the Objective information
	 */
	public Result showUpdateObjective() {
		return ok(updateObjective.render(selectedObjective));
	}	
	
	
	/**
	 * Shows the Objective Creation Page
	 * @return Result of the Objective creation
	 */
	public Result showCreateObjective() {
		
		return ok(createObjective.render());
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
	 * Deletes the selected Objective from the database.  Uses Ajax and JSON.
	 * Shows the FrontObjective Page.
	 * @return Result of the deleting the Objective.
	 */
	public Result deleteObjective() {
		try {
			ObjectiveDao objectiveDao = cassandraFactory.getObjectiveDao();
			objectiveDao.deleteObjective(selectedObjective.getObjectiveId(), selectedObjective.getTenantId());
		} catch (DaoException e) {
			Logger.error("Error occurred while deleting objective ", e);
		}

		return redirect("/objective/1/10/ascendingName");
	}
	
	
}
