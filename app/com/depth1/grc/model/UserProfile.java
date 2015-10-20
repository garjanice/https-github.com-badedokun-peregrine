/**
 * 
 */
package com.depth1.grc.model;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.utils.UUIDs;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

/**
 * This class is the business object for creating, updating, deleting, viewing, and finding a user.<br> 
 * @author Bisi Adedokun
 *
 */
public class UserProfile {
	
    private UUID id;
    @Required
    private long tenantId;
    @Required
    private String fname;
    @Required
    private String lname;
    @Required
    private String gender;
    @Required
    private String username;
    @Required
    private String password;
    @Required
    @Email
    private String email;
    @Required
    private String street1;
    private String street2;
    @Required
    private String city;
    private String zipcode;
    private String state;
    private String province;
    @Required
    private String country;
    private String latitude;
    private String longitude;
    private String lineofdefense;
    private UUIDs createdate;
    @Required
    private String status;
    private Map<String, String> phones;
    private String timeZone;
    private String locale;
    private String language;
	private String minitial;
    private String salutation;
    private String pfname;
    private String title;
	private long uuidTime;
	private Date dateUtil;
    
    /**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	/**
	 * @return the tenantId
	 */
	public long getTenantId() {
		return tenantId;
	}
	/**
	 * @param tenantid the tenantId to set
	 */
	public void setTenantId(long tenantid) {
		this.tenantId = tenantid;
	}
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
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
	/**
	 * @return the emailAddress
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}
	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	/**
	 * @return the email_address
	 */

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * @return the lineofdefense
	 */
	public String getLineofdefense() {
		return lineofdefense;
	}
	/**
	 * @param lineofdefense the lineofdefense to set
	 */
	public void setLineofdefense(String lineofdefense) {
		this.lineofdefense = lineofdefense;
	}
	/**
	 * @return the createdate
	 */
	public UUIDs getCreatedate() {
		return createdate;
	}
	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(UUIDs createdate) {
		this.createdate = createdate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the phones
	 */
	public Map<String, String> getPhones() {
		return phones;
	}
	/**
	 * @param phones the phones to set
	 */
	public void setPhones(Map<String, String> phones) {
		this.phones = phones;
	}
	
    /**
	 * @return the minitial
	 */
	public String getMinitial() {
		return minitial;
	}
	/**
	 * @param minitial the minitial to set
	 */
	public void setMinitial(String minitial) {
		this.minitial = minitial;
	}
	/**
	 * @return the salutation
	 */
	public String getSalutation() {
		return salutation;
	}
	/**
	 * @param salutation the salutation to set
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	/**
	 * @return the pfname
	 */
	public String getPfname() {
		return pfname;
	}
	/**
	 * @param pfname the pfname to set
	 */
	public void setPfname(String pfname) {
		this.pfname = pfname;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the uuidTime
	 */
	public long getUuidTime() {
		return uuidTime;
	}
	/**
	 * @param uuidTime the uuidTime to set
	 */
	public void setUuidTime(long uuidTime) {
		this.uuidTime = uuidTime;
	}
	/**
	 * @return the dateUtil
	 */
	public Date getDateUtil() {
		return dateUtil;
	}
	/**
	 * @param dateUtil the dateUtil to set
	 */
	public void setDateUtil(Date dateUtil) {
		this.dateUtil = dateUtil;
	}	
	

}
