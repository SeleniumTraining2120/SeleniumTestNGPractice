package com.testNGPract;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersTestNG {
	
	@Test
	@Parameters({"a", "b"})
	public void add(int a, int b) {
		int c = a+b;
		System.out.println("Final output = " + c);
	}
	
	@Test
	@Parameters({"greet"})
	public void greeting(String str) {
		
		System.out.println("Hi Good Morning From..." + str);
		
	}

}
