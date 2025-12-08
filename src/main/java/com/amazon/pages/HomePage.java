package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class HomePage extends BasePage {

	private Actions action;

	@FindBy(xpath = "//span[normalize-space()='Account & Lists']")
	public WebElement accountsAndLists;

	@FindBy(xpath = "//div[@id='nav-flyout-ya-signin']//span[text()='Sign in']")
	public WebElement siginButton;

	@FindBy(xpath = "//div[@id='nav-flyout-ya-signin']//a[text()='Start here.']")
	public WebElement newCustomerRegisterHere;

	@FindBy(xpath = "//button[@class='a-button-text']")
	public WebElement continueShopping;

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		//clickContinueShoppingIfPresent();
	}

	public void openRegistrationPage() {

		action.moveToElement(getaacountsAndListsElement()).perform();
		getnewCustomerRegisterHere().click();
	}

	public void openSiginPage() {
		action.moveToElement(getaacountsAndListsElement()).perform();
		getSigninButton().click();
	}

	public WebElement getaacountsAndListsElement() {
		wait.waitForElementToBeVisible(accountsAndLists);
		return accountsAndLists;
	}

	public WebElement getnewCustomerRegisterHere() {
		wait.waitForElementToBeVisible(accountsAndLists);
		return newCustomerRegisterHere;
	}

	public WebElement getSigninButton() {
		wait.waitForElementToBeVisible(siginButton);
		return siginButton;
	}

	public void clickcontinueShopping() {
		getcontinueShopping().click();
	}

	public WebElement getcontinueShopping() {
		wait.waitForElementToBeVisible(continueShopping);
		return continueShopping;
	}

	public void clickContinueShoppingIfPresent() {
		try {
			wait.waitForElementToBeVisible(continueShopping);
			if (wait.getElementPresentState() == true) {

				if (getcontinueShopping().isDisplayed()) {
					clickcontinueShopping();
					System.out.println("Continue Shopping button clicked.");
				} else {
					System.out.println("Continue Shopping button NOT found. Skipping...");
				}
			}
		} catch (Exception e) {
			System.out.println("Error while checking Continue Shopping button: " + e.getMessage());
		}
	}
}
