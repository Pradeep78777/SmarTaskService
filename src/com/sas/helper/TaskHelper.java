/**
 * 
 */
package com.sas.helper;

/**
 * @author Pradeep Ravichandran
 *
 */
public class TaskHelper {
	public static TaskHelper taskHelper = null;

	public static TaskHelper getInstance() {
		if (taskHelper == null) {
			taskHelper = new TaskHelper();
		}
		return taskHelper;
	}
}
