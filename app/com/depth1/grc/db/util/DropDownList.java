/**
 * 
 */
package com.depth1.grc.db.util;

import java.util.List;
import java.util.Map;

/**
 * This interface provides methods that return list of data to populate different drop down list
 * @author Bisi Adedokun
 * @version Rev 1.0 
 * Created: 10/19/2015
 *
 */
public interface DropDownList {
	
	/**
     * Retrieves all countries of the world.
     * 
     * @return list of countries
     * @exception DataException if errors occurs while retrieving data from the table
     */
    public List<String> getCountry() throws DataException;
    
    /**
     * Retrieves common spoken languages of the world.
     * 
     * @return list of all common spoken world languages
     * @exception DataException if errors occurs while retrieving data from the table
     */
    public List<String> getLanguage() throws DataException;     
    
    /**
     * Retrieves states in a given country.
     * 
     * @param country The ISO code of the country, for example 'US' or 'CA' or 'CH'
     * @return list of all states in the specified country
     * @exception DataException if errors occurs while retrieving data from the table
     */
    public List<String> getState(String country) throws DataException;
    
    /**
     * Retrieves common titles.
     * 
     * @return list of all titles
     * @exception DataException if errors occurs while retrieving data from the table
     */
    public List<String> getTitle() throws DataException; 
    
    /**
     * Retrieves world time zones.
     * 
     * @return list of all time zones in the world
     * @exception DataException if errors occurs while retrieving data from the table
     */
    public List<String> getTimezone() throws DataException; 
    
    /**
     * Retrieves Three Line of Defense and their descriptions.
     * 
     * @return list of lines of defense (LoD)
     * @exception DataException if errors occurs while retrieving data from the table
     */
    public List<String> getLod() throws DataException;
    
    /**
     * Retrieves Line of Defense functions.
     * 
     * @return list of lines of defense functions
     * @exception DataException if errors occurs while retrieving data from the table
     */
    public List<String> getLodFunction() throws DataException;      
    
    /**
     * Creates a control principle.
     * 
     * @exception DataException if errors occurs while creating a control principle
     */
    public void createControlPrinciple() throws DataException;
    
    /**
     * Updates a control principle.
     * 
     * @exception DataException if errors occurs while updating a control principle
     */
    public void updateControlPrinciple() throws DataException;
    
    /**
     * Updates a control principle.
     * 
     * @exception DataException if errors occurs while updating a control principle
     */
    public void deleteControlPrinciple() throws DataException; 
    
    /**
     * Retrieves control principle.
     * 
     * @exception DataException if errors occurs while updating a control principle
     */
    public List<String> getControlPrinciple() throws DataException;    
    
   

}
