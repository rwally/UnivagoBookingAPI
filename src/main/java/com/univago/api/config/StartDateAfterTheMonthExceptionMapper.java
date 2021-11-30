package com.univago.api.config;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.univago.api.exceptions.StartDateAfterTheMonthException;

/**
 * StartDateAfterTheMonthExceptionMapper
 * 
 * @author rwally
 *
 */
@Produces
public class StartDateAfterTheMonthExceptionMapper implements ExceptionMapper<StartDateAfterTheMonthException> {

	@Override
	public Response toResponse(StartDateAfterTheMonthException exception) {
		return Response.status(Response.Status.NOT_ACCEPTABLE).type(MediaType.APPLICATION_JSON)
				.entity("The reservation must begin in the next 30 days").build();
	}

}