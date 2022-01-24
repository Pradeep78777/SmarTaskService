package com.sas.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sas.dto.ResponseDTO;
import com.sas.dto.TagDTO;
import com.sas.filter.Secured;
import com.sas.service.TagService;

@Path("/tag")
public class TagController {
	/**
	 * Method to get all tag Type from table based on given Criteria
	 * 
	 * @author Pradeep Ravichandran
	 * @param tagDto
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getTagValues")
	@Secured
	public ResponseDTO getTagTypes(TagDTO tagCriteria) {
		return TagService.getInstance().getTagTypes(tagCriteria);
	}
}
