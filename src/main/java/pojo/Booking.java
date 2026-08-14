package pojo;

public class Booking {

	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private BookingDates bookingdates;
	private String additionalneeds;

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the totalprice
	 */
	public int getTotalprice() {
		return totalprice;
	}

	/**
	 * @param totalprice the totalprice to set
	 */
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	/**
	 * @return the depositpaid
	 */
	public boolean isDepositpaid() {
		return depositpaid;
	}

	/**
	 * @param depositpaid the depositpaid to set
	 */
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	/**
	 * @return the bookingdates
	 */
	public BookingDates getBookingdates() {
		return bookingdates;
	}

	/**
	 * @param bookingdates the bookingdates to set
	 */
	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}

	/**
	 * @return the additionalneeds
	 */
	public String getAdditionalneeds() {
		return additionalneeds;
	}

	/**
	 * @param additionalneeds the additionalneeds to set
	 */
	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

}
