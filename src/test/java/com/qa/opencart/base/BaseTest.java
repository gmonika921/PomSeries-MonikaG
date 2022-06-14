package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.Commonspage;
import com.qa.opencart.pages.ForgottenPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {

	DriverFactory df;
	protected Properties prop;
	public WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ForgottenPage forgetPage;
	protected Commonspage commPage;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage regpage;
	
	protected SoftAssert softAssert;

	@BeforeTest
	/*
	 * This setup method is responsible to initialise the driver. but we already
	 * have Driver factory for that. So here we create object of that
	 * If we use Before method here, the browser will be launched 5 times depending on the number 
	 * of test cases in testng. If the application is not stable, we can use Before method
	 */

	public void Setup() {

		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		accPage = new AccountsPage(driver);
		forgetPage = new ForgottenPage(driver);
		softAssert = new SoftAssert();

	}

	@AfterTest

	public void teardown() {
		driver.quit();
	}

}
