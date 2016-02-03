/**
 * 
 */
package com.depth1.grc.db.util;

import java.util.List;
import java.util.Map;

import com.depth1.grc.exception.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Tenant;
import com.depth1.grc.model.TenantDao;

import play.Logger;
import play.Play;
import play.cache.Cache;
import play.mvc.Controller;

/**
 * @author badedokun
 *
 */
public class CacheUtil extends Controller {
	
	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory.getDaoFactory(DaoFactory.CASSANDRA);
	static DaoFactory rdbFactory = DaoFactory.getDaoFactory(DaoFactory.MARIADB);	

	/**
	 * 
	 */
	public CacheUtil() {
		
	}
	
	/**
	 * Puts Tenant in Play framework cache for later use. This method is invoked at application startup
	 * 
	 */
	public static void setTenantInCache() {
		String cacheKey = "tenant.key";
		TenantDao tenantDao = null;
		// Check if Tenant is already in cache
		@SuppressWarnings("unchecked")
		Map<String, Long> cache = (Map<String, Long>) Cache.get(cacheKey);
		if (cache == null) {
			try {
				tenantDao = cassandraFactory.getTenantDao();
				cache = tenantDao.cacheTenant();
				// Set Tenant in cache
				Cache.set(cacheKey, cache);

			} catch (DaoException e) {
				Logger.error("Error occured while getting data into the cache", e);
			}

		}		

	}	

}
