package com.univago.api.config;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.univago.api.exceptions.RoomNotAvailableException;

/**
 * RoomNotAvailableExceptionMapper
 * 
 * @author rwally
 *
 */
@Produces
public class RoomNotAvailableExceptionMapper implements ExceptionMapper<RoomNotAvailableException> {

	@Override
	public Response toResponse(RoomNotAvailableException exception) {
		return Response.status(Response.Status.NOT_ACCEPTABLE).type(MediaType.APPLICATION_JSON)
				.entity("The room is not available.").build();
	}

}
