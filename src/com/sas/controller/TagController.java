package com.sas.controller;

import javax.ws.rs.Path;

import com.sas.dto.ResponseDTO;
import com.sas.dto.TagDTO;
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
	@Path("/getTagValues")
	public ResponseDTO getTagTypes(TagDTO tagCriteria) {
		ResponseDTO responseDTO = TagService.getInstance().getTagTypes(tagCriteria);
		return responseDTO;
	}
}
