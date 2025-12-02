package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.base.BasePage;

public class RegistrationPage extends BasePage {

	@FindBy(xpath="//input[@id='ap_email_login']")
	WebElement email_login;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement continueButton;
	
	@FindBy(xpath="//input[@id='ap_password']")
	WebElement password;
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	public void verifyMobileNumberFunctionality() {

	}

	public void clickOnSigninLink() {

	}

}
