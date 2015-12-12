/**
 * 
 */
package com.depth1.grc.db.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import play.Play;

/**
 * Java Persistence utility methods for managing transactions
 * @author Bisi Adedokun
 *
 */

public class JpaUtil {
	
	private final static String PERSISTENCE_UNIT = Play.application().configuration().getString("jpa.mariadb");

	/**
	 * 
	 */
	public JpaUtil() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets a factory to load the persistence unit
	 * 
	 * @return A factory with the persistence unit
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }
	
	/**
	 * Begins a transaction scope
	 * 
	 */
	public static void beginTransaction(EntityManager em) {
		em.getTransaction().begin();
	}
	
	/**
	 * Commits a transaction to the data store
	 * 
	 */
	public static void comitTransaction(EntityManager em) {
		em.getTransaction().commit();	
	}	
	
	/**
	 * Rollbacks a failed transaction
	 * 
	 */
	public static void rollbackTransaction(EntityManager em) {
		em.getTransaction().rollback();	
	}
	
	/**
	 * Rollbacks a failed transaction
	 * 
	 */
	public static void closeTransaction(EntityManager em) {
		em.close();
	}	

}
