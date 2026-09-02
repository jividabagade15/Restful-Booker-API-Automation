package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {
	static ObjectMapper mapper= new ObjectMapper();
	public static <T> T readJson(String filePath, Class<T> classType) throws IOException {
		InputStream inputStream= JsonDataReader.class.getClassLoader().getResourceAsStream(filePath);
		
		if(inputStream==null) {
			new FileNotFoundException("Test data file not found");
		}
		
		return mapper.readValue( inputStream, classType);
	}

}
