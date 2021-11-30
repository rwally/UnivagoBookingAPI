package com.univago.api.util;

import java.util.ArrayList;
import java.util.List;

import com.univago.api.types.Booking;

/**
 * 
 * BookingGenerator
 * 
 * @author rwally
 *
 */
public class BookingGenerator {

	/**
	 * Generate test data to test the API
	 * 
	 * @return a list of booking test data
	 */
	public static List<Booking> generateBookings() {
		List<Booking> bookings = new ArrayList<>();
		bookings.add(new Booking("0", "20/11/2021", "22/11/2021", "Steve", "Mandanda"));
		bookings.add(new Booking("1", "23/11/2021", "24/11/2021", "William", "Saliba"));
		bookings.add(new Booking("2", "27/11/2021", "28/11/2021", "Luan", "Peres"));
		bookings.add(new Booking("3", "1/12/2021", "2/12/2021", "Leonardo", "Balerdi"));
		bookings.add(new Booking("4", "12/12/2021", "14/12/2021", "Boubacar", "Kamara"));
		bookings.add(new Booking("5", "15/12/2021", "16/12/2021", "Matteo", "Guendouzi"));
		bookings.add(new Booking("6", "17/11/2021", "18/12/2021", "Konrad", "De La Fuente"));
		bookings.add(new Booking("7", "20/12/2021", "21/12/2021", "Cengiz", "Under"));
		bookings.add(new Booking("8", "23/12/2021", "24/12/2021", "Dimitri", "Payet"));
		bookings.add(new Booking("9", "24/12/2021", "26/12/2021", "Bamba", "Dieng"));
		bookings.add(new Booking("10", "30/12/2021", "31/12/2021", "Arek", "Milik"));
		return bookings;
	}
}
