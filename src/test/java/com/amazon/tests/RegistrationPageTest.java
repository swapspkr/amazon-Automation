package com.amazon.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.basetest.BaseTest;

public class RegistrationPageTest extends BaseTest {

	@BeforeMethod(alwaysRun = true)
	public void setupPage() {

		// open registration page from homepage
		homePage.openRegistrationPage();
		signinPage.setupCorrectRegistrationPage();
	}

	@Test(groups = { "" })
	public void isLogoPresentTest() {

		try {
			Assert.assertTrue(basepage.verifyLogo(), "ERROR -- Logo is not Present");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Test(groups={"sanity"})
	public void pageTitleTest() {

		try {
			String actualTitle = registrationPage.getTitleofPage();
			String expectedTitle = "Amazon Registration";
			Assert.assertEquals(actualTitle, expectedTitle, "ERROR- page title doesnot match");
			logger.info("Page Title : => "+actualTitle);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Test(groups = { "regression" })
	public void verifyMobileNumberTest() {
		registrationPage.verifyMobileNumberFunctionality("8585828289", "TestUser", "Testuser");
		Assert.assertEquals(registrationPage.getTitleofPage(), "Authentication required");
	}

	@Test
	public void signinLinkTest() {
		registrationPage.clickOnSigninLink();
		Assert.assertEquals(signinPage.getTitleofPage(), "amazon.in/ap/signin");
		Assert.assertTrue(signinPage.getSiginText().isDisplayed());
	}

	@Test(groups = { "" })
	public void fieldsOnPageTest() {
		List<String> fieldName = excelReader.getFieldNameFromExcel("RegistrationPage");
		Assert.assertTrue(registrationPage.verifyPresenceOfElementsOnPage(fieldName));

	}

}
