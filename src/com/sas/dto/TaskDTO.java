package com.sas.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskDTO {
	private int empId;
	private int projectId;
	private String taskName;
	private String description;
	private String priority;
	private String taskdocLocation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date endDate;
	private String taskStatus;
	private String dueDateChangeCount;
	private String estHour;
	private int assignedTo;
	private String manager;
	private String fileURL;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTaskdocLocation() {
		return taskdocLocation;
	}

	public void setTaskdocLocation(String taskdocLocation) {
		this.taskdocLocation = taskdocLocation;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getDueDateChangeCount() {
		return dueDateChangeCount;
	}

	public void setDueDateChangeCount(String dueDateChangeCount) {
		this.dueDateChangeCount = dueDateChangeCount;
	}

	public String getEstHour() {
		return estHour;
	}

	public void setEstHour(String estHour) {
		this.estHour = estHour;
	}

	public int getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

}
