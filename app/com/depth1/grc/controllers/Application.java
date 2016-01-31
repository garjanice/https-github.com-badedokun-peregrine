package com.depth1.grc.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.Long;

import play.Logger;
import play.data.Form;
import play.data.Form.Field;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.mvc.Security;

import com.datastax.driver.core.Session;
import com.depth1.grc.db.util.DropDownList;
import com.depth1.grc.exception.DaoException;
import com.depth1.grc.exception.DataException;
import com.depth1.grc.jpa.models.JpaObjectiveDao;
import com.depth1.grc.jpa.models.LineOfDefenseDao;
import com.depth1.grc.jpa.models.Measure;
import com.depth1.grc.jpa.models.Objective;
import com.depth1.grc.jpa.models.LineOfDefense;
import com.depth1.grc.jpa.models.LodFunction;
import com.depth1.grc.jpa.models.ControlPrinciple;
import com.depth1.grc.jpa.models.JpaLineOfDefenseDao;
import com.depth1.grc.jpa.models.LodSort;
import com.depth1.grc.jpa.models.PrincipleSort;
import com.depth1.grc.jpa.models.ObjectiveDao;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Department;
import com.depth1.grc.model.PrintPdfRiskAssessment;
import com.depth1.grc.model.RiskAssessment;
import com.depth1.grc.model.RiskAssessmentDao;
import com.depth1.grc.model.RiskAssessmentSort;
import com.depth1.grc.model.Policy;
import com.depth1.grc.model.PolicyDao;
import com.depth1.grc.model.PolicySort;
import com.depth1.grc.views.html.*;
import com.fasterxml.jackson.databind.JsonNode;

@Security.Authenticated(Secured.class)
public class Application extends Controller {

	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory.getDaoFactory(DaoFactory.CASSANDRA);
	static DaoFactory rdbFactory = DaoFactory.getDaoFactory(DaoFactory.MARIADB);
	
	final static Form<RiskAssessment> rAForm = Form.form(RiskAssessment.class);
	static List<RiskAssessment> riskAssessments;
	static RiskAssessment selectedRA;

	final static Form<Policy> policyForm = Form.form(Policy.class);
	static List<Policy> policies;
	static Policy selectedPolicy;
	
	final static Form<LineOfDefense> lodForm=Form.form(LineOfDefense.class);
	static List<LineOfDefense> lods;
	static LineOfDefense selectedlod;
	
	final static Form<ControlPrinciple> cpForm=Form.form(ControlPrinciple.class);
	static List<ControlPrinciple> cps;
	static ControlPrinciple selectedcp;
	
	/**
	 * This method is used as a client to test getting data from the Cassandra
	 * database It displays data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * @return a result in the future in an non-blocking fashion
	 */
	public static Result getTimezone() {
		
		try {
			DropDownList dropDown = rdbFactory.getDropDownList();
			List<String> timezones = dropDown.getTimezone();
			//Collections.sort(titles);
			for (String row : timezones) {
				System.out.printf(" %s\n", row);						
			}

		} catch (DataException e) {

		}
		return ok();

	}		
	
	/**
	 * This method is used as a client to test getting data from the Cassandra
	 * database It displays data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * @return a result in the future in an non-blocking fashion
	 */
	public static Result getTitle() {
		
		try {
			DropDownList dropDown = cassandraFactory.getDropDownList();
			List<String> titles = dropDown.getTitle();
			Collections.sort(titles);
			for (String row : titles) {
				System.out.printf(" %s\n", row);						
			}

		} catch (DataException e) {

		}
		return ok();

	}	
	
