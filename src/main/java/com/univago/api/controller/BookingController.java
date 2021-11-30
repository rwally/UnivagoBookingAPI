package com.univago.api.controller;

import java.text.ParseException;
import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.univago.api.exceptions.BookingDoesNotExistException;
import com.univago.api.exceptions.RoomNotAvailableException;
import com.univago.api.exceptions.StartDateAfterTheMonthException;
import com.univago.api.exceptions.StartDateBeforeTodayDateException;
import com.univago.api.exceptions.StayLongerThanThreeDaysException;
import com.univago.api.service.BookingService;
import com.univago.api.types.Booking;
import com.univago.api.util.BookingGenerator;

import io.swagger.annotations.Api;

/**
 * BookingController
 * 
 * @author rwally
 *
 */
@Component
@Path("/booking")
@Produces("application/json")
@Api(value = "BookingController Resource", produces = "application/json")
public class BookingController {

	/** bookings : simulate test bookings */
	private List<Booking> bookings = BookingGenerator.generateBookings();

	/** bookingserviceso */
	@Autowired
	private BookingService bookingserviceso;

	/**
	 * Get booking by id
	 * 
	 * @param bookingId id to find
	 * @return the found booking
	 * @throws BookingDoesNotExistException
	 */
	@GET
	@Path("/{booking-id}")
	public Response getBooking(@PathParam("booking-id") String bookingId) throws BookingDoesNotExistException {
		Response resp = null;

		for (Booking existingBooking : bookings) {
			if (existingBooking.getBookingId().equals(bookingId)) {
				resp = Response.status(Response.Status.OK).entity(existingBooking).build();
			}
		}

		if (resp == null) {
			throw new BookingDoesNotExistException();
		}

		return resp;
	}

	/**
	 * Get every booking
	 * 
	 * @return the response
	 */
	@GET
	@Path("/all")
	public Response getBooking() {
		return Response.status(Response.Status.OK).entity(bookings).build();
	}

	/**
	 * Check availability of the room by start date and end date
	 * 
	 * @param startDate start date
	 * @param endDate   end date
	 * @return response
	 * @throws RoomNotAvailableException
	 */
	@GET
	@Path("/check-availability")
	public Response checkAvailabilityByStartDateAndEndDate(@QueryParam("start-date") String startDate,
			@QueryParam("end-date") String endDate) throws RoomNotAvailableException {

		if (!bookingserviceso.isRoomAvailable(startDate, endDate, bookings)) {
			throw new RoomNotAvailableException();
		}

		return Response.status(Response.Status.OK).build();
	}

	/**
	 * Create a new reservation by start date, end date, first name and last name of
	 * the customer
	 * 
	 * @param startDate start date
	 * @param endDate   end date
	 * @param firstName first name of the customer
	 * @param lastName  last name of the customer
	 * @return response
	 * @throws StartDateBeforeTodayDateException
	 * @throws StartDateAfterTheMonthException
	 * @throws RoomNotAvailableException
	 * @throws StayLongerThanThreeDaysException
	 */
	@PUT
	@Path("/new-reservation")
	public Response createBooking(@QueryParam("start-date") String startDate, @QueryParam("end-date") String endDate,
			@QueryParam("first-name") String firstName, @QueryParam("last-name") String lastName)
			throws StartDateBeforeTodayDateException, StartDateAfterTheMonthException, RoomNotAvailableException,
			StayLongerThanThreeDaysException {

		// Init the new booking
		Booking newBooking = new Booking(Integer.toString(bookings.size()), startDate, endDate, firstName, lastName);

		// Validate the new booking by checking every condition
		if (!bookingserviceso.isStartDateAfterTodayDatePlusOneDay(startDate)) {
			throw new StartDateBeforeTodayDateException();
		} else if (!bookingserviceso.isStartDateLessThanThirtyDaysFromToday(startDate)) {
			throw new StartDateAfterTheMonthException();
		} else if (!bookingserviceso.isRoomAvailable(startDate, endDate, bookings)) {
			throw new RoomNotAvailableException();
		} else if (!bookingserviceso.isStartDateBeforeEndDate(startDate, endDate)) {
			throw new StartDateBeforeTodayDateException();
		} else if (!bookingserviceso.isStayDurationLessThanThreeDays(startDate, endDate)) {
			throw new StayLongerThanThreeDaysException();
		}

		// Add the new booking
		bookings.add(newBooking);

		return Response.status(Response.Status.OK).entity("The room has been successfully booked").build();
	}

