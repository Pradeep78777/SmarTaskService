package com.sas.data;

public class UserDAO {
	public static UserDAO userDAO = null;

	public static UserDAO getInstance() {
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}
}
