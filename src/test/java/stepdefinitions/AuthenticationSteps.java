package stepdefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import org.testng.Assert;
import context.TestContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.UserCredentials;
import utils.JsonDataReader;
import utils.ResponseValidator;
import utils.SpecBuilder;

public class AuthenticationSteps {
	private RequestSpecification request;
	private Response response;
	private TestContext context;
	private UserCredentials credentials;

	/**
	 * @param context
	 */
	public AuthenticationSteps(TestContext context) {
		this.context = context;
	}

	@Given("valid user credentials")
	public void valid_user_credentials() throws IOException {
		credentials = JsonDataReader.readJson("testdata/authenticationData.json",
				UserCredentials.class);
		request = given().spec(SpecBuilder.requestBuilder()).log().all().body(credentials);

	}

	@When("a POST request is sent to the authentication endpoint")
	public void a_post_request_is_sent_to_the_authentication_endpoint() {
		response = request.when().post("/auth");

	}

	@Then("API responds with status code {int}")
	public void api_responds_with_status_code(Integer expectedStatusCode) {
		response.then().log().all();
		ResponseValidator.validateStatusCode(response, expectedStatusCode);

	}

	@Then("the response body includes a valid authentication token")
	public void the_response_body_includes_a_valid_authentication_token() {
		String token = response.jsonPath().getString("token");
		context.setToken(token);
		Assert.assertFalse(token.isBlank(), "Authentication token should not be null");

	}

	@Given("request payload contains {string} and {string}")
	public void request_payload_contains_and(String username, String password) throws IOException {
		credentials = new UserCredentials();
		credentials.setUsername(username);
		credentials.setPassword(password);
		request = given().spec(SpecBuilder.requestBuilder()).log().all().body(credentials);
	}

	@Then("the response indicates authentication failure")
	public void the_response_indicates_authentication_failure() {
		ResponseValidator.validateFieldValue(response, "reason", "Bad credentials");

	}

	@Then("the response matches the authentication schema")
	public void the_response_matches_the_authentication_schema() {
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/AuthenticationResponse.json"));
	}

}
