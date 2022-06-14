package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ForgottenPageTest extends BaseTest {

	@BeforeTest

	public void setup() {
		loginPage.clickONForgottenPwdLink();
	}

	@Test
	public void forgotPageTitleTest() {
		String actualtitle = forgetPage.getForgottenPageTitle();
		System.out.println("The actual title of forgottenpage is:" + actualtitle);
		Assert.assertEquals(actualtitle, Constants.FORGOTTEN_PAGE_TITLE);

	}

	@Test
	public void forgotPageUrlTest() {
		String actualurl = forgetPage.getForgottenPageUrl();
		System.out.println("The actual url of forgottenpage is:" + actualurl);
		Assert.assertTrue(actualurl.contains(Constants.FORGOTTEN_PAGE_URLFRACTION));
	}

	@Test
	public void isSearchfieldexistTest() {
		Assert.assertTrue(forgetPage.isSearchFieldExist());
	}

	@Test
	public void isLoginLinkexistTest() {
		Assert.assertTrue(forgetPage.isLoginLinkexist());
	}

	@Test
	public void logoutMsgTest() {
		String expectedlogoutmsg = forgetPage.getForgotMsg();
		System.out.println("The logout messgae in forgotten password page is :" + expectedlogoutmsg);
		Assert.assertTrue(expectedlogoutmsg.contains(Constants.FORGOTTEN_PAGE_MSG));
	}

	@Test
	public void isUserenteredemail_idTest() {
		forgetPage.clickOnContinue(prop.getProperty("usernameForgotPage").trim());
	}

}
