/**
 * 
 */
package com.depth1.grc.model;

import java.util.List;
import java.util.UUID;

import com.depth1.grc.exception.DaoException;

/**
 * The Department interface that defines the contract that all implementing classes must abide by
 * It provides API to expose to caller of classes that implement the interface
 * @author Bisi Adedokun
 *
 */
public interface DepartmentDao {
	
	/**
	 * Creates a department.
	 * 
	 * @param department department to create
	 * @throws DaoException if error occurs while creating the Department in the data store
	 */
	public void createDepartment(Department department) throws DaoException;
	
	/**
	 * Deletes a department.
	 * 
	 * @param deptid department ID to delete
	 * @return boolean True if the Department is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a department from the data store
	 */
	public boolean deleteDepartment(UUID deptid) throws DaoException;
	
	
	/**
	 * List department in the data store.
	 * 
	 * @return List list of departments
	 * @throws DaoException if error occurs while reading departments from the data store
	 */
	public List<Department> listDepartment(long tenantId) throws DaoException;
	
	/**
	 * Updates Department.
	 * 
	 * @param department The department to update
	 * @return boolean True if the department is successfully updated, false otherwise
	 * @throws DaoException if error occurs while updating a department in the data store
	 */
	
	public boolean updateDepartment(Department department) throws DaoException;
	
	/**
	 * Finds a department.
	 * 
	 * @param deptId department ID to find
	 * @return department that was found
	 * @throws DaoException if error occurs while finding a department in the data store
	 */
	public Department getDepartment(UUID deptId) throws DaoException;
	/**
	 * Finds a department.
	 * 
	 * @param name department name to find
	 * @return department that was found
	 * @throws DaoException if error occurs while finding a department in the data store
	 */
	public Department getDepartment(String deptName, long tenantId) throws DaoException;
	
	/**
	 * Finds a department.
	 * 
	 * @param name department name to find
	 * @return true if a department if found, false otherwise
	 * @throws DaoException if error occurs while finding a department in the data store
	 */
	//public boolean findDepartment(String name) throws DaoException;	
		

}
