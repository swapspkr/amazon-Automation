package com.amazon.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.basetest.BaseTest;

public class SigninPageTest extends BaseTest {

	@BeforeMethod(alwaysRun=true)
	public void setupPage() {
		//open sigin page from homepage
		homePage.openSiginPage();
	}
	
	@Test(groups={""})
	public void isLogoPresentTest() {
		try {
			Assert.assertTrue(signinPage.verifyLogo(),"ERROR -- Logo is not Present");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		
	}

	@Test(groups={""})
	public void pageTitleTest() {
		System.out.println("Current Page Title :" +signinPage.getTitleofPage());
		Assert.assertEquals(signinPage.getTitleofPage(),"amazon.in","ERROR- page title doesnot match");
	}
	
	/*Verify navigate back and forth
	 * navigate back and verify previous page
	 * navigate forward and verify
	 * refresh the page and verify if we navigate to same page 
	 */
	
	@Test(groups={""})
	public void backAndForthScenarioTest() {
		basepage.navigateBack();
		Assert.assertEquals(homePage.getTitleofPage(), "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
		basepage.navigateForward();
		Assert.assertEquals(signinPage.getTitleofPage(), "amazon.in");
		basepage.refresh();
		Assert.assertEquals(signinPage.getTitleofPage(), "amazon.in");
	}
	
	@Test
	public void siginWithUnregisterEmailTest() {
		try {
			signinPage.enterUserName("abc@test.com");
			logger.info("entered username .. ");
			signinPage.clickContinueBtn();
			logger.info("click on continue button ..");
			Assert.assertEquals(signinPage.getUnregisteredEmailError(), "Looks like you are new to Amazon");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void siginWithInvalidEmailTest() {
		signinPage.enterUserName("abcd@test");
		logger.info("entered InvalidEmail .. ");
		signinPage.clickContinueBtn();
		logger.info("click on continue button .. ");
		Assert.assertEquals(signinPage.getInvalidEmailError(), "Invalid email address.");
	}
	
	@Test
	public void siginWithValidCredentailTest() {
		signinPage.siginWithValidCredentials("abc@test.com","asdasdfsfd");
		logger.info("entered valid credentials .. ");
		signinPage.clickContinueBtn();
		logger.info("click on continue button .. ");
		Assert.assertEquals(homePage.getTitleofPage(),"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
	}
	
	
	// verify input fields/field label/links/button on sigin page
	@Test
	public void fieldsOnThePageTests() {
		
		
		// we can store all expected fields in excel and compare actual field at run time
		// Assert all fields and print if any field is not present from expected list
	}
	
	@Test
	public void validateMobileNumberField() {

	}

	@Test
	public void isUserExists() {

	}

	@Test
	public void createBusinessAccountTest() {
		signinPage.clickOnCreateBusinessAccountLink();
		Assert.assertEquals(businessPage.getTitleofPage(), "Amazon Business",
				"ERROR- Amazon Business Page not opened after clicking");
	}
}
