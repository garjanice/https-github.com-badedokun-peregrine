/**
 * 
 */
package com.depth1.grc.model;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;


/**
 * This class is the business object for creating, updating, deleting, viewing, and finding a user.<br> 
 * @author Bisi Adedokun
 *
 */
public class UserProfile {
	
    private UUID userProfileId;
    @Required
    private long tenantId;
    @Required
    private String firstName;
    @Required
    private String lastName;
    @Required
    private String gender;
    @Required
    @MaxLength(128)
    @Email
    private String username;
    @Required
    @MaxLength(128)
    @MinLength(6)
    private String password;
    @Required
    @MaxLength(128)
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
    private String lineOfDefense; //values: 1LoD, 2LoD, 3LoD
    private Date createDate;
    @Required
    private String status;
    private Map<String, String> phones;
    private String timeZone;
    private String locale;
    private String language;
	private String middleInitial;
    private String salutation;
    private String preferredFirstName;
    private String title;
	private UUID deptId;
	private String deptName;
	private int lodFunctionId;
	private String lodFunction;
    
    /**
	 * @return the id
	 */
	public UUID getUserProfileId() {
		return userProfileId;
	}
	/**
	 * @param id the id to set
	 */
	public void setUserProfileId(UUID id) {
		this.userProfileId = id;
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
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

		this.username = username.toLowerCase();
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

		this.email = email.toLowerCase();
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
	 * @return the lineOfDefense
	 */
	public String getLineOfDefense() {
		return lineOfDefense;
	}
	/**
	 * @param lineOfDefense the lineOfDefense to set
	 */
	public void setLineOfDefense(String lineOfDefense) {
		this.lineOfDefense = lineOfDefense;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	 * @return the middleInitial
	 */
	public String getMiddleInitial() {
		return middleInitial;
	}
	/**
	 * @param middleInitial the middleInitial to set
	 */
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
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
	 * @return the preferred first name
	 */
	public String getPreferredFirstName() {
		return preferredFirstName;
	}
	/**
	 * @param preferredFirstName the preferredFirstName to set
	 */
	public void setPreferredFirstName(String preferredFirstName) {
		this.preferredFirstName = preferredFirstName;
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
	 * @return the deptId
	 */
	public UUID getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(UUID deptId) {
		this.deptId = deptId;
	}	
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}	
	/**
	 * @return the lodFunctionId
	 */
	public int getLodFunctionId() {
		return lodFunctionId;
	}
	/**
	 * @param lodFunctionId the lodFunctionId to set
	 */
	public void setLodFunctionId(int lodFunctionId) {
		this.lodFunctionId = lodFunctionId;
	}	
	/**
	 * @return the lodFunction
	 */
	public String getLodFunction() {
		return lodFunction;
	}
	/**
	 * @param lodFunction the lodFunction to set
	 */
	public void setLodFunction(String lodFunction) {
		this.lodFunction = lodFunction;
	}	

}
