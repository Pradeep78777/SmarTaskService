/**
 * 
 */
package com.sas.util;

/**
 * @author Pradeep Ravichandran
 *
 */
public class MessageConstants {
	// error Messages
	public static final String REQUEST_NULL = "The Given Request is Empty";
	// -- tasks
	public static final String STATUS_IS_NULL = "Status is null";
	public static final String TASK_NAME_IS_NULL = "Task Name is null";
	public static final String PROJECT_IS_NULL = "Project is null";
	public static final String ASSIGNED_TO_IS_NULL = "Assigned To is null";
	public static final String MANAGER_IS_NULL = "Manager is null";
	public static final String DESCRIPTION_IS_NULL = "Description is null";
	public static final String PRIORITY_IS_NULL = "Priority is null";
	public static final String STATUS_NOT_OPEN = "Status is not Open";
	public static final String INVALID_START_DATE = "Invalid Start Date";
	public static final String INVALID_END_DATE = "Invalid End Date";
	public static final String CHECK_DESCRIPTION_LENGTH = "Description is Not Meaning full";
	// suggestion messages
	public static final String SUGGESTION_FOR_REQUEST_NULL = "Please provide the Correct Request";
	// -- tasks
	public static final String SUGGESTION_FOR_STATUS_IS_NULL = "Status Cannot be Null While Creating Task";
	public static final String SUGGESTION_FOR_TASK_NAME_IS_NULL = "Task Name Cannot be Null While Creating Task";
	public static final String SUGGESTION_FOR_PROJECT_IS_NULL = "Project Cannot be Null While Creating Task";
	public static final String SUGGESTION_FOR_ASSIGNED_TO_IS_NULL = "Assigned To Cannot be Null While Creating Task";
	public static final String SUGGESTION_FOR_MANAGER_IS_NULL = "Manager Cannot be Null While Creating Task";
	public static final String SUGGESTION_FOR_DESCRIPTION_IS_NULL = "Description Cannot be Null While Creating Task";
	public static final String SUGGESTION_FOR_PRIORITY_IS_NULL = "Priority Cannot be Null While Creating Task";
	public static final String SUGGESTION_FOR_STATUS_NOT_OPEN = "Status is Open While creating Task";
	public static final String SUGGESTION_FOR_INVALID_START_DATE = "Start Date must be today or above";
	public static final String SUGGESTION_FOR_INVALID_END_DATE = "End Date must be today or above";
	public static final String SUGGESTION_FOR_CHECK_DESCRIPTION_LENGTH = "Description must be greater than 50 characters";

	public static final String TASK_CREATED_SUCCESSFULLY = "Your Task is Successfully Created";
	public static final String TASK_CREATED_FAILED = "Task Created Failed. Please Contact Admin!";
	public static final String TASK_CREATED_BUT_FILE_UPLOAD_FAILED = "Task Created Successfully but File upload Failed";
}
