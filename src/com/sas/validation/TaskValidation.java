/**
 * 
 */
package com.sas.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sas.dto.TaskDTO;
import com.sas.dto.ValidationDTO;
import com.sas.util.DateUtil;
import com.sas.util.MessageConstants;
import com.sas.util.StringUtil;
import com.sas.util.TagConstants;

/**
 * @author Pradeep Ravichandran
 *
 */
public class TaskValidation {
	public static TaskValidation taskValidation = null;

	public static TaskValidation getInstance() {
		if (taskValidation == null) {
			taskValidation = new TaskValidation();
		}
		return taskValidation;
	}

	/**
	 * Method to validate creating Task
	 * 
	 * @return
	 */
	public List<ValidationDTO> validateCreateTask(TaskDTO taskCriteria) {
		List<ValidationDTO> validationDTOs = null;
		ValidationDTO validation = null;
		if (taskCriteria != null) {
			validationDTOs = new ArrayList<ValidationDTO>();
			List<ValidationDTO> nullValidationList = checkNullValidation(taskCriteria);
			if (StringUtil.isListNotNullOrEmpty(nullValidationList)) {
				// check Task status
				if (!StringUtil.isEqual(taskCriteria.getTaskStatus(), TagConstants.TASK_STATUS_OPEN)) {
					validation = new ValidationDTO();
					validation.setDesc(MessageConstants.STATUS_NOT_OPEN);
					validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_STATUS_NOT_OPEN);
					validationDTOs.add(validation);
				}
				// check start date
				if (taskCriteria.getStartDate() != null) {
					Date startDate = taskCriteria.getStartDate();
					Date todayDate = DateUtil.getTodayDate();
					if (startDate.before(todayDate)) {
						validation = new ValidationDTO();
						validation.setDesc(MessageConstants.INVALID_START_DATE);
						validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_INVALID_START_DATE);
						validationDTOs.add(validation);
					}
				}
				// check start date
				if (taskCriteria.getEndDate() != null) {
					Date endDate = taskCriteria.getEndDate();
					Date todayDate = DateUtil.getTodayDate();
					if (endDate.before(todayDate)) {
						validation = new ValidationDTO();
						validation.setDesc(MessageConstants.INVALID_END_DATE);
						validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_INVALID_END_DATE);
						validationDTOs.add(validation);
					}
				}
				// check description length
				if (taskCriteria.getDescription().trim().length() >= 50) {
					validation = new ValidationDTO();
					validation.setDesc(MessageConstants.CHECK_DESCRIPTION_LENGTH);
					validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_CHECK_DESCRIPTION_LENGTH);
					validationDTOs.add(validation);

				}
			}
		} else {
			validationDTOs = new ArrayList<ValidationDTO>();
			validation = new ValidationDTO();
			validation.setDesc(MessageConstants.REQUEST_NULL);
			validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_REQUEST_NULL);
			validationDTOs.add(validation);
		}
		return validationDTOs;
	}

	/**
	 * Method to check Null Values
	 * 
	 * @author prade
	 * 
	 * @return
	 */
	private List<ValidationDTO> checkNullValidation(TaskDTO taskCriteria) {
		List<ValidationDTO> nullValidationList = new ArrayList<ValidationDTO>();
		ValidationDTO validation = null;
		if (StringUtil.isNullOrEmpty(taskCriteria.getTaskStatus())) {
			validation = new ValidationDTO();
			validation.setDesc(MessageConstants.STATUS_IS_NULL);
			validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_STATUS_IS_NULL);
			nullValidationList.add(validation);
		}
		if (StringUtil.isNullOrEmpty(taskCriteria.getTaskName())) {
			validation = new ValidationDTO();
			validation.setDesc(MessageConstants.TASK_NAME_IS_NULL);
			validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_TASK_NAME_IS_NULL);
			nullValidationList.add(validation);
		}
		if (StringUtil.isNullOrEmpty(taskCriteria.getDescription())) {
			validation = new ValidationDTO();
			validation.setDesc(MessageConstants.DESCRIPTION_IS_NULL);
			validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_DESCRIPTION_IS_NULL);
			nullValidationList.add(validation);
		}
		if (taskCriteria.getProjectId() == 0 || taskCriteria.getProjectId() < 0) {
			validation = new ValidationDTO();
			validation.setDesc(MessageConstants.PROJECT_IS_NULL);
			validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_PROJECT_IS_NULL);
			nullValidationList.add(validation);
		}
		if (taskCriteria.getAssignedTo() == 0 || taskCriteria.getAssignedTo() < 0) {
			validation = new ValidationDTO();
			validation.setDesc(MessageConstants.ASSIGNED_TO_IS_NULL);
			validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_ASSIGNED_TO_IS_NULL);
			nullValidationList.add(validation);
		}
		if (StringUtil.isNullOrEmpty(taskCriteria.getPriority())) {
			validation = new ValidationDTO();
			validation.setDesc(MessageConstants.PRIORITY_IS_NULL);
			validation.setSuggestedAction(MessageConstants.SUGGESTION_FOR_PRIORITY_IS_NULL);
			nullValidationList.add(validation);

		}
		return nullValidationList;
	}
}
