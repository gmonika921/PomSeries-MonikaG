package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static String TEST_DATA_SHEET_PATH = ".src/test/resource/TestData";
	private static Workbook book;
	private static Sheet sheet;
	
	
	
	public static Object[][] getTestData(String sheetname){
		
		Object data[][] = null;
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH); 
			// This is from java to setup the path of excel
			book = WorkbookFactory.create(ip); // to create one excel in here so we can fill the data
			sheet = book.getSheet(sheetname); // In order to get to the sheet
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; 
			//getLastRowNum means go to the last row and check the number
			//getRow means go to the first row which is 0 in java and 1 in execl and then getLastCellNum means go to the 
			//last cell number of that excel sheet
			//initialise the 2d object static array
			
			
			// using loop to read data from each co-ordinate and i+1 because in co-ordinate 00 we have headings not data
			for (int i = 0; i<sheet.getLastRowNum(); i++) {
				for (int j = 0; j<sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
			
		 
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (InvalidFormatException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return data;

}	
	}
		

	