	/**
	 * Update the start date and end date of an existing reservation by first name
	 * and last name
	 * 
	 * @param startDate start date
	 * @param endDate   end date
	 * @param firstName first name of the customer
	 * @param lastName  last name of the customer
	 * @return response
	 * @throws StartDateBeforeTodayDateException
	 * @throws StartDateAfterTheMonthException
	 * @throws RoomNotAvailableException
	 * @throws StayLongerThanThreeDaysException
	 */
	@PATCH
	@Path("/modify-reservation")
	public Response updateBooking(@QueryParam("start-date") String startDate, @QueryParam("end-date") String endDate,
			@QueryParam("first-name") String firstName, @QueryParam("last-name") String lastName)
			throws ParseException, StartDateBeforeTodayDateException, StartDateAfterTheMonthException,
			RoomNotAvailableException, StayLongerThanThreeDaysException {

		Booking updatedBooking = new Booking("", startDate, endDate, firstName, lastName);
		Booking formerBooking = new Booking();

		int index = 0;
		for (Booking aBooking : bookings) {
			if (aBooking.getFirstName().equals(updatedBooking.getFirstName())
					&& aBooking.getLastName().equals(updatedBooking.getLastName())) {
				index = bookings.indexOf(aBooking);
				formerBooking = aBooking;
				updatedBooking.setBookingId(aBooking.getBookingId());
			}
		}

		// Remove the former booking for the updated booking verification
		bookings.remove(index);

		// Validate the new booking by checking every condition
		try {
			if (!bookingserviceso.isStartDateAfterTodayDatePlusOneDay(startDate)) {
				throw new StartDateBeforeTodayDateException();
			} else if (!bookingserviceso.isStartDateLessThanThirtyDaysFromToday(startDate)) {
				throw new StartDateAfterTheMonthException();
			} else if (!bookingserviceso.isRoomAvailable(startDate, endDate, bookings)) {
				throw new RoomNotAvailableException();
			} else if (!bookingserviceso.isStartDateBeforeEndDate(startDate, endDate)) {
				throw new StartDateBeforeTodayDateException();
			} else if (!bookingserviceso.isStayDurationLessThanThreeDays(startDate, endDate)) {
				throw new StayLongerThanThreeDaysException();
			}
		} catch (Exception e) {
			// If an exception is thrown, put the former booking back in the list
			bookings.set(index, formerBooking);
		}

		// Add the new booking
		bookings.set(index, updatedBooking);

		return Response.status(Response.Status.OK).entity("The reservation has been successfully updated").build();
	}

	/**
	 * Delete a reservation by start date, end date, first name and last name
	 * 
	 * @param startDate start date
	 * @param endDate   end date
	 * @param firstName first name of the customer
	 * @param lastName  last name of the customer
	 * @return the response
	 * @throws BookingDoesNotExistException
	 */
	@DELETE
	@Path("/cancel-reservation")
	public Response deleteBooking(@QueryParam("start-date") String startDate, @QueryParam("end-date") String endDate,
			@QueryParam("first-name") String firstName, @QueryParam("last-name") String lastName)
			throws BookingDoesNotExistException {

		Boolean foundBooking = false;
		ListIterator<Booking> iterBookings = bookings.listIterator();

		while (iterBookings.hasNext()) {
			Booking existingBooking = iterBookings.next();
			if (existingBooking.getFirstName().equals(firstName) && existingBooking.getLastName().equals(lastName)
					&& existingBooking.getStartDate().equals(startDate)
					&& existingBooking.getEndDate().equals(endDate)) {
				iterBookings.remove();
				foundBooking = true;
			}
		}

		if (!foundBooking) {
			throw new BookingDoesNotExistException();
		}

		return Response.ok().entity("The booking has been deleted successfully").build();
	}

	/**
	 * Delete a reservation by id
	 * 
	 * @param bookingId id
	 * @return the response
	 * @throws BookingDoesNotExistException
	 */
	@DELETE
	@Path("/{booking-id}")
	public Response deleteBookingById(@PathParam("booking-id") String bookingId) throws BookingDoesNotExistException {
		Boolean foundBooking = false;
		ListIterator<Booking> iterBookings = bookings.listIterator();

		while (iterBookings.hasNext()) {
			Booking existingBooking = iterBookings.next();
			if (existingBooking.getBookingId().equals(bookingId)) {
				iterBookings.remove();
				foundBooking = true;
			}
		}

		if (!foundBooking) {
			throw new BookingDoesNotExistException();
		}

		return Response.ok().entity("The booking has been deleted successfully").build();
	}

}
