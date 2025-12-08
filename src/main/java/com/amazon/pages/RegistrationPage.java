package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class RegistrationPage extends BasePage {

	@FindBy(xpath = "//input[@id='ap_phone_number']")
	WebElement mobileNumberField;

	@FindBy(xpath = "//input[@id='ap_customer_name']")
	WebElement yournameField;

	@FindBy(xpath = "//input[@id='ap_password']")
	WebElement passwordField;

	@FindBy(xpath = "//input[@id='continue']")
	WebElement verifyMobileNumber;

	@FindBy(xpath = "//a[@id='ra-sign-in-link']")
	WebElement signinInstead;

	@FindBy(xpath = "//div[contains(text(),'Enter your name')]")
	WebElement EmailorMobileNumberText;

	@FindBy(xpath = "//div[contains(text(),'Enter your password')]")
	WebElement PasswordText;

	public RegistrationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void verifyMobileNumberFunctionality(String mNumber, String yName, String pwd) {
		enterMobileNumber(mNumber);
		enterYourName(yName);
		enterPassword(pwd);
		clickVerifyMobileNumber();
	}

	public void clickOnSigninLink() {

	}

	// Page Actions methods

	public void enterMobileNumber(String mobileNum) {
		try {
			getmobileNumberField().sendKeys(mobileNum);
			logger.info("Entered Mobile Number => " + mobileNum);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void enterYourName(String yourName) {
		try {
			getyournameField().sendKeys(yourName);
			logger.info("Entered Name => " + yourName);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void enterPassword(String password) {
		try {
			getpasswordField().sendKeys(password);
			logger.info("Entered password => " + password);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void clickVerifyMobileNumber() {
		try {
			getverifyMobileNumber().click();
			logger.info("clicked on verifyMobileNumber button..");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
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

	public boolean verifyPresenceOfElementsOnPage(List<String> fieldNames) {
		boolean result = true;
		int count = 0;
		for (String fieldname : fieldNames) {
			WebElement element = getElementByFieldName(fieldname);

			if (element == null) {
				System.out.println(
						"Element is null,so please make sure to fieldName" + fieldname + " is added in excelsheet");
				result = false;
				continue;
			}

			if (wait.getElementPresentState() == true) {
				if (element.isDisplayed() == true) {
					System.out.println(fieldname + "is present on the page");
				}
			} else {
				System.out.println(fieldname + "is not present on the page");
				count++;
			}
		}
		if (count != 0) {

			result = false;
		}

		return result;
	}

	private WebElement getElementByFieldName(String fieldname) {
		WebElement element = null;
		try {
			switch (fieldname.trim().toLowerCase()) {
			case "your name":
				getyournameField();
				element = yournameField;
				break;
			case "mobile number":
				getmobileNumberField();
				element = mobileNumberField;
				break;
			case "password":
				getpasswordField();
				element = passwordField;
				break;
			case "verify mobile number":
				getverifyMobileNumber();
				element = verifyMobileNumber;
				break;
			case "sign in instead":
				signinInstead();
				element = signinInstead;
				break;
			default:
				throw new IllegalArgumentException("Invalid FieldName" + fieldname);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return element;
	}
	
	@Override
	public String getTitleofPage() {
		return driver.getTitle();
	}
}
