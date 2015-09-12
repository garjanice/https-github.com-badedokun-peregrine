/**
 * 
 */
package com.depth1.grc.model;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

/**
 * This class is the business object for user login to the application
 * 
 * @author Bisi Adedokun
 *
 */
public class Login {
	
	@Required
	@Email
	private String username;
	@Required
	private String password;

	/**
	 * @return the email
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param email the email to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
