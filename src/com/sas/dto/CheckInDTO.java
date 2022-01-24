package com.sas.dto;

public class CheckInDTO {
	private int empId;
	private String checkInAt;
	private String checkOutAt;
	private int totalHours;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getCheckInAt() {
		return checkInAt;
	}

	public void setCheckInAt(String checkInAt) {
		this.checkInAt = checkInAt;
	}

	public String getCheckOutAt() {
		return checkOutAt;
	}

	public void setCheckOutAt(String checkOutAt) {
		this.checkOutAt = checkOutAt;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

}
