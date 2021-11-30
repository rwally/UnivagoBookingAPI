package com.univago.api.config;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.univago.api.exceptions.StartDateAfterEndDateException;

/**
 * StartDateAfterEndDateExceptionMapper
 * 
 * @author rwally
 *
 */
@Produces
public class StartDateAfterEndDateExceptionMapper implements ExceptionMapper<StartDateAfterEndDateException> {

	@Override
	public Response toResponse(StartDateAfterEndDateException exception) {
		return Response.status(Response.Status.NOT_ACCEPTABLE).type(MediaType.APPLICATION_JSON)
				.entity("The end date must be after the start date").build();
	}

}