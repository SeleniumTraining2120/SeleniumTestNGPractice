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
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertsForCrossBrowser {

	
	
	String expValue = "This alert appeared after 5 seconds";
	String actValue;

	@Test
	public void alertHandle() {
		try {
			FileInputStream fis = new FileInputStream(
					"D:\\CEP_WorkSpace\\TestPractice\\TestNGProject\\Locators.properties");
			Properties prop = new Properties();
			prop.load(fis);
			System.setProperty("webdriver.chrome.driver", "D:\\CEP_WorkSpace\\TestNGProject\\Drivers\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://demoqa.com/alerts");

			driver.findElement(By.id(prop.getProperty("timeAlert_btn"))).click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert a1 = driver.switchTo().alert();

			actValue = a1.getText();

			System.out.println("Timer Alert message: " + actValue);

			Assert.assertEquals(actValue, expValue);

			Thread.sleep(1000);
			a1.accept();

			driver.quit();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
