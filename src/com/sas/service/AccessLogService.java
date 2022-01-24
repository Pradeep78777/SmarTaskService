package com.sas.service;

import com.sas.data.AccessLogDAO;
import com.sas.data.ErrorLogDAO;
import com.sas.dto.AccessLogDTO;

public class AccessLogService {

	ErrorLogDAO logDao = new ErrorLogDAO();

	public boolean insertCommunicationAccessLogRecords(AccessLogDTO pAccesslogDto) {

		try {
			// System.out.println("new
			// query------"+Calendar.getInstance().getTime());
			AccessLogDAO pAccessLogDAO = new AccessLogDAO();
			boolean isSuccessful = pAccessLogDAO.insertCommunicationAccessLogRecords(pAccesslogDto);
			return isSuccessful;
		} catch (Exception e) {
			logDao.insertErrorLogRecords("AccessLogService", "insertCommunicationAccessLogRecords",
					pAccesslogDto.toString() + "", null, e.getMessage());
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * Method to inset access log input records
	 * 
	 * @param pAccesslogDto
	 * @return
	 * @author Dinesh
	 */
	public boolean insertAccessLogInputRecords(AccessLogDTO pAccesslogDto) {
		try {
			AccessLogDAO pAccessLogDAO = new AccessLogDAO();
			boolean isSuccessful = pAccessLogDAO.insertAccessLogInputRecords(pAccesslogDto);
			return isSuccessful;
		} catch (Exception e) {
			logDao.insertErrorLogRecords("AccessLogService", "insertAccessLogInputRecords",
					pAccesslogDto.toString() + "", null, e.getMessage());
			e.printStackTrace();
		}
		return false;

	}

	public boolean insertResponseData(AccessLogDTO pAccesslogDto) {
		try {
			AccessLogDAO pAccessLogDAO = new AccessLogDAO();
			boolean isSuccessful = pAccessLogDAO.insertResponseData(pAccesslogDto);
			return isSuccessful;
		} catch (Exception e) {
			logDao.insertErrorLogRecords("AccessLogService", "insertCommunicationAccessLogRecords",
					pAccesslogDto.toString() + "", null, e.getMessage());
			e.printStackTrace();
		}
		return false;

	}
}
