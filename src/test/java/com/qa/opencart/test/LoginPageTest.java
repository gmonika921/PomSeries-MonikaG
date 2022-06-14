package com.qa.opencart.test;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC-- 100 Design login Page for open cart application")
@Story ("UserStory (US) - 101 - Design login page features")
public class LoginPageTest extends BaseTest {

	@Description("Verify Login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPagetitleTest() {
			String actualTitle = loginPage.getLoginPageTitle();
			System.out.println("Login Page title is : "  +actualTitle);
			Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
		}
	@Description("Verify Login Page URL Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actualUrl = loginPage.getLogincurrentUrl();
		System.out.println("Login Page url is :" +actualUrl);
		Assert.assertTrue(actualUrl.contains(Constants.LOGIN_PAGE_URLFRACTION));
	}
	
	@Description("Verify Login Page PwdLink Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 3)
	public void isPwdLinkExistTest() {
	 Assert.assertTrue(loginPage.isForgotPassDisplayed());
	
	}
	
	@Description("Verify Login Page Registerlink Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void isRegisterLinkExistTest() {
	 Assert.assertTrue(loginPage.isRegisterLinkDisplayed());
	
	}
	
	@Description("Verify user is able to login with correct credentials")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void loginTest() {
	 AccountsPage accpage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	 String title = accpage.getAccountPageTitle();
	 Assert.assertEquals(title,Constants.ACCOUNTS_PAGE_TITLE);
	 Assert.assertTrue(accpage.isLogoutLinkExist());
	
	}

}
