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
 * @author Bisi Adedokun
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
     * @exception DataException if errors occurs while retrieving data from the table
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
     * Retrieves common spoken languages of the world.
     * 
     * @return list of all common spoken world languages
     * @exception DataException if errors occurs while retrieving data from the table
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getLanguage() throws DataException {
		final String cacheKey = "languages";
		final String column = "language";
		final String table = "language";
		try {
			final Object languages = Cache.get(cacheKey);
			if (languages == null) {
				ResultSetFuture results = DataReaderUtil.getAll(table);
				final List<String> allLanguages = DataReaderUtil.getColumnValues(results, column, table);

				Cache.set(cacheKey, allLanguages);
				return allLanguages;
			} else {

				return (List<String>) languages;

			}
		} catch (QueryExecutionException e) {
			throw new DataException(e);
		}
	}	
	
	

    /**
     * Retrieves states in a given country.
     * 
     * @param countryCode The ISO code of the country, for example 'US' or 'CA' or 'CH'
     * @return list of all states in the specified country
     * @exception DataException if errors occurs while retrieving data from the table
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
	 * Retrieves states in a given country.
	 * 
	 * @param countryCode An ISO country code such as 'US, CA, CH, FR, NG, etc.'
	 * @return rows of states in a country
	 * @throws DaoException if error occurs while getting states from the state table
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
	
    /**
     * Retrieves common titles.
     * 
     * @return list of all titles
     * @exception DataException if errors occurs while retrieving data from the table
     */
	@SuppressWarnings("unchecked")
	public List<String> getTitle() throws DataException {
		final String cacheKey = "titles";
		final String column = "title";
		final String table = "title";
		try {
			final Object titles = Cache.get(cacheKey);
			if (titles == null) {
				ResultSetFuture results = DataReaderUtil.getAll(table);
				final List<String> allTitles = DataReaderUtil.getColumnValues(results, column, table);

				Cache.set(cacheKey, allTitles);
				return allTitles;
			} else {

				return (List<String>) titles;

			}
		} catch (QueryExecutionException e) {
			throw new DataException(e);
		}
	}

}
