package com.erpsystem.crms.model;



public class PersonModel {

	private long personId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String address;
	//private LocalDate dob;
	private String dob;
	//private long age;
	private String age;
	private String aadharNo;
	private String panNo;
	private String mailId;
	private String organisation;
	private String orgLocation;
	private String orgRole;
	//private long city;
	private String city;
	//private Blob photo;
	private String pinCode;
	private String occupation;
	//private long branch;
	private String branch;
	private long reffId;
	
	
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNo() {
				return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/*public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	public long getAge() {
		return age;
	}
	public void setAge(long age) {
		this.age = age;
	}*/
	
	
	public String getAadharNo() {
		return aadharNo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getOrgLocation() {
		return orgLocation;
	}
	public void setOrgLocation(String orgLocation) {
		this.orgLocation = orgLocation;
	}
	public String getOrgRole() {
		return orgRole;
	}
	public void setOrgRole(String orgRole) {
		this.orgRole = orgRole;
	}
	/*public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}*/
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	/*public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}*/
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	/*public long getBranch() {
		return branch;
	}
	public void setBranch(long branch) {
		this.branch = branch;
	}*/
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public long getReffId() {
		return reffId;
	}
	public void setReffId(long reffId) {
		this.reffId = reffId;
	}
	
	
	

}
