/**
 * 
 */
package com.sas.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sas.dto.TaskDTO;
import com.sas.util.DBUtil;

/**
 * @author Pradeep Ravichandran
 *
 */
public class TaskDAO {
	private Connection conn = null;
	public static TaskDAO taskDao = null;

	public static TaskDAO getInstance() {
		if (taskDao == null) {
			taskDao = new TaskDAO();
		}
		return taskDao;
	}

	/**
	 * Method to create task for the particular Employee
	 * 
	 * @author Pradeep Ravichandran
	 * @param taskDTO
	 * @return
	 */
	public int createTask(TaskDTO taskCriteria) {
		PreparedStatement pStmnt = null;
		ResultSet rset = null;
		int taskId = 0;
		try {

			conn = DBUtil.getConnection();
			pStmnt = conn.prepareStatement(
					" INSERT INTO tbl_task (emp_id,project_id,task_name,description,priority,start_date,end_date, "
							+ " task_status,due_date_change_count,est_hour,assigned_to,task_manager,created_by,updated_by) "
							+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					Statement.RETURN_GENERATED_KEYS);
			int parampos = 1;
			pStmnt.setInt(parampos++, taskCriteria.getEmpId());
			pStmnt.setInt(parampos++, taskCriteria.getProjectId());
			pStmnt.setString(parampos++, taskCriteria.getTaskName());
			pStmnt.setString(parampos++, taskCriteria.getDescription());
			pStmnt.setString(parampos++, taskCriteria.getPriority());
			pStmnt.setDate(parampos++, taskCriteria.getStartDate());
			pStmnt.setDate(parampos++, taskCriteria.getEndDate());
			pStmnt.setString(parampos++, taskCriteria.getTaskStatus());
			pStmnt.setString(parampos++, taskCriteria.getDueDateChangeCount());
			pStmnt.setString(parampos++, taskCriteria.getEstHour());
			pStmnt.setInt(parampos++, taskCriteria.getAssignedTo());
			pStmnt.setString(parampos++, taskCriteria.getManager());
			pStmnt.setInt(parampos++, taskCriteria.getAssignedTo());
			pStmnt.setInt(parampos++, taskCriteria.getAssignedTo());
			pStmnt.executeUpdate();
			rset = pStmnt.getGeneratedKeys();
			if (rset.next()) {
				taskId = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmnt != null) {
					pStmnt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return taskId;
	}

	/**
	 * Method to update the task table when file is uploaded
	 * 
	 * @param taskCriteria
	 * @param taskId
	 * @return
	 */
	public int updatefile(TaskDTO taskCriteria, int taskId) {
		Connection conn = null;
		PreparedStatement pStmnt = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			pStmnt = conn.prepareStatement("update tbl_task set file_url = ? , taskdoc_location = ? where id = ?");
			int parampos = 1;
			pStmnt.setString(parampos++, taskCriteria.getFileURL());
			pStmnt.setString(parampos++, taskCriteria.getTaskdocLocation());
			pStmnt.setInt(parampos++, taskId);
			count = pStmnt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmnt != null) {
					pStmnt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}

}
