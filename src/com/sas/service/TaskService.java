/**
 * 
 */
package com.sas.service;

import java.util.List;

import com.sas.dto.ResponseDTO;
import com.sas.dto.TaskDTO;
import com.sas.dto.ValidationDTO;
import com.sas.util.AppConstants;
import com.sas.util.StringUtil;
import com.sas.validation.TaskValidation;

/**
 * @author Pradeep Ravichandran
 *
 */
public class TaskService {
	public static TaskService taskService = null;

	public static TaskService getInstance() {
		if (taskService == null) {
			taskService = new TaskService();
		}
		return taskService;
	}

	/**
	 * Method to create task for the particular Employee
	 * 
	 * @author Pradeep Ravichandran
	 * @param taskDTO
	 * @return
	 */
	public ResponseDTO createTask(TaskDTO taskCriteria) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<ValidationDTO> validationDtoList = TaskValidation.getInstance().validateCreateTask(taskCriteria);
		if (StringUtil.isListNullOrEmpty(validationDtoList)) {

		} else {
			responseDTO.setStatus(AppConstants.FAILED_STATUS);
			responseDTO.setMessage(AppConstants.FAILED);
			responseDTO.setErrrorResult(validationDtoList);
		}
		return responseDTO;
	}
}
