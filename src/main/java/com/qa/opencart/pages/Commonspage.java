package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;


public class Commonspage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By searchfield = By.name("search");
	private By searchicon = By.cssSelector("div#search button");
	
	public Commonspage(WebDriver driver) {
		this.driver= driver;
		eleUtil = new ElementUtil(this.driver);
	}

	
	public SearchResultPage doSearch(String productName) {
		WebElement searchEle = eleUtil.waitForElementPresent(searchfield,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		searchEle.clear();
		searchEle.sendKeys(productName);
		eleUtil.doclick(searchicon);
		return new SearchResultPage(this.driver);
		
		
	}
}
