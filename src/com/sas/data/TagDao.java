package com.sas.data;

public class TagDao {
	public static TagDao tagDao = null;

	public static TagDao getInstance() {
		if (tagDao == null) {
			tagDao = new TagDao();
		}
		return tagDao;
	}

}
