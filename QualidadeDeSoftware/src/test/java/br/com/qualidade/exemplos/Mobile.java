package br.com.qualidade.exemplos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.iphone.IPhoneDriver;
import org.testng.annotations.Test;

public class Mobile {
	
	WebDriver driver;

	@Test
	public void htmlUnitDriver1() throws Exception {
		driver = new AndroidDriver();
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
	
	@Test
	public void htmlUnitDriver2() throws Exception {
		driver = new IPhoneDriver();
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
}
