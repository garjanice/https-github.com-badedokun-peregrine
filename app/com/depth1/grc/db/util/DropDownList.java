/**
 * 
 */
package com.depth1.grc.db.util;

import java.util.List;

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
     */
    public List<String> getCountry() throws DataException;
    
    /**
     * Retrieves states in a given country.
     * 
     * @param country The ISO name of the country, for example 'US' or 'CA' or 'CH'
     * @return list of all states in the specified country
     */
    public List<String> getState(String country) throws DataException;    

}
