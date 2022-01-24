package com.sas.dto;

import java.util.List;

public class TagDTO {
	private int id;
	private String tagType;
	private List<String> tagTypes;
	private String name;
	private int orderBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public List<String> getTagTypes() {
		return tagTypes;
	}

	public void setTagTypes(List<String> tagTypes) {
		this.tagTypes = tagTypes;
	}

}
