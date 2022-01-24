package com.sas.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import org.slf4j.MDC;

import com.sas.data.ErrorLogDAO;
import com.sas.dto.AccessLogDTO;
import com.sas.service.AccessLogService;
import com.sas.util.AppConstants;
import com.sas.util.CSEnvVariables;
import com.sas.util.TokenAuthModule;

/**
 * Authentication filter to log user request
 * 
 * @author Pradeep K
 *
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter, ContainerResponseFilter {

	public static void main(String args[]) throws IOException {
		ContainerRequestContext requestContext1 = null;
		AuthenticationFilter a = new AuthenticationFilter();
		a.filter(requestContext1);
	}

	String contentType = "content-type";
	@Context
	HttpServletRequest request;
	@Context
	private Providers providers;
	ErrorLogDAO logDAO = new ErrorLogDAO();

	/**
	 * Response filter to capture user request details
	 * 
	 * @param requestContext
	 * @throws IOException
	 * @author sankar
	 * @on 14-02-2018
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader = null;
		try {
			java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
			MDC.put("start-time", String.valueOf(System.currentTimeMillis()));
			AccessLogDTO dto = new AccessLogDTO();
			HashMap<String, String> map = new HashMap<String, String>();
			authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
			// Get the HTTP Authorization header from the request
			String path = requestContext.getUriInfo().getPath();
			dto.setUrl(path);
			String ip = request.getRemoteAddr();
			dto.setDeviceIp(ip);
			dto.setCreatedOn(timestamp);
			String user_agent = request.getHeader("user-agent");
			dto.setUserAgent(user_agent);
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = request.getHeader(key);
				map.put(key, value);
				if (key.equalsIgnoreCase(contentType)) {
					dto.setContentType(value);
				}
			}
			if (path.contains("/")) {
				path = path.split("/")[path.split("/").length - 1];
			}
			if (CSEnvVariables.getMethodNames(AppConstants.SECURED_METHODS).contains(path)) {
				// Check if the HTTP Authorization header is present and
				// formatted correctly
				if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
					throw new NotAuthorizedException("Authorization header must be provided");
				}

				// Extract the token from the HTTP Authorization header
				String token[] = authorizationHeader.substring("Bearer".length()).trim().split(" ");
				String user_id = (token[0]);
				dto.setUserId(user_id);
				// Validate the token
				TokenAuthModule.validateToken(token[1], token[0]);
			}

			// Insert user request details by thread
			ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS,
					new LinkedBlockingQueue<Runnable>());
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						AccessLogService logService = new AccessLogService();
						logService.insertCommunicationAccessLogRecords(dto);
					} catch (Exception e) {
						ErrorLogDAO logDAO = new ErrorLogDAO();
						logDAO.insertErrorLogRecords("AuthenticationFilter", "insertRequestDetails", dto + "", null,
								e.getMessage());
						e.printStackTrace();
					} finally {
						executor.shutdown();
					}
				}
			});

		} catch (Exception e) {
			logDAO.insertErrorLogRecords("AuthenticationFilter", "ContainerRequestContext", authorizationHeader + "",
					null, e.getMessage());
			requestContext
					.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(AppConstants.UNAUTHORIZED).build());
		}
	}

	/**
	 * Response filter to capture user response details
	 * 
	 * @param requestContext
	 * @param responseContext
	 * @throws IOException
	 * @author sankar
	 * @on 14-02-2018
	 */
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		String authorizationHeader = null;
		try {
			String tempPath = requestContext.getUriInfo().getPath();
			final String path = tempPath;
			if (path.contains("/")) {
				tempPath = tempPath.split("/")[tempPath.split("/").length - 1];
			}
			if (CSEnvVariables.getMethodNames(AppConstants.SECURED_METHODS).contains(tempPath)) {
				authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
				if (authorizationHeader == null) {
					throw new NotAuthorizedException("Authorization header must be provided");
				} else {
					String token[] = authorizationHeader.substring("Bearer".length()).trim().split(" ");
					String user_id = (token[0]).toString();
					String stTime = (String) MDC.get("start-time");
					if (null == stTime || stTime.length() == 0) {
						return;
					}
					long startTime = Long.parseLong(stTime);
					long executionTime = System.currentTimeMillis() - startTime;
					// System.out.println(executionTime);

					// Insert user request details by thread
					ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS,
							new LinkedBlockingQueue<Runnable>());
					executor.execute(new Runnable() {
						@Override
						public void run() {
							try {
								payloadMessage(responseContext, executionTime, user_id, path);
							} catch (Exception e) {
								ErrorLogDAO logDAO = new ErrorLogDAO();
								logDAO.insertErrorLogRecords("AuthenticationFilter", "insertRequestDetails",
										responseContext + "" + executionTime + "" + user_id + "" + path, null,
										e.getMessage());
								e.printStackTrace();
							} finally {
								executor.shutdown();
							}
						}
					});
				}
				// clear the context on exit
				MDC.clear();
			}
		} catch (Exception e) {
			logDAO.insertErrorLogRecords("AuthenticationFilter", "ContainerRequestContext", authorizationHeader + "",
					null, e.getMessage());
			String debug = CSEnvVariables.getProperty("debug");
			if (null != debug && debug.trim().equals("true")) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Method to get response message from response filter
	 * 
	 * @param responseContext
	 * @param elapsedTime
	 * @param user_id
	 * @return
	 * @throws IOException
	 * @author sankar
	 * @on 14-02-2018
	 */
	@SuppressWarnings("unchecked")
	private String payloadMessage(ContainerResponseContext responseContext, long elapsedTime, String user_id,
			String path) throws IOException {
		String message = "";
		try {
			if (responseContext.hasEntity()) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Class<?> entityClass = responseContext.getEntityClass();
				java.lang.reflect.Type entityType = responseContext.getEntityType();
				Annotation[] entityAnnotations = responseContext.getEntityAnnotations();
				MediaType mediaType = responseContext.getMediaType();
				MessageBodyWriter<Object> bodyWriter = (MessageBodyWriter<Object>) providers
						.getMessageBodyWriter(entityClass, entityType, entityAnnotations, mediaType);
				System.out.println(responseContext.getEntity());
				System.out.println(entityClass);
				System.out.println(entityType);
				System.out.println(entityAnnotations);
				System.out.println(mediaType);
				System.out.println(responseContext.getHeaders());
				System.out.println(baos);
				bodyWriter.writeTo(responseContext.getEntity(), entityClass, entityType, entityAnnotations, mediaType,
						responseContext.getHeaders(), baos);
				message = message.concat(new String(baos.toByteArray()));
				baos.close();
				AccessLogDTO dto = new AccessLogDTO();
				dto.setUrl(path);
				dto.setElapsedTime(elapsedTime);
				dto.setContentType(String.valueOf(mediaType));
				dto.setResponseData(message);
				dto.setUserId(user_id);
				AccessLogService logService = new AccessLogService();
				logService.insertResponseData(dto);
			}
		} catch (Exception e) {
			logDAO.insertErrorLogRecords("AuthenticationFilter", "ContainerRequestContext", message + "", user_id,
					e.getMessage());
			e.printStackTrace();

		}
		return message;
	}

}
