package com.amazon.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class BasePage {

	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver=driver;
	}

	public WebDriver getDriver(String browser) {

		switch (browser) {

		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Invalid browser name: " + browser);
		}
		return driver;
	}
	
	public void quitDriver() {
		driver.quit();
	}
	
	// common method 
	
	// logo
	public boolean verifyLogo() {
		return false;
	}
	
	// verify title
	public String getTitleofPage() {
		return driver.getTitle();
	}
	
	// current url
	public String verifyCurrentPageUrl() {
		return driver.getCurrentUrl();
	}

	public void navigateBack() {
		// TODO Auto-generated method stub
		
	}

	public void navigateForward() {
		// TODO Auto-generated method stub
		
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	
}
