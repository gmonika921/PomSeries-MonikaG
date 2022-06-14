package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By resultPageHeader = By.cssSelector("div#content h1");
	
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String searchResultPageHeader() {
		return eleUtil.dogetElementText(resultPageHeader);
	}
	
	public ProductInfoPage selectProductName(String mainProductName) {
		WebElement mainProductele = eleUtil.waitForElementVisible(By.linkText(mainProductName), Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		mainProductele.click();
		return new ProductInfoPage(this.driver);
		
	}

}
