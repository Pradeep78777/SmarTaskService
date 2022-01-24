/**
 * 
 */
package com.sas.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sas.dto.ResponseDTO;
import com.sas.dto.TaskDTO;
import com.sas.filter.Secured;
import com.sas.service.TaskService;

/**
 * @author Pradeep Ravichandran
 *
 */
@Path("/task")
public class TaskController {
	/**
	 * Method to create task for the particular Employee
	 * 
	 * @author Pradeep Ravichandran
	 * @param taskDTO
	 * @return
	 */
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createTask")
	@Secured
	public ResponseDTO createTask(TaskDTO taskCriteria) {
		return TaskService.getInstance().createTask(taskCriteria);
	}

}
