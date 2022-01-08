package com.testNGPract;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SecondClass {

	
	@BeforeMethod
	public void secondBeforeMethod() {
		
		System.out.println("Second Class Before Method out put....");
	}
	
	@AfterMethod
	public void secondAfterMethod() {
		System.out.println("Second Class After Method out put....");
	}
	
	@Test
	public void secMethod1() {
		
		System.out.println("Second Class Test Method-1 out....");
	}
}
