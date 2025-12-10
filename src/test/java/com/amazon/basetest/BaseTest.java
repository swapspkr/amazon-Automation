package com.amazon.basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import com.amazon.base.BasePage;
import com.amazon.pages.AmazonBusinessPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.RegistrationPage;
import com.amazon.pages.SigninPage;
import com.amazon.pages.StartHerePage;
import com.amazon.reports.ExtentReportManager;
import com.amazon.testdatareaders.ExcelReader;
import com.amazon.utils.ConfigReader;
import com.aventstack.extentreports.Status;

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

	private static int totalTest=0;
	private static int testPassed=0;
	private static int testfailed=0;
	private static int testSkipped=0;
	
	public WebDriver getLocalDriver() {
		return localDriver.get();
	}

	
	  @BeforeSuite(alwaysRun = true) 
	  public void setupSuite() {
	  //String suiteName = context.getSuite().getName(); 
	  ITestContext context = Reporter.getCurrentTestResult().getTestContext();
	  String suiteName = context.getSuite().getName();
	  String groupList[] =  context.getIncludedGroups(); 
	  String includedGroupList = String.join(",",groupList); 
	  logger.info("Starting execution for Test Suite => " + suiteName+ " with Tags => " + includedGroupList);
	  ExtentReportManager.setupExtentReport();
	  ExtentReportManager.getReport().setSystemInfo("Suite Name", suiteName); 
	  }
	 

	@BeforeTest(alwaysRun = true)
	public void setupTest(ITestContext context) {

		String testName = context.getName();
		logger.info("Starting Execution for Test => " + testName);
		ExtentReportManager.getReport().setSystemInfo("Test Name", testName);
	}

	@BeforeMethod(alwaysRun = true)
	public void baseSetup() {
		// 1. Load config first
		configreader = new ConfigReader();
		// 2. Create driver 
		driver = BasePage.getDriver(configreader.getBrowser());
		localDriver.set(driver);

		basepage = new BasePage(getLocalDriver());
		// 4. Open URL
		getLocalDriver().get(configreader.getUrl());

		long threadId = Thread.currentThread().getId();
		String sessionId = ((RemoteWebDriver) getLocalDriver()).getSessionId().toString();
		//System.out.println("Starting Session ID :" +sessionId + "/ Thread ID : " + threadId);
		logger.info("Starting Session ID => " + sessionId + " Thread ID : "+threadId);
		// 5. Initialize all page objects with valid driver
		homePage = new HomePage(getLocalDriver());
		startherePage = new StartHerePage(getLocalDriver());
		registrationPage = new RegistrationPage(getLocalDriver());
		signinPage = new SigninPage(getLocalDriver());
		businessPage = new AmazonBusinessPage(getLocalDriver());
		excelReader = new ExcelReader("src/test/resources/testdata/" + configreader.getFileName());
		homePage.clickContinueShoppingIfPresent();
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser(ITestResult result) {
		
		totalTest++;
		if(result.getStatus() == ITestResult.FAILURE) {
			ExtentReportManager.getTest().fail(result.getThrowable());
			testfailed++;
		} else if(result.getStatus() == ITestResult.SKIP) {
			ExtentReportManager.getTest().skip(result.getThrowable());
			testSkipped++;
		}else {
			ExtentReportManager.getTest().pass("Test Passed");
			testPassed++;
		}
		
		ExtentReportManager.getTest().log(Status.INFO, "Test Method execution Completed");
		
		getLocalDriver().quit();
		localDriver.remove();
		logger.info("Closing browser"+configreader.getBrowser());
	}
	
	@AfterSuite(alwaysRun = true)
	public void generateReport() {
		//ExtentReportManager.getReport().setSystemInfo("Total test executed =>", String.valueOf(totalTest-testSkipped));
		//ExtentReportManager.getReport().setSystemInfo("Pass tests =>", String.valueOf(testPassed));
		//ExtentReportManager.getReport().setSystemInfo("Failed tests =>", String.valueOf(testfailed));
		//ExtentReportManager.getReport().setSystemInfo("Skipped tests =>", String.valueOf(testSkipped));
		
		double passPercent = (double)testPassed/(totalTest-testSkipped);
		passPercent = passPercent*100;
		
		//ExtentReportManager.getReport().setSystemInfo("Passed % =>", String.format("%.2f",passPercent));
		ExtentReportManager.createCustomTable(String.valueOf(totalTest-testSkipped), String.valueOf(testPassed), String.valueOf(testfailed), String.valueOf(testSkipped), String.format("%.2f",passPercent));
		ExtentReportManager.flushReport();
	}

}
