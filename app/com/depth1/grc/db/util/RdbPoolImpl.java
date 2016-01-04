/**
 * 
 */
package com.depth1.grc.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.depth1.grc.exception.DataStoreException;

import play.Logger;
import play.Play;

/**
 * This class is the concrete implementation of the object pool pattern
 * It implements the 3 abstract methods in the ConnectionPool class
 * @author Bisi Adedokun
 *
 */
public class RdbPoolImpl extends ConnectionPool<Connection> {
	private final static String dbURL = Play.application().configuration().getString("db.mariadb.url");
	private final static String username = Play.application().configuration().getString("db.mariadb.username");
	private final static String password = Play.application().configuration().getString("db.mariadb.password");
	private final static String driver = Play.application().configuration().getString("db.mariadb.driver");

	/**
	 * Creates a connection to the data store.
	 * 
	 * @return T The object type that was created
	 * @throws DataStoreException If error occurs while connecting to the data store
	 */	
	@Override
	public Connection create() throws DataStoreException {
		Connection connection = null;
		try {
			
			Class.forName(driver);
			connection = DriverManager.getConnection(dbURL, username, password);
			Logger.info("Connected to MariaDB");
		} catch (ClassNotFoundException ex) {
			throw new DataStoreException("Failed to load MariaDB driver");
		} catch (SQLException se) {
			throw new DataStoreException("Failed to connect to MariaDB database", se);
		}

		return connection;
	}
	
	/**
	 * Validates the connection to determine if it expired or still valid.
	 * @param reusable Object type to validate
	 * @return boolean True if the connection is still valid, false otherwise
	 * @throws DataStoreException If error occurs while validating the connection to the data store
	 */
	@Override
	public boolean validate(Connection pool) throws DataStoreException {
		try {
			return (!((Connection) pool).isClosed());
		} catch (DataStoreException | SQLException dse) {
			throw new DataStoreException("Unable to connect to the MariaDB cluster.");
			
		}
	}

	/**
	 * Expires/Closes a connection to the data store
	 * @param reusable Object type to expire
	 * @throws DataStoreException If error occurs while closing a connection to the data store
	 */
	@Override
	public void expire(Connection pool) throws DataStoreException {
		try {
			((Connection) pool).close();
		} catch (DataStoreException dse) {
			throw new DataStoreException("Unable to close connection to the MariaDB cluster.");
		} catch (SQLException e) {
			throw new DataStoreException("Unable to close connection to the MariaDB cluster.");
		}

	}

}
