package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.base.BasePage;

public class SigninPage extends BasePage{
	
	
	@FindBy(xpath="//input[@id='ap_password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='ap_password']")
	WebElement signInButton;
	
	@FindBy(xpath="//a[@id='auth-fpp-link-bottom']")
	WebElement forgotPasswordLink;
	
	public SigninPage(WebDriver driver) {
		super(driver);
		
	}

	public void enterUserName(String email) {
		
	}
	
	public void clickContinueBtn() {
		
	}
	
	public void verifyUserExists() {
		
	}
	
	public void enterPassword() {
		
	}
	
	public void clickSignInBtn() {
		
	}
	
	public void clickOnCreateNewAccount() {
		
	}

	public void clickOnCreateBusinessAccountLink() {
		
		
	}

	public void siginWithValidCredentials(String email, String password) {
		
		
	}

	public String getUnregisteredEmailError() {
		
		return null;
	}

	public String getInvalidEmailError() {
		// TODO Auto-generated method stub
		return null;
	}

	public void verifyPresenceOfElementOnPage() {
		// TODO Auto-generated method stub
		
	}
}
