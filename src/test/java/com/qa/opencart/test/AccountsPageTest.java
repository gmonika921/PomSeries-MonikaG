package com.qa.opencart.test;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.Commonspage;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeTest

	public void setup() {
//		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		accPage = new AccountsPage(this.driver);
	}

	@Test(enabled = false)
	public void AccountPageTitleTest() {
		String accpagetitle = accPage.getAccountPageTitle();
		System.out.println("The account page title is :" + accpagetitle);
		Assert.assertEquals(accpagetitle, Constants.ACCOUNTS_PAGE_TITLE);

	}

	@Test(enabled = false)
	public void AccountPageUrlTest() {
		String accpageurl = accPage.getAccountPageUrl();
		System.out.println("Account page url is :" + accpageurl);
		Assert.assertTrue(accpageurl.contains(Constants.ACCOUNTS_PAGE_URLFRACTION));
	}

	@Test(enabled = false)
	public void isSearchFieldexistTest() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}

	@Test(enabled = false)
	public void isLogoutLinkDisplayedTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test(enabled = false)
	public void accountPageHeaderTest() {
		String actualheader = accPage.getAccountPageheader();
		System.out.println("Account section Header is :" + actualheader);
		Assert.assertEquals(actualheader, Constants.ACCOUNTS_PAGE_HEADER);
	}

	@Test(enabled = false)
	public void accountPageHeaderSecTest() {
		List<String> actualaccheaderseclist = accPage.getAccPageHeaderSection();
		System.out.println("Account Page Header Sec List is :" + actualaccheaderseclist);
		Collections.sort(actualaccheaderseclist);
		Collections.sort(Constants.ACCOUNTS_PAGE_SECTION_HEADERLIST);
		Assert.assertEquals(actualaccheaderseclist, Constants.ACCOUNTS_PAGE_SECTION_HEADERLIST);

	}

	@Test(enabled = false)
	public void isUserLoggedout() {
		accPage.clickOnLogout();
		String expectedlogoutmsg = loginPage.getLogoutMsg();
		System.out.println("The logout message is:" + expectedlogoutmsg);
		Assert.assertEquals(expectedlogoutmsg, Constants.LOGOUT_MSG);

	}

	@DataProvider
	public Object[][] getProductname() {
		return new Object[][] {

				{ "Macbook" }, { "iMac" }, { "Samsung" }, { "Apple" }

		};

	}

	@Test(dataProvider = "getProductname")
	public void doSearch(String productName) {
		commPage = new Commonspage(driver);
		searchResultPage = commPage.doSearch(productName);
		// searching a product with name and clicking the search icon
		String reultPageHeader = searchResultPage.searchResultPageHeader();
		// after searching by dosearch - it's getting header
		Assert.assertTrue(reultPageHeader.contains(productName));
		// Asserting whether product name search and getting header is equal or not or
		// using contains function
	}

	@DataProvider
	public Object[][] getProductdata() {
		return new Object[][] {

				{ "Macbook", "MacBook Pro" }, { "Macbook", "MacBook Air" }, { "Samsung", "Samsung SyncMaster 941BW" },
				{ "Apple", "Apple Cinema 30\"" }

		};

	}

	@Test(dataProvider = "getProductdata")
	public void productinfoTest(String productName, String mainProductnames) {
		commPage = new Commonspage(driver);
		searchResultPage = commPage.doSearch(productName);
		// searching a product with name and clicking the search icon like Macbook
		productInfoPage = searchResultPage.selectProductName(mainProductnames);
		// Searching by Macbook-Pro and clicking in it
		String mainProductNameValue = productInfoPage.getMainProductName();
		// Capturing the product info page header
		System.out.println(mainProductNameValue);
		// Printing it
		Assert.assertEquals(mainProductNameValue, mainProductnames);
		// Comparing the macbook-pro that is mainProductnames to mainProductvalue

	}
	

}


