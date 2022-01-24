package com.sas.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sas.dto.EmployeeDetailsDTO;
import com.sas.util.DBUtil;

public class UserDAO {
	public static UserDAO userDAO = null;

	public static UserDAO getInstance() {
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}

	/**
	 * Get employee details for login details
	 * 
	 * @author GOWRI SANKAR R
	 * @param pDto
	 * @return
	 */
	public EmployeeDetailsDTO getEmployeeDetails(EmployeeDetailsDTO pDto) {
		EmployeeDetailsDTO response = null;
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT id, name, email, mobile, password, role, email_verified, mobile_verified, password_updated_on, wrong_entry, is_locked "
					+ " FROM tbl_emp_details WHERE (email = ? or id = ?) and password = ?  and active_status = ? ";
			pStmt = conn.prepareStatement(query);
			int paramPos = 1;
			pStmt.setString(paramPos++, pDto.getEmail());
			if (pDto.getEmail().matches("[0-9]+")) {
				pStmt.setInt(paramPos++, Integer.valueOf(pDto.getEmail()));
			} else {
				pStmt.setInt(paramPos++, 0);
			}
			pStmt.setString(paramPos++, pDto.getPassword());
			pStmt.setInt(paramPos++, 1);
			rSet = pStmt.executeQuery();
			if (rSet != null && rSet.next()) {
				while (rSet.next()) {
					response = new EmployeeDetailsDTO();
					response.setEmpId(rSet.getInt("id"));
					response.setName(rSet.getString("name"));
					response.setEmail(rSet.getString("email"));
					response.setMobile(rSet.getString("mobile"));
					response.setRole(rSet.getInt("role"));
					response.setEmailVerified(rSet.getInt("email_verified"));
					response.setMobileVerified(rSet.getString("mobile_verified"));
					response.setPasswordUpdatedOn(rSet.getString("password_updated_on"));
					response.setWrongEntry(rSet.getInt("wrong_entry"));
					response.setIsLocked(rSet.getInt("is_locked"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
