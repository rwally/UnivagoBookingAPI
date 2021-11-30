package com.univago.api.controller.v2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.univago.api.controller.BookingController;

import io.swagger.annotations.Api;

/**
 * 
 * BookingControllerV2
 * 
 * @author rwally
 *
 */
@Component
@Path("/v2/booking")
@Produces("application/json")
@Api(value = "BookingControllerV2 Resource", produces = "application/json")
public class BookingControllerV2 extends BookingController {

	@GET
	@Path("/{booking-id}")
	public Response getBooking(@PathParam("booking-id") String bookingId) {
		return Response.ok().entity("V2 placeholder").build();
	}

}
