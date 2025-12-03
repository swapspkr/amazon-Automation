package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class StartHerePage extends BasePage{

	@FindBy(xpath="//input[@id='ap_email_login']")
	WebElement email_login;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement continueButton;
	
	@FindBy(xpath="//input[@id='ap_password']")
	WebElement password;
	
	@FindBy(xpath="//span[@id='intention-submit-button']//input[@type='submit']")
	WebElement proceedToCreateAccount;
	
	@FindBy(xpath="//h1[normalize-space()='Looks like you are new to Amazon']")
	WebElement newToAmazonMessage;
	
	public StartHerePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement getemail_login() {
		wait.waitForElementToBeVisible(email_login);
		return email_login;
	}
	public WebElement getpassword() {
		wait.waitForElementToBeVisible(password);
		return password;
	}
	public WebElement getcontinueButton() {
		wait.waitForElementToBeClickable(continueButton);
		return continueButton;
	}
	
	public WebElement getnewToAmazonMessage() {
		wait.waitForElementToBeVisible(newToAmazonMessage);
		return newToAmazonMessage;
	}
	public WebElement getproceedToCreateAccountButton() {
		wait.waitForElementToBeVisible(proceedToCreateAccount);
		return proceedToCreateAccount;
	}
	

}
