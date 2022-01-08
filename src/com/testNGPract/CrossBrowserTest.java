package com.testNGPract;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTest {

	WebDriver driver;
	Properties prop;
	String expValue = "This alert appeared after 5 seconds";
	String actValue;
	
	@BeforeTest
	public void loadLocators() throws Throwable {
		FileInputStream fis = new FileInputStream("D:\\CEP_WorkSpace\\TestNGProject\\Properties\\Locators.properties");
		prop = new Properties();
		prop.load(fis);
	}
	
	@BeforeClass
	@Parameters("browser")
	public void invokeBrowserInstance(String browserName) {
		
		if(browserName.equalsIgnoreCase("fireFox")){
			System.setProperty("webdriver.gecko.driver", "D:\\CEP_WorkSpace\\TestNGProject\\Drivers\\geckodriver.exe");			
			driver = new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\CEP_WorkSpace\\TestNGProject\\Drivers\\chromedriver.exe");			
			driver = new ChromeDriver();
		}
		driver.get("https://demoqa.com");
		
	}
	
	
	
	@Test
	public void testCaseOne() {

		try {			
			driver.navigate().to("https://demoqa.com/alerts");
			Thread.sleep(3000);
			driver.findElement(By.id(prop.getProperty("timeAlert_btn"))).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert a1 = driver.switchTo().alert();
			actValue = a1.getText();
			System.out.println("Timer Alert message: " + actValue);
			Assert.assertEquals(actValue, expValue);
			Thread.sleep(3000);
			a1.accept();
			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
