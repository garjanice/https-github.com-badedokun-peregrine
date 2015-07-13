/**
 * 
 */
package com.depth1.grc.model.common;

/**
 * This class uses a static factory method to return the appropriate keyspace (database) for the 
 * application based on deployment model, which could be either on premise or cloud (SaaS)
 * @author Bisi Adedokun
 *
 */
public class Keyspace {
	
	public static final String ONPREMISE_KEYSPACE = "grc";
	
	public static final String CLOUD_KEYSPACE = "grc_cloud";
	
	/**
	 * Returns the appropriate Keyspace based on the deployment model.
	 * 
	 * @return keyspace to use
	 */
	public static String valueOf(Boolean deployModel) {
		return deployModel.booleanValue() ? ONPREMISE_KEYSPACE : CLOUD_KEYSPACE;
	}
	

}
