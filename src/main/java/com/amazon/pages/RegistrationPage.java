package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class RegistrationPage extends BasePage {

	
	@FindBy(xpath="//input[@id='ap_phone_number']")
	WebElement mobileNumberField;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement yournameField;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement passwordField;
		
	@FindBy(xpath="//input[@type='submit']")
	WebElement verifyMobileNumber;
	
	@FindBy(xpath="//a[@id='ra-sign-in-link']")
	WebElement signinInstead;
	
	@FindBy(xpath="//div[contains(text(),'Enter your name')]")
	WebElement EmailorMobileNumberText;
	
	@FindBy(xpath="//div[contains(text(),'Enter your password')]")
	WebElement PasswordText;

	
	public RegistrationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void verifyMobileNumberFunctionality(String mNumber,String yName,String pwd) {
		enterMobileNumber(mNumber);
		enterYourName(yName);
		enterPassword(pwd);
		clickVerifyMobileNumber();
	}

	public void clickOnSigninLink() {

	}
	
	// Page Actions methods 
	
	public void enterMobileNumber(String mobileNum) {
		getmobileNumberField().sendKeys(mobileNum);	
	}
	
	public void enterYourName(String yourName) {
		getyournameField().sendKeys(yourName);
	}
	
	public void enterPassword(String password) {
		getpasswordField().sendKeys(password);
	}
	
	public void clickVerifyMobileNumber() {
		getverifyMobileNumber().click();
	}
	
	// get method for page webelements 
	
	public WebElement getmobileNumberField() {
		wait.waitForElementToBeVisible(mobileNumberField);
		return mobileNumberField;
	}
		
	public WebElement getyournameField() {
		wait.waitForElementToBeClickable(yournameField);
		return yournameField;
	}
	
	public WebElement getpasswordField() {
		wait.waitForElementToBeVisible(passwordField);
		return passwordField;
	}
	
	public WebElement getverifyMobileNumber() {
		wait.waitForElementToBeVisible(verifyMobileNumber);
		return verifyMobileNumber;
	}
	
	public WebElement signinInstead() {
		wait.waitForElementToBeVisible(signinInstead);
		return signinInstead;
	}
	
	public WebElement getEmailorMobileNumberText() {
		wait.waitForElementToBeVisible(EmailorMobileNumberText);
		return EmailorMobileNumberText;
	}
	
	public WebElement getPasswordText() {
		wait.waitForElementToBeVisible(PasswordText);
		return PasswordText;
	}
}
