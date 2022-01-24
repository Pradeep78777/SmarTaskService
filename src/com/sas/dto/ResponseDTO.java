package com.sas.dto;

public class ResponseDTO {
	private int status;
	private String message;
	private String reason;
	private Object result;
	private Object errrorResult;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getErrrorResult() {
		return errrorResult;
	}

	public void setErrrorResult(Object errrorResult) {
		this.errrorResult = errrorResult;
	}

}
