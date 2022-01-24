package com.sas.dto;

public class AccessLogDto {
	private String url;
	private String userId;
	private String deviceIp;
	private String userAgent;
	private String contentType;
	private String authenticateToken;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getAuthenticateToken() {
		return authenticateToken;
	}

	public void setAuthenticateToken(String authenticateToken) {
		this.authenticateToken = authenticateToken;
	}

}
