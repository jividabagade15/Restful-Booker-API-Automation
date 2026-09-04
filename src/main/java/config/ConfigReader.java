package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	static Properties prop = new Properties();

	public static String getProperty(String property) {
		try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (inputStream == null) {
				throw new RuntimeException("File not found");
			}

			prop.load(inputStream);

		} catch (IOException ie) {
			throw new RuntimeException("Failed to load the file");
		}
		return prop.getProperty(property);

	}

}
