package com.testNGPract;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ParallelTest {

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
	
	

 @BeforeMethod
	public void invokeBrowserInstance() {
		System.setProperty("webdriver.chrome.driver", "D:\\CEP_WorkSpace\\TestNGProject\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
			//driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCaseTwo() {
		try {
			driver.get("https://demoqa.com");
			driver.navigate().to("https://demoqa.com/alerts");
			driver.findElement(By.cssSelector(prop.getProperty("noramalAlert_btn"))).click();
			Thread.sleep(500);
			Alert a = driver.switchTo().alert();
			String alertText = a.getText();
			System.out.println("Noramal Alert message: " + alertText);
			Thread.sleep(3000);
			a.accept();
			//driver.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
