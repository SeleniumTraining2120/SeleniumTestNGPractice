package com.testNGPract;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTestNGClass {

	
	@BeforeTest
	public void berofeTestTag() {
		System.out.println("Before Test out put....");
	}	
	@AfterTest
	public void afterTestTag() {
		System.out.println("After Test out put....");
	}	
	@BeforeClass
	public void beforeClassTest() {
		System.out.println("Before class out put....");
	}	
	@AfterClass
	public void afterClassTest() {
		System.out.println("After class out put....");
	}
	@BeforeMethod
	public void beforeTestMethod() {
		
		System.out.println("Before Method out put....");
	}	
	@AfterMethod
	public void afterTestMethod() {
		System.out.println("After Method out put....");
	}
	
	@Test(dependsOnMethods = {"testMethod3"}, groups = {"TestGrp1"})
	public void testMethod2() {
		
		System.out.println("Test Method-2 out....");
	}
	
	@Test(groups = {"Test", "TestGrp1"})
	public void testMethod1() {
		
		System.out.println("Test Method-1 out....");
	}
	@Test(groups = {"TestGrp1"})
	public void testMethod3() {
		
		System.out.println("Test Method-3 out....");
	}
	
	
}
