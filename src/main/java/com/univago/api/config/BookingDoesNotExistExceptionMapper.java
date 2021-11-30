package com.univago.api.config;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.univago.api.exceptions.BookingDoesNotExistException;

/**
 * BookingDoesNotExistExceptionMapper
 * 
 * @author rwally
 *
 */
@Produces
public class BookingDoesNotExistExceptionMapper implements ExceptionMapper<BookingDoesNotExistException> {

	@Override
	public Response toResponse(BookingDoesNotExistException exception) {
		return Response.status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON)
				.entity("The booking does not exist.").build();
	}

}
