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
	@When("a GET request is sent to the booking endpoint")
	public void a_get_request_is_sent_to_the_booking_endpoint() throws IOException {
		RestAssured.baseURI= ConfigReader.getProperty("baseUrl");
		response= given().log().all()
		.when().get("/booking");
	}

	@Then("the booking API responds with status code {int}")
	public void the_booking_api_responds_with_status_code(Integer expectedStatusCode) {
		response.then().log().all().extract().response();
		Assert.assertEquals(response.getStatusCode(),expectedStatusCode,"Unexpected status code for get all bookings");
		
	}

	@Then("the response contains booking IDs")
	public void the_response_contains_booking_IDs() {
		List<Integer> bookingIds= response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(),"List for booking Ids should not be empty");
	}

}
