package com.univago.api.types;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Booking
 * 
 * @author rwally
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "booking-id", "start-date", "end-date", "first-name", "last-name" })
@Generated("jsonschema2pojo")
public class Booking {

	@JsonProperty("booking-id")
	private String bookingId;
	@JsonProperty("start-date")
	private String startDate;
	@JsonProperty("end-date")
	private String endDate;
	@JsonProperty("first-name")
	private String firstName;
	@JsonProperty("last-name")
	private String lastName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Booking() {
	}

	/**
	 *
	 * @param firstName
	 * @param lastName
	 * @param endDate
	 * @param bookingId
	 * @param startDate
	 */
	public Booking(String bookingId, String startDate, String endDate, String firstName, String lastName) {
		super();
		this.bookingId = bookingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@JsonProperty("booking-id")
	public String getBookingId() {
		return bookingId;
	}

	@JsonProperty("booking-id")
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	@JsonProperty("start-date")
	public String getStartDate() {
		return startDate;
	}

	@JsonProperty("start-date")
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@JsonProperty("end-date")
	public String getEndDate() {
		return endDate;
	}

	@JsonProperty("end-date")
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@JsonProperty("first-name")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("first-name")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("last-name")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("last-name")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Booking.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("bookingId");
		sb.append('=');
		sb.append(((this.bookingId == null) ? "<null>" : this.bookingId));
		sb.append(',');
		sb.append("startDate");
		sb.append('=');
		sb.append(((this.startDate == null) ? "<null>" : this.startDate));
		sb.append(',');
		sb.append("endDate");
		sb.append('=');
		sb.append(((this.endDate == null) ? "<null>" : this.endDate));
		sb.append(',');
		sb.append("firstName");
		sb.append('=');
		sb.append(((this.firstName == null) ? "<null>" : this.firstName));
		sb.append(',');
		sb.append("lastName");
		sb.append('=');
		sb.append(((this.lastName == null) ? "<null>" : this.lastName));
		sb.append(',');
		sb.append("additionalProperties");
		sb.append('=');
		sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = ((result * 31) + ((this.firstName == null) ? 0 : this.firstName.hashCode()));
		result = ((result * 31) + ((this.lastName == null) ? 0 : this.lastName.hashCode()));
		result = ((result * 31) + ((this.endDate == null) ? 0 : this.endDate.hashCode()));
		result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
		result = ((result * 31) + ((this.bookingId == null) ? 0 : this.bookingId.hashCode()));
		result = ((result * 31) + ((this.startDate == null) ? 0 : this.startDate.hashCode()));
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Booking) == false) {
			return false;
		}
		Booking rhs = ((Booking) other);
		return (((((((this.firstName == rhs.firstName)
				|| ((this.firstName != null) && this.firstName.equals(rhs.firstName)))
				&& ((this.lastName == rhs.lastName) || ((this.lastName != null) && this.lastName.equals(rhs.lastName))))
				&& ((this.endDate == rhs.endDate) || ((this.endDate != null) && this.endDate.equals(rhs.endDate))))
				&& ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null)
						&& this.additionalProperties.equals(rhs.additionalProperties))))
				&& ((this.bookingId == rhs.bookingId)
						|| ((this.bookingId != null) && this.bookingId.equals(rhs.bookingId))))
				&& ((this.startDate == rhs.startDate)
						|| ((this.startDate != null) && this.startDate.equals(rhs.startDate))));
	}

}