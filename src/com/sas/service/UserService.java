package com.sas.service;

import com.sas.Cache.CacheController;
import com.sas.data.UserDAO;
import com.sas.dto.EmployeeDetailsDTO;
import com.sas.dto.ResponseDTO;
import com.sas.util.AppConstants;
import com.sas.util.CommonMethod;
import com.sas.util.StringUtil;
import com.sas.util.TokenAuthModule;

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
			// Null check username and password
			if (StringUtil.isNotNullOrEmpty(pDto.getEmail()) && StringUtil.isNotNullOrEmpty(pDto.getPassword())) {
				// Change password as encrypted
				pDto.setPassword(CommonMethod.PasswordEncryption(pDto.getPassword()));
				// Get the User details from data base
				EmployeeDetailsDTO employeeDetails = UserDAO.getInstance().getEmployeeDetails(pDto);
				if (employeeDetails != null) {
					// Check User is locked or not if not locked send success or
					// send error response
					if (employeeDetails.getIsLocked() == 0) {
						// Create new session id and store it in cache
						String newSid = CommonMethod.random256Key();
						CacheController.getUser256Cache().put(String.valueOf(employeeDetails.getEmpId()), newSid);
						TokenAuthModule.storeTokenCache(newSid, String.valueOf(employeeDetails.getEmpId()));
						// Store the device ip for further process
						CacheController.getUserDeviceIp().put(String.valueOf(employeeDetails.getEmpId()),
								pDto.getDeviceIp());
						employeeDetails.setUserSessionId(newSid);
						response.setStatus(AppConstants.SUCCESS_STATUS);
						response.setMessage(AppConstants.SUCCESS);
						response.setReason(AppConstants.LOGIN_SUCESS);
						response.setResult(employeeDetails);
					} else {
						response.setStatus(AppConstants.FAILED_STATUS);
						response.setMessage(AppConstants.FAILED);
						response.setReason(AppConstants.USER_BLOCKED);
					}
				} else {
					response = checkWrongEntryCount(pDto.getEmail());
				}
			} else {
				response.setStatus(AppConstants.FAILED_STATUS);
				response.setMessage(AppConstants.FAILED);
				response.setReason(AppConstants.INVALID_PARAMETER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * Method to update the wrong entry
	 * 
	 * @author GOWRI SANKAR R
	 * @param email
	 */
	private ResponseDTO checkWrongEntryCount(String email) {
		ResponseDTO response = new ResponseDTO();
		// Get User Details By employee Id or employee email
		EmployeeDetailsDTO employeeDetailsWithoutPass = UserDAO.getInstance().getEmployeeDetailsWithoutPass(email);
		if (employeeDetailsWithoutPass != null && employeeDetailsWithoutPass.getEmpId() > 0) {
			if (employeeDetailsWithoutPass.getWrongEntry() > 3 && employeeDetailsWithoutPass.getIsLocked() > 0) {
				response.setStatus(AppConstants.FAILED_STATUS);
				response.setMessage(AppConstants.FAILED);
				response.setReason(AppConstants.USER_BLOCKED);
			} else {
				if (employeeDetailsWithoutPass.getWrongEntry() < 3) {
					int wrongEntryCount = employeeDetailsWithoutPass.getWrongEntry() + 1;
					if (wrongEntryCount >= 3) {
						UserDAO.getInstance().updateIdAsBlocked(employeeDetailsWithoutPass.getEmpId(), true);
						response.setStatus(AppConstants.FAILED_STATUS);
						response.setMessage(AppConstants.FAILED);
						response.setReason(AppConstants.USER_BLOCKED);
					} else {
						UserDAO.getInstance().updateWrongEntryCount(employeeDetailsWithoutPass.getEmpId(),
								employeeDetailsWithoutPass.getWrongEntry() + 1);
						response.setStatus(AppConstants.FAILED_STATUS);
						response.setMessage(AppConstants.FAILED);
						response.setReason(AppConstants.PASSWORD_WRONG);
					}
				} else {
					UserDAO.getInstance().updateIdAsBlocked(employeeDetailsWithoutPass.getEmpId(), true);
					response.setStatus(AppConstants.FAILED_STATUS);
					response.setMessage(AppConstants.FAILED);
					response.setReason(AppConstants.USER_BLOCKED);
				}
			}
		} else {
			response.setStatus(AppConstants.FAILED_STATUS);
			response.setMessage(AppConstants.FAILED);
			response.setReason(AppConstants.EMP_ID_INVALID);
		}
		return response;
	}

}
