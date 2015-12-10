package com.depth1.grc.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.utils.UUIDs;
import com.depth1.grc.util.DateUtility;


import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

/**
 * This class is the business object for creating, updating, deleting, viewing, and finding a tenant.<br>
 */

public class Tenant
{
		
	private UUID id;

	private long tenantId;
	@Required
	private String name;
	@Required
	private String type;
	@Required
	private String street1;
	private String street2;
	@Required
	private String city;
	@Required
	private String zipcode;
	private String state;
	private String province;
	private String latitude;
	private String longitude;
	@Required	
	private String country;
	private Map<String, String> phones;
	@Required
	private String contactPersonName;
	@Required
	@Email
	private String contactPersonEmail;
	private Map<String, String> contactPersonPhones;
	//@DateFormat("MM-dd-yyyy")
	private Timestamp serviceStartDate;
	private UUIDs createDate;
	@Required
	private String companyUrl;
	@Required
	private String status;
	private String ipaddress;
	private long uuidTime;
	private Date startDateUtil;
	private Date createDateUtil;
	private String phoneName1;
	private String phoneName2;
	private String phoneName3;
	private String phoneName4;
	private String phoneName5;
	private String phoneName6;
	private String phoneName7;
	private String phoneName8;
	private String phoneNumber1;
	private String phoneNumber2;
	private String phoneNumber3;
	private String phoneNumber4;
	private String phoneNumber5;
	private String phoneNumber6;
	private String phoneNumber7;
	private String phoneNumber8;
	

		
	public CassandraTenantDao cassandraTenantDao;
	
	/**
	 * No argument constructor
	 */
	public Tenant(){
		super();		
	}
	
	public void setPhoneName1(String phoneName1){
		this.phoneName1 = phoneName1;
		
	}
	
	public String getPhoneName1(){
		return phoneName1;

	}
	
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

