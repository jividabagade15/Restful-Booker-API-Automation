package stepdefinitions;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.Assert;

import config.ConfigReader;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthenticationSteps {
	private RequestSpecification request;
	private Response response;
	
	@Given("the request payload contains valid user credentials")
	public void the_request_payload_contains_valid_user_credentials() throws IOException {
		RestAssured.baseURI=ConfigReader.getProperty("baseUrl");
		request= given().contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}");
		
	}

	@When("a POST request is sent to the authentication endpoint")
	public void a_post_request_is_sent_to_the_authentication_endpoint() {
		response=request.when().post("/auth");

	}

	@Then("API responds with status code {int}")
	public void api_responds_with_status_code(Integer expectedStatusCode) {
		response= response.then().log().all().extract().response();
		Assert.assertEquals(response.getStatusCode(),expectedStatusCode,"Expected status code 200 for successful authentication");
		
	}

	@Then("the response body includes a valid authentication token")
	public void the_response_body_includes_a_valid_authentication_token() {
		String token=response.jsonPath().getString("token");
		Assert.assertFalse(token.isBlank(),"Authentication token should not be null");
	
	}
}
