package com.testNGPract;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HandelingAsserst {

	String expValue = "This appeared after 5 seconds";
	String actValue;
	WebDriver driver;
	
	@BeforeMethod
	public void invokeBrowser() {
		System.setProperty("webdriver.chrome.driver", "D:\\CEP_WorkSpace\\TestPractice\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	public void testHardAssert() {
		try {
			
			driver.get("https://demoqa.com/alerts");
			FileInputStream fis = new FileInputStream(
					"D:\\CEP_WorkSpace\\TestPractice\\Properties\\Locators.properties");
			Properties prop = new Properties();
			prop.load(fis);
			driver.findElement(By.id(prop.getProperty("timeAlert_btn"))).click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert a1 = driver.switchTo().alert();

			actValue = a1.getText();

			System.out.println("Timer Alert message: " + actValue);

			//Assert.assertEquals(actValue, expValue);

			Thread.sleep(1000);
			a1.accept();

			driver.quit();

		} catch (Exception e) {

			e.printStackTrace();
			driver.quit();
		}
	}

	@Test
	public void testSoftAssert() {

		try {			
			FileInputStream fis = new FileInputStream(
					"D:\\CEP_WorkSpace\\TestPractice\\Properties\\Locators.properties");
			Properties prop = new Properties();
			prop.load(fis);
			SoftAssert soft = new SoftAssert();		
			driver.findElement(By.id(prop.getProperty("timeAlert_btn"))).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert a1 = driver.switchTo().alert();

			actValue = a1.getText();

			System.out.println("Timer Alert message: " + actValue);

			soft.assertEquals(actValue, expValue);					

			Thread.sleep(1000);
			a1.accept();

			driver.quit();
			
			soft.assertAll();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	@AfterClass
	public void browserQuit() {
		driver.quit();
	}

	

}
