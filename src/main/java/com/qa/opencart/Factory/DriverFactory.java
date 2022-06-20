package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.customexceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	/*
	 * This class is for driver 
	 */
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method will return the driver on the basis of browsername
	 * @param Properties prop
	 * return - will return webdriver
	 * @throws FrameworkException 
	 */


	
	public WebDriver init_driver(Properties prop) throws FrameworkException {
//		String browsername = prop.getProperty("browser").trim();
		String browsername = System.getProperty("browser");
		System.out.println("Browser name is : " +browsername);
		optionsManager = new OptionsManager(prop);
		if(browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		
		else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
		}
		
		else if (browsername.equalsIgnoreCase("safari")) {
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		else {
			System.out.println("Please enter the correct browsername");
			throw new FrameworkException("no browser found...");
	}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	
	
	}
	

	/** It will return the local copy of webdriver as we put set method to set the driver.
	 * This method will return the local copy of the driver and give to the 5 individual thread over there.
	 * Synchronized mode so no thread is blocking other thread, it's good to use this.
	 */
	
	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * This method is used to initialise the properties from respective env. prop files
	 * @return this returns properties class objects with all the config properties
	 */
	
	public Properties init_prop() {
		FileInputStream ip = null;
		prop = new Properties();
		
		// maven command line arguments
		// mvn clean install -Denv = "qa"
		
		String envName = System.getProperty("env");
		System.out.println("Running test on environment " +envName);
		
		if (envName == null) {
			System.out.println("Since no env is given....hence running on qa");
			try {
				ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
			
			else {
				try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
				case "dev":
					ip = new FileInputStream("./src/test/resource/config/dev.config.properties");
				case "stage":
					ip = new FileInputStream("./src/test/resource/config/stage.config.properties");
				case "uat":
					ip = new FileInputStream("./src/test/resource/config/uat.config.properties");
				case "prod":
					ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
					
					break;

				default:
					System.out.println("Please pass the right env value: " +envName);
					throw new FrameworkException("no env found");
					
				}
			}
			
				catch(FileNotFoundException e) {
					e.printStackTrace();
				} catch (FrameworkException e) {
					e.printStackTrace();
				}
			}
		
	try {
		prop.load(ip);
	} catch (IOException e) {
		e.printStackTrace();
	}	
		
		
		return prop;
	}

	
	/**
	 * This method will help to take screenshot
	 */
	
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./Screeshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	

}