/*
 * (c) copyright, 2018
 * 
 */
package com.triglib.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This is a singleton class parse the application.properties file under
 * src/main/resources and provide the actual value at runtime based on key
 * asked.
 * 
 * @author ravi ranjan kumar
 * @since 15-dec-2018
 * @version 1.0
 * 
 */
public class AppConfig {

	private Properties properties;
	private static final String APP_CONFIG_FILE = "application.properties";

	/**
	 * 
	 */
	private AppConfig() {

		if (this.properties == null) {
			this.properties = new Properties();

			try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(APP_CONFIG_FILE)) {
				this.properties.load(inputStream);
			} catch (IOException e) {
				System.out.println("properties file not found");
				e.printStackTrace();
			}

		}
	}

	/**
	 * Helper class which creates the single object of AppConfig class which will be
	 * used by multiple thread simultaneously.
	 * 
	 *
	 */
	private static final class AppConfigHelper {
		private static volatile AppConfig appConfig = new AppConfig();
	}

	/**
	 * return the value of the key from application.properties file.
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(final String key) {
		return AppConfigHelper.appConfig.properties.getProperty(key);
	}
}
