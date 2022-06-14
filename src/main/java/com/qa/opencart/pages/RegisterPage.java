package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By emailid = By.id("input-email");
	private By telephonenum = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	
	private By subscribeyes = By.xpath("(//label[@class = 'radio-inline'])[position() = 1]/input");
	private By subscribeNo = By.xpath("(//label[@class = 'radio-inline'])[position() = 2]/input");
	
	private By agreebtn = By.name("agree");
	private By continuebtn = By.xpath("//input[@type = 'submit']");
	
	private By registerlink = By.linkText("Register");
	private By logoutlink = By.linkText("Logout");
	private By successMsg = By.cssSelector("div#content h1");

		public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		}
		
		public boolean userRegister (String firstname, String lastname, String emailid, String telephonenum, 
				                  String password,String subscribe ) {
			eleUtil.doSendKeys(this.firstname,firstname );
			eleUtil.doSendKeys(this.lastname,lastname );
			eleUtil.doSendKeys(this.emailid,emailid );
			eleUtil.doSendKeys(this.telephonenum, telephonenum);
			eleUtil.doSendKeys(this.password,password);
			
			
			if(subscribe.equalsIgnoreCase("yes")) {
				eleUtil.doclick(subscribeyes);
			}
			else {
				eleUtil.doclick(subscribeNo);
			}
			
			eleUtil.doclick(agreebtn);
			eleUtil.doclick(continuebtn);
			
			String successmsg = eleUtil.waitForElementVisible(successMsg, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
			System.out.println("success Msg is :" +successmsg);
			
			if(successmsg.contains(Constants.REGISTER_SUCCESS_MSG)) {
				eleUtil.doActionsClick(logoutlink);
				eleUtil.doclick(registerlink);
				return true;
			}
			else {
				return false;
			}
			
		}
		
		
		
		

	

}
