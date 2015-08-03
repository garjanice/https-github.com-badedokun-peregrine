/**
 * 
 */
package com.depth1.grc.model;

import java.util.Date;
import java.util.UUID;

/**
 * @author badedokun
 *
 */
public class UserProfile {
	
    private UUID id;
    private long tenantid;
    private String fname;
    private String lname;
    private String address1;
    private String address2;
    private String city;
    private String zipcode;
    private String state;
    private String province;
    private String country;
    private String username;
    private String password;
    private String email;
    private String deskPhone;
    private String mobilePhone;
    private String challengeQuestion;
    private String challengeAnswer;
    private String passwordExpired;
    private int passwordRetry;
    private Date passwordCreateDate;
    private Date passwordInvalidDate;
    private String passwordSalt;
    private String timeZone;
    private String locale;
    private String language;
	
    
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
	 * @return the tenantid
	 */
	public long getTenantid() {
		return tenantid;
	}
	/**
	 * @param tenantid the tenantid to set
	 */
	public void setTenantid(long tenantid) {
		this.tenantid = tenantid;
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
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
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
	 * @return the deskPhone
	 */
	public String getDeskPhone() {
		return deskPhone;
	}
	/**
	 * @param deskPhone the deskPhone to set
	 */
	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}
	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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
	 * @return the challengeQuestion
	 */
	public String getChallengeQuestion() {
		return challengeQuestion;
	}
	/**
	 * @param challengeQuestion the challengeQuestion to set
	 */
	public void setChallengeQuestion(String challengeQuestion) {
		this.challengeQuestion = challengeQuestion;
	}
	/**
	 * @return the challengeAnswer
	 */
	public String getChallengeAnswer() {
		return challengeAnswer;
	}
	/**
	 * @param challengeAnswer the challengeAnswer to set
	 */
	public void setChallengeAnswer(String challengeAnswer) {
		this.challengeAnswer = challengeAnswer;
	}
	/**
	 * @return the passwordExpired
	 */
	public String getPasswordExpired() {
		return passwordExpired;
	}
	/**
	 * @param passwordExpired the passwordExpired to set
	 */
	public void setPasswordExpired(String passwordExpired) {
		this.passwordExpired = passwordExpired;
	}
	/**
	 * @return the passwordRetry
	 */
	public int getPasswordRetry() {
		return passwordRetry;
	}
	/**
	 * @param passwordRetry the passwordRetry to set
	 */
	public void setPasswordRetry(int passwordRetry) {
		this.passwordRetry = passwordRetry;
	}
	/**
	 * @return the passwordCreateDate
	 */
	public Date getPasswordCreateDate() {
		return passwordCreateDate;
	}
	/**
	 * @param passwordCreateDate the passwordCreateDate to set
	 */
	public void setPasswordCreateDate(Date passwordCreateDate) {
		this.passwordCreateDate = passwordCreateDate;
	}
	/**
	 * @return the passwordInvalidDate
	 */
	public Date getPasswordInvalidDate() {
		return passwordInvalidDate;
	}
	/**
	 * @param passwordInvalidDate the passwordInvalidDate to set
	 */
	public void setPasswordInvalidDate(Date passwordInvalidDate) {
		this.passwordInvalidDate = passwordInvalidDate;
	}
	/**
	 * @return the passwordSalt
	 */
	public String getPasswordSalt() {
		return passwordSalt;
	}
	/**
	 * @param passwordSalt the passwordSalt to set
	 */
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	/**
	 * @return the time_zone
	 */
	public String getTime_zone() {
		return timeZone;
	}
	/**
	 * @param time_zone the time_zone to set
	 */
	public void setTime_zone(String timeZone) {
		this.timeZone = timeZone;
	}
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
	
	

}
