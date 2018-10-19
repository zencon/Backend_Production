package com.erpsystem.crms.model;

import java.time.LocalDate;

public class EmployeeModel {

	private long employeeid;
	private String dateofjoining;
	private String designation;
	private long branch;
	private long reportingTo;
	private long personid;


	
	public long getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(long employeeid) {
		this.employeeid = employeeid;
	}
	public String getDateofjoining() {
		return dateofjoining;
	}
	public void setDateofjoining(String dateofjoining) {
		this.dateofjoining = dateofjoining;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public long getBranch() {
		return branch;
	}
	public void setBranch(long branch) {
		this.branch = branch;
	}
	public long getReportingTo() {
		return reportingTo;
	}
	public void setReportingTo(long reportingTo) {
		this.reportingTo = reportingTo;
	}
	public long getPersonid() {
		return personid;
	}
	public void setPersonid(long personid) {
		this.personid = personid;
	}
	
	
	
}
