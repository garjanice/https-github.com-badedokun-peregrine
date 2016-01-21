/**
 * 
 */
package com.depth1.grc.model;

import java.util.Date;
import java.util.UUID;

import play.data.validation.Constraints.Required;

/**
 * This class is used to manage departments
 * @author Bisi Adedokun
 *
 */
public class Department {
	
	private UUID deptId;
	@Required
	private long tenantId;
	@Required
	private String deptName;
	private String deptShortName;
	private String description;
	private Date createDate;

	/**
	 * 
	 */
	public Department() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the deptId
	 */
	public UUID getDeptId() {
		return deptId;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(UUID deptId) {
		this.deptId = deptId;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the tenantId
	 */
	public long getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the deptShortName
	 */
	public String getDeptShortName() {
		return deptShortName;
	}

	/**
	 * @param deptShortName the deptShortName to set
	 */
	public void setDeptShortName(String deptShortName) {
		this.deptShortName = deptShortName;
	}

}
