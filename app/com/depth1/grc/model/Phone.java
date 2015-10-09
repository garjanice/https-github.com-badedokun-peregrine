/**
 * 
 */
package com.depth1.grc.model;

import com.datastax.driver.mapping.annotations.UDT;

/**
 * A class to hold phone alias and the corresponding number
 * @author Bisi Adedokun
 *
 */
@UDT (keyspace = "grc", name = "phone")
public class Phone {
	
	private String alias;
	private String number;
	
	
	
	/**
	 * No argument constructor
	 */
	public Phone() {
		
	}

	/**
	 * @param alias phone alias such as 'home' or 'office' or 'mobile'
	 * @param number phone number
	 */
	public Phone(String alias, String number) {
		this.alias = alias;
		this.number = number;
	}
	
	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

}
