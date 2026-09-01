package utils;

import org.testng.Assert;

import io.restassured.response.Response;

public class ResponseValidator {

	public static void validateStatusCode(Response response, int expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected status code");

	}

	public static void validateFieldNotNull(Response response, String fieldName) {

		String actualValue = response.jsonPath().getString(fieldName);
		Assert.assertNotNull(actualValue, fieldName + " should be present in the response");
	}

	public static void validateFieldValue(Response response, String fieldName, Object expectedValue) {
		String actualValue = response.jsonPath().getString(fieldName);
		Assert.assertEquals(actualValue, expectedValue, fieldName + " in response does not match the request");

	}

}
