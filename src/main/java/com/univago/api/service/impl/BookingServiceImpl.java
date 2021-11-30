package com.univago.api.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.springframework.stereotype.Service;

import com.univago.api.service.BookingService;
import com.univago.api.types.Booking;

/**
 * Classe service impl BookingServiceImpl
 * 
 * @author rwally
 *
 */
@Service
public class BookingServiceImpl implements BookingService {

	/** DD_MM_YYYY : date pattern */
	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	@Override
	public Boolean isStartDateBeforeEndDate(String startDate, String endDate) {
		Date startDateToCheck = null;
		Date endDateToCheck = null;

		try {
			startDateToCheck = DateUtils.parseDate(startDate, DD_MM_YYYY);
			endDateToCheck = DateUtils.parseDate(endDate, DD_MM_YYYY);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (startDateToCheck.compareTo(endDateToCheck) < 0);
	}

	@Override
	public Boolean isRoomAvailable(String startDate, String endDate, List<Booking> bookings) {
		Boolean roomAvailable = false;
		Date startDateToCheck = null;
		Date endDateToCheck = null;
		try {
			startDateToCheck = DateUtils.parseDate(startDate, DD_MM_YYYY);
			endDateToCheck = DateUtils.parseDate(endDate, DD_MM_YYYY);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Interval> alreadyBooked = new ArrayList<>();

		// Iterate through existing bookings to get every intervals
		for (Booking existingBooking : bookings) {
			DateTime bookedStart = null;
			DateTime bookedEnd = null;
			try {
				bookedStart = new DateTime(DateUtils.parseDate(existingBooking.getStartDate(), DD_MM_YYYY));
				bookedEnd = new DateTime(DateUtils.parseDate(existingBooking.getEndDate(), DD_MM_YYYY));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Interval interval = new Interval(bookedStart, bookedEnd);
			alreadyBooked.add(interval);

		}

		DateTime startDateToCheckTime = new DateTime(startDateToCheck);
		DateTime endDateToCheckTime = new DateTime(endDateToCheck);
		Interval newBookingInterval = new Interval(startDateToCheckTime, endDateToCheckTime);

		for (Interval intervalBooked : alreadyBooked) {
			// If the new booking interval is an already booked interval, the room is not
			// available
			if (!intervalBooked.contains(newBookingInterval) && !newBookingInterval.contains(intervalBooked)
					&& !newBookingInterval.getEnd().isEqual(intervalBooked.getEnd())
					&& !newBookingInterval.getStart().isEqual(intervalBooked.getStart())) {
				roomAvailable = true;
			}
		}

		return roomAvailable;

	}

	@Override
	public Boolean isStartDateAfterTodayDatePlusOneDay(String startDate) {
		Date startDateToCheck = null;
		Date todayDate = null;
		try {
			startDateToCheck = DateUtils.parseDate(startDate, DD_MM_YYYY);
			todayDate = DateUtils.truncate(new Date(), Calendar.DATE);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (startDateToCheck.compareTo(DateUtils.addDays(todayDate, 1)) > 0);
	}

	@Override
	public Boolean isStartDateLessThanThirtyDaysFromToday(String startDate) {
		Date startDateToCheck = null;
		Date todayDate = null;
		try {
			startDateToCheck = DateUtils.parseDate(startDate, DD_MM_YYYY);
			todayDate = DateUtils.truncate(new Date(), Calendar.DATE);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return (startDateToCheck.compareTo(DateUtils.addDays(todayDate, 30)) < 0);
	}

	@Override
	public Boolean isStayDurationLessThanThreeDays(String startDate, String endDate) {
		Date startDateToCheck = null;
		Date endDateToCheck = null;
		int stayDuration = 0;
		try {
			startDateToCheck = DateUtils.parseDate(startDate, DD_MM_YYYY);
			endDateToCheck = DateUtils.parseDate(endDate, DD_MM_YYYY);

		} catch (ParseException e) {

			e.printStackTrace();
		}
		DateTime startDateToCheckTime = new DateTime(startDateToCheck);
		DateTime endDateToCheckTime = new DateTime(endDateToCheck);
		return (stayDuration = Days.daysBetween(startDateToCheckTime, endDateToCheckTime).getDays()) <= 3;
	}

}
