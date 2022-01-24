package com.sas.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sas.dto.TagDTO;
import com.sas.util.DBUtil;
import com.sas.util.StringUtil;

public class TagDAO {
	private Connection conn = null;
	public static TagDAO tagDao = null;

	public static TagDAO getInstance() {
		if (tagDao == null) {
			tagDao = new TagDAO();
		}
		return tagDao;
	}

	/**
	 * Method to get all tag Type from table based on given Criteria
	 * 
	 * @author Pradeep Ravichandran
	 * @param tagDto
	 * @return
	 */

	public List<TagDTO> getAllTagRecordsByCondition(TagDTO tagCriteria) {
		List<TagDTO> tagLists = null;
		TagDTO dto = null;
		try {
			conn = DBUtil.getConnection();
			StringBuffer queryString = new StringBuffer();
			queryString.append("SELECT id,tag_type,name,order_by FROM tag ");
			List<String> conditions = getConditon(tagCriteria);
			if (!conditions.isEmpty()) {
				queryString.append(" where " + StringUtil.convertConditionsListToString(conditions));
				queryString.append(" order by order_by asc ");
			}
			PreparedStatement pStmt = conn.prepareStatement(queryString.toString());
			ResultSet rSet = pStmt.executeQuery();
			if (rSet != null) {
				tagLists = new ArrayList<TagDTO>();
				while (rSet.next()) {
					dto = new TagDTO();
					dto.setId(rSet.getInt("id"));
					dto.setTagType(rSet.getString("tag_type"));
					dto.setName(rSet.getString("name"));
					dto.setOrderBy(rSet.getInt("order_by"));
					tagLists.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tagLists;
	}

	/**
	 * Method to form the where condition for the tag table
	 * 
	 * @author Pradeep Ravichandran
	 * @param tagCriteria
	 * @return
	 */
	private List<String> getConditon(TagDTO tagCriteria) {
		List<String> conditions = new ArrayList<String>();
		conditions.add(" active_status = 1 ");
		if (StringUtil.isNotNullOrEmpty(tagCriteria.getTagType())) {
			conditions.add(" tag_type = " + tagCriteria.getTagType() + " ");
		}
		if (StringUtil.isListNotNullOrEmpty(tagCriteria.getTagTypes())) {
			conditions.add(" tag_type in " + StringUtil.getInSearch(tagCriteria.getTagTypes()) + " ");
		}
		return conditions;
	}
}
