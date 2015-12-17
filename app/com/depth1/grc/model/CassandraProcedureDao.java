package com.depth1.grc.model;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;

import org.joda.time.LocalDateTime;

import java.util.Calendar;

import play.Logger;
import play.mvc.Http.MultipartFormData;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.querybuilder.Assignment;
import com.datastax.driver.core.utils.UUIDs;
import com.depth1.grc.util.IdProducer;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

/**
 * This class implements the Data Access Object pattern (GoF). It provides capability to create, read, update, delete 
 * a Procedure - the typical CRUD functions in any business application.
 * @author Nilima Shinde
 * Create Date: 10/10/2015
  */

public class CassandraProcedureDao implements ProcedureDao {
	
	
	/**
	 * Creates a new procedure in the procedure table.
	 * @param procedure the procedure to create
	 * @throws DaoException if an error occurs while creating a procedure in the procedure table
	 */
	@Override
	public void createProcedure(Procedure procedure) throws DaoException {
		String procedurePrefix = "p";
 		try {
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());   
			Statement insert = QueryBuilder.insertInto("grc", "procedure")
					.value("id", UUID.randomUUID()) // TBD
					.value("name", procedure.getName())
					.value("author", procedure.getAuthor())
					.value("version", procedure.getVersion())
					.value("procedureid",IdProducer.nextStringId(procedurePrefix))
					.value("policyid", procedure.getPolicyId())
					.value("tenantid", procedure.getTenantId())
					.value("description", procedure.getDescription())
					.value("createdate", currentTimestamp)
					.value("format", procedure.getFormat())
					.value("language", procedure.getLanguage())
					.value("subject", procedure.getSubject())
					.value("reference", procedure.getReference())
					.value("approver", procedure.getApprover())
					.value("owner", procedure.getOwner())
				 	.value("last_updated_date", currentTimestamp);
				     
			CassandraDaoFactory.getSession().execute(insert);
					Logger.info("Inserted successfully to database");
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} finally {
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
	}

	/**
	 * Updates procedure information in the procedure table.
	 * @param procedure the procedure to update
     * @return boolean true if update succeed, false otherwise
     * @throws DaoException if an error occurs while updating procedure from the table
     */
	@Override
	public boolean updateProcedure(Procedure procedure) throws DaoException {
        boolean update = false;
       
        try {
            
        	Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        	
        	Update.Assignments updateProcedure = QueryBuilder
                    .update("grc", "procedure")
                    .with(set("name", procedure.getName()))
                    .and(set("author", procedure.getAuthor()))
                    .and(set("version", procedure.getVersion()))
                    .and(set("description", procedure.getDescription()))
                    .and(set("format", procedure.getFormat()))
                    .and(set("language", procedure.getLanguage())) 
                    .and(set("subject", procedure.getSubject()))
                    .and(set("reference", procedure.getReference()))
                    .and(set("approver", procedure.getApprover()))
                    .and(set("owner", procedure.getOwner()))
        	        .and(set("last_updated_date", currentTimestamp));                  
                     Statement updateDetails = updateProcedure
                    .where(eq("id", procedure.getId()));

                     CassandraDaoFactory.getSession().execute(updateDetails);
            update = true;
        } catch (DriverException e) {
            Logger.error("Error occurred while attempting to update procedure ", e);
        } finally {
            CassandraDaoFactory.close(CassandraDaoFactory.getSession());
        }
        return update;
	}


    /**
     * This method is called when the 'Delete' button is clicked and prompts the user if they want to
     * delete the selected Procedure.
     * @param procedure to be deleted.
     * @return true if the deletion was successful.
     * @throws DaoException error if deletion failed.
     */
	
	
	@Override
	public boolean deleteProcedure(Procedure procedure) throws DaoException {
        boolean del = false;
     
        try {
            Delete.Where delete = QueryBuilder.delete().from("grc", "procedure")
                    .where(eq("id", procedure.getId()));
            CassandraDaoFactory.getSession().execute(delete);
            
            del = true;
        } catch (DriverException e) {
            Logger.error("Error occurred while attempting to delete procedure", e);
        } finally {
            CassandraDaoFactory.close(CassandraDaoFactory.getSession());
        }
        return del;
	}

