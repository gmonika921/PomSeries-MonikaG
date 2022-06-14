package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By header = By.cssSelector("div#logo h1 a");
	private By accountheadersection = By.cssSelector("div#content h2");
	private By searchfield = By.name("search");
	private By logoutlink = By.linkText("Logout");

	// Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// Action class

	public String getAccountPageTitle() {
		String Title = eleUtil.waitForTitleValue(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println(Title);
		return Title;

	}

	public String getAccountPageUrl() {
		String url = eleUtil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URLFRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println(url);
		return url;
	}

	public String getAccountPageheader() {
		return eleUtil.waitForElementVisible(header, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
	}

	public ArrayList<String> getAccPageHeaderSection() {
		List<WebElement> accsecList = eleUtil.waitForElementsVisible(accountheadersection, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		ArrayList<String> accSectionValList = new ArrayList<String>();

		for (WebElement e : accsecList) {
			String text = e.getText();
			accSectionValList.add(text);

		}

		return accSectionValList;

	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutlink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}

	public boolean isSearchFieldExist() {
		return eleUtil.waitForElementVisible(searchfield, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}

	public LoginPage clickOnLogout() {
		if (isLogoutLinkExist()) {
			eleUtil.waitForElementVisible(logoutlink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).click();
			return new LoginPage(driver);
		}
		return null;
	} 
	
	
	

}
