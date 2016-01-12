package com.depth1.grc.model;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.utils.UUIDs;
import com.depth1.grc.exception.DaoException;
import com.depth1.grc.model.common.Keyspace;
import com.depth1.grc.util.DateUtility;
import com.depth1.grc.util.IdProducer;

import play.Logger;
import play.Play;

/**
 * This class implements the Data Access Object pattern (GoF). It provides capability to create, read, update, delete 
 * a Procedure - the typical CRUD functions in any business application.
 * @author Nilima Shinde
 * Create Date: 10/10/2015
  */

public class CassandraProcedureDao implements ProcedureDao {
	
	//select the type of deployment model from the configuration file
	private final static Boolean keyspace = Play.application().configuration().getBoolean("onpremise.deploy.model");
	
	/**
	 * Creates a new procedure in the procedure table.
	 * @param procedure the procedure to create
	 * @throws DaoException if an error occurs while creating a procedure in the procedure table
	 */
	@Override
	public void createProcedure(Procedure procedure) throws DaoException {
		String procedurePrefix = "p";
 		try {   
			Statement insert = QueryBuilder.insertInto(Keyspace.valueOf(keyspace), "procedure")
					.value("id", UUID.randomUUID()) // TBD
					.value("name", procedure.getName())
					.value("author", procedure.getAuthor())
					.value("version", procedure.getVersion())
					.value("procedureid",IdProducer.nextStringId(procedurePrefix))
					.value("policyid", procedure.getPolicyId())
					.value("tenantid", procedure.getTenantId())
					.value("description", procedure.getDescription())
					.value("createdate", UUIDs.timeBased())
					.value("format", procedure.getFormat())
					.value("language", procedure.getLanguage())
					.value("subject", procedure.getSubject())
					.value("reference", procedure.getReference())
					.value("approver", procedure.getApprover())
					.value("owner", procedure.getOwner())
				 	.value("last_updated_date", Timestamp.valueOf(java.time.LocalDateTime.now()));
				     
			CassandraDaoFactory.getSession().execute(insert);
					Logger.info("Inserted successfully to database");
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting data into the database ", e);
		} finally {
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
	}

    /**
     * Deletes a procedure when the 'Delete' button is clicked and prompts the user to confirm deletion.
     * @param procedure - procedure to be deleted
     * @return true if the deletion was successful
     * @throws DaoException if error occurs while deleting a procedure
     */
	
	
	@Override
	public boolean deleteProcedure(Procedure procedure) throws DaoException {
        boolean del = false;
     
        try {
            Delete.Where delete = QueryBuilder.delete().from(Keyspace.valueOf(keyspace), "procedure")
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


    private List<Procedure> getResultList(ResultSet result) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Lists all of the Procedures on the front-end UI.
     * 
     * @return List containing all Procedures
     * @throws DaoException error if unable to retrieve list of Procedures
     */
	
	@Override
	public List<Procedure> listProcedure() throws DaoException {
		
		List<Procedure> listProcedure = new ArrayList<>();

		try {
			Statement listAllP = QueryBuilder.select().all()
					.from(Keyspace.valueOf(keyspace), "procedure");

			ResultSet result = CassandraDaoFactory.getSession().execute(listAllP);

			if (result == null) {
				return null;
			}

			for (Row row : result.all()) {
				Procedure procedure = new Procedure();
				listProcedure.add(setProcedureAttributes(procedure, row));
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
     * Restores procedure information in the procedure table.
     * @param procedureId the procedure to restore
     * @return boolean true if restore succeed, false otherwise
     * @throws DaoException if an error occurs while restoring procedure from the table
     */
	@Override
	public boolean restoreProcedure(String procedureId) throws DaoException {
		
		try{
			Statement restore = QueryBuilder
							.update(Keyspace.valueOf(keyspace),"procedure")
							.with(QueryBuilder.set("is_deleted", false))
							.where(QueryBuilder.eq("id", UUID.fromString(procedureId)));
			CassandraDaoFactory.getSession().execute(restore);
			
			return true;
		} catch (DriverException e){
			Logger.error("Error while restoring procedure from the data store ",e);
			return false;
		} finally{
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
	}

	
	/**
	 * Sets procedure attributes
	 * 
	 * @param procedure the procedure attributes to set
	 * @param row the result of a query 
	 * @return procedure with the attributes set
	 */
	private Procedure setProcedureAttributes(Procedure procedure, Row row) {
		procedure.setId(row.getUUID("id"));
		procedure.setName(row.getString("name"));
		procedure.setProcedureId(row.getString("procedureid"));
		procedure.setDescription(row.getString("description"));
		procedure.setAuthor(row.getString("author"));
		procedure.setVersion(row.getString("version"));
		procedure.setCreateDate(row.getTimestamp("createdate"));
		procedure.setFormat(row.getString("format"));
		procedure.setLanguage(row.getString("language"));
		procedure.setSubject(row.getString("subject"));
		procedure.setReference(row.getString("reference"));
		procedure.setApprover(row.getString("approver"));
		procedure.setOwner(row.getString("owner"));
		procedure.setLastUpdatedDate(row.getTimestamp("last_updated_date"));
		Date date = DateUtility.convertTimeuuid(row.getUUID("createdate"));
		procedure.setCreateDate(date);
		
		return procedure;	
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
        	
        	Update.Assignments updateProcedure = QueryBuilder
                    .update(Keyspace.valueOf(keyspace), "procedure")
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
        	        .and(set("last_updated_date", Timestamp.valueOf(java.time.LocalDateTime.now())));                  
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
	* Views all Deleted procedures 
	* @return a list containing all Procedures
	* @throws DaoException if error occurs while retrieving procedure from the table
	*/
	
	@Override
	public List<Procedure> viewAllDeletedProcedure() throws DaoException {
		List<Procedure> listProcedure = new ArrayList<>();

		try {
			Statement viewAllDeletedProcedure = QueryBuilder
					.select()
					.all()
					.from(Keyspace.valueOf(keyspace), "procedure")
					.where(QueryBuilder.eq("is_deleted", true));

			ResultSet result = CassandraDaoFactory.getSession().execute(viewAllDeletedProcedure);;

			if (result == null) {
				return null;
			}

			for (Row row : result.all()) {
				Procedure procedure = new Procedure();
				listProcedure.add(setProcedureAttributes(procedure, row));
				//result.iterator();
			}
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving list of Procedures from database ", e);
		} finally {
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}

		return listProcedure;


	}
	
	/**
	* View all procedures 
	* @return List of Procedures to view
	* @throws DaoException if error occurs while retrieving procedure from the table
	*/
	@Override
	public List<Procedure> viewAllProcedure() throws DaoException {
        List<Procedure> listProcedure = new ArrayList<>();
        
        try {
            Statement viewAllProcedure = QueryBuilder
            		.select()
            		.all()
                    .from(Keyspace.valueOf(keyspace), "procedure")
                    .where(QueryBuilder.eq("is_deleted", false));
            ResultSet result = CassandraDaoFactory.getSession().execute(viewAllProcedure);
            
            if (result == null) {
                return null;
            }

            for (Row row : result.all()) {
                Procedure procedure = new Procedure();
                listProcedure.add(setProcedureAttributes(procedure, row));
                //result.iterator();
            }
        } catch (DriverException e) {
            Logger.error("Error occurred while retrieving list of Procedures from database ", e);
        } finally {
          
            CassandraDaoFactory.close(CassandraDaoFactory.getSession());

        }

        return listProcedure;
	}
	
	/**
	* Views a procedure by Procedure id.
	* @param id the UUID of the procedure to view
	* @return Procedure to view
	* @throws DaoException if error occurs while retrieving procedure from the table
	*/
	
	@Override
	public Procedure viewProcedureById(UUID id) throws DaoException {
		List<Procedure> listProcedure;
			try {
			Statement viewProcedureById = QueryBuilder.select().all().from(Keyspace.valueOf(keyspace), "procedure").where(QueryBuilder.eq("id", id));

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
	* Views a procedure by Procedure name.
	* @param procedureName the procedure name to view
	* @return Procedure to view
	* @throws DaoException if error occurs while retrieving procedure from the table
	*/
	
	@Override
	public Procedure viewProcedureByName(String procedureName) throws DaoException {
		List<Procedure> listProcedure;
		try {
			Statement viewProcedureById = QueryBuilder.select().all().from(Keyspace.valueOf(keyspace), "procedure").where(QueryBuilder.eq("name", procedureName));

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

}
