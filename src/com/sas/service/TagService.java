package com.sas.service;

import java.util.List;

import com.sas.data.TagDAO;
import com.sas.dto.ResponseDTO;
import com.sas.dto.TagDTO;
import com.sas.util.StringUtil;

public class TagService {
	public static TagService tagService = null;

	public static TagService getInstance() {
		if (tagService == null) {
			tagService = new TagService();
		}
		return tagService;
	}

	/**
	 * Method to get all tag Type from table based on given Criteria
	 * 
	 * @author Pradeep Ravichandran
	 * @param tagDto
	 * @return
	 */
	public ResponseDTO getTagTypes(TagDTO tagCriteria) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<TagDTO> tagDTOList = TagDAO.getInstance().getAllTagRecordsByCondition(tagCriteria);
		if (StringUtil.isListNotNullOrEmpty(tagDTOList)) {
			responseDTO.setResult(tagDTOList);
		} else {

		}
		return responseDTO;
	}
}
