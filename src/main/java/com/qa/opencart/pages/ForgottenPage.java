package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ForgottenPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchfield = By.name("search");
	private By forgotmsg = By.cssSelector("div#content h1");
	private By email_id = By.id("input-email");
	private By continuebtn = By.cssSelector("input.btn.btn-primary");
	private By loginlink = By.linkText("Login");
	
	public ForgottenPage(WebDriver driver){
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getForgottenPageTitle() {
		String title = eleUtil.waitForTitleValue(Constants.FORGOTTEN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println("Forgotten page title is :" +title);
		return title;
	}
	
	public String getForgottenPageUrl() {
		String url = eleUtil.waitForUrlContains(Constants.FORGOTTEN_PAGE_URLFRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println("Forgotten page url is :" +url);
		return url;
	}
	

	
	public boolean isSearchFieldExist() {
		return eleUtil.waitForElementVisible(searchfield, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	public String getForgotMsg() {
		return eleUtil.waitForElementVisible(forgotmsg, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
	}
	
	public boolean isLoginLinkexist() {
		return eleUtil.waitForElementVisible(loginlink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	public LoginPage clickOnContinue(String usernameforgotPage) {
		eleUtil.waitForElementVisible(email_id, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).sendKeys(usernameforgotPage);;
		eleUtil.waitForElementVisible(continuebtn, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).click();;
		return new LoginPage(driver);
	}

}
