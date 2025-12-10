package com.amazon.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.utils.ConfigReader;
import com.amazon.utils.CustomWait;



public class BasePage {

	protected WebDriver driver;
	protected CustomWait wait;
	protected ConfigReader configreader;
	protected static final Logger logger = LogManager.getLogger(BasePage.class);
	
	@FindBy(xpath="//a[@id='nav-logo-sprites']")
	private WebElement logo;

	public BasePage(WebDriver driver) {
		this.driver=driver;
		this.configreader =  new ConfigReader();
		PageFactory.initElements(driver, this);
		this.wait = new CustomWait(driver, Duration.ofSeconds(configreader.getGlobalWait()));	
	}

	public static WebDriver getDriver(String browser) {
		WebDriver driver;
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

	public WebDriver getRemoteDriver(String browser) throws MalformedURLException {

	    DesiredCapabilities capabilities = new DesiredCapabilities();

	    if (browser.equalsIgnoreCase("chrome")) {
	        capabilities.setBrowserName(configreader.getBrowser());
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--incognito");
	        capabilities.merge(options);

	    } else if (browser.equalsIgnoreCase("firefox")) {
	        capabilities.setBrowserName(configreader.getBrowser());
	        FirefoxOptions options = new FirefoxOptions();
	        options.addArguments("-private");
	        capabilities.merge(options);

	    } else {
	        capabilities.setBrowserName(configreader.getBrowser());
	        EdgeOptions options = new EdgeOptions();
	        options.addArguments("--inprivate");
	        capabilities.merge(options);
	    }

	    String gridUrl = configreader.getGridUrl();  // Fetch this from config.properties
	    logger.info("Executiong on Grid having URL => " +gridUrl);
	    
	    driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
	    driver.manage().window().maximize();
	    return driver;
	}

	
}
