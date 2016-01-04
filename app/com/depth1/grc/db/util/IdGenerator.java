package com.depth1.grc.db.util;

import java.util.HashMap;
import java.util.Map;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.QueryExecutionException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.depth1.grc.exception.DataException;
import com.depth1.grc.model.CassandraDaoFactory;

import play.Logger;

/**
 * Helper class to compute unique ID for classes that requires unique integer or long Id
 */
public class IdGenerator {

    // a map of table names to maximum ID numbers
    private static Map<String, Integer> tableToMaxIntegerIdMap = new HashMap<String, Integer>( );
    private static Map<String, Long> tableToMaxLongIdMap = new HashMap<String, Long>( );

    /**
     * Compute a new unique int ID. It is assumed that the specified table
     * has a column named 'id' of type 'int.' It is assumed 
     * that all parts of the program will use this method to compute
     * new IDs.
     * @param tableName the table to query to get the maximum id from
     * @param dbSession the cluster session to connect to
     * @return the next available unique ID for a table
     * @throws DataException when a query exception occurs
     */
	public final static synchronized int getNextIntegerId(String tableName) throws DataException {
		try {
			// if a max has already been retrieved from this table,
			// compute the next id without hitting the database
			if (tableToMaxIntegerIdMap.containsKey(tableName)) {
				Integer curMax = (Integer) tableToMaxIntegerIdMap.get(tableName);
				Integer newMax = new Integer(curMax.intValue() + 1);
				tableToMaxIntegerIdMap.put(tableName, newMax);
				return newMax.intValue();
			}

			Statement select = QueryBuilder.select().column("id").from("grc", tableName);
			ResultSet result = CassandraDaoFactory.getSession().execute(select);
			Row row = result.one();
			int max = row.getInt(1);
			max++;
			tableToMaxIntegerIdMap.put(tableName, new Integer(max));
			return max;
		} catch (QueryExecutionException e) {
			Logger.error("Error occurred while executing a query ", e);
			throw new DataException(e);
		} finally {
			// done, close the session
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
	}
	
    /**
     * Compute a new unique long ID. It is assumed that the specified table
     * has a column named 'id' of type 'bigint.' It is assumed 
     * that all parts of the program will use this method to compute
     * new IDs.
     * @param tableName the table to query to get the maximum id from
     * @param dbSession the cluster session to connect to
     * @return the next available unique ID for a table
     * @throws DataException when a query exception occurs
     */
	public final static synchronized long getNextLongId(String tableName) throws DataException {
		try {
			// if a max has already been retrieved from this table,
			// compute the next id without hitting the database
			if (tableToMaxLongIdMap.containsKey(tableName)) {
				Long curMax = (Long) tableToMaxLongIdMap.get(tableName);
				Long newMax = new Long(curMax.longValue() + 1L);
				tableToMaxLongIdMap.put(tableName, newMax);
				return newMax.longValue();
			}

			Statement select = QueryBuilder.select().column("id").from("grc", tableName);
			ResultSet result = CassandraDaoFactory.getSession().execute(select);
			Row row = result.one();
			long max = row.getLong(1);
			max++;
			tableToMaxLongIdMap.put(tableName, new Long(max));
			return max;
		} catch (QueryExecutionException e) {
			Logger.error("Error occurred while executing a query ", e);
			throw new DataException(e);
		} finally {
			// done, close the session
			CassandraDaoFactory.close(CassandraDaoFactory.getSession());
		}
	}	
} 
