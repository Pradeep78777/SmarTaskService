package com.sas.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import com.sas.util.DBUtil;

/**
 * ErrorLog - DAO
 * 
 * @Author Pradeep.R
 */

public class ErrorLogDAO {

	static String qry = null;
	java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());

	/**
	 * Method used to insert record to table
	 * 
	 * @Author Pradeep.R
	 * @param pErrorLogDto
	 * @{link ErrorLogDTO}
	 * @return @{link boolean}
	 * 
	 */
	public boolean insertErrorLogRecords(String className, String methodName, String parameter, String cust_id,
			String errorMsg) {
		Connection conn = null;
		boolean isSuccessful = false;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;
		try {
			conn = DBUtil.getConnection();
			qry = " INSERT INTO tbl_error_log(class_name,method_name,parameter,error_msg,created_on, "
					+ " created_by,updated_by,updated_on) VALUES (?,?,?,?,?,?,?,?) ";
			pStmt = conn.prepareStatement(qry);
			int paramPos = 1;
			pStmt.setString(paramPos++, className);
			pStmt.setString(paramPos++, methodName);
			pStmt.setString(paramPos++, parameter);
			pStmt.setString(paramPos++, StringUtils.abbreviate(errorMsg, 500));
			pStmt.setTimestamp(paramPos++, timestamp);
			pStmt.setString(paramPos++, cust_id);
			pStmt.setString(paramPos++, cust_id);
			pStmt.setTimestamp(paramPos++, timestamp);
			pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rSet != null) {
					rSet.close();
				}
				if (pStmt != null) {
					pStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isSuccessful;
	}

//	/**
//	 * method to get all error records
//	 * 
//	 * @Author Pradeep.R
//	 * @author pradeep.R
//	 * @return
//	 */
//	public List<ErrorLogDTO> getAllErrorLogRecords() {
//		Connection conn = null;
//		List<ErrorLogDTO> pErrorLogDtoList = new ArrayList<>();
//		PreparedStatement pStmt = null;
//		ResultSet rSet = null;
//		try {
//			conn = DBUtil.getConnection();
//
//			String qry = " SELECT class_name,method_name,parameter,error_msg,created_on,created_by,updated_on, "
//					+ " updated_by,active_status FROM tbl_error_log where active_status = ? ";
//			pStmt = conn.prepareStatement(qry);
//			int active_status = 1;
//			pStmt.setInt(1, active_status);
//			rSet = pStmt.executeQuery();
//			if (rSet != null) {
//				while (rSet.next()) {
//					ErrorLogDTO dto = new ErrorLogDTO();
//					dto.setClassName(rSet.getString("class_name"));
//					dto.setMethodName(rSet.getString("method_name"));
//					dto.setParameter(rSet.getString("parameter"));
//					dto.setErrorMsg(rSet.getString("error_msg"));
//					dto.setCreated_on(rSet.getDate("created_on"));
//					dto.setCreated_by(rSet.getString("created_by"));
//					dto.setUpdated_on(rSet.getDate("updated_on"));
//					dto.setUpdated_by(rSet.getString("updated_by"));
//					dto.setActive_status(rSet.getInt("active_status"));
//					pErrorLogDtoList.add(dto);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rSet != null) {
//					rSet.close();
//				}
//				if (pStmt != null) {
//					pStmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		return pErrorLogDtoList;
//	}
}
