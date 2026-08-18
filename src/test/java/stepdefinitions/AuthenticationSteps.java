package stepdefinitions;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.Assert;
import context.TestContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.SpecBuilder;

public class AuthenticationSteps {
	private RequestSpecification request;
	private Response response;
	private TestContext context;

	/**
	 * @param context
	 */
	public AuthenticationSteps(TestContext context) {
		this.context = context;
	}

	@Given("valid user credentials")
	public void valid_user_credentials() throws IOException {
		request = given().spec(SpecBuilder.requestBuilder()).log().all()
				.body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"password123\"\r\n" + "}");

	}

	@When("a POST request is sent to the authentication endpoint")
	public void a_post_request_is_sent_to_the_authentication_endpoint() {
		response = request.when().post("/auth");

	}

	@Then("API responds with status code {int}")
	public void api_responds_with_status_code(Integer expectedStatusCode) {
		response = response.then().log().all().extract().response();
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
				"Expected status code 200 for successful authentication");

	}

	@Then("the response body includes a valid authentication token")
	public void the_response_body_includes_a_valid_authentication_token() {
		String token = response.jsonPath().getString("token");
		context.setToken(token);
		Assert.assertFalse(token.isBlank(), "Authentication token should not be null");

	}

	@Given("invalid user credentials")
	public void invalid_user_credentials() throws IOException {
		request = given().spec(SpecBuilder.requestBuilder()).log().all().body("{\r\n"
				+ "    \"username\" : \"admin569\",\r\n" + "    \"password\" : \"passwordkjygtf123\"\r\n" + "}");
	}

	@Then("the response indicates authentication failure")
	public void the_response_indicates_authentication_failure() {
		Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
	}

}
