/**
 * 
 */
package com.depth1.grc.coso.models;

import java.util.List;

import com.depth1.grc.exception.DaoException;

/**
 * @author Bisi Adedokun
 *
 */
public interface ExecutiveManagementDao {
	
	/**
	 * Creates an organization mission.
	 * 
	 * @param mission the mission to create
	 * @throws DaoException if error occurs while creating a mission in the data store
	 */
	public void createMission(Mission mission) throws DaoException;
	
	/**
	 * Deletes a mission.
	 * 
	 * @param missionId the mission ID to delete
	 * @param tenantId the tenant ID of the tenant whose mission to delete
	 * @return true if the Mission is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a mission from the data store
	 */
	public boolean deleteMission(long missionId, long tenantId) throws DaoException;
	
	
	/**
	 * Finds a Mission.
	 * 
	 * @param missionId the mission ID to find
	 * @param tenantId the tenant ID of the tenant whose mission to find
	 * @return Mission that was found
	 * @throws DaoException if error occurs while finding control assessment question in the data store
	 */
	public Mission getMission(long missionId, long tenantId) throws DaoException;
	
	/**
	 * Lists missions in the data store.
	 * 
	 * @param tenantId the tenant ID of the tenant whose mission to find
	 * @return List of mission
	 * @throws DaoException if error occurs while reading mission from the data store
	 */
	public List<Mission> listMission(long tenantId) throws DaoException;
	
	/**
	 * Lists missions.
	 * 
	 * @param missionId the mission ID to find
	 * @param tenantId the tenant ID of the tenant whose mission to find
	 * @return List of missions that were found
	 * @throws DaoException if error occurs while finding mission in the data store
	 */
	public List<Mission> listMission(long missionId, long tenantId) throws DaoException;	
		
	/**
	 * Updates mission.
	 * 
	 * @param mission the mission to update
	 * @return true if the mission is updated successfully, false otherwise
	 * @throws DaoException if error occurs while updating a mission in the data store
	 */
	public boolean updateMission(Mission mission) throws DaoException; 
	
	/**
	 * Creates a risk appetite.
	 * 
	 * @param appetite the risk appetite to create
	 * @throws DaoException if error occurs while creating the risk appetite in the data store
	 */
	public void createRiskAppetite(RiskAppetite appetite) throws DaoException;
	
	/**
	 * Deletes a risk appetite.
	 * 
	 * @param appetiteId the risk appetite ID to delete
	 * @param tenantId the tenant ID of the tenant whose risk appetite to delete
	 * @return true if the risk appetite is successfully deleted, false otherwise
	 * @throws DaoException if error occurs while deleting a risk appetite from the data store
	 */
	public boolean deleteRiskAppetite(long appetiteId, long tenantId) throws DaoException;
	
	
	/**
	 * Finds a risk appetite.
	 * 
	 * @param appetiteId the risk appetite ID to find
	 * @param tenantId the tenant ID of the tenant whose risk appetite to find
	 * @return Risk appetite that was found
	 * @throws DaoException if error occurs while finding risk appetite in the data store
	 */
	public RiskAppetite getRiskAppetite(long appetiteId, long tenantId) throws DaoException;
	
	/**
	 * Lists risk appetite in the data store.
	 * 
	 * @param tenantId the tenant ID of the tenant whose risk appetite to find
	 * @return List of Risk appetite
	 * @throws DaoException if error occurs while reading risk appetite from the data store
	 */
	public List<RiskAppetite> listRiskAppetite(long tenantId) throws DaoException;
	
	/**
	 * Lists risk appetites.
	 * 
	 * @param appetiteId the risk appetite ID to find
	 * @param tenantId the tenant ID of the tenant whose risk appetite to find
	 * @return List of risk appetite that were found
	 * @throws DaoException if error occurs while finding control assessment questionnaire in the data store
	 */
	public List<RiskAppetite> listRiskAppetite(long questionnaireId, long tenantId) throws DaoException;	
		
	/**
	 * Updates risk appetite.
	 * 
	 * @param appetite the risk appetite to update
	 * @return true if the risk appetite is update successfully, false otherwise
	 * @throws DaoException if error occurs while updating a risk appetite in the data store
	 */
	public boolean updateRiskAppetite(RiskAppetite appetite) throws DaoException; 	
	
	
		

}
