package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.Commonspage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.DescriptionConstants;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productinfosetup() {
		commPage = new Commonspage(driver);
		productInfoPage = new ProductInfoPage(driver);
	}
	
	@DataProvider
	public Object[][] getproductData(){
		return new Object[][] {
			{ "Macbook" , "MacBook Pro" , 4},
			{"Macbook" , "MacBook Air" , 4},
			{ "Samsung", "Samsung SyncMaster 941BW" , 1 },
			{ "Apple", "Apple Cinema 30\"" , 6 }
		};
	}
	
	@Test (dataProvider = "getproductData")
	public void productImagesCountTest(String searchkey , String ProductName , int imagescount) {
		searchResultPage = commPage.doSearch(searchkey);
		// searching a product with name and clicking the search icon like Macbook
		productInfoPage = searchResultPage.selectProductName(ProductName);
		// Searching by Macbook-Pro and clicking in it
		int count = productInfoPage.getProductImagescount();
		Assert.assertEquals(count, imagescount);
	}
	
	@Test
	public void productDescriptionTest() {
		searchResultPage = commPage.doSearch("Macbook");
		// searching a product with name and clicking the search icon like Macbook
		productInfoPage = searchResultPage.selectProductName("MacBook Air");
		// Searching by Macbook-Pro and clicking in it
		String productdes = productInfoPage.getProductDescription();
		System.out.println(productdes);
		softAssert.assertTrue(productdes!=null);
		softAssert.assertFalse(productdes.isEmpty());
		softAssert.assertTrue(productdes.contains(DescriptionConstants.MACBOOK_AIR_DESCRIPTION));
		softAssert.assertAll();
		
	}
	@Test
	public void productDataTest() {
		searchResultPage = commPage.doSearch("Macbook");
		// searching a product with name and clicking the search icon like Macbook
		productInfoPage = searchResultPage.selectProductName("MacBook Air");
		// Searching by Macbook-Pro and clicking in it
		Map<String, String> actProductInfo = productInfoPage.getProductInfo();
		actProductInfo.forEach((k,v) -> System.out.println(k +":" +v)); //Brand : Apple
		
		softAssert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfo.get("Availability"), "In Stock");
//		softAssert.assertEquals(actProductInfo.get("prive"), "$1,202.00");
		softAssert.assertEquals(actProductInfo.get("name"), "MacBook Air");
		softAssert.assertAll();
		
		
//		Hash-map result - random order
//		Brand:Apple
//		Availability:In Stock
//		price:$1,202.00
//		name:MacBook Air
//		Product Code:Product 17
//		Reward Points:700
//		extra price:Ex Tax: $1,000.00
		
//		LinkedHashMap - Orderbased
//		name:MacBook Air
//		Brand:Apple
//		Product Code:Product 17
//		Reward Points:700
//		Availability:In Stock
//		price:$1,202.00
//		extra price:Ex Tax: $1,000.00
//	

//		TreeMap - sorted map, capital letter then small letter then numeric values
//		Availability:In Stock
//		Brand:Apple
//		Product Code:Product 17
//		Reward Points:700
//		extra price:Ex Tax: $1,000.00
//		name:MacBook Air
//		price:$1,202.00
		
		
	}
	
	

}
