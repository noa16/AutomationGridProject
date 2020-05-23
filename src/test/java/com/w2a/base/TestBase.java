package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.LogStatus;
import com.sun.tools.sjavac.Log;

public class TestBase {
	
	
	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();
	public RemoteWebDriver driver= null;
	public FileInputStream fis;
	public Properties Config = new Properties();
	public Properties Locators = new Properties();
	public String browser;
	public Logger log = Logger.getLogger("devpionyLogger");
	public void setUp() {
		
		if(driver==null) {
			try {
				fis = new FileInputStream("C:\\Users\\noa\\eclipse-workspace\\DataGridProject\\src\\test\\resources\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream("C:\\Users\\noa\\eclipse-workspace\\DataGridProject\\src\\test\\resources\\locators.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Locators.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	public void openBrowser(String browser) throws MalformedURLException {
		this.browser=browser;
		DesiredCapabilities cap = null;
		if(browser.equals("chrome")) {
			cap =  DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
			//Log.debug("open chrome browser");
			
			
		}
		else {
			
			cap =  DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.ANY);
            			
		}
		
		

		driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"),cap);//connect to the hub in docker container
		setWebDriver(driver);//create sperate thred for each driver
		getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		
		
		
	}
	
	public void setWebDriver(RemoteWebDriver driver) {
		
		dr.set(driver);
	}
	
	public WebDriver getDriver() {
		return dr.get();
	}
	
	
	public void click(String locator) {
		try {
		getDriver().findElement(By.xpath(Locators.getProperty(locator))).click();
		}catch(Error e) {
			
		}
	}
	public void type(String locator,String value) {
		
		getDriver().findElement(By.xpath(Locators.getProperty(locator))).sendKeys(value);
		
	}
	
	public void navigate(String url) {
	
		getDriver().get(url);
		
	
	}

}
