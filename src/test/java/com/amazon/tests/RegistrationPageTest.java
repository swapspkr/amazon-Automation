package com.amazon.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.basetest.BaseTest;

public class RegistrationPageTest extends BaseTest{
	
	
	@BeforeMethod(alwaysRun=true)
	public void setupPage() {
	
		// open registration page from homepage
		homePage.openRegistrationPage();
		
	}

	@Test(groups= {"sanity"})
	public void isLogoPresentTest() {
		
		Assert.assertTrue(basepage.verifyLogo(),"ERROR -- Logo is not Present");
	}
	
	@Test(groups= {"sanity"})
	public void pageTitleTest() {
		
		Assert.assertEquals(registrationPage.getTitleofPage(),"Amazon Registration","ERROR- page title doesnot match");
	}
	
	@Test(groups= {"regression"})
	public void verifyMobileNumberTest() {
		registrationPage.verifyMobileNumberFunctionality("8585828289", "TestUser","Testuser");
		Assert.assertEquals(registrationPage.getTitleofPage(),"Authentication required");
	}
	
	@Test
	public void signinLinkTest() {
		registrationPage.clickOnSigninLink();
		Assert.assertEquals(signinPage.getTitleofPage(), "amazon.in/ap/signin");
		Assert.assertTrue(signinPage.getSiginText().isDisplayed());
	}
	
	

}
