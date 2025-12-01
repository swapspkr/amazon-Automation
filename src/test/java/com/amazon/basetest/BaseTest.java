package com.amazon.basetest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.amazon.base.BasePage;
import com.amazon.pages.HomePage;
import com.amazon.pages.RegistrationPage;
import com.amazon.pages.SigninPage;

public class BaseTest {
	
	public BasePage basepage ;
	public WebDriver driver;
	public RegistrationPage registrationPage;
	public SigninPage signinPage;
	public HomePage homePage;
	
	@BeforeMethod
	public void baseSetup() {
		basepage = new BasePage();
		driver = basepage.getDriver("chrome");
		driver.get("https://www.amazon.in/");
		registrationPage = new RegistrationPage();
		signinPage = new SigninPage();
		
	}
	
	@AfterMethod
	public void closeSetup() {
		basepage.quitDriver();
	}

}
