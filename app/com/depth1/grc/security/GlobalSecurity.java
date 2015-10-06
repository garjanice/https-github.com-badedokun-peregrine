/**
 * 
 */
package com.depth1.grc.security;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.realm.Realm;

/**
 * @author badedokun
 *
 */
public class GlobalSecurity {
	
	
	public static void security() {
		
		Realm realm = null; //instantiate or acquire a Realm instance.  We'll discuss Realms later.

		SecurityManager securityManager = new DefaultSecurityManager(realm);

				//Make the SecurityManager instance available to the entire application via static memory:
		SecurityUtils.setSecurityManager(securityManager);
		
		
	}
	
	

}
