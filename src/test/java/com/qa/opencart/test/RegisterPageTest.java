package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	
	public void regpagesetup() {
	regpage	= loginPage.clickRegisterLink();
	}
	
	public String getRandomEMailId() {
		Random random = new Random();
		String email = "febautomation" +random.nextInt(1000) +"@gmail.com";
		return email;
		
	}
	
	@DataProvider
	public Object[][] getRegisterTestData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAMES);
		return regData;
		
	}
	
	
	@Test (dataProvider = "getRegisterTestData")
	public void userRegisterTest(String firstname, String lastname, String telephone , 
				String password, String subscribe) {
		Assert.assertTrue
		(regpage.userRegister(firstname, lastname, getRandomEMailId(), telephone, password, subscribe));
		
	}

}
