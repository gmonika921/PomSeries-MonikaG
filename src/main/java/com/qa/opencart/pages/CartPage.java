package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class CartPage {
	
	private By cart = By.id("by.cart");
	private By item = By.id("item-it");
	
	public void printcart() {
		System.out.println("This is my cart page");
		System.out.println("click on cart");
	}

}
