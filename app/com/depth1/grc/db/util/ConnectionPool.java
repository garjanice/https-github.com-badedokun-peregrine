package com.depth1.grc.db.util;

import java.util.Enumeration;
import java.util.Hashtable;
/**
 * 
 * This class implements the ObjectPool pattern to provide an efficient way of accessing data store 
 * resource pool.
 * @author Bisi Adedokun
 * Date: 07/01/2015
 *
 */
public abstract class ConnectionPool<T> {
	
	private long expirationTime;

	private Hashtable<T, Long> locked, unlocked;
	
	
	/**
	 * No argument constructor
	 * 
	 */
	public ConnectionPool(){
		expirationTime = 30000; // 30 seconds
	    locked = new Hashtable<T, Long>();
	    unlocked = new Hashtable<T, Long>();
	}

	/**

	 * Creates a connection to the data store.
	 * 
	 * @return T The object type that was created
	 * @throws DataStoreException If error occurs while connecting to the data store
	 */
	
	public abstract T create() throws DataStoreException;
	
	/**
	 * Validates the connection to determine if it expired or still valid.
	 * @param reusable Object type to validate
	 * @return boolean True if the connection is still valid, false otherwise
	 * @throws DataStoreException If error occurs while validating the connection to the data store
	 * 
	 */
	
	public abstract boolean validate(T reusable) throws DataStoreException ;
	
	/**
	 * Expires/Closes a connection to the data store
	 * @param reusable Object type to expire
	 * @throws DataStoreException If error occurs while closing a connection to the data store
	 */
	
	public abstract void expire(T reusable) throws DataStoreException;

	
	/**
	 * Checks out a connection from the connection pool if an object is available in the pool.
	 * If no object is available it creates a new object and put it in the pool
	 * @return The type of connection to check out from the pool
	 * @throws DataStoreException When error occurs while creating an object to put in the pool
	 *
	 */
	
	public synchronized T checkOut() throws DataStoreException {
		long now = System.currentTimeMillis();
		T pool;
		if (unlocked.size() > 0) {
			Enumeration<T> list = unlocked.keys();
			while (list.hasMoreElements()) {
				pool = list.nextElement();
				if ((now - unlocked.get(pool)) > expirationTime) {
					// object has expired
					unlocked.remove(pool);
					expire(pool);
					pool = null;
				} else {
					if (validate(pool)) {
						unlocked.remove(pool);
						locked.put(pool, now);
						return (pool);
					} else {
						// object failed validation
						unlocked.remove(pool);
						expire(pool);
						pool = null;
					}
				}
			}
		}
		
		// no objects available, create a new one
		try {
			pool = create();
			locked.put(pool, now);
		} catch (DataStoreException dse) {
			throw new DataStoreException("Unable to to connect to the cluster.");

		}
		return (pool);
	}
	
	/**
	 * Puts object in the pool.
	 * 
	 * @param pool The type of object to put in the pool
	 */
	
	public synchronized void checkIn(T pool) {
		locked.remove(pool);
	    unlocked.put(pool, System.currentTimeMillis());	
	}	

}
