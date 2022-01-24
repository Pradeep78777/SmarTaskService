package com.sas.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sas.dto.AccessLogDTO;
import com.sas.util.DBUtil;

public class AccessLogDAO {
	ErrorLogDAO errordao = new ErrorLogDAO();

	/**
	 * method to insert communication access log Records
	 * 
	 * @param pAccesslogDto
	 * @return
	 */
	public boolean insertCommunicationAccessLogRecords(AccessLogDTO pAccesslogDto) {
		Connection conn = null;
		boolean isSuccessful = false;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;
		try {
			conn = DBUtil.getConnection();
			pStmt = conn.prepareStatement("INSERT INTO tbl_access_log(uri,user_id,device_ip,user_agent,content_type,"
					+ "created_on) values(?,?,?,?,?,?)");
			int paramPos = 1;
			pStmt.setString(paramPos++, pAccesslogDto.getUrl());
			pStmt.setString(paramPos++, pAccesslogDto.getUserId());
			pStmt.setString(paramPos++, pAccesslogDto.getDeviceIp());
			pStmt.setString(paramPos++, pAccesslogDto.getUserAgent());
			pStmt.setString(paramPos++, pAccesslogDto.getContentType());
			pStmt.setTimestamp(paramPos++, pAccesslogDto.getCreatedOn());
			isSuccessful = pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			errordao.insertErrorLogRecords("AccessLogDAO", "insertCommunicationAccessLogRecords",
					pAccesslogDto.toString(), "conn not closed", e.getMessage());
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
				errordao.insertErrorLogRecords("AccessLogDAO", "insertCommunicationAccessLogRecords", "conn not closed",
						"conn not closed", e.getMessage());
			}
		}
		return isSuccessful;
	}

	/**
	 * Method to insert user response details
	 * 
	 * @param pAccesslogDto
	 * @return
	 */
	public boolean insertResponseData(AccessLogDTO pAccesslogDto) {
		Connection conn = null;
		boolean isSuccessful = false;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;
		try {
			conn = DBUtil.getConnection();
			pStmt = conn.prepareStatement(
					"INSERT INTO tbl_response_log(url,user_id,content_type,elapsed_time,response_data) values(?,?,?,?,?) ");
			int paramPos = 1;
			pStmt.setString(paramPos++, pAccesslogDto.getUrl());
			pStmt.setString(paramPos++, pAccesslogDto.getUserId());
			pStmt.setString(paramPos++, pAccesslogDto.getContentType());
			pStmt.setLong(paramPos++, pAccesslogDto.getElapsedTime());
			pStmt.setString(paramPos++, pAccesslogDto.getResponseData());
			isSuccessful = pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			errordao.insertErrorLogRecords("AccessLogDAO", "insertResponseData", pAccesslogDto.toString() + "", null,
					e.getMessage());
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
				errordao.insertErrorLogRecords("AccessLogDAO", "insertResponseData", "conn not closed",
						"conn not closed", e.getMessage());
			}
		}
		return isSuccessful;
	}

	/**
	 * Method to inset access log input records
	 * 
	 * @param pAccesslogDto
	 * @return
	 * @author Dinesh
	 */
	public boolean insertAccessLogInputRecords(AccessLogDTO pAccesslogDto) {
		Connection conn = null;
		boolean isSuccessful = false;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;
		try {
			conn = DBUtil.getConnection();
			pStmt = conn.prepareStatement(
					"INSERT INTO tbl_access_log_input(user_id,path,input,created_on,device_ip,user_agent,content_type,domain)"
							+ " values(?,?,?,?,?,?,?,?)");
			int paramPos = 1;
			pStmt.setString(paramPos++, pAccesslogDto.getUserId());
			pStmt.setString(paramPos++, pAccesslogDto.getUrl());
			pStmt.setString(paramPos++, pAccesslogDto.getInput());
			// pStmt.setString(paramPos++, pAccesslogDto.getUser_id());
			pStmt.setTimestamp(paramPos++, pAccesslogDto.getCreatedOn());
			// pStmt.setInt(paramPos++, 1);
			pStmt.setString(paramPos++, pAccesslogDto.getDeviceIp());
			pStmt.setString(paramPos++, pAccesslogDto.getUserAgent());
			pStmt.setString(paramPos++, pAccesslogDto.getContentType());
			pStmt.setString(paramPos++, pAccesslogDto.getDomain());
			isSuccessful = pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			errordao.insertErrorLogRecords("AccessLogDAO", "insertAccessLogInputRecords", pAccesslogDto.toString(),
					"conn not closed", e.getMessage());
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
				errordao.insertErrorLogRecords("AccessLogDAO", "insertAccessLogInputRecords", "conn not closed",
						"conn not closed", e.getMessage());
			}
		}
		return isSuccessful;
	}
}
