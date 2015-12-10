package com.depth1.grc.db.util;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

import play.Play;

/**
 * This class is the concrete implementation of the object pool pattern
 * It implements the 3 abstract methods in the ConnectionPool class
 * @author Bisi Adedokun
 *
 */
public class CassandraPoolImpl extends ConnectionPool<Session> {
	
	private final static String REMOTE_CLUSTER = Play.application().configuration().getString("cassandra.remote.cluster");
    private Cluster cluster;
    private Session session;

	/**
	 * Creates a connection to the data store.
	 * 
	 * @return T The object type that was created
	 * @throws DataStoreException If error occurs while connecting to the data store
	 */

    @Override
    public Session create() throws DataStoreException {
    	cluster = Cluster.builder().addContactPoint(REMOTE_CLUSTER)
    			.withCredentials("super", "cheetah27").build();
    	session = cluster.connect();
    	// test connection to the database;
    	Metadata metadata = cluster.getMetadata();
    	System.out.printf("Connected to cassandra cluster: %s\n",
    			metadata.getClusterName());
    	for ( Host host : metadata.getAllHosts() ) {
    		System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
    				host.getDatacenter(), host.getAddress(), host.getRack());
    	}
    	//
    	return session;
    }

	/**
	 * Validates the connection to determine if it expired or still valid.
	 * @param reusable Object type to validate
	 * @return boolean True if the connection is still valid, false otherwise
	 * @throws DataStoreException If error occurs while validating the connection to the data store
	 */
	@Override
	public boolean validate(Session pool) throws DataStoreException {
		try {
			return (!((Session) pool).isClosed());
		} catch (DataStoreException dse) {
			throw new DataStoreException("Unable to connect to the cluster.");
			
		}
		
	}

	/**
	 * Expires/Closes a connection to the data store
	 * @param reusable Object type to expire
	 * @throws DataStoreException If error occurs while closing a connection to the data store
	 */
	@Override
	public void expire(Session pool) throws DataStoreException {
		  try {
		      ((Session) pool).close();
		      ((Session) pool).getCluster().close();
		    } catch (DataStoreException dse) {
		    	throw new DataStoreException("Unable to connect to the cluster.");
		    }

	}

}
