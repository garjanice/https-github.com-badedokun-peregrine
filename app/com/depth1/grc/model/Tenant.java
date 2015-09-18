package com.depth1.grc.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import com.depth1.grc.model.common.DateFormat;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

/**
 * This class is the business object for creating, updating, deleting, viewing, and finding a tenant.<br>
 */

public class Tenant
{
		
	private UUID id;
	
	private long tenantId;
		
	private String name;
	
	@Required
	private String type;
	
	@Required
	private String address1;
	
	private String address2;
	
	@Required
	private String city;
	
	@Required
	private String zipcode;
	
		
	private String state;
	
		
	private String province;
	
	@Required	
	private String country;
	
	@Required
	private String contactPersonName;
	
	@Required
	private String contactPersonPhone;

	@Required
	@Email
	private String contactPersonEmail;
	
	@Required
	@DateFormat("MM-dd-yyyy")
	private Date serviceStartDate;
	
	
	@DateFormat("MM-dd-yyyy")
	private Date createDate;
	
	@Required
	private String companyUrl;
	
	@Required
	private String phoneNumber;
	
	@Required
	private String status;
	
	
	private String ipaddress;
	
	private String email;
	
		
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

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public String getServiceStartDate() {
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		String dateToStr = df.format(serviceStartDate);
		return dateToStr;
	}

	public void setServiceStartDate(String ssd) {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(ssd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.serviceStartDate = date;
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

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public CassandraTenantDao getCassandraTenantDao() {
		return cassandraTenantDao;
	}

	public void setCassandraTenantDao(CassandraTenantDao cassandraTenantDao) {
		this.cassandraTenantDao = cassandraTenantDao;
	}

	public String getContactPersonPhone() {
		return contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}
	
}

