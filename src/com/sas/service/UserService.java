package com.sas.service;

import com.sas.data.UserDAO;
import com.sas.dto.EmployeeDetailsDTO;
import com.sas.dto.ResponseDTO;
import com.sas.util.CommonMethod;

public class UserService {
	public static UserService userService = null;

	public static UserService getInstance() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}

	/**
	 * Method for user login
	 * 
	 * @author GOWRI SANKAR R
	 * @param pDto
	 * @return
	 */
	public ResponseDTO userLogin(EmployeeDetailsDTO pDto) {
		ResponseDTO response = new ResponseDTO();
		try {
			// Change password as encrypted
			pDto.setPassword(CommonMethod.PasswordEncryption(pDto.getPassword()));
			// Get the User details from data base
			EmployeeDetailsDTO employeeDetails = UserDAO.getInstance().getEmployeeDetails(pDto);
			if (employeeDetails != null) {

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
