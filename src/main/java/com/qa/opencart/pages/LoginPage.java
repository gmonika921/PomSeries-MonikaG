package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	/*
	 * Private by locators - Obeject Repository (OR)
	 */
	private By email_id = By.id("input-email");
	private By pwd_id = By.id("input-password");
	private By login_btn = By.xpath("//input[@value = 'Login']");
	private By forgetpasswordlink = By.linkText("Forgotten Password");
	private By registerlink = By.linkText("Register");
	private By logoutMsg = By.cssSelector("div#content h1");
	
	

	/*
	 * Create public page class constructor and pass the driver. So if we call the
	 * LoginPage class, this constructor will be called along with driver
	 */

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	/*
	 * Create public page actions. It should be pulic in nature and use private
	 * variables. This is a classic example of Encapsulation.
	 */
    @Step("getting login page title")
	public String getLoginPageTitle() {
		String Title = eleUtil.waitForTitleValue(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println("Login page title is :" + Title);
		return Title;
	}
    @Step("getting login page url")
	public String getLogincurrentUrl() {
		String url = eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URLFRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println("Login page Url is :" + url);
		return url;
	}
    @Step("verifying forgot password is displayed or not")
	public boolean isForgotPassDisplayed() {
		return eleUtil.waitForElementVisible(forgetpasswordlink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();

	}
    @Step("verifying forgot password link is clickable")
	public ForgottenPage clickONForgottenPwdLink() {
		if (isForgotPassDisplayed()) {
			eleUtil.waitForElementVisible(forgetpasswordlink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).click();
			return new ForgottenPage(driver);
		}
		return null;
	}
    @Step("verifying register link displayed or not")
	public boolean isRegisterLinkDisplayed() {
		return eleUtil.waitForElementVisible(registerlink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
    @Step("login with username{0} and password{1}")
	public AccountsPage doLogin(String username, String password) {

		eleUtil.waitForElementVisible(email_id, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).sendKeys(username);
		eleUtil.waitForElementVisible(pwd_id, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).sendKeys(password);
		eleUtil.waitForElementVisible(login_btn, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).click();
		return new AccountsPage(driver);
	}
    @Step("logging out successfully")
	public String getLogoutMsg() {
		String logoutmsg = eleUtil.waitForElementVisible(logoutMsg, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
		System.out.println("The logout msg is : " +logoutmsg);
		return logoutmsg;
	}
    @Step("navigating to Register page")
	public RegisterPage clickRegisterLink() {
		eleUtil.doclick(registerlink);
		return new RegisterPage(this.driver);
	}


}
