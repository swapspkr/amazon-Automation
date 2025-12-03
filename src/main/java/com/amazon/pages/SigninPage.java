package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.base.BasePage;

public class SigninPage extends BasePage{
	
	
	@FindBy(id="ap_email_login")
	WebElement email;
	
	@FindBy(xpath="//input[@id='ap_password']")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement continueBtn;
	
	@FindBy(xpath="//a[@id='auth-fpp-link-bottom']")
	WebElement forgotPasswordLink;
	
	@FindBy(xpath="//h1[normalize-space()='Sign in']")
	WebElement SigninText;
	
	@FindBy(id="signInSubmit")
	WebElement SigninBtn;
	
	@FindBy(xpath="//a[@id='ab-registration-ingress-link']")
	WebElement createNewBusinessAccount;
	
	@FindBy(xpath="//div[@id='invalid-email-alert']//div[@class='a-alert-content' and contains(text(),'Invalid email address.')]")
	WebElement invalidEmailError;
	
	@FindBy(xpath="//h1[normalize-space()='Looks like you are new to Amazon']")
	WebElement unRegistedUserMessage;
	
	public SigninPage(WebDriver driver) {
		super(driver);
		
	}
	
	public WebElement getSiginText() {
		wait.waitForElementToBeVisible(SigninText);
		return SigninText;
	}
	
	public WebElement getpassword() {
		wait.waitForElementToBeVisible(password);
		return password;
	}
	
	public WebElement getemail() {
		wait.waitForElementToBeVisible(email);
		return email;
	}
	
	public WebElement getcontinueBtn() {
		wait.waitForElementToBeVisible(continueBtn);
		return continueBtn;
	}
	public WebElement getSiginBtn() {
		wait.waitForElementToBeVisible(SigninBtn);
		return SigninBtn;
	}
	
	public WebElement getcreateNewBusinessAccount() {
		wait.waitForElementToBeClickable(createNewBusinessAccount);
		return createNewBusinessAccount;
	}
	
	public WebElement getInvalidEmailError() {
		wait.waitForElementToBeVisible(invalidEmailError);
		return invalidEmailError;
	}
	
	public void enterUserName(String email) {
		getemail().sendKeys(email);
	}
	
	public void clickContinueBtn() {
		getcontinueBtn().click();
	}
	
	public void verifyUserExists() {
		
	}
	
	public void enterPassword(String password) {
		getpassword().sendKeys(password);
	}
	
	public void clickSignInBtn() {
		getSiginBtn().click();
	}
	
	public void clickOnCreateNewAccount() {
		
	}

	public void clickOnCreateBusinessAccountLink() {
		
		getcreateNewBusinessAccount().click();
	}

	public void siginWithValidCredentials(String email, String password) {
		
		enterUserName(email);
		enterPassword(password);
		clickSignInBtn();
	}

	public String getUnregisteredEmailError() {
		return unRegistedUserMessage.getText();
		 
	}

	

	public void verifyPresenceOfElementOnPage() {
		// TODO Auto-generated method stub
		
	}
}
