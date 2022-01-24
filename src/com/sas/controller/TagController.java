package com.sas.controller;

import javax.ws.rs.Path;

import com.sas.dto.TagDTO;

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
	public void getTagTypes(TagDTO tagCriteria) {

	}
}
