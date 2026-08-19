package stepdefinitions;

import java.io.IOException;
import java.util.List;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.BookingDates;
import utils.SpecBuilder;
import context.TestContext;

public class BookingSteps {
	private Response response;
	private int id;
	private Booking booking;
	private TestContext context;

	public BookingSteps(TestContext context) {
		this.context = context;
	}

	@When("a GET request is sent to the booking endpoint")
	public void a_get_request_is_sent_to_the_booking_endpoint() throws IOException {

		response = given().spec(SpecBuilder.requestBuilder()).log().all().when().get("/booking");
	}

	@Then("the booking API responds with status code {int}")
	public void the_booking_api_responds_with_status_code(Integer expectedStatusCode) {
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected status code for booking request");
	}

	@Then("the response contains booking IDs")
	public void the_response_contains_booking_IDs() {
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(), "Booking ID list should not be empty");
	}

	@Given("a valid booking ID")
	public void a_valid_booking_id() throws IOException {
		response = given().spec(SpecBuilder.requestBuilder()).log().all().when().get("/booking");
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(), "List for booking Ids should not be empty");
		id = bookingIds.get(0);

	}

	@When("a GET request is sent to the booking endpoint using the booking ID")
	public void a_get_request_is_sent_to_the_booking_endpoint_using_the_booking_id() throws IOException {
		response = given().spec(SpecBuilder.requestBuilder()).log().all().pathParam("id", id).when()
				.get("/booking/{id}");

	}

	@Then("the response contains booking details")
	public void the_response_contains_booking_details() {
		String firstName = response.jsonPath().getString("firstname");
		String totalPrice = response.jsonPath().getString("totalprice");
		Assert.assertNotNull(firstName, "First name should be present in booking response");
		Assert.assertNotNull(totalPrice, "The total price should be present in booking response");

	}

	@Given("a valid booking payload")
	public void a_valid_booking_payload() {
		booking = new Booking();
		booking.setFirstname("John");
		booking.setLastname("Cavill");
		booking.setTotalprice(589);
		booking.setDepositpaid(false);
		BookingDates dates = new BookingDates();
		dates.setCheckin("2018-02-13");
		dates.setCheckout("2019-02-13");
		booking.setBookingdates(dates);
		booking.setAdditionalneeds("Lunch");
	}

	@When("a POST request is sent to the booking endpoint")
	public void a_post_request_is_sent_to_the_booking_endpoint() throws IOException {
		response = given().spec(SpecBuilder.requestBuilder()).log().all().body(booking).when().post("/booking");
	}

	@Then("response contains the booking with an assigned booking ID")
	public void response_contains_the_booking_with_an_assigned_booking_id() {
		id = response.jsonPath().get("bookingid");
		String firstName = response.jsonPath().getString("booking.firstname");
		Assert.assertTrue(id > 0, "A valid booking ID should be assigned");
		Assert.assertEquals(firstName, booking.getFirstname(), "Firstname in response does not match the request");
	}

	@Then("the booking ID is captured from the response")
	public void the_booking_id_is_captured_from_the_response() {
		id = response.jsonPath().get("bookingid");
		Assert.assertTrue(id > 0, "A valid booking ID should be assigned");
	}

	@Given("an updated booking payload")
	public void an_updated_booking_payload() {
		booking = new Booking();
		booking.setFirstname("John");
		booking.setLastname("Kate");
		booking.setTotalprice(589);
		booking.setDepositpaid(false);
		BookingDates dates = new BookingDates();
		dates.setCheckin("2018-02-13");
		dates.setCheckout("2020-09-01");
		booking.setBookingdates(dates);
		booking.setAdditionalneeds("Dinner");
	}

	@When("a PUT request is sent to the booking endpoint using the booking ID")
	public void a_put_request_is_sent_to_the_booking_endpoint_using_the_booking_id() throws IOException {

		String token = context.getToken();
		response = given().spec(SpecBuilder.requestBuilder()).log().all().pathParam("id", id).cookie("token", token)
				.body(booking).when().put("/booking/{id}");
	}

	@Then("the response contains the updated booking details")
	public void the_response_contains_the_updated_booking_details() {
		String updatedLastName = response.jsonPath().getString("lastname");
		String updatedAdditionalNeeds = response.jsonPath().getString("additionalneeds");

		Assert.assertEquals(updatedLastName, booking.getLastname(), "Lastname was not updated correctly");
		Assert.assertEquals(updatedAdditionalNeeds, booking.getAdditionalneeds(),
				"Additional needs were not updated correctly");

	}

	@When("a DELETE request is sent to the booking endpoint using the booking ID")
	public void a_delete_request_is_sent_to_the_booking_endpoint_using_the_booking_id() throws IOException {
		String token = context.getToken();
		response = given().spec(SpecBuilder.requestBuilder()).log().all().pathParam("id", id).cookie("token", token)
				.when().delete("/booking/{id}");
	}

	@Given("an invalid booking ID")
	public void an_invalid_booking_id() {
		id = 9999999;
	}

	@Given("an invalid body payload")
	public void an_invalid_body_payload() {
		booking = new Booking();
		booking.setLastname("Kate");
		booking.setDepositpaid(false);
		BookingDates dates = new BookingDates();
		dates.setCheckin("2018-02-13");
		dates.setCheckout("2020-09-01");
		booking.setBookingdates(dates);
		booking.setAdditionalneeds("Dinner");
	}

	@When("a PUT request is sent to the booking endpoint without authentication using the booking ID")
	public void a_put_request_is_sent_to_the_booking_endpoint_without_authentication_using_the_booking_id()
			throws IOException {
		response = given().spec(SpecBuilder.requestBuilder()).log().all().pathParam("id", id).body(booking).when()
				.put("/booking/{id}");
	}

	@Then("the response matches the booking schema")
	public void the_response_matches_the_booking_schema() {
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/bookingResponse.json"));
	}
}
