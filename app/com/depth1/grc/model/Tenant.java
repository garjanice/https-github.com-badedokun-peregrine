package com.depth1.grc.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.utils.UUIDs;

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
		
	public CassandraTenantDao cassandraTenantDao;
	
	/**
	 * No argument constructor
	 */
	public Tenant(){
		super();
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
	
}

