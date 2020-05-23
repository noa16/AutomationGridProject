package com.w2a.suite.purchaseset1.testcase;

import java.net.MalformedURLException;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.DataUtil;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.dataProvider;

public class PurchasePs4 extends TestBase {

	@Test(dataProviderClass = dataProvider.class, dataProvider = "purchase")
	public void purchaseps4(Hashtable<String, String> data) {

		super.setUp();
		ExcelReader excel = new ExcelReader(
				"C:\\Users\\noa\\eclipse-workspace\\DataGridProject\\src\\main\\resources\\testdata\\Suite.xlsx");

		System.out.println("what is runmode" + data.get("runmode"));
		DataUtil.checkExecution("PurchaseSet1", "purchasexbox", data.get("runmode"), excel);
		
		try {
			openBrowser(data.get("browser"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		navigate(Config.getProperty("testurl"));
		System.out.println("blasssssssssssssssssss");
		click(("name_XPATH"));
		System.out.println("click on name");
		type("name_XPATH",data.get("name"));
		System.out.println("inserton name");
		click("email_XPATH");
		type(("email_XPATH"),data.get("email"));
		click("btnSubmit_XPATH");
		click(("ps_XPATH"));
		click("nextSection_XPATH");
		click("submit_XPATH");
		
		

		

	}
	
	@AfterMethod
	public void endUp() {
		getDriver().quit();
	}

}
