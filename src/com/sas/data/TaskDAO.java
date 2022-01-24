/**
 * 
 */
package com.sas.data;

import java.sql.Connection;

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

}
