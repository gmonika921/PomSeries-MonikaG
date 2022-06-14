package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

//	======================================Single Element===================================================

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public By getBy(String locatortype, String locatorvalue) {

		By locator = null;

		switch (locatortype.toLowerCase()) {
		case "id":
			locator = By.id(locatorvalue);
			break;

		case "name":
			locator = By.name(locatorvalue);
			break;

		case "className":
			locator = By.className(locatorvalue);
			break;

		case "xpath":
			locator = By.xpath(locatorvalue);
			break;

		case "linkText":
			locator = By.linkText(locatorvalue);
			break;

		case "partialLinkText":
			locator = By.partialLinkText(locatorvalue);
			break;

		case "cssSelector":
			locator = By.cssSelector(locatorvalue);
			break;

		case "tagName":
			locator = By.tagName(locatorvalue);
			break;

		default:

			System.out.println("Please pass the valid locator :" + locator);

			break;
		}

		return locator;
	}

	public void doclick(By locator) {
		getElement(locator).click();
	}

	public String dogetElementText(By locator) {
		return getElement(locator).getText();

	}

	public String dogetAttributeOfElement(By locator, String value) {
		return getElement(locator).getAttribute(value);
	}

	public boolean doisDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

//	======================================Multiple Elements===================================================

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public void clickonElementfromSelection(By locator, String value) {
		List<WebElement> elelist = getElements(locator);

//		System.out.println(elelist.size());

		for (WebElement e : elelist) {
			String text = e.getText();
//			System.out.println(text);

			if (text.contains(value)) {
				e.click();
				break;
			}
		}

	}

	public void printAllElementsText(By locator) {
		List<WebElement> elelist = getElements(locator);

		System.out.println(elelist.size());

		for (WebElement e : elelist) {
			String text = e.getText();
			System.out.println(text);

		}
	}

	public List<String> getElementsTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>();
		List<WebElement> elelist = getElements(locator);

		for (WebElement e : elelist) {
			String text = e.getText();
			eleTextList.add(text);
		}

		return eleTextList;
	}

	public int getElementListCount(By locator) {
		return getElements(locator).size();
	}

//	==================================DropDown=============================================

	public void doselectdropdownbyindex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doselectdropdownbyvalue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	public void doselectdropdownbyVisiletext(By locator, String VisibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(VisibleText);
	}

	public void printSelectDropDownValue(By locator) {

		Select select = new Select(getElement(locator));

		List<WebElement> Optionlist = select.getOptions();

		System.out.println(Optionlist.size());

		for (WebElement e : Optionlist) {
			String text = e.getText();
			System.out.println(text);
		}
	}

	public void SelectValueFromDropDown(By locator, String value) {

		Select select = new Select(getElement(locator));

		List<WebElement> Optionlist = select.getOptions();

		System.out.println(Optionlist.size());

		for (WebElement e : Optionlist) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(value)) {
				e.click();
				break;
			}
		}
	}

	public int getSelectFromDropDownCount(By locator) {

		return getElements(locator).size();

	}

	public List<String> printSelectDropDownValueList(By locator) {

		List<String> ValuesList = new ArrayList<String>();

		Select select = new Select(getElement(locator));

		List<WebElement> Optionlist = select.getOptions();

		System.out.println(Optionlist.size());

		for (WebElement e : Optionlist) {
			String text = e.getText();
			ValuesList.add(text);
		}

		return ValuesList;
	}

	public void selectCountryFromDropDown(By locator, String value) {
		List<WebElement> optionlist = getElements(locator);

		System.out.println(optionlist.size());

		for (int i = -0; i < optionlist.size(); i++) {
			String text = optionlist.get(i).getText();
			System.out.println(text);

			if (text.contains(value)) {
				optionlist.get(i).click();
				break;
			}
		}
	}

//	==========================Action class Send and Click Keys===========================================
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();

	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();

	}

//	======================================Wait Utils==================================================
	public WebElement waitForElementPresent( By locator , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementVisible(By locator , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public List<WebElement> waitForElementsVisible(By locator , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public String waitForTitleContains(String titleFraction, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {

			return driver.getTitle();

		}
		return null;
	}

	public String waitForTitleValue(String titleValue, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		if (wait.until(ExpectedConditions.titleIs(titleValue))) {

			return driver.getTitle();

		}
		return null;

	}

	public String waitForUrlContains(String urlFraction, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {

			return driver.getCurrentUrl();

		}
		return null;
	}

	public String waitForURLValue(String urlIs, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		if (wait.until(ExpectedConditions.urlToBe(urlIs))) {

			return driver.getCurrentUrl();

		}
		return null;

	}
}
