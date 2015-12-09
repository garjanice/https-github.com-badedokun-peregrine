/**
 * 
 */
package com.depth1.grc.model;

import java.util.List;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.FrozenValue;
import com.datastax.driver.mapping.annotations.UDT;

/**
 * This class is used to store address object
 * @author Bisi Aadedokun
 *
 */
@UDT (keyspace = "grc", name = "address")
public class Address {
	private String street1;
	private String street2;
	private String city;
	private String zipcode;
	private String state;
	private String province;
	private String country;
	private String longitude;
	private String latitude;
	//@Frozen
	//private Phone phones;
	@Field(name="phones")
	@FrozenValue
	private List<Phone> phones;
   
	/**
	 * No argument constructor
	 */
	public Address() {
		
	}


	/**
	 * @return the street1
	 */
	public String getStreet1() {
		return street1;
	}


	/**
	 * @param street1 the street1 to set
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}


	/**
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}


	/**
	 * @param street2 the street2 to set
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}


	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}


	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}


	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}


	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	/**
	 * @return the phone
	 */
	public List<Phone> getPhones() {
		return phones;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	

}
