package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class SigninPage extends BasePage {

	@FindBy(xpath = "//h1[normalize-space()='Sign in or create account']")
	WebElement siginOrCreateAccount;

	@FindBy(id = "ap_email_login")
	WebElement email;

	@FindBy(xpath = "//input[@id='ap_password']")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement continueBtn;

	@FindBy(xpath = "//a[@id='auth-fpp-link-bottom']")
	WebElement forgotPasswordLink;

	@FindBy(xpath = "//h1[normalize-space()='Sign in']")
	WebElement SigninText;

	@FindBy(id = "signInSubmit")
	WebElement SigninBtn;

	@FindBy(xpath = "//a[@id='ab-registration-ingress-link']")
	WebElement createNewBusinessAccount;

	@FindBy(xpath = "//div[@id='invalid-email-alert']//div[@class='a-alert-content' and contains(text(),'Invalid email address.')]")
	WebElement invalidEmailError;

	@FindBy(xpath = "//h1[normalize-space()='Looks like you are new to Amazon']")
	WebElement unRegistedUserMessage;

	@FindBy(xpath = "//span[contains(@id,'intention-submit-button')]//input[@type='submit']")
	WebElement proceedToCreateAccount;

	@FindBy(xpath="//a[@class='a-link-nav-icon']")
	WebElement amazonLogo;
	
	public SigninPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void setupCorrectRegistrationPage() {
		wait.waitForElementToBeVisible(siginOrCreateAccount);
		if (wait.getElementPresentState() == true) {
			if (getsignOrCreateAccountHeader().isDisplayed()) {
				getemail().sendKeys("testsdsf@dfdtrtrs.com ");
				clickContinueBtn();
				clickOnproceedToCreateAccount();
			} else {
				System.out.println("Facing issue in setting up registration page");
			}
		}
	}

	public WebElement getsignOrCreateAccountHeader() {
		return siginOrCreateAccount;
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
	
	public WebElement getAmazonLogo() {
		wait.waitForElementToBeVisible(amazonLogo);
		return amazonLogo;
	}

	public void enterUserName(String email) {
		try {
			getemail().sendKeys(email);
			logger.info("Entered email =>" + email);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void clickContinueBtn() {
		try {
			getcontinueBtn().click();
			logger.info("clicked on continue button =>");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void verifyUserExists() {

	}

	public void enterPassword(String password) {
		try {
			getpassword().sendKeys(password);
			logger.info("Entered password =>" + password);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void clickSignInBtn() {
		try {
			getSiginBtn().click();
			logger.info("clicked Sigin button =>");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void clickOnproceedToCreateAccount() {
		try {
			proceedToCreateAccount.click();
			logger.info("clicked on proceedToCreateAccount =>");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void clickOnCreateBusinessAccountLink() {

		try {
			getcreateNewBusinessAccount().click();
			logger.info("clicked on createNewBusinessAccount =>");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void siginWithValidCredentials(String email, String password) {

		try {
			enterUserName(email);
			logger.info("Entered username =>" + email);
			enterPassword(password);
			logger.info("Entered password =>" + password);
			clickSignInBtn();
			logger.info("Click on signin Button");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public String getUnregisteredEmailError() {
		return unRegistedUserMessage.getText();
	}

	public void verifyPresenceOfElementOnPage() {

	}
	
	public boolean verifyLogo() {
		return getAmazonLogo().isDisplayed();
	}
	
	public String getTitleofPage() {
		return driver.getTitle();
	}
}
