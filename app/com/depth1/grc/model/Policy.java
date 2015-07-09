//////////////////////////////////////////////////////////////////
// Policy.java	-	Data Model for Policy						//
// ver 1.0														//
// Team-A														//
// Platform: Win 8.1 Pro, Lenovo Y40-70, JDK 1.8.0_u45 64bit	//
//				Scala 2.11.6, Play 2.4.2						//
//////////////////////////////////////////////////////////////////

/*
 * Description:
 * --------------------------------------------------
 * This is the data model for Policy.
 * Attributes are made public because Play Framework
 * will automatically generate getters and setters.
 * 
 * Maintanance History:
 * --------------------------------------------------
 * ver 1.0: Jul 7, 2015
 * - First version.
 * 
 */

package com.depth1.grc.model;

import java.util.UUID;
import java.sql.Date;

public class Policy {
	public String tenantId;
	public UUID policyId;			//unique
	public String name;
	public String description;
	public String author;
	public String version;
	public Date createDate;
	public String format;
	public String language;
	public String subject;
	public String source;
	public Date sunsetDate;
	public String category;
	public String classification;
	public String reference;
	public String legal;
	public String regulatory;
	public String approver;
	public String producingFunction;
	public String complianceCategory;
	public String owner;
	public String documentContact;
	public String functionalApplicability;
	public String geographicApplicability;
	public Date issueDate;
	public Date lastReviewDate;
	public Date nextReviewDate;
	public Date lastModifiedDate;
}
