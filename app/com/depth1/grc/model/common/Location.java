package com.depth1.grc.model.common;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.QueryExecutionException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.depth1.grc.model.CassandraDaoFactory;
import com.depth1.grc.model.DaoException;

import play.Logger;

/**
 * This class provides methods that return list of data to populate different drop down list
 * in member or official profiles
 *
 */
public class Location {

    /**
     * Retrieves all countries of the world.
     * 
     * @return a list of countries
     * @throws DaoException if error occurs while executing the query statement
     */
    public static List<String> getCountry() throws DaoException {
    	Session dbSession = CassandraDaoFactory.connect();
		try {
			Statement getAllCountries = QueryBuilder.select()
					.column("country_name").from("grc", "country");

			ResultSet result = dbSession.execute(getAllCountries);
			return getColumnValues(result, "country_name");
		} catch (QueryExecutionException e) {
			Logger.error("Error occured while executing the query");
			throw new DaoException(e);
		} finally {
			CassandraDaoFactory.close(dbSession);
		}

	}

    /**
     * Retrieves states in a given country.
     * 
     * @param country the ISO name of the country, for example 'US' or 'CA' or 'CH'
     * @return list of all states in the specified country
     * @throws DaoException if error occurs while executing the query statement
     */
    public static List<String> getState(String country) throws DaoException {
    	Session dbSession = CassandraDaoFactory.connect();
		try {
			Statement getState = QueryBuilder.select().column("short_name")
					.from("grc", "state").allowFiltering()
					.where(QueryBuilder.eq("country_code", country));

			ResultSet result = dbSession.execute(getState);
			return getColumnValues(result, "short_name");
		} catch (QueryExecutionException e) {
			Logger.error("Error occured while executing the query");
			throw new DaoException(e);
		} finally {
			CassandraDaoFactory.close(dbSession);
		}
	}
    
    /**
     * Returns a list of values for a given column.
     *  
     * @param result The result set returned from the querying the table
     * @param columnName The column name that we want to return it value
     * @return a list of values for the column
     */
    private static List<String> getColumnValues(ResultSet result, String columnName) {
        if (result == null) {
            return null;
        }
        assert columnName != null;
        
        final List<Row> allRows = result.all();
        if (allRows == null) {
            return null;
        } else {
            List<String> values = new ArrayList<>();
            for (Row row : allRows) {
            	values.add(row.getString(columnName));
            }
            return values;
        }
    }
    

}
