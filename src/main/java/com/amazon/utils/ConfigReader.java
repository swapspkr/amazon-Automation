package com.amazon.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties prop;

	public ConfigReader() {
		prop = new Properties();
		try {
			FileInputStream input = new FileInputStream("src/test/resources/configuration/config.properties");
			prop.load(input);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public String getUrl() {
		return prop.getProperty("URL");
	}

	public String getBrowser() {
		return prop.getProperty("BROWSER");
	}

	public String getValidEmail() {
		return prop.getProperty("VALIDEMAIL");
	}

	public Long getGlobalWait() {
		return Long.parseLong(prop.getProperty("GLOBALWAIT"));
	}

	public String getFileName() {
		return prop.getProperty("FIELDS_VERIFICATION_EXCEL");
	}

	public String getServerURl() {
		return prop.getProperty("ENV");
	}

	public String getMaxRetriesValue() {
		return prop.getProperty("MAX_RETRIES");
	}

	public String getParallelMode() {
		return prop.getProperty("PARALLEL_MODE");
	}

	public String getMaxParallelTest() {
		return prop.getProperty("MAX_PARALLEL_TESTS");
	}
	
	public String getRunMode() {
		return prop.getProperty("RUN_MODE");
	}
	
	public String getGridUrl() {
		return prop.getProperty("GRID_URL");
	}
}
