/**
 * 
 */
package com.sas.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sas.data.TaskDAO;
import com.sas.dto.ResponseDTO;
import com.sas.dto.TaskDTO;
import com.sas.dto.ValidationDTO;
import com.sas.util.AppConstants;
import com.sas.util.MessageConstants;
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
	public ResponseDTO createTask(List<FormDataBodyPart> bodyParts, String task) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TaskDTO taskCriteria = mapper.readValue(task, TaskDTO.class);
			List<ValidationDTO> validationDtoList = TaskValidation.getInstance().validateCreateTask(taskCriteria);
			if (StringUtil.isListNullOrEmpty(validationDtoList)) {
				int taskId = TaskDAO.getInstance().createTask(taskCriteria);
				if (taskId > 0) {
					if (bodyParts != null) {
						int updateCount = saveFile(bodyParts, taskId, taskCriteria);
						if (updateCount > 0) {
							responseDTO.setStatus(AppConstants.SUCCESS_STATUS);
							responseDTO.setMessage(AppConstants.SUCCESS);
							responseDTO.setReason(MessageConstants.TASK_CREATED_SUCCESSFULLY);
						} else {
							responseDTO.setStatus(AppConstants.FAILED_STATUS);
							responseDTO.setMessage(AppConstants.FAILED);
							responseDTO.setReason(MessageConstants.TASK_CREATED_BUT_FILE_UPLOAD_FAILED);
						}
					} else {
						responseDTO.setStatus(AppConstants.SUCCESS_STATUS);
						responseDTO.setMessage(AppConstants.SUCCESS);
						responseDTO.setReason(MessageConstants.TASK_CREATED_SUCCESSFULLY);
					}
				} else {
					responseDTO.setStatus(AppConstants.FAILED_STATUS);
					responseDTO.setMessage(AppConstants.FAILED);
					responseDTO.setReason(MessageConstants.TASK_CREATED_FAILED);
				}
			} else {
				responseDTO.setStatus(AppConstants.FAILED_STATUS);
				responseDTO.setMessage(AppConstants.FAILED);
				responseDTO.setErrrorResult(validationDtoList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseDTO;
	}

	/**
	 * Method to save the uploaded Document for the particular task Id
	 * 
	 * @author Pradeep Ravichandran
	 * @param bodyParts
	 * @return
	 */
	private int saveFile(List<FormDataBodyPart> bodyParts, int taskId, TaskDTO taskCriteria) {
		try {
			for (FormDataBodyPart formDataBodyPart : bodyParts) {
				FormDataContentDisposition contentDisposition = formDataBodyPart.getFormDataContentDisposition();
				if (contentDisposition.getFileName() != null) {
					String fileName = System.currentTimeMillis() + "_" + contentDisposition.getFileName();
					InputStream is = formDataBodyPart.getEntityAs(InputStream.class);
					int read = 0;
					String filePath = AppConstants.PROJ_DIR + AppConstants.UPLOADS_DIR + taskId + "\\";
					File dir = new File(filePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					byte[] bytes = new byte[1024];
					OutputStream out = new FileOutputStream(dir + "\\" + fileName);
					while ((read = is.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}
					out.flush();
					out.close();
					taskCriteria.setTaskdocLocation(AppConstants.PROJ_DIR + AppConstants.UPLOADS_DIR + taskId);
					taskCriteria.setFileURL(AppConstants.UPLOAD_URL + taskId + fileName);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaskDAO.getInstance().updatefile(taskCriteria, taskId);
	}
}
