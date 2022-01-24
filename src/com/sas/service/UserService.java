package com.sas.service;

import com.sas.dto.EmployeeDetailsDTO;
import com.sas.dto.ResponseDTO;

public class UserService {
	public static UserService userService = null;

	public static UserService getInstance() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}

	public ResponseDTO userLogin(EmployeeDetailsDTO pDto) {
		// TODO Auto-generated method stub
		return null;
	}
}
