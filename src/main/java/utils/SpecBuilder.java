package utils;

import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

	public static RequestSpecification requestBuilder() {
		return new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseUrl")).setContentType(ContentType.JSON)
				.build();
	}
}
