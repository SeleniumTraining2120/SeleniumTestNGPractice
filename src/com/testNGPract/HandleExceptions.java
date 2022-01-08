package com.testNGPract;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class HandleExceptions {

	WebDriver driver;
	Properties prop;

	public static void main(String[] args) throws IOException, SQLException {

		try {

			int a = 20;
			System.out.println(a);
			String s = "krishna";
			int i = Integer.parseInt(s);
			System.out.println(i);

		} catch (NullPointerException ne) {
			System.out.println("Nullpointer catch block");
			ne.printStackTrace();

		} catch (ArithmeticException ae) {
			System.out.println("Arithmatic catch block");
			ae.printStackTrace();

		} catch (NumberFormatException ne) {
			System.out.println("NumberFormate catch block");
			ne.printStackTrace();
			
		} catch (Exception e) {
			System.out.println("Exception catch block");
			e.printStackTrace();

		} finally {
			System.out.println("This is finally block code.....");
		}

	}

}
