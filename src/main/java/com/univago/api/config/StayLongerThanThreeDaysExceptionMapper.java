package com.univago.api.config;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.univago.api.exceptions.StayLongerThanThreeDaysException;

/**
 * StayLongerThanThreeDaysExceptionMapper
 * 
 * @author rwally
 *
 */
@Produces
public class StayLongerThanThreeDaysExceptionMapper implements ExceptionMapper<StayLongerThanThreeDaysException> {

	@Override
	public Response toResponse(StayLongerThanThreeDaysException exception) {
		return Response.status(Response.Status.NOT_ACCEPTABLE).type(MediaType.APPLICATION_JSON)
				.entity("The stay must not be longer than 3 days").build();
	}

}
