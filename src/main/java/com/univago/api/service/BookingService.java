package com.univago.api.service;

import java.util.List;

import com.univago.api.types.Booking;

/**
 * Classe service BookingService
 * 
 * @author rwally
 *
 */
public interface BookingService {

	/**
	 * Check if the start date is before the end date
	 * 
	 * @param startDate the start date of the reservation
	 * @param endDate   the end date of the reservation
	 * @return true if the start date is before the end date
	 */
	public abstract Boolean isStartDateBeforeEndDate(String startDate, String endDate);

	/**
	 * Check if the room is available from a start date (inclusive) to an end date
	 * (inclusive)
	 * 
	 * @param startDate start date
	 * @param endDate   end date
	 * @param bookings  list of existing bookings
	 * @return true if the room is available
	 */
	public abstract Boolean isRoomAvailable(String startDate, String endDate, List<Booking> bookings);

	/**
	 * Check if the start date of the reservation is after today's date plus one
	 * day. For example : if today's date is 28/11/2021 and the start date is
	 * 29/11/2021, it will return true but if the start date is 28/11/2021 it will
	 * return false
	 * 
	 * @param startDate the desired start date
	 * @return true if the start date is after today plus one day
	 */
	public abstract Boolean isStartDateAfterTodayDatePlusOneDay(String startDate);

	/**
	 * Check if the start date is less than 30 days from today's date. For example :
	 * if today's date is 1/12/2021 and the start date is 1/1/2022, it will return
	 * false
	 * 
	 * @param startDate the desired start date
	 * @return true if the start date is less than thirty days from today's date
	 */
	public abstract Boolean isStartDateLessThanThirtyDaysFromToday(String startDate);

	/**
	 * Check if the end date minus the start date is less than three days
	 * 
	 * @param startDate the desired start date
	 * @param endDate   the desired end date
	 * @return true if the stay duration is less than three days
	 */
	public abstract Boolean isStayDurationLessThanThreeDays(String startDate, String endDate);

}
