/**
 * 
 */
package com.depth1.grc.db.util;

import java.util.Enumeration;
import java.util.Hashtable;

import com.datastax.driver.core.exceptions.DriverException;

/**
 * @author Bisi Adedokun
 * This class implements the ObjectPool pattern to provide an efficient way of accessing data store resource
 * pool.
 *
 */
public abstract class ConnectionPool<T> {
	
	private long expirationTime;

	private Hashtable<T, Long> locked, unlocked;
	
	//public Client client;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public ConnectionPool(){
		expirationTime = 30000; // 30 seconds
	    locked = new Hashtable<T, Long>();
	    unlocked = new Hashtable<T, Long>();
		//super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public abstract T create() throws DriverException;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public abstract boolean validate(T reusable) throws DriverException ;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public abstract void expire(T reusable) throws DriverException;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @throws DataException 
	 * @generated
	 * @ordered
	 */
	
	public synchronized T checkOut() throws DriverException {
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
		} catch (DriverException de) {
			throw new DriverException("Unable to to connect to the cluster.");

		}
		return (pool);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public synchronized void checkIn(T pool) {
		locked.remove(pool);
	    unlocked.put(pool, System.currentTimeMillis());	
	}	

}
