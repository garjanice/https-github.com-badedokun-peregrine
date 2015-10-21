/**
 * 
 */
package com.depth1.grc.db.util;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.exceptions.QueryExecutionException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.depth1.grc.model.CassandraDaoFactory;
import com.depth1.grc.model.DaoException;
import com.depth1.grc.model.common.Keyspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import play.Logger;
import play.Play;
import play.cache.Cache;

/**
 * @author badedokun
 *
 */
public class DropDownListReader implements DropDownList {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DropDownListReader.class);
	
	//select the type of deployment model from the configuration file
			private final static Boolean keyspace = Play.application().configuration().getBoolean("onpremise.deploy.model");

	/**
	 * 
	 */
	public DropDownListReader() {
		// TODO Auto-generated constructor stub
	}

	/**
     * Retrieves all countries of the world.
     * 
     * @return list of countries
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCountry() throws DataException {
		final String cacheKey = "countries";
		final String column = "country_name";
		final String table = "country";
		try {
			final Object countries = Cache.get(cacheKey);
			if (countries == null) {
				ResultSetFuture results = DataReaderUtil.getAll(table);
				final List<String> allCountries = DataReaderUtil.getColumnValues(results, column, table);

				Cache.set(cacheKey, allCountries);
				return allCountries;
			} else {

				return (List<String>) countries;

			}
		} catch (QueryExecutionException e) {
			throw new DataException(e);
		}
	}

	 /**
     * Retrieves states in a given country.
     * 
     * @param country The ISO name of the country, for example 'US' or 'CA' or 'CH'
     * @return list of all states in the specified country
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getState(String countryCode) throws DataException {
		final String cacheKey = "states";
		final String column = "short_name";
		final String table = "state";
		try {
			final Object states = Cache.get(cacheKey);
			if (states == null) {
				ResultSetFuture results = getCountryState(countryCode);
				final List<String> allStates = DataReaderUtil.getColumnValues(results, column, table);

				Cache.set(cacheKey, allStates);
				return allStates;
			} else {

				return (List<String>) states;

			}
		} catch (QueryExecutionException e) {
			throw new DataException(e);
		}
	}
	
	/**
	 * Get a user profile that matches the given criteria of userId
	 * 
	 * @return a row that matches the user profile
	 * @throws DaoException if error occurs while getting user profiles from the user profile table
	 */
	private ResultSetFuture getCountryState(String countryCode) {
		Select.Where select = null;
		try {
		 select = QueryBuilder.select()
				.all()
				.from(Keyspace.valueOf(keyspace), "state")
				.where(eq("country_code", countryCode));				
				
		} catch (DriverException e) {
			LOGGER.error("Error occurred while retrieving a data from the state table ", e);
		} finally {
			// close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
		return CassandraDaoFactory.getSession().executeAsync(select);
		
	}		

}
