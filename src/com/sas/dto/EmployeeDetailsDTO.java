package com.sas.dto;

public class EmployeeDetailsDTO {
	private int empId;
	private String name;
	private String email;
	private String mobile;
	private String password;
	private int role;
	private int emailVerified;
	private String mobileVerified;
	private String passwordUpdatedOn;
	private int wrongEntry;
	private int isLocked;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(int emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getMobileVerified() {
		return mobileVerified;
	}

	public void setMobileVerified(String mobileVerified) {
		this.mobileVerified = mobileVerified;
	}

	public String getPasswordUpdatedOn() {
		return passwordUpdatedOn;
	}

	public void setPasswordUpdatedOn(String passwordUpdatedOn) {
		this.passwordUpdatedOn = passwordUpdatedOn;
	}

	public int getWrongEntry() {
		return wrongEntry;
	}

	public void setWrongEntry(int wrongEntry) {
		this.wrongEntry = wrongEntry;
	}

	public int getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}

}
