package com.depth1.grc.db.util;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.DriverException;

import play.Play;

public class CassandraPoolImpl extends ConnectionPool<Session> {
	
	private final static String REMOTE_CLUSTER = Play.application().configuration().getString("cassandra.remote.cluster");
    private Cluster cluster;
    private Session session;

	@Override
	public Session create() throws DriverException {
		cluster = Cluster.builder().addContactPoint(REMOTE_CLUSTER).build();
		session = cluster.connect();
		// test connection to the database;
		Metadata metadata = cluster.getMetadata();
		System.out.printf("Connected to cluster: %s\n",
		metadata.getClusterName());
		for ( Host host : metadata.getAllHosts() ) {
		System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
		host.getDatacenter(), host.getAddress(), host.getRack());
		}
		//
		return session;
	}

	@Override
	public boolean validate(Session pool) throws DriverException {
		try {
			return (!((Session) pool).isClosed());
		} catch (DriverException e) {
			throw new DriverException("Unable to to connect to the cluster.");
			
		}
		
	}


	@Override
	public void expire(Session pool) throws DriverException {
		  try {
		      ((Session) pool).close();
		    } catch (DriverException e) {
		    	throw new DriverException("Unable to to connect to the cluster.");
		    }

	}

}
