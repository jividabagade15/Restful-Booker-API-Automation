package stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;

import static io.restassured.RestAssured.*;
import config.ConfigReader;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BookingSteps {
	private Response response;
	private int id;

	@When("a GET request is sent to the booking endpoint")
	public void a_get_request_is_sent_to_the_booking_endpoint() throws IOException {
		RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
		response = given().log().all().when().get("/booking");
	}

	@Then("the booking API responds with status code {int}")
	public void the_booking_api_responds_with_status_code(Integer expectedStatusCode) {
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected status code for booking request");

	}

	@Then("the response contains booking IDs")
	public void the_response_contains_booking_IDs() {
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(), "List for booking Ids should not be empty");
	}

	@Given("a valid booking ID")
	public void a_valid_booking_id() throws IOException {
		RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
		response = given().log().all().when().get("/booking");
		
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(), "List for booking Ids should not be empty");

		id = bookingIds.get(0);

	}

	@When("a GET request is sent to the booking endpoint using the booking ID")
	public void a_get_request_is_sent_to_the_booking_endpoint_using_the_booking_id() throws IOException {
		RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
		response = given().log().all().pathParam("id", id).when().get("/booking/{id}");

	}

	@Then("the response contains booking details")
	public void the_response_contains_booking_details() {
		String firstName = response.jsonPath().getString("firstname");
		String totalPrice = response.jsonPath().getString("totalprice");

		Assert.assertNotNull(firstName, "First name should be present in booking response");
		Assert.assertNotNull(totalPrice, "The total price should be present in booking response");

	}
}
