package com.amazon.basetest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.amazon.base.BasePage;
import com.amazon.pages.AmazonBusinessPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.RegistrationPage;
import com.amazon.pages.SigninPage;
import com.amazon.pages.StartHerePage;
import com.amazon.testdatareaders.ExcelReader;
import com.amazon.utils.ConfigReader;

public class BaseTest {
	
	public BasePage basepage ;
	public WebDriver driver;
	public StartHerePage startherePage;
	public RegistrationPage registrationPage;
	public SigninPage signinPage;
	public HomePage homePage;
	public AmazonBusinessPage businessPage;
	public ConfigReader configreader;
	public ExcelReader excelReader;
	
	@BeforeMethod
	public void baseSetup() {
		// 1. Load config first
	    configreader = new ConfigReader();

	    // 2. Initialize BasePage AFTER config is loaded
	    basepage = new BasePage(driver);

	    // 3. Create driver
	    driver = basepage.getDriver(configreader.getBrowser());
	    // 4. Open URL
		driver.get(configreader.getUrl());
		// 5. Initialize all page objects with valid driver
		homePage = new HomePage(driver);
		startherePage = new StartHerePage(driver);
		registrationPage = new RegistrationPage(driver);
		signinPage = new SigninPage(driver);
		businessPage = new AmazonBusinessPage(driver);
		excelReader = new ExcelReader(".src/test/resources/testdata/"+configreader.getFileName());
	}
	
	@AfterMethod
	public void closeSetup() {
		basepage.quitDriver();
	}

}
