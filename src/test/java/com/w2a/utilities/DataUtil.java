package com.w2a.utilities;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;

public class DataUtil {
	
	public static void checkExecution(String testSuiteName,String testCaseName,String runmode,ExcelReader excel) {
		if(!isSuiteRunnable(testSuiteName)) {//if the suite is runnable	
			throw new SkipException("skipping the suite:"+testCaseName+"the runmode of the suite"+testSuiteName+"is no!");
		}
		if(!isTestRunnable(testCaseName,excel)) {//if the test is runnable
			throw new SkipException("skipping the test:"+testCaseName+"the runmode of the suite"+testSuiteName+"is no!");
		}
		if(runmode.equalsIgnoreCase(constant.RUNMOD_NO))//if each data in the test is runnable
		{
		   throw new SkipException("Skipping the test :"+testCaseName+"because runmode is "+constant.RUNMOD_NO);
		   
			
			
		}
		
		System.out.println("the test: "+testCaseName+"can run");
	
	
	}
	
	public static boolean isTestRunnable(String testCaseName,ExcelReader excel) {
		
	    int rows = excel.getRowCount(constant.TESTCASE_SHEET);
	    for(int i=2; i<rows;i++) {
	    	
	    	String data = excel.getCellData(constant.TESTCASE_SHEET, constant.TESTCASE_COL, i);//search the name of the test in the excel(in TestCases) and check the runmode of the test
	    	if(data.equals(testCaseName)) {
	    		String runmode = excel.getCellData(constant.TESTCASE_SHEET, constant.RUNMOD_COL,i);
	    		if(runmode.equals(constant.RUNMOD_YES)) {
	    			return true;
	    		}
	    		else {
	    			return false;
	    		}
	    	}
	    	
	    }
		return true;
		
	}
	

	
	
	public static boolean isSuiteRunnable(String suiteName) {
		
		ExcelReader excel = new ExcelReader(constant.SUITE_PATH);
		int rows = excel.getRowCount(constant.SUITE_NAME);//get the path for suite and count the rows
		
		System.out.println("total number of row in suite : "+rows);
		for(int i =2 ; i<rows ;i++) {
			
			String data = excel.getCellData(constant.SUITE_NAME,constant.SUITE_COLNAME, i);//get the suite name from the table
			if(data.equals(suiteName)) {//check if the name of the suite from the excel is equals to suitename param 
				String runmode = excel.getCellData(constant.SUITE_NAME,constant.RUNMOD_COL , i);
				System.out.println("runmode in "+suiteName+" is: "+runmode);
				if(runmode.equalsIgnoreCase(constant.RUNMOD_YES)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
		
	}
	
	
@DataProvider
public static Object[][] getData(ExcelReader excel,String testCase){
	
	int rows = excel.getRowCount(testCase);//get total row from the sheet
	System.out.println("Total row in"+ testCase +" is: "+rows);
	
	
	String testName = testCase;
	
	int testCaseRowNum=1;
	for(testCaseRowNum=1;testCaseRowNum<rows;testCaseRowNum++) {
		
		String testCaseName = excel.getCellData(constant.SHEET_DATA, 0, testCaseRowNum);//extract the name of the test from testData sheet
		
		
		if(testCase.equalsIgnoreCase(testCaseName)) {
			break;
		}
	    
	
	    }
		
		System.out.println("The test start from row :"+testCaseRowNum);
		
		//find the test case start row
		
		int dataStartRowNum = testCaseRowNum +2;//from the row we get the name of the test we move two row to get to the data
		int testRows = 0;
		while(!(excel.getCellData(constant.SHEET_DATA, 0, dataStartRowNum+testRows)).equals("")) {//count the total row of the test until we get to an empty row
			
			testRows++;//offset from the test case name
			
		}
		System.out.println("Total rows" +testRows);
		
		int colsStartColNum = testCaseRowNum+1;
		int totalcolstest = 0;
		while(!excel.getCellData(constant.SHEET_DATA, totalcolstest,colsStartColNum).equals("")) {
			totalcolstest++;
		}
		System.out.println("the total column is"+totalcolstest);
		
		
		Object[][] data = new Object[testRows][1];
		int i=0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

			Hashtable<String, String> table = new Hashtable<String, String>();

			for (int cNum = 0; cNum < totalcolstest; cNum++) {

				 
				String testData = excel.getCellData(constant.SHEET_DATA, cNum, rNum);
				String colName = excel.getCellData(constant.SHEET_DATA, cNum, colsStartColNum);

				table.put(colName, testData);

			}

			data[i][0] = table;
			i++;

		}
		return data;
			
		 
	}
	
}




