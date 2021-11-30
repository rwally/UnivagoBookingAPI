package com.univago.api.config;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.univago.api.exceptions.StartDateBeforeTodayDateException;

/**
 * StartDateBeforeTodayDateExceptionMapper
 * 
 * @author rwally
 *
 */
@Produces
public class StartDateBeforeTodayDateExceptionMapper implements ExceptionMapper<StartDateBeforeTodayDateException> {

	@Override
	public Response toResponse(StartDateBeforeTodayDateException exception) {
		return Response.status(Response.Status.NOT_ACCEPTABLE).type(MediaType.APPLICATION_JSON)
				.entity("The start date must be after today").build();
	}

}