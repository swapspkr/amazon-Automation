package com.amazon.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.utils.ConfigReader;
import com.amazon.utils.CustomWait;



public class BasePage {

	protected WebDriver driver;
	protected CustomWait wait;
	protected ConfigReader configreader;
	protected static final Logger logger = LogManager.getLogger(BasePage.class);
	
	@FindBy(xpath="//a[@class='a-link-nav-icon']")
	private WebElement logo;

	public BasePage(WebDriver driver) {
		this.driver=driver;
		configreader =  new ConfigReader();
		if (driver != null) {
	        PageFactory.initElements(driver, this);
	        wait = new CustomWait(driver, Duration.ofSeconds(configreader.getGlobalWait()));
	    }
		
	}

	public WebDriver getDriver(String browser) {

		switch (browser.toLowerCase()) {

		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
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
		return getLogo().isDisplayed();
	}
	
	// verify title
	public String getTitleofPage() {
		return driver.getTitle();
	}
	
	public WebElement getLogo() {
		wait.waitForElementToBeVisible(logo);
		return logo;
	}
	
	// current url
	public String verifyCurrentPageUrl() {
		return driver.getCurrentUrl();
	}

	public void navigateBack() {
		driver.navigate().back();
		
	}

	public void navigateForward() {
		driver.navigate().forward();
		
	}

	public void refresh() {
		driver.navigate().refresh();
		
	}


	
}