	public long getTenantId() {
		return tenantId;
	}

	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String address1) {
		this.street1 = address1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String address2) {
		this.street2 = address2;

	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}

	/**
	 * @return the contactPersonPhones
	 */
	public Map<String, String> getContactPersonPhones() {
		return contactPersonPhones;
	}

	/**
	 * @param contactPersonPhones the contactPersonPhones to set
	 */
	public void setContactPersonPhones(Map<String, String> contactPersonPhones) {
		this.contactPersonPhones = contactPersonPhones;
	}

	public Timestamp getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Timestamp date) {
		this.serviceStartDate = date;
	}

	/**
	 * @return the createDate
	 */
	public UUIDs getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(UUIDs createDate) {
		this.createDate = createDate;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
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
	public Date getStartDateUtil() {
		return startDateUtil;
	}

	/**
	 * @param dateUtil the dateUtil to set
	 */
	public void setStartDateUtil(Date dateUtil) {
		this.startDateUtil = dateUtil;
	}

	/**
	 * @return the createDateUtil
	 */
	public Date getCreateDateUtil() {
		return createDateUtil;
	}

	/**
	 * @param createDateUtil the createDateUtil to set
	 */
	public void setCreateDateUtil(Date createDateUtil) {
		this.createDateUtil = createDateUtil;
	}

	public CassandraTenantDao getCassandraTenantDao() {
		return cassandraTenantDao;
	}

	public void setCassandraTenantDao(CassandraTenantDao cassandraTenantDao) {
		this.cassandraTenantDao = cassandraTenantDao;
	}
	public String getCreateDateString() {		
		return DateUtility.formatDateFromUuid("MM/dd/yyyy",createDateUtil );
	}
	public String getServiceStartDateString(){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return DateUtility.toString(serviceStartDate, df);
	}
	public void setServiceStartDateString(String date){
		try{
			serviceStartDate = DateUtility.toTimestamp(date, "MM/dd/yyyy");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @return the phoneName2
	 */
	public String getPhoneName2() {
		return phoneName2;
	}

	/**
	 * @param phoneName2 the phoneName2 to set
	 */
	public void setPhoneName2(String phoneName2) {
		this.phoneName2 = phoneName2;
	}

	/**
	 * @return the phoneName3
	 */
	public String getPhoneName3() {
		return phoneName3;
	}

	/**
	 * @param phoneName3 the phoneName3 to set
	 */
	public void setPhoneName3(String phoneName3) {
		this.phoneName3 = phoneName3;
	}

	/**
	 * @return the phoneName4
	 */
	public String getPhoneName4() {
		return phoneName4;
	}

	/**
	 * @param phoneName4 the phoneName4 to set
	 */
	public void setPhoneName4(String phoneName4) {
		this.phoneName4 = phoneName4;
	}

	/**
	 * @return the phoneNumber1
	 */
	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	/**
	 * @param phoneNumber1 the phoneNumber1 to set
	 */
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	/**
	 * @return the phoneNumber2
	 */
	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	/**
	 * @param phoneNumber2 the phoneNumber2 to set
	 */
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	/**
	 * @return the phoneNumber3
	 */
	public String getPhoneNumber3() {
		return phoneNumber3;
	}

	/**
	 * @param phoneNumber3 the phoneNumber3 to set
	 */
	public void setPhoneNumber3(String phoneNumber3) {
		this.phoneNumber3 = phoneNumber3;
	}

	/**
	 * @return the phoneNumber4
	 */
	public String getPhoneNumber4() {
		return phoneNumber4;
	}

	/**
	 * @param phoneNumber4 the phoneNumber4 to set
	 */
	public void setPhoneNumber4(String phoneNumber4) {
		this.phoneNumber4 = phoneNumber4;
	}

	/**
	 * @return the phoneName5
	 */
	public String getPhoneName5() {
		return phoneName5;
	}

	/**
	 * @param phoneName5 the phoneName5 to set
	 */
	public void setPhoneName5(String phoneName5) {
		this.phoneName5 = phoneName5;
	}

	/**
	 * @return the phoneName6
	 */
	public String getPhoneName6() {
		return phoneName6;
	}

	/**
	 * @param phoneName6 the phoneName6 to set
	 */
	public void setPhoneName6(String phoneName6) {
		this.phoneName6 = phoneName6;
	}

	/**
	 * @return the phoneName7
	 */
	public String getPhoneName7() {
		return phoneName7;
	}

	/**
	 * @param phoneName7 the phoneName7 to set
	 */
	public void setPhoneName7(String phoneName7) {
		this.phoneName7 = phoneName7;
	}

	/**
	 * @return the phoneName8
	 */
	public String getPhoneName8() {
		return phoneName8;
	}

	/**
	 * @param phoneName8 the phoneName8 to set
	 */
	public void setPhoneName8(String phoneName8) {
		this.phoneName8 = phoneName8;
	}

	/**
	 * @return the phoneNumber5
	 */
	public String getPhoneNumber5() {
		return phoneNumber5;
	}

	/**
	 * @param phoneNumber5 the phoneNumber5 to set
	 */
	public void setPhoneNumber5(String phoneNumber5) {
		this.phoneNumber5 = phoneNumber5;
	}

	/**
	 * @return the phoneNumber6
	 */
	public String getPhoneNumber6() {
		return phoneNumber6;
	}

	/**
	 * @param phoneNumber6 the phoneNumber6 to set
	 */
	public void setPhoneNumber6(String phoneNumber6) {
		this.phoneNumber6 = phoneNumber6;
	}

	/**
	 * @return the phoneNumber7
	 */
	public String getPhoneNumber7() {
		return phoneNumber7;
	}

	/**
	 * @param phoneNumber7 the phoneNumber7 to set
	 */
	public void setPhoneNumber7(String phoneNumber7) {
		this.phoneNumber7 = phoneNumber7;
	}

	/**
	 * @return the phoneNumber8
	 */
	public String getPhoneNumber8() {
		return phoneNumber8;
	}

	/**
	 * @param phoneNumber8 the phoneNumber8 to set
	 */
	public void setPhoneNumber8(String phoneNumber8) {
		this.phoneNumber8 = phoneNumber8;
	}

	
}

