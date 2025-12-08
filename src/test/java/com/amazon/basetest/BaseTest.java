package com.amazon.basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.amazon.base.BasePage;
import com.amazon.pages.AmazonBusinessPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.RegistrationPage;
import com.amazon.pages.SigninPage;
import com.amazon.pages.StartHerePage;
import com.amazon.testdatareaders.ExcelReader;
import com.amazon.utils.ConfigReader;

public class BaseTest {
	private static ThreadLocal<WebDriver> localDriver = new ThreadLocal<>();
	protected static final Logger logger = LogManager.getLogger(BaseTest.class);

	public BasePage basepage;
	public WebDriver driver;
	public StartHerePage startherePage;
	public RegistrationPage registrationPage;
	public SigninPage signinPage;
	public HomePage homePage;
	public AmazonBusinessPage businessPage;
	public ConfigReader configreader;
	public ExcelReader excelReader;

	public WebDriver getLocalDriver() {
		return localDriver.get();
	}

	/*
	 * @BeforeSuite(alwaysRun=true) public void setupSuite(ITestContext context) {
	 * String suiteName = context.getSuite().getName(); String groupList[] =
	 * context.getIncludedGroups(); String includedGroupList =
	 * String.join(",",groupList);
	 * logger.info("Starting execution for Test Suite => "
	 * +suiteName+" with Tags => "+includedGroupList); }
	 */

	@BeforeTest(alwaysRun = true)
	public void setupTest(ITestContext context) {
		String suiteName = context.getSuite().getName();
		String groupList[] = context.getIncludedGroups();
		String includedGroupList = String.join(",",groupList);
		logger.info("Starting execution for Test Suite => "+suiteName+" with Tags => "+includedGroupList);

		String testName = context.getName();
		logger.info("Starting Execution for Test => "+testName);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void baseSetup() {
		// 1. Load config first
		configreader = new ConfigReader();
		// 2. Create driver FIRST — BasePage must NOT be used before driver exists
	    //driver = new BasePage(null).getDriver(configreader.getBrowser());
	    driver = BasePage.getDriver(configreader.getBrowser());
	    localDriver.set(driver);

	    basepage = new BasePage(getLocalDriver());
		// 4. Open URL
		getLocalDriver().get(configreader.getUrl());

		long threadId = Thread.currentThread().getId();
		String sessionId = ((RemoteWebDriver) getLocalDriver()).getSessionId().toString();
		System.out.println("Starting Session ID :" + sessionId + "Thread ID : " + threadId);

		// 5. Initialize all page objects with valid driver
		homePage = new HomePage(getLocalDriver());
		startherePage = new StartHerePage(getLocalDriver());
		registrationPage = new RegistrationPage(getLocalDriver());
		signinPage = new SigninPage(getLocalDriver());
		businessPage = new AmazonBusinessPage(getLocalDriver());
		excelReader = new ExcelReader("src/test/resources/testdata/" +configreader.getFileName());
		homePage.clickContinueShoppingIfPresent();
	}

	@AfterMethod(alwaysRun = true)
	public void closeSetup() {
		getLocalDriver().quit();
		localDriver.remove();
	}

}
