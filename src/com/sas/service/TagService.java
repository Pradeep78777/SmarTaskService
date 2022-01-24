package com.sas.service;

public class TagService {
	public static TagService tagService = null;

	public static TagService getInstance() {
		if (tagService == null) {
			tagService = new TagService();
		}
		return tagService;
	}
}
