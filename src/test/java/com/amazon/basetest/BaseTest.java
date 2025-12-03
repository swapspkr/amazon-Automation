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
	
	@BeforeMethod
	public void baseSetup() {
		basepage = new BasePage(driver);
		driver = basepage.getDriver(configreader.getBrowser());
		driver.get(configreader.getUrl());
		homePage = new HomePage(driver);
		startherePage = new StartHerePage(driver);
		registrationPage = new RegistrationPage(driver);
		signinPage = new SigninPage(driver);
		businessPage = new AmazonBusinessPage(driver);
		configreader = new ConfigReader();
	}
	
	@AfterMethod
	public void closeSetup() {
		basepage.quitDriver();
	}

}
