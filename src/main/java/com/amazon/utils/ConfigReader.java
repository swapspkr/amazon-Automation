package com.amazon.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties prop ;
	
	public ConfigReader() {
		prop = new Properties();
		try {
			FileInputStream input = new FileInputStream("src/test/resources/resources/config.properties");
			prop.load(input);
		}catch(IOException io ) {
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
}
