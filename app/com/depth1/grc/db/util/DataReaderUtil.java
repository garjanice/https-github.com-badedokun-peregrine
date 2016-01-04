/**
 * 
 */
package com.depth1.grc.db.util;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Select.Where;
import com.depth1.grc.exception.DaoException;
import com.depth1.grc.model.CassandraDaoFactory;
import com.depth1.grc.model.common.Keyspace;

import play.Logger;
import play.Play;

/**
 * Data reader utility class for getting data from the data store
 * @author Bisi Adedokun
 * @version Rev 1.0
 * Created 10/19/2015
 *
 */
public class DataReaderUtil {
	
	//select the type of deployment model from the configuration file
	private final static Boolean keyspace = Play.application().configuration().getBoolean("onpremise.deploy.model");	

	
	/**
	 * Gets all rows in a given table
	 * 
	 * @param table the table to query
	 * @param tenantId the tenant ID to get data about
	 * @return all rows in the table that belongs to a particular tenant
	 * @throws DaoException if error occurs while getting user profiles from the user profile table
	 */
	public static ResultSetFuture getAll(String table, long tenantId) throws DaoException {

		Where query = null;
		try {
			query = QueryBuilder.select().all().from(Keyspace.valueOf(keyspace), table)
					.where(eq("tenantid", tenantId));

		} catch (DriverException e) {
			Logger.error("Error occurred while getting data from the data store: " + table, e);
		} finally {
			// close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}

		return CassandraDaoFactory.getSession().executeAsync(query);

	}
	
	/**
	 * Gets all rows in a given table
	 * 
	 * @param table the table to query
	 * @param tenantId the tenant ID to get data about
	 * @return all rows in the table that belongs to a particular tenant
	 * @throws DaoException if error occurs while getting user profiles from the user profile table
	 */
	public static ResultSetFuture getAll(String table) throws DaoException {

		Select query = null;
		try {
			query = QueryBuilder.select().all().from(Keyspace.valueOf(keyspace), table);
					

		} catch (DriverException e) {
			Logger.error("Error occurred while getting data from the data store: " + table, e);
		} finally {
			// close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}

		return CassandraDaoFactory.getSession().executeAsync(query);

	}	
	
	/**
	 * Gets all rows in the user profile table
	 * 
	 * @return all rows in the user profile table
	 * @throws DaoException if error occurs while getting user profiles from the user profile table
	 */
	public static ResultSet getAllColumns(String table) throws DaoException {

		Select query = null;
		try {
			query = QueryBuilder.select().all().from(Keyspace.valueOf(keyspace), table);

		} catch (DriverException e) {
			Logger.error("Error occurred while getting data from the data store: " + table, e);
		} finally {
			// close the connection to the database();
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}

		return CassandraDaoFactory.getSession().execute(query);

	}		
	
	/**
     * Returns a list of values for a given column.
     *  
     * @param result The result set returned from querying the table
     * @param columnName name of column we want to return its value
     * @param table name of the table to query
     * @return list of values for the column
     */
	public static List<String> getColumnValues(ResultSetFuture results, String columnName, String table) {
		if (results == null) {
			return null;
		}

		final List<Row> allRows = results.getUninterruptibly().all();
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
	
	/**
     * Returns a list of values for a given column.
     *  
     * @param result The result set returned from querying the table
     * @param columnName name of column we want to return its value
     * @param table name of the table to query
     * @return list of values for the column
     */
	public static List<String> getColumnValues(ResultSetFuture results, String[] columnName, String table) {
		String[] columns = {""};
		if (results == null) {
			return null;
		}

		final List<Row> allRows = results.getUninterruptibly().all();
		if (allRows == null) {
			return null;
		} else {
			List<String[]> values = new ArrayList<>();
			for (Row row : allRows) {
				for (int i = 0; i < columnName.length; i++) {
					columns[i] = row.getString(i);
				}
				for (int j = 0; j < columns.length; j++) {
					columns[0].concat(" ").concat(columns[1]);
				}
				values.add(columns);
			}
			return null; // TODO needs fixing
		}
	}	

}
