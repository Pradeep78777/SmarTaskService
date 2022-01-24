package com.sas.helper;

public class Taghelper {
	public static Taghelper tagHelper = null;

	public static Taghelper getInstance() {
		if (tagHelper == null) {
			tagHelper = new Taghelper();
		}
		return tagHelper;
	}

}
