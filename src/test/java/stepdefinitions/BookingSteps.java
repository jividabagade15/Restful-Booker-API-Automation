package stepdefinitions;

import java.io.IOException;
import java.util.List;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pojo.Booking;
import utils.JsonDataReader;
import utils.ResponseValidator;
import utils.SpecBuilder;
import utils.TestDataPaths;
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
	public void a_get_request_is_sent_to_the_booking_endpoint() {

		response = given().spec(SpecBuilder.requestBuilder()).log().all().when().get("/booking");
		response.then().log().all();
	}

	@Then("the booking API responds with status code {int}")
	public void the_booking_api_responds_with_status_code(Integer expectedStatusCode) {
		ResponseValidator.validateStatusCode(response, expectedStatusCode);
	}

	@Then("the response contains booking IDs")
	public void the_response_contains_booking_IDs() {
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(), "Booking ID list should not be empty");
	}

	@Given("a valid booking ID")
	public void a_valid_booking_id() {
		response = given().spec(SpecBuilder.requestBuilder()).log().all().when().get("/booking");
		response.then().log().all();
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(), "List for booking Ids should not be empty");
		id = bookingIds.get(0);

	}

	@When("a GET request is sent to the booking endpoint using the booking ID")
	public void a_get_request_is_sent_to_the_booking_endpoint_using_the_booking_id() {
		response = given().spec(SpecBuilder.requestBuilder()).pathParam("id", id).log().all().when()
				.get("/booking/{id}");
		response.then().log().all();

	}

	@Then("the response contains booking details")
	public void the_response_contains_booking_details() {

		ResponseValidator.validateFieldNotNull(response, "firstname");
		ResponseValidator.validateFieldNotNull(response, "totalprice");

	}

	@Given("a valid booking payload")
	public void a_valid_booking_payload() throws IOException {
		booking = JsonDataReader.readJson(TestDataPaths.BOOKING_DATA, Booking.class);
	}

	@When("a POST request is sent to the booking endpoint")
	public void a_post_request_is_sent_to_the_booking_endpoint() {
		response = given().spec(SpecBuilder.requestBuilder()).body(booking).log().all().when().post("/booking");
		response.then().log().all();
	}

	@Then("response contains the booking with an assigned booking ID")
	public void response_contains_the_booking_with_an_assigned_booking_id() {
		id = response.jsonPath().get("bookingid");
		Assert.assertTrue(id > 0, "A valid booking ID should be assigned");
		ResponseValidator.validateFieldValue(response, "booking.firstname", booking.getFirstname());
	}

	@Then("the booking ID is captured from the response")
	public void the_booking_id_is_captured_from_the_response() {
		id = response.jsonPath().get("bookingid");
		Assert.assertTrue(id > 0, "A valid booking ID should be assigned");
	}

	@Given("an updated booking payload")
	public void an_updated_booking_payload() throws IOException {
		booking = JsonDataReader.readJson(TestDataPaths.UPDATED_BOOKING_DATA, Booking.class);
	}

	@When("a PUT request is sent to the booking endpoint using the booking ID")
	public void a_put_request_is_sent_to_the_booking_endpoint_using_the_booking_id() {

		String token = context.getToken();
		response = given().spec(SpecBuilder.requestBuilder()).pathParam("id", id).cookie("token", token).body(booking)
				.log().all().when().put("/booking/{id}");
		response.then().log().all();
	}

	@Then("the response contains the updated booking details")
	public void the_response_contains_the_updated_booking_details() {

		ResponseValidator.validateFieldValue(response, "lastname", booking.getLastname());
		ResponseValidator.validateFieldValue(response, "additionalneeds", booking.getAdditionalneeds());

	}

	@When("a DELETE request is sent to the booking endpoint using the booking ID")
	public void a_delete_request_is_sent_to_the_booking_endpoint_using_the_booking_id() {
		String token = context.getToken();
		response = given().spec(SpecBuilder.requestBuilder()).pathParam("id", id).cookie("token", token).log().all()
				.when().delete("/booking/{id}");
		response.then().log().all();
	}

	@Given("an invalid booking ID")
	public void an_invalid_booking_id() {
		id = 9999999;
	}

	@Given("an invalid body payload")
	public void an_invalid_body_payload() throws IOException {
		booking = JsonDataReader.readJson(TestDataPaths.INVALID_BOOKING_DATA, Booking.class);
	}

	@When("a PUT request is sent to the booking endpoint without authentication using the booking ID")
	public void a_put_request_is_sent_to_the_booking_endpoint_without_authentication_using_the_booking_id() {
		response = given().spec(SpecBuilder.requestBuilder()).pathParam("id", id).body(booking).log().all().when()
				.put("/booking/{id}");
		response.then().log().all();
	}

	@Then("the response matches the booking schema")
	public void the_response_matches_the_booking_schema() {
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/bookingResponse.json"));
	}
}
