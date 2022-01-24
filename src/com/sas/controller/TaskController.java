/**
 * 
 */
package com.sas.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.sas.dto.ResponseDTO;
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
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createTask")
	@Secured
	public ResponseDTO createTask(@Context ContainerRequestContext requestContext,
			@FormDataParam("file") List<FormDataBodyPart> bodyParts, @FormDataParam("task") String task) {
		return TaskService.getInstance().createTask(bodyParts, task);
	}

}
