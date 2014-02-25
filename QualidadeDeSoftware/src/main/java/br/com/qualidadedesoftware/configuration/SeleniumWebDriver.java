package br.com.qualidadedesoftware.configuration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SeleniumWebDriver {
	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		DesiredCapabilities capabillities = DesiredCapabilities.firefox();
		capabillities.setCapability("screenrecorder", true);
		capabillities.setCapability("version", "11");
		capabillities.setCapability("platform", Platform.WINDOWS);
		capabillities.setCapability("name", "Testing Selenium 2");

		// Open browser and navigate to website
		// driver = new RemoteWebDriver(new URL("http://ClientKey:ClientSecret@hub.testingbot.com:4444/wd/hub"), capabillities);
		driver = new FirefoxDriver(capabillities);
		driver.get("http://selenium.polteq.com/testshop/index.php");
	}

	@AfterMethod
	public void tearDown() {
		// Quit browser
		driver.quit();
	}
}
