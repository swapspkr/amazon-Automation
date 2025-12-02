package com.amazon.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.basetest.BaseTest;

public class RegistrationPageTest extends BaseTest {

	@BeforeMethod
	public void setupPage() {

		// open registration page from homepage
		homePage.openRegistrationPage();

	}

	@Test
	public void isLogoPresentTest() {

		Assert.assertTrue(registrationPage.verifyLogo(), "ERROR -- Logo is not Present");
	}

	@Test
	public void pageTitleTest() {

		Assert.assertEquals(registrationPage.getTitleofPage(), "Amazon Registration",
				"ERROR- page title doesnot match");
	}

	// verify input fields/field label/links/button on sigin page
	@Test
	public void fieldsOnThePageTests() {

		signinPage.verifyPresenceOfElementOnPage();
		// we can store all expected fields in excel and compare actual field at run
		// time
		// Assert all fields and print if any field is not present from expected list
	}

	@Test
	public void verifyMobileNumberTest() {
		registrationPage.verifyMobileNumberFunctionality();
	}

	@Test
	public void verifySigninLink() {
		registrationPage.clickOnSigninLink();
		Assert.assertEquals(signinPage.getTitleofPage(), "Amazon Sign In",
				"ERROR- Signin Page not opened after clicking");
	}
}