	/**
	 * Restore procedure information in the procedure table.
	 * @param procedureId the procedure to restore
     * @return boolean true if restore succeed, false otherwise
     * @throws DaoException if an error occurs while restoring procedure from the table
     */
	@Override
	public boolean restoreProcedure(String procedureId) throws DaoException {
		
		try{
			Statement restore = QueryBuilder
							.update("grc","procedure")
							.with(QueryBuilder.set("is_deleted", false))
							.where(QueryBuilder.eq("id", UUID.fromString(procedureId)));
			CassandraDaoFactory.getSession().execute(restore);
			
			return true;
		} catch (DriverException e){
			Logger.error("Error while restoring",e);
			return false;
		} finally{
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
	}

	 /**
     * Lists all of the Procedures on the front-end UI
     * @return List containing all Procedures
     * @throws DaoException error if unable to retrieve list of Procedures
     */
	
	@Override
	public List<Procedure> listProcedure() throws DaoException {
		
		List<Procedure> listProcedure = new ArrayList<>();
     
        try {
            Statement listAllP = QueryBuilder.select().all()
                    .from("grc", "procedure");

            ResultSet result = CassandraDaoFactory.getSession().execute(listAllP);
            
            if (result == null) {
                return null;
            }
          
		for (Row row : result.all()) {
			Procedure procedure = new Procedure();
			procedure.setId(row.getUUID("id"));
			procedure.setName(row.getString("name"));
			procedure.setDescription(row.getString("description"));
		    procedure.setProcedureId(row.getString("procedureid"));
			procedure.setAuthor(row.getString("author"));
			procedure.setVersion(row.getString("version"));
			procedure.setCreationDate(new Timestamp(row.getDate("createdate").getTime()));
			procedure.setFormat(row.getString("format"));
			procedure.setLanguage(row.getString("language"));
			procedure.setSubject(row.getString("subject"));
			procedure.setReference(row.getString("reference"));
			procedure.setApprover(row.getString("approver"));
			procedure.setOwner(row.getString("owner"));
			procedure.setLastUpdatedDate(new Timestamp(row.getDate("last_updated_date").getTime()));
			listProcedure.add(procedure);
			result.iterator();
		 } 
        }catch (DriverException e) {
	            Logger.error("Error occurred while retrieving list of Procedures from database ", e);
	        } finally {
	            CassandraDaoFactory.close(CassandraDaoFactory.getSession());
	        }

	        return listProcedure;
		}

	
	/**
	* View a procedure by Procedure name
	* @param procedureName to view
	* @return Procedure to view
	* @throws DaoException if an error occurs while viewing procedure from the table
	*/
	
	@Override
	public Procedure viewProcedureByName(String procedureName) throws DaoException {
		List<Procedure> listProcedure;
		//Session dbSession = CassandraDaoFactory.connect();
		try {
			Statement viewProcedureById = QueryBuilder.select().all().from("grc", "procedure").where(QueryBuilder.eq("name", procedureName));

			ResultSet result = CassandraDaoFactory.getSession().execute(viewProcedureById);
			
			if (result == null) {
				return null;
			}
			listProcedure = getResultList(result);
			if(listProcedure.isEmpty())
				return null;
			return listProcedure.get(0);
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving list of Procedure from database ", e);
		} finally {
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return null;	
	}

	
	private List<Procedure> getResultList(ResultSet result) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	* View a procedure by Procedure id
	*@param procedure UUID to view
	* @return Procedure to view
	* @throws DaoException if an error occurs while viewing procedure from the table
	*/
	
	@Override
	public Procedure viewProcedureById(UUID id) throws DaoException {
		List<Procedure> listProcedure;
			try {
			Statement viewProcedureById = QueryBuilder.select().all().from("grc", "procedure").where(QueryBuilder.eq("id", id));

			ResultSet result = CassandraDaoFactory.getSession().execute(viewProcedureById);
			
			if (result == null) {
				return null;
			}
			listProcedure = getResultList(result);
			if(listProcedure.isEmpty())
				return null;
			Procedure head = listProcedure.get(0);
			id = head.getId();
			return head;
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving list of Procedures from database ", e);
		} finally {
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return null;
	}
	
	/**
	* View all procedure 
	*@param void
	*@return List of Procedures to view
	*@throws DaoException if an error occurs while viewing procedure from the table
	*/
	@Override
	public List<Procedure> viewAllProcedure() throws DaoException {
        List<Procedure> listProcedure = new ArrayList<>();
        
        try {
            Statement viewAllProcedure = QueryBuilder
            		.select()
            		.all()
                    .from("grc", "procedure")
                    .where(QueryBuilder.eq("is_deleted", false));
            ResultSet result = CassandraDaoFactory.getSession().execute(viewAllProcedure);
            
            if (result == null) {
                return null;
            }

            for (Row row : result.all()) {
                Procedure procedure = new Procedure();
                procedure.setId(row.getUUID("id"));
                procedure.setName(row.getString("name"));
                procedure.setProcedureId(row.getString("procedureid"));
                procedure.setDescription(row.getString("description"));
                procedure.setAuthor(row.getString("author"));
                procedure.setVersion(row.getString("version"));
                procedure.setCreationDate(new Timestamp(row.getDate("createdate").getTime()));
                procedure.setFormat(row.getString("format"));
                procedure.setLanguage(row.getString("language"));
                procedure.setSubject(row.getString("subject"));
                procedure.setReference(row.getString("reference"));
                procedure.setApprover(row.getString("approver"));
                procedure.setOwner(row.getString("owner"));
                procedure.setLastUpdatedDate(new Timestamp(row.getDate("last_updated_date").getTime()));
							
		        listProcedure.add(procedure);
                result.iterator();
            }
        } catch (DriverException e) {
            Logger.error("Error occurred while retrieving list of Procedures from database ", e);
        } finally {
          
            CassandraDaoFactory.close(CassandraDaoFactory.getSession());

        }

        return listProcedure;
	}
	
	/**
	* View all Deleted procedure 
	*@param void
	*@return a list containing all Procedures
	*@throws DaoException if an error occurs while viewing procedure from the table
	*/
	
	@Override
	public List<Procedure> viewAllDeletedProcedure() throws DaoException {
        List<Procedure> listProcedure = new ArrayList<>();
     
        try {
            Statement viewAllDeletedProcedure = QueryBuilder
            		.select()
            		.all()
                    .from("grc", "procedure")
                    .where(QueryBuilder.eq("is_deleted", true));

            ResultSet result = CassandraDaoFactory.getSession().execute(viewAllDeletedProcedure);;
            
            if (result == null) {
                return null;
            }

            for (Row row : result.all()) {
                Procedure procedure = new Procedure();
                procedure.setId(row.getUUID("id"));
                procedure.setName(row.getString("name"));
                procedure.setProcedureId(row.getString("procedureid"));
                procedure.setDescription(row.getString("description"));
                procedure.setAuthor(row.getString("author"));
                procedure.setVersion(row.getString("version"));
                procedure.setCreationDate(new Timestamp(row.getDate("createdate").getTime()));
                procedure.setFormat(row.getString("format"));
                procedure.setLanguage(row.getString("language"));
                procedure.setSubject(row.getString("subject"));
                procedure.setReference(row.getString("reference"));
                procedure.setApprover(row.getString("approver"));
                procedure.setOwner(row.getString("owner"));
                procedure.setLastUpdatedDate(new Timestamp(row.getDate("last_updated_date").getTime()));
							
		        listProcedure.add(procedure);
                result.iterator();
            }
        } catch (DriverException e) {
            Logger.error("Error occurred while retrieving list of Procedures from database ", e);
        } finally {
        	CassandraDaoFactory.close(CassandraDaoFactory.getSession());
        }

        return listProcedure;
	

}

}



