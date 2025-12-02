package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class HomePage extends BasePage{

	@FindBy(id="nav-link-accountList")
	public WebElement accountsAndLists;
	
	@FindBy(xpath="//div[@id='nav-flyout-ya-signin']//span[text()='Sign in']")
	public WebElement siginButton;
	
	@FindBy(xpath="//div[@id='nav-flyout-ya-signin']//a[text()='Start here.']")
	public WebElement newCustomerRegisterHere;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	public void openRegistrationPage() {
		
	}
	
	public void openSiginPage() {
		
	}

	

	
}
