package com.w2a.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;


public  class dataProvider  {
	
	@DataProvider(name="purchase" ,parallel=true)
	public static Object[][] getPurchase(Method m){//the name of the method equel to the test case
		
		System.out.println(m.getName());///reflection
		ExcelReader excel = new ExcelReader(constant.PURCHASE1_SUITE);
		String testcase = m.getName();
		return DataUtil.getData(excel, testcase);
	}
	
	/*
	@DataProvider(name="purchasePs4" )
	public static Object[][] getPs4(Method m ){
		

		System.out.println(m.getName());///reflection
		ExcelReader excel = new ExcelReader(constant.PURCHASE1_SUITE);
		String testcase = m.getName();
		return DataUtil.getData(excel, testcase);
		
	}
*/
	
}
