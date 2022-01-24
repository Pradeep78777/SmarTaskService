/**
 * 
 */
package com.sas.dto;

/**
 * @author Pradeep Ravichandran
 *
 */
public class ValidationDTO {
	private String errorType;
	private String desc;
	private String suggestedAction;

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSuggestedAction() {
		return suggestedAction;
	}

	public void setSuggestedAction(String suggestedAction) {
		this.suggestedAction = suggestedAction;
	}

}
