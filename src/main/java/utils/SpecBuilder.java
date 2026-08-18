package utils;

import java.io.IOException;

import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

	public static RequestSpecification requestBuilder() throws IOException {
		return new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseUrl")).setContentType(ContentType.JSON)
				.build();
	}
}
