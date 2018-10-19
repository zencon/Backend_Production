package com.erpsystem.crms.model;



public class InquiryModel {

	private long inquiryId;
	private String datevalue; 
	private long branch;
	private long enquirysource;
	private long enquiryfor;
	private long targetProduct;
	private String description;
	private String processStatus;
	private String followupdate;
	private String remarkvalue;
	private long statusvalue;
	private String lastUpdate;
	private long personid;
	
	
	public long getInquiryId() {
		return inquiryId;
	}
	public void setInquiryId(long inquiryId) {
		this.inquiryId = inquiryId;
	}
	public String getDatevalue() {
		return datevalue;
	}
	public void setDatevalue(String datevalue) {
		this.datevalue = datevalue;
	}
	public long getBranch() {
		return branch;
	}
	public void setBranch(long branch) {
		this.branch = branch;
	}
	public long getEnquirysource() {
		return enquirysource;
	}
	public void setEnquirysource(long enquirysource) {
		this.enquirysource = enquirysource;
	}
	public long getEnquiryfor() {
		return enquiryfor;
	}
	public void setEnquiryfor(long enquiryfor) {
		this.enquiryfor = enquiryfor;
	}
	public long getTargetProduct() {
		return targetProduct;
	}
	public void setTargetProduct(long targetProduct) {
		this.targetProduct = targetProduct;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getFollowupdate() {
		return followupdate;
	}
	public void setFollowupdate(String followupdate) {
		this.followupdate = followupdate;
	}
	public String getRemarkvalue() {
		return remarkvalue;
	}
	public void setRemarkvalue(String remarkvalue) {
		this.remarkvalue = remarkvalue;
	}
	public long getStatusvalue() {
		return statusvalue;
	}
	public void setStatusvalue(long statusvalue) {
		this.statusvalue = statusvalue;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public long getPersonid() {
		return personid;
	}
	public void setPersonid(long personid) {
		this.personid = personid;
	}
	
	
	
}
