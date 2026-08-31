package utils;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {
	
	public static <T> T readJson(String filePath, Class<T> classType) throws IOException {
		ObjectMapper mapper= new ObjectMapper();
		
		return mapper.readValue( new File(filePath), classType);
	}

}
