package br.com.qualidade.exemplos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.qualidadedesoftware.configuration.SeleniumWebDriver;

public class FirstTest extends SeleniumWebDriver {
	@Test
	public void login() {
		// Navigate to login page
		driver.findElement(By.className("login")).click();
		// Fill in the form
		driver.findElement(By.id("email")).sendKeys("tester@test.com");
		driver.findElement(By.id("passwd")).sendKeys("tester");
		driver.findElement(By.id("SubmitLogin")).click();

		Assert.assertTrue(driver.findElement(
				By.cssSelector("ul.myaccount_lnk_list")).isDisplayed());
	}

	@Test
	public void startChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", this.getClass()
				.getClassLoader().getResource("chromedriver.exe").getPath());
		WebDriver driver = new ChromeDriver();
		driver.get("http://selenium.polteq.com/testshop/");
		System.out.println(driver.getTitle());
		driver.quit();
	}
}
