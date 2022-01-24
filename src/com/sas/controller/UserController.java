package com.sas.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.sas.dto.AccessLogDTO;
import com.sas.dto.EmployeeDetailsDTO;
import com.sas.dto.ResponseDTO;
import com.sas.filter.Secured;
import com.sas.service.UserService;
import com.sas.util.CommonMethod;

@Path("/user")
public class UserController {
	AccessLogDTO accessLog = new AccessLogDTO();
	String contentType = "content-type";
	@Context
	HttpServletRequest request;
	java.sql.Timestamp created_on = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());

	@Path("/login")
	@POST
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDTO userLogin(@Context ContainerRequestContext requestContext, EmployeeDetailsDTO pDto) {
		accessLog.setDeviceIp(request.getRemoteAddr());
		accessLog.setUserAgent(request.getHeader("user-agent"));
		accessLog.setUrl(requestContext.getUriInfo().getPath());
		accessLog.setCreatedOn(created_on);
		// accessLog.setDomain(new
		// URL(request.getRequestURL().toString()).getHost());
		CommonMethod.inputAccessLogDetails(accessLog, pDto, String.valueOf(pDto.getEmpId()));
		ResponseDTO response = UserService.getInstance().userLogin(pDto);
		return response;
	}
}
