package br.com.qualidade.exemplos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class Browsers {
	
	WebDriver driver;

	@Test
	public void htmlUnitDriver1() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
	
	@Test
	public void htmlUnitDriver2() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.CHROME_16);
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
	
	@Test
	public void htmlUnitDriver5() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_3_6);
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
	
	@Test
	public void htmlUnitDriver3() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_10);
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
	
	@Test
	public void htmlUnitDriver4() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
	
	@Test
	public void htmlUnitDriver7() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_6);
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
	
	@Test
	public void htmlUnitDriver8() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_7);
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
	
	@Test
	public void htmlUnitDriver9() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_8);
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
	
	@Test
	public void htmlUnitDriver10() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
	
	@Test
	public void htmlUnitDriver6() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_10);
		driver.get("http://selenium.polteq.com/testshop/");
		driver.quit();
	}
}
