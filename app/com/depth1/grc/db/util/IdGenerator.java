package com.depth1.grc.db.util;

import java.util.HashMap;
import java.util.Map;

import play.Logger;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.QueryExecutionException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.depth1.grc.model.CassandraDaoFactory;

/**
 * Helper methods for relational database access using JDBC.
 */
public class IdGenerator {

    // a map of table names to maximum ID numbers
    private static Map<String, Integer> tableToMaxIDMap = new HashMap<String, Integer>( );

    /**
     * Compute a new unique ID. It is assumed that the specified table
     * has a column named 'id' of type 'long'. It is assumed that
     * that all parts of the program will use this method to compute
     * new IDs.
     * @param tableName the table to query to get the maximum id from
     * @param dbSession the cluster session to connect to
     * @return the next available unique ID for a table
     * @throws DataException when a query exception occurs
     */
	public static synchronized long getNextID(String tableName,
			Session dbSession) throws DataException {
		// Statement stmt = null;
		dbSession = CassandraDaoFactory.connect();
		try {
			// if a max has already been retrieved from this table,
			// compute the next id without hitting the database
			if (tableToMaxIDMap.containsKey(tableName)) {
				Integer curMax = (Integer) tableToMaxIDMap.get(tableName);
				Integer newMax = new Integer(curMax.intValue() + 1);
				tableToMaxIDMap.put(tableName, newMax);
				return newMax.intValue();
			}
			/*
			 * ResultSet rs = stmt.executeQuery( "SELECT MAX(id) FROM " +
			 * tableName); if (result.one( )) { max = rs.getLong(1); }
			 */
			Statement select = QueryBuilder.select().column("id")
					.from(tableName);
			ResultSet result = dbSession.execute(select);
			Row row = result.one();
			int max = row.getInt(1);
			max++;
			tableToMaxIDMap.put(tableName, new Integer(max));
			return max;
		} catch (QueryExecutionException e) {
			Logger.error("Error occurred while executing a query ", e);
			throw new DataException(e);
		} finally {
			// just close the session
			CassandraDaoFactory.close(dbSession);
		}
	}
} 