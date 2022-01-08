package com.testNGPract;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Test1 {
	
	WebDriver driver;
	Properties prop;
	
	@Test
	public void loadLocators() throws IOException  {
		
		FileInputStream fis = new FileInputStream("D:\\CEP_WorkSpace\\TestNGProject\\Properties\\Locators.properties");
		prop = new Properties();
		prop.load(fis);
		
		System.out.println(prop.getProperty("table_xpath"));
		
	}

}
