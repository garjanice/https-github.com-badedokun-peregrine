/**
 * 
 */
package com.depth1.grc.model;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Delete.Where;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.utils.UUIDs;
import com.depth1.grc.db.util.DataReaderUtil;
import com.depth1.grc.exception.DaoException;
import com.depth1.grc.model.common.Keyspace;
import com.depth1.grc.util.DateUtility;

import play.Logger;
import play.Play;

/**
 * This class implements the DepartmentDao interface
 * 
 * @author Bisi Adedokun
 *
 */
public class CassandraDepartmentDao implements DepartmentDao {

	//select the type of deployment model from the configuration file
	private final static Boolean keyspace = Play.application().configuration().getBoolean("onpremise.deploy.model");	

	public CassandraDepartmentDao() {
		
	}

	/**
	 * Creates a department.
	 * 
	 * @param department department to create
	 * @throws DaoException if error occurs while creating the Department in the data store
	 */
	@Override
	public void createDepartment(Department department) throws DaoException {
		Statement insert = null;
		try {
			insert = QueryBuilder
					.insertInto(Keyspace.valueOf(keyspace), "department")
					.value("deptid", UUID.randomUUID())
					.value("tenantid", department.getTenantId())
					.value("deptname", department.getDeptName())
					.value("deptshortname", department.getDeptShortName())
					.value("description", department.getDescription())
					.value("createdate", UUIDs.timeBased());
			CassandraDaoFactory.getSession().execute(insert);					
		} catch (DriverException e) {
			Logger.error("Error occurred while inserting department in the department table ", e);
		} finally {
			//close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}		
	}

	/**
	 * Deletes a department.
	 * 
	 * @param deptid department ID to delete
	 * @return boolean True if the Department is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a department from the data store
	 */
	@Override
	public boolean deleteDepartment(UUID deptid) throws DaoException {
		boolean deleted = false;
		try {					
			Where delete = QueryBuilder.delete()
					.from(Keyspace.valueOf(keyspace), "department")
					.where(eq("deptid", deptid));							

			CassandraDaoFactory.getSession().execute(delete);
			deleted = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while deleting department from the department table ", e);
		} finally {
			//close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return deleted;
	}

	/**
	 * List department in the data store.
	 * 
	 * @return List list of departments
	 * @throws DaoException if error occurs while reading departments from the data store
	 */
	@Override
	public List<Department> listDepartment(long tenantId) throws DaoException {
		
		List<Department> list = new ArrayList<>();

		String table = "department";
		try {								
			ResultSetFuture results = DataReaderUtil.getAll(table, tenantId);
			if (results == null) {
				return null;
			}
			// get data elements from the Result set
			for (Row row : results.getUninterruptibly()) {
				Department dept = new Department();
				list.add(setDepartmentAttributes(dept, row));
			}
		} catch (DriverException e) {
			Logger.error("Error occurred while getting user profile data from the user profile table ", e);
		} finally {
			//close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		
		return list;
	}

	/**
	 * Updates Department.
	 * 
	 * @param department The department to update
	 * @return boolean True if the department is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a department in the data store
	 */
	@Override
	public boolean updateDepartment(Department department) throws DaoException {
		boolean updated = false;		
		try {
			Update.Assignments updateAssignments = QueryBuilder
					.update(Keyspace.valueOf(keyspace), "department")					
					.with(set("deptname", department.getDeptName()))
					.and(set("deptshortname", department.getDeptShortName()))
					.and(set("description", department.getDescription()));
			Statement updateDetails = updateAssignments
					.where(eq("deptid", department.getDeptId()))
					.and(eq("tenantid", department.getTenantId()));
			CassandraDaoFactory.getSession().execute(updateDetails);
			updated = true;
		} catch (DriverException e) {
			Logger.error("Error occurred while updating data in the department table ", e);
		} finally {
			//close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return updated;
	}			

	/**
	 * Finds a department.
	 * 
	 * @param deptId department ID to find
	 * @return department that was found
	 * @throws DaoException if error occurs while finding a department in the data store
	 */
	@Override
	public Department getDepartment(UUID deptId) throws DaoException {
		Department dept = null;
		try {					
			
			ResultSetFuture result = getOneDepartment(deptId);
			if (result == null) {
				return null;
			}
			// get data elements from the Result set
			for (Row row : result.getUninterruptibly()) {
				dept = new Department();
				dept = setDepartmentAttributes(dept, row);								
			}

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving user profile data from the user profile table ", e);
		} finally {
			// close the connection to the database
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}		
		
		return dept;
	}

	/**
	 * Finds a department.
	 * 
	 * @param name department name to find
	 * @return department that was found
	 * @throws DaoException if error occurs while finding a department in the data store
	 */
	@Override
	public Department getDepartment(String deptName, long tenantId) throws DaoException {
		Department dept = null;
		try {					
			
			ResultSetFuture result = getOneDepartment(deptName, tenantId);
			if (result == null) {
				return null;
			}
			// get data elements from the Result set
			for (Row row : result.getUninterruptibly()) {
				dept = new Department();
				dept = setDepartmentAttributes(dept, row);								
			}

		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving user profile data from the user profile table ", e);
		} finally {
			// close the connection to the database
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}			
		
		return dept;
	}
	
	/**
	 * Gets a department that matches the given criteria of deptId
	 * 
	 * @return a row that matches the department
	 * @throws DaoException if error occurs while getting department from the department table
	 */
	private ResultSetFuture getOneDepartment(UUID deptId) throws DaoException {
		Select.Where select = null;
		try {
		 select = QueryBuilder.select()
				.all()
				.from(Keyspace.valueOf(keyspace), "department")
				.where(eq("deptid", deptId));				
				
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving a user profile from the user profile table ", e);
		} finally {
			// close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return CassandraDaoFactory.getSession().executeAsync(select);
		
	}	
	
	/**
	 * Gets a department that matches the given criteria of deptId
	 * 
	 * @return a row that matches the department
	 * @throws DaoException if error occurs while getting department from the department table
	 */
	private ResultSetFuture getOneDepartment(String deptName, long tenantId) throws DaoException {
		Select.Where select = null;
		try {
		 select = QueryBuilder.select()
				.all()
				.from(Keyspace.valueOf(keyspace), "department")
				.where(eq("deptname", deptName))
		 		.and(eq("tenantid", tenantId));
				
		} catch (DriverException e) {
			Logger.error("Error occurred while retrieving a user profile from the user profile table ", e);
		} finally {
			// close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return CassandraDaoFactory.getSession().executeAsync(select);
		
	}		
	
	/**
	 * Sets user profile attributes
	 * 
	 * @param user the user profile attributes to set
	 * @param row the result of a query 
	 * @return user profile with the attributes set
	 */
	private Department setDepartmentAttributes(Department dept, Row row) {
		dept.setDeptId(row.getUUID("id"));
		dept.setTenantId(row.getLong("tenantid"));
		dept.setDeptName(row.getString("deptname"));
		dept.setDeptShortName(row.getString("deptshortname"));
		dept.setDescription(row.getString("description"));
		Date date = DateUtility.convertTimeuuid(row.getUUID("createdate"));
		dept.setCreateDate(date);
		return dept;	
	}	

}
