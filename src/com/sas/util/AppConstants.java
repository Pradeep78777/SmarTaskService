package com.sas.util;

public class AppConstants {

	// Sucess and failed message
	public static final String SUCCESS = "Success";
	public static final String FAILED = "Failed";
	public static final int SUCCESS_STATUS = 1;
	public static final int FAILED_STATUS = 0;

	// Login Message
	public static final String LOGIN_SUCESS = "Login sucessfull";
	public static final String PASSWORD_WRONG = "Given User name or password is wrong";
	public static final String EMP_ID_INVALID = "Employee Id or email is Invalid";
	public static final String USER_BLOCKED = "User is Blocked Please contact Administrator";
	public static final String INVALID_PARAMETER = "Given Parameter's are invalid";

	// For Authorization Filter

	public static final String SECURED_METHODS = "securedMethods";
	public static final String UNAUTHORIZED = "Unauthorized";

	// For File Upload

	public static final String PROJ_DIR = "C://Stoneage//TaskManager//";
	public static final String UPLOADS_DIR = "file//uploads//";
	public static final String SITE_URL = "http://13.59.180.22//";
	public static final String UPLOAD_URL = SITE_URL + UPLOADS_DIR;
}