	/**
	 * @return the result of the RAC creation
	 */
	public Result addRiskAssessment() {
		Form<RiskAssessment> filledRA = rAForm.bindFromRequest();
		RiskAssessment criteria = filledRA.get();
		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory
					.getRiskAssessmentDao();
			riskAssessmentDao.createRiskAssessment(criteria);
		} catch (DaoException e) {

			Logger.error("Error occurred while creating tenant ", e);
		}
		return ok();
	}

	/**
	 * Creates a policy 
	 * @param void
	 * @return Result of the policy created
	 */
	public Result createPolicy() {
		// create form object from the request
		Form<Policy> filledPolicy = policyForm.bindFromRequest();
		// check for required and validate input fields
		// TODO : Validate input fields for Date and Strings
		if (filledPolicy.hasErrors()) {
			Logger.error("The error in the form are " + filledPolicy.errorsAsJson());
			return badRequest(filledPolicy.errorsAsJson());
		}
		// Bind policy object with the form object
		Policy criteria = filledPolicy.get();
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			// create policy on DB
			policyDao.createPolicy(criteria);
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy ", e);
		}
		if (filledPolicy.field("policybody").value() != null) {
			savePolicyBodyDocument(criteria.getName(), filledPolicy.field("policybody"));
		}
		return redirect("/policy/1/10/descendingName");
	}

	/**
	 * Creates strategic objective using JPA.
	 * 
	 * @return
	 */
	public Result createSO() {
		Objective objective;
		JpaObjectiveDao obj = new JpaObjectiveDao();
		try {
			
			//objective = new StrategicObjective(21L, "Strategy 2050", "COSO Enterprise Risk Management Framework");
			 
			/*Measure measure1 = new Measure(21L, "40% in customer base", objective);
			Measure measure2 = new Measure(21L, "35% increase in revenue", objective);
			Measure measure3 = new Measure(21L, "40% increase in profit", objective);
			Measure measure4 = new Measure(21L, "Minimize software defect by 99% in 2016", objective);
			Measure measure5 = new Measure(21L, "Expand sales to Europe and EMEA regions in 2016", objective);
			Measure measure6 = new Measure(21L, "Expand sales to APAC region in 2017", objective);
			Set<Measure> measureSet = Collections.synchronizedSet(new LinkedHashSet<Measure>());
			measureSet.add(measure1);
			measureSet.add(measure2);
			measureSet.add(measure3);
			measureSet.add(measure4);
			measureSet.add(measure5);
			measureSet.add(measure6);
			obj = new JpaStrategicObjectiveDao();
			obj.createStrategicObjective(objective, measureSet);*/
			//obj = new JpaStrategicObjectiveDao();
			//StrategicObjective so = new StrategicObjective();
			//so = obj.getStrategicObjective(13);
			//List<StrategicObjective> result = obj.listStrategicObjective("Strategy 2018");
			//result.forEach(results->Logger.info("The SO: " + results.getObjective()));
			/*so.setName("Strategy 2060");
			so.setObjective("Most Comprehensive Risk Management Software with Built-in 3LoD");
			obj.updateStrategicObjective(so);*/
			Objective so = new Objective();
			//obj = new JpaStrategicObjectiveDao();
			so = obj.getObjective(9L);
			//Set<Measure> set = (Set<Measure>)obj.getMeasure(so.getObjectiveId());
			so.setName("Strategy 2014");
			so.setObjective("Risk Management Software with Built-in 3LoD");
			Set<Measure> measureSet = new LinkedHashSet<Measure>();
			Measure m1 = new Measure();
			Measure m2 = new Measure();
			Measure m3 = new Measure();
			Measure m4 = new Measure();
			Map<Long, Measure> measure1 = new HashMap<>();
			Set<Objective> set = obj.getMeasure(so.getObjectiveId());
			set.forEach(results-> {
				Logger.info("Objective name: "+ results.getName());
				Logger.info("Objective is: "+ results.getObjective());
				Set<Measure> m = results.getMeasure();
				m.forEach(r-> {
					Logger.info("Measure Id: "+ r.getMeasureId());
					Logger.info("Measure is: "+ r.getMeasure());
					try {
						if (r.getMeasureId() == 37) {
							m1.setMeasureId(r.getMeasureId());
							m1.setMeasure("Acquire major bank customers in 2017");
							Logger.info("Measure r ID: " + r.getMeasureId());
							Logger.info("Measure m1 ID: " + m1.getMeasureId());
							measure1.put(m1.getMeasureId(), m1);
							//obj.updateMeasure(m1, 9L);
						}
						if (r.getMeasureId() == 38) {
							m2.setMeasureId(r.getMeasureId());
							m2.setMeasure("Form partnerships with Software Vendors");
							Logger.info("Measure r ID: " + r.getMeasureId());
							Logger.info("Measure m2 ID: " + m2.getMeasureId());
							measure1.put(m2.getMeasureId(), m2);
							//obj.updateMeasure(m2, 9L);
						}
						if (r.getMeasureId() == 41) {
							m3.setMeasureId(r.getMeasureId());
							m3.setMeasure("Host Trade Show in Las Vegas");
							Logger.info("Measure r ID: " + r.getMeasureId());
							Logger.info("Measure m2 ID: " + m3.getMeasureId());
							measure1.put(m3.getMeasureId(), m3);
							//obj.updateMeasure(m2, 9L);
						}
						if (r.getMeasureId() == 39) {
							m4.setMeasureId(r.getMeasureId());
							m4.setMeasure("Increase operation in North America, Europe and Asia");
							Logger.info("Measure r ID: " + r.getMeasureId());
							Logger.info("Measure m2 ID: " + m4.getMeasureId());
							measure1.put(m4.getMeasureId(), m4);
							//obj.updateMeasure(m2, 9L);
						}
					} catch (Exception e) {
						Logger.error("Error updating measure table.", e);

					}
				});
				
			});
			obj.updateObjective(so);
			obj.updateMeasure(measure1, so);
			/*measureSet.add(m1);
			measureSet.add(m2);
			so.setName("Strategy 2015");
			so.setObjective("Comprehensive Risk Management Software with Built-in 3LoD");*/
			//so.setMeasure(measureSet);
			//obj.updateStrategicObjective(so);
			//Logger.info("The measure"););
			//so.setTenantId(20L);
			/*Measure measure1 = new Measure(17L, "28% increase in customer base", so);
			Measure measure2 = new Measure(17L, "30% increase in Profit Margin", so);
			long measureId = so.getObjectiveId();
			Measure measure = new Measure();
			measure.setMeasure("28% increase in customer base");*/
			//measure.set
			/*Set<Measure> measureSet = Collections.synchronizedSet(new LinkedHashSet<Measure>());
			measureSet.add(measure1);
			measureSet.add(measure2);
			so.setMeasure(measureSet);
			obj.updateStrategicObjective(so);*/
	
		} catch (Exception e) {
			Logger.error("Error occured while [updating] data in the data store.", e);
		}	
		return ok();
		}
	
	/**
	 * Deletes a policy.
	 * 
	 * @param UUID of policy
	 * @return delete policy page
	 */
	public Result deletePolicy() {
		//boolean result = false;
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			boolean result = policyDao.deletePolicy(selectedPolicy.getId());
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Delete Page ", e);
		}		
		return redirect("/policy/1/10/descendingName");
	}
	
	/**
     * Action method for the 'Delete' button. Deletes selected Risk Assessment
     * @return
     */
	public Result deleteRiskAssessment() {
		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory
					.getRiskAssessmentDao();
			riskAssessmentDao.deleteRiskAssessment(selectedRA);
		} catch (DaoException e) {
			Logger.error("Error occurred while deleting risk assessment criteria ",	e);
		}

		return redirect("/riskAssessment/1/10/descendingRisk");
	}
	
	/**
	 * Downloads a policy to a file
	 * @param policy name
	 * @return creates a policy file
	 */
	public Result downloadPolicy(String name){
		String filepath = "public/policyDocuments/" + name;
		return ok(new java.io.File(filepath));

	}
	private String getPolicyBodyAsString(String name){
		String policyBody = "";
		try {
			policyBody = new String(Files.readAllBytes(Paths.get("public/policyDocuments/" + name)));
		} catch(IOException e){
				Logger.error("Error reading policy body file into string: ", e);
		}
		return policyBody;
	}

	public Result index() {
		Department dept = new Department();
		long tenantId = 1443847131068L;
		dept.setTenantId(tenantId);
		dept.setDeptName("Engineering");
		dept.setDescription("Responsible for product engineering");
		OrganizationResource.createDepartment(dept);
		//
		Department dept1 = new Department();
		//long tenantId = 1443947438722L;
		dept1.setTenantId(tenantId);
		dept1.setDeptName("Global Technology");
		dept1.setDescription("Responsible for infrastructure management");
		OrganizationResource.createDepartment(dept1);
		
		return ok(index.render());
	}
	/**
	 * This method allows users to update selected Risk Assessments
	 * 
	 * @return update Risk Assessment page
	 */
	public Result printRA() {
		PrintPdfRiskAssessment pdf = new PrintPdfRiskAssessment();
		pdf.printRiskAssessment(selectedRA);
		return redirect("/assets/pdf/RA.pdf");
	}
	/**
	 * This method prints data retrieved from the Cassandra database It displays
	 * data it reads from the database on the console
	 * 
	 * @param session
	 *            The established session to the cluster
	 * 
	 */

	public void printState(Session session) {
		/*ResultSetFuture result = getState(session);
		for (Row row : result.getUninterruptibly()) {
			System.out.printf("%s: %s  %s\n", row.getString("country"),
					row.getString("short_name"), row.getString("long_name"));
		}
		session.close();*/

	}
	
	
	/**
	 * Restores a policy 
	 * @param UUID of policy
	 * @return restore policy page
	 */
	public Result restorePolicy() {
		boolean result = false;
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			result = policyDao.restorePolicy(selectedPolicy.getId());
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Restore Page ", e);
		}
		return redirect("/policy/restore/1/10/descendingName");
	}	
	
	/**
	 * Saves the policy body document to a file on the server file system
	 * @param file name of policy to be saved, text-area field of policy body
	 * @return void
	 */
	private void savePolicyBodyDocument(String fileName, Field policyBody) {
		try {
			//TODO: Replace the path with path on server for file storage
			String dirString = "public/policyDocuments/";
			Path dirPath = Paths.get(dirString);
			if(Files.notExists(dirPath)) {
				Files.createDirectory(dirPath);				
			}
			Path filePath = Paths.get(dirString + fileName);
			File policyDoc = filePath.toFile();
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(policyDoc)),true); 
			//flushing the buffer after file-write 
			writer.print(policyBody.value());
			writer.close();
			Logger.info("Policy Body Documented at " + dirString + fileName);
		}
		catch (IOException e) {
			Logger.error("Error while storing the policy body document " + e);
		}
	}		
	
	/**
	 * Policy
	 */
	public Result setSelectedPolicy() {
		
		JsonNode node = request().body().asJson().get("val");
		
		 if(node == null){
		      return badRequest("empty json"); 
		    }
		String inputString = node.textValue();
		int index = Integer.parseInt(inputString);		
		selectedPolicy = policies.get(index);
		return ok();
	}	
	
	public Result setSelectedRA() {
		//RiskAssessmentSort riskAssessmentUtil = new RiskAssessmentSort();
		JsonNode node = request().body().asJson().get("val");
		
		 if(node == null){
		      return badRequest("empty json"); 
		    }
		String inputString = node.textValue();
		int index = Integer.parseInt(inputString);
		//int size = 0;
		
		selectedRA = riskAssessments.get(index);
		return ok();
	}	
	
	

	

	/**
	 * Shows a create policy editor page 
	 * @param void
	 * @return policy editor page
	 */
	public Result showCreatePolicyEditorPage() {

		// return ok(viewPolicy.render(selectedPolicy));
		return ok();
	}		
	
	/**
	 * Shows a create policy page 
	 * @param void
	 * @return policy create page
	 */
	public Result showCreatePolicyPage() {

		return ok(createPolicy.render(policyForm));
	}
	/**
	 * This method shows the create Risk Assessment page if the 'Create' button
	 * is clicked
	 * 
	 * @return create Risk Assessment page
	 */
	public Result showCreateRAPage() {

		return ok(createRA.render());
	}

	/**
	 * Shows the front page of the Risk Assessment UI
	 * 
	 * @return to the main page
	 */
	public Result showFrontRAPage() {

		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory.getRiskAssessmentDao();
			riskAssessments = riskAssessmentDao.listRiskAssessment();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating risk assessment criteria ", e);
		}
		return ok(frontRA.render(riskAssessments, riskAssessments.size()));
	}
	
	/**
	 *  Pagination for RiskAssessment
	 */
	
	
	public Result showFrontRAPageQuery(int page, int view, String order, String query){
		int size = 0;

		RiskAssessmentSort riskAssessmentSort = new RiskAssessmentSort();

		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory.getRiskAssessmentDao();
			riskAssessments = riskAssessmentDao.listRiskAssessment();
			size = riskAssessments.size();
			if(query.compareTo("")!= 0){
				riskAssessments = riskAssessmentSort.filterDataByQuery(riskAssessments, query);
				size = riskAssessments.size();
				
			}
			if(size > 0){

				riskAssessments = riskAssessmentSort.sortRiskAssessment(riskAssessments, order);
				riskAssessments = riskAssessmentSort.paginateRiskAssessment(riskAssessments, view, page);

			}
		} catch (DaoException e) {
			Logger.error("Error occurred while creating risk assessment criteria ", e);
		}

		return ok(frontRA.render(riskAssessments, size));
	}
	
	

	/**
	 * Shows a list of policy - Front page for Policy 
	 * @param void
	 * @return policy list page
	 */
	public Result showPolicyListPage() {

		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			;
			policies = policyDao.viewAllPolicy();
			
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(policyListPage.render(policies, 1));
	}
		
	public Result showPolicyListPageQuery(int page, int view, String order, String query){
		int size = 0;
		try {
			PolicySort policySort = new PolicySort();
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllPolicy();
			size = policies.size();
			if(query.compareTo("")!= 0){
				policies = policySort.filterDataByQuery(policies, query);
				size = policies.size();
				
			}
			if(size > 0){
				policies = policySort.sortPolicy(policies, order);
				policies = policySort.paginatePolicy(policies, view, page);
			}
			
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(policyListPage.render(policies, size));
	}
	
	/**
	 * Shows a restore policy page 
	 * @param void
	 * @return policy restore page
	 */
	public Result showRestorePolicyPage(int page, int view, String order, String query){
		int size = 0;
		try {
			PolicySort policySort = new PolicySort();
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			policies = policyDao.viewAllDeletedPolicy();
			size = policies.size();
			if(query.compareTo("")!= 0){
				policies = policySort.filterDataByQuery(policies, query);
				size = policies.size();
				
			}
			if(size > 0){
				policies = policySort.sortPolicy(policies, order);
				policies = policySort.paginatePolicy(policies, view, page);
			}
			
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page ", e);
		}

		return ok(restorePolicy.render(policies, size));
	}
	
	/**
	 * Shows a update policy page 
	 * @param void
	 * @return policy update page
	 */
	public Result showUpdatePolicyPage() {
		
		String policybody = getPolicyBodyAsString(selectedPolicy.getName());
		return ok(updatePolicy.render(selectedPolicy, policybody));
		
	}

	/**
	 * This method allows users to update selected Risk Assessments
	 * 
	 * @return update Risk Assessment page
	 */
	public Result showUpdateRAPage() {

		return ok(updateRA.render(selectedRA));
	}



	/**
	 * Shows a view policy page 
	 * @param void
	 * @return policy view page
	 */
	public Result showViewPolicyPage(UUID id) {		

		PolicyDao policyDao;
		try {
			policyDao = cassandraFactory.getPolicyDao();
			final Policy policy = policyDao.viewPolicyById(id);
			String policyBody = "";
			try{policyBody = new String(Files.readAllBytes(Paths.get("public/policyDocuments/" + policy.getName())));}
			catch(IOException e){
				Logger.error("Error reading policy body file into string: ", e);
			}
			return ok(viewPolicy.render(policy, policyBody));
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy Front Page: ", e);
		}
		return ok();
	}

	/**
	 * This method allows users to view Risk Assessments
	 * 
	 * @return view Risk Assessment page
	 */
	public Result showViewRAPage() {

		return ok(viewRA.render(selectedRA));
	}

	
	/**
	 * Updates a policy 
	 * @param void
	 * @return Result of the policy updated
	 */
	public Result updatePolicy() {
		// create form object from the request
		Form<Policy> filledPolicy = policyForm.bindFromRequest();
		// check for required and validate input fields
		// TODO : Validate input fields for Date and Strings
		if (filledPolicy.hasErrors()) {
			Logger.error("The error in the form are " + filledPolicy.errorsAsJson());
			return badRequest(filledPolicy.errorsAsJson());
		}
		// Bind policy object with the form object
		Policy criteria = filledPolicy.get();
		try {
			PolicyDao policyDao = cassandraFactory.getPolicyDao();
			// create policy on DB
			policyDao.updatePolicy(selectedPolicy.getId(), criteria);
			if (filledPolicy.field("policybody").value() != null) {
				savePolicyBodyDocument(criteria.getName(), filledPolicy.field("policybody"));
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Policy ", e);
		} catch (IllegalArgumentException e) {
			Logger.error("Invalid UUID");
			return badRequest("Invalid UUID");
		}

		return redirect("/policy/1/10/descendingName");
	}
	
	/**
	 * @param RiskAssessment
	 *            The RA criteria to update
	 * @return the result of the RAC creation
	 */
	public Result updateRiskAssessment() {
		Form<RiskAssessment> filledRA = rAForm.bindFromRequest();
		RiskAssessment criteria = filledRA.get();
		try {
			RiskAssessmentDao riskAssessmentDao = cassandraFactory
					.getRiskAssessmentDao();
			riskAssessmentDao.updateRiskAssessment(criteria);
		} catch (DaoException e) {
			Logger.error(
					"Error occurred while updating risk assessment criteria ",
					e);
		}

		return redirect("/riskAssessment/1/10/descendingRisk");
	}		

	
	public Result uploadFilePolicyBody(){
		FilePart uploadedFile = request().body().asMultipartFormData().getFile("file");
		if(uploadedFile == null){
		    return badRequest("empty file"); 
		    }
		File file = uploadedFile.getFile();
		String fileName = uploadedFile.getFilename();
		
		return ok();
	}

	/**
	 * Creates a Line of Defense.
	 * 
	 * @param lod Line of Defense to create
	 * @throws DaoException if error occurs while creating the Line of Defense in the data store
	 */
	public Result addLineOfDefense(){
	//	LineOfDefense lod=new LineOfDefense();
		Form<LineOfDefense> filledlod= lodForm.bindFromRequest();
		LineOfDefense lodcriteria = filledlod.get();
		try{
				LineOfDefenseDao lodDao=rdbFactory.getLineOfDefenseDao();
				lodDao.createLineOfDefense(lodcriteria);
		}catch(DataException e) {
			Logger.error("Error occured while creating line of defense");
		}
		return redirect("/lod/1/10/descendingLod");
	}

	public Result createLineOfDefensePage(){
		return ok(createlod.render(lodForm));
	}
	
	/**
	 * Deletes a Line of Defense.
	 * 
	 * @param lod Line of Defense to delete
	 * @return boolean True if the line of defense was successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a line of defense from the data store
	 */
	public Result deleteLineOfDefense(String lod){

		try {
			LineOfDefenseDao lodDao = rdbFactory.getLineOfDefenseDao();
			lodDao.deleteLineOfDefense(lod);
		} catch (DataException e) {
			Logger.error("Error occured while deleting strategic objective", e);
		}
		
		return ok("/lod/1/10/descendingLod");
	}
	
	/**
	 * Select the Line of defense which the user wants to view, update, delete
	 * @return
	 */
	public Result setSelectedLineOfDefense() {
		
		JsonNode node = request().body().asJson().get("val");
		
		 if(node == null){
		      return badRequest("empty json"); 
		    }
		String inputString = node.textValue();
		int index = Integer.parseInt(inputString);		
		selectedlod = lods.get(index);
		return ok();
	}
	
	/**
	 * Shows the front page of the Line Of Defense UI
	 * 
	 * @return to the main page
	 */
	 public Result showFrontLodPage() {

		try {
			LineOfDefenseDao lodDao=rdbFactory.getLineOfDefenseDao();
			lods = lodDao.listLineOfDefense();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Line Of Defense criteria ", e);
		}
		return ok(frontlod.render(lods,lods.size()));
	}
	
	/**
	 * Lists all the Line Of Defenses
	 * @param page
	 * @param view
	 * @param order
	 * @param query
	 * @return DaoException if error occurs while creating the Control Principle in the data store
	 */
	public Result showFrontlodPageQuery(int page, int view, String order, String query){
		int size = 0;

		try {
			LineOfDefenseDao lodDao=rdbFactory.getLineOfDefenseDao();
			LodSort lodSort = new LodSort();
			lods = lodDao.listLineOfDefense();
			size = lods.size();
			if(query.compareTo("")!= 0){
				lods = lodSort.filterDataByQuery(lods, query);
				size = lods.size();
			}
			if(size > 0){
				lods = lodSort.sortLod(lods, order);
				lods = lodSort.paginateLineOfDefense(lods, view, page);
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while listing Line Of Defense criteria ", e);
		}
		return ok(frontlod.render(lods,size));
	}
	/**
	 * Views the selected Line of Defense
	 */
	public Result showViewlodPage(String lod) {
		/*try {
			//LineOfDefense lod1=new LineOfDefense();
			LineOfDefenseDao lodDao=rdbFactory.getLineOfDefenseDao();
			selectedlod=lodDao.findLineOfDefense(lod);
			Logger.info("The required lod is " +selectedlod.getLod());
		}catch(DaoException e){
			Logger.error("Error occured while viewing Line of Defense ");
		}*/
		return ok(viewLod.render(selectedlod));
	}
	/**
	 * Updates a Line of Defense.
	 * @param lod Line of defense to update
	 * @return boolean True if the line of defense was successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a line of defense in the data store
	 */
	public Result updateLineofDefense(){
		Form<LineOfDefense> filledlod= lodForm.bindFromRequest();
		LineOfDefense lodcriteria = filledlod.get();
		try{
			LineOfDefenseDao lodDao=rdbFactory.getLineOfDefenseDao();
			lodDao.updateLineOfDefense(lodcriteria);
		}catch(DaoException e){
			Logger.error("Error occured while updating data in Line Of Defense tables");
		}
		return ok("/lod/1/10/descendingLod");
	}

	public Result updateLodPage(){
		return ok(updatelod.render(selectedlod));
	}
	/**
	 * Creates a Control Principle.
	 * 
	 * @param controlPrinciple The control principle to create
	 * @throws DaoException if error occurs while creating the control principle in the data store
	 */
	public Result addControlPrinciple() {
		Form<ControlPrinciple> filledcp=cpForm.bindFromRequest();
		ControlPrinciple cpcriteria=filledcp.get();
		try {
				LineOfDefenseDao cpdao=rdbFactory.getLineOfDefenseDao();
				cpdao.createControlPrinciple(cpcriteria);
		}catch(DaoException e){
			Logger.error("Error occured while creating Control Principle");
		}
		return ok("/cp/1/10/descendingComponent");
	}
	
	/**
	 * 
	 * @return the page where the creation of control principle takes place
	 */
	public Result addControlPrinciplePage(){
		return ok(createcp.render(cpForm));
	}

	/**
	 * Select the Line of defense which the user wants to view, update, delete
	 * @return
	 */
	public Result setSelectedPrinciple() {
		
		JsonNode node = request().body().asJson().get("val");
		
		 if(node == null){
		      return badRequest("empty json"); 
		    }
		String inputString = node.textValue();
		int index = Integer.parseInt(inputString);		
		selectedcp = cps.get(index);
		return ok();
	}
	
	/**
	 * Lists all the Line Of Defenses
	 * @param page
	 * @param view
	 * @param order
	 * @param query
	 * @return DaoException if error occurs while creating the Control Principle in the data store
	 */
	public Result showFrontcpPageQuery(int page, int view, String order, String query){
		int size = 0;

		try {
			LineOfDefenseDao cpDao=rdbFactory.getLineOfDefenseDao();
			PrincipleSort cpSort = new PrincipleSort();
			cps = cpDao.listControlPrinciple();
			size = cps.size();
			if(query.compareTo("")!= 0){
				cps = cpSort.filterDataByQuery(cps, query);
				size = cps.size();
			}
			if(size > 0){
				cps = cpSort.sortPrinciple(cps, order);
				cps = cpSort.paginateControlPrinciple(cps, view, page);
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while listing Control Principle criteria ", e);
		}
		return ok(frontcp.render(cps,size));
	}
	
	public Result showViewcpPage() {
		return ok(viewcp.render(selectedcp));
	}

}
