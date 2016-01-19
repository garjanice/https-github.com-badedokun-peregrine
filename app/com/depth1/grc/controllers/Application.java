package com.depth1.grc.controllers;

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
import org.jsoup.*;
import play.Logger;
import play.data.Form;
import play.data.Form.Field;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.mvc.Security;

import com.datastax.driver.core.Session;
import com.depth1.grc.db.util.DropDownList;
import com.depth1.grc.exception.DaoException;
import com.depth1.grc.exception.DataException;
import com.depth1.grc.jpa.models.JpaObjectiveDao;
import com.depth1.grc.jpa.models.Measure;
import com.depth1.grc.jpa.models.Objective;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Department;
import com.depth1.grc.model.PrintPdfRiskAssessment;
import com.depth1.grc.model.Procedure;
import com.depth1.grc.model.ProcedureDao;
import com.depth1.grc.model.RiskAssessment;
import com.depth1.grc.model.RiskAssessmentDao;
import com.depth1.grc.model.RiskAssessmentSort;
import com.depth1.grc.util.IdProducer;
import com.depth1.grc.model.Policy;
import com.depth1.grc.model.PolicyDao;
import com.depth1.grc.model.PolicySort;
import com.depth1.grc.model.PrintPdfProcedure;
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
	
	final static Form<Procedure> procedureForm = Form.form(Procedure.class);
	static List<Procedure> procedures;
	static Procedure selectedProcedure;
	
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

	
	/**
	 * The call to home page of the application.
	 * 
	 * @return
	 */
	public Result index() {
		
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
	 * Creates a Procedure
	 * @return Result of the procedure created
	 */

	public Result createProcedure() {
		// create form object from the request
		Form<Procedure> filledProcedure = procedureForm.bindFromRequest();
		// check for required and validate input fields
		Procedure criteria = filledProcedure.get();
		System.out.println("Here it is: " + criteria.getDescription());
		try {
			ProcedureDao procedureDao = cassandraFactory.getProcedureDao();
			// create procedure on DB
			procedureDao.createProcedure(criteria);

		} catch (DaoException e) {
			Logger.error("Error occurred while creating Procedure ", e);
		}
		if (filledProcedure.field("procedurebody").value() != null) {
			saveProcedureBodyDocument(criteria.getName(),
					filledProcedure.field("procedurebody"));
		}
		return redirect("/procedure");
	}

	/**
	 * Saves the procedure body document to a file on the server file system
	 * @param fileName of procedure to be saved, text-area field of procedure body
	 * @return void
	 */

	private void saveProcedureBodyDocument(String fileName, Field procedureBody) {
		try {
			// TODO: Replace the path with path on server for file storage
			String dirString = "public/procedureDocuments/";
			Path dirPath = Paths.get(dirString);
			if (Files.notExists(dirPath)) {
				Files.createDirectory(dirPath);
			}
			Path filePath = Paths.get(dirString + fileName);
			File procedureDoc = filePath.toFile();
			PrintWriter writer = new PrintWriter(new BufferedWriter(
					new FileWriter(procedureDoc)), true);
			// flushing the buffer after file-write
			writer.print(Jsoup.parse(procedureBody.value()).asText());
			writer.close();
			Logger.info("Procedure Body Documented at " + dirString + fileName);
		} catch (IOException e) {
			Logger.error("Error while storing the procedure body document " + e);
		}
	}

	/**
	 * Updates the selected Procedure information and then displays the list of Procedures on the FrontTenant Page
	 * @return Result of updating the Procedure
	 */
	public Result updateProcedure() {
		Form<Procedure> filledProcedure = procedureForm.bindFromRequest();
		Procedure criteria = filledProcedure.get();
		criteria.setId(selectedProcedure.getId());
		try {
			ProcedureDao procedureDao = cassandraFactory.getProcedureDao();
			procedureDao.updateProcedure(criteria);
		} catch (DaoException e) {
			Logger.error("Error occurred while updating procedure criteria ", e);
		}

		return redirect("/procedure");
	}

	/**
	 * This method allows users to update selected Procedure
	 * @return update Procedure page
	 */

	public Result showUpdateProcedurePage() {

		return ok(updateProcedure.render(selectedProcedure));
	}


	/**
	 * Deletes the selected Procedure from the database.Uses Ajax and JSON.
	 * Shows the FrontProcedure Page.
	 * @return Result of the deleting the Procedure.
	 */

	public Result deleteProcedure() {
		try {
			ProcedureDao procedureDao = cassandraFactory.getProcedureDao();
			procedureDao.deleteProcedure(selectedProcedure);
		} catch (DaoException e) {
			Logger.error("Error occurred while deleting procedure ", e);
		}

		return redirect("/procedure");
	}

	/**
	 * Restores a procedure
	 * @param procedureId
	 * @return restore procedure page
	 */

	public Result restoreProcedure(String procedureId) {
		// Logger.error("correct");
		// call cassandra procedure dao
		boolean result = false;
		try {
			ProcedureDao procedureDao = cassandraFactory.getProcedureDao();
			result = procedureDao.restoreProcedure(procedureId);
			// System.out.println("COMPLETED result = " + result );
		} catch (DaoException e) {
			System.out.println("ERROR OCCURED");
			Logger.error("Error occurred while creating Procedure Front Page ",
					e);
		}

		return ok(restoreProcedure.render(procedures));

		// return TODO;
	}

	/**
	 * Shows the front page of the Procedure UI
	 * @return to the main page
	 */

	public Result showFrontProcedurePage() {

		try {
			ProcedureDao procedureDao = cassandraFactory.getProcedureDao();
			procedures = procedureDao.listProcedure();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating procedure criteria ", e);
		}

		return ok(frontProcedure.render(procedures, procedures.size()));
	}
	/**
	 * Sets the the Procedure Selected on the Front Page as the selectedProcedure
	 * @return a message that the JSON was received ok
	 */

	public Result setSelectedProcedure() {
		Procedure procedure = new Procedure();
		JsonNode node = request().body().asJson().get("val");

		if (node == null) {
			return badRequest("empty json");
		}
		String inputString = node.textValue();
		int index = Integer.parseInt(inputString);
		int size = 0;

		selectedProcedure = procedures.get(index);
		return ok();
	}

	/**
	 * This method shows the create Procedure page if the 'Create' button is clicked
	 * @return create Procedure page
	 */

	public Result showCreateProcedurePage() {

		return ok(createProcedure.render());

	}

	/**
	 * Shows a create procedure editor page
	 * @return procedure editor page
	 */

	public Result showCreateProcedureEditorPage() {

		return ok();
	}

	/**
	 * This method allows users to view procedure
	 * @return view Procedure page
	 */
	public Result showViewProcedurePage() {

		return ok(viewProcedure.render(selectedProcedure));
	}

	/**
	 * Shows a delete procedure page
	 * @return procedure delete page
	 */
	public Result showDeleteProcedurePage() {
		try {
			ProcedureDao procedureDao = cassandraFactory.getProcedureDao();
			procedures = procedureDao.viewAllProcedure();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Procedure Front Page ",
					e);
		}

		return ok(deleteProcedure.render(procedures));

	}

	/**
	 * Shows a restore procedure page
	 * @return procedure restore page
	 */
	public Result showRestoreProcedurePage() {
		try {
			ProcedureDao procedureDao = cassandraFactory.getProcedureDao();
			procedures = procedureDao.viewAllDeletedProcedure();
		} catch (DaoException e) {
			Logger.error("Error occurred while creating Procedure Front Page ",
					e);
		}

		return ok(restoreProcedure.render(procedures));
	}

	/**
	 * Downloads a procedure to a file
	 * @param name
	 * @return creates a procedure file
	 */

	public Result downloadProcedure(String name) {
		String filepath = "public/procedureDocuments/" + name;
		return ok(new java.io.File(filepath));

	}

	/**
	 * This method allows users to print selected Procedure
	 * @return PDF page of procedure
	 */
	public Result printProcedure() {

		PrintPdfProcedure pdf = new PrintPdfProcedure();
		pdf.printProcedure(selectedProcedure);
		return redirect("/assets/pdf/Procedure.pdf");

	}

}
