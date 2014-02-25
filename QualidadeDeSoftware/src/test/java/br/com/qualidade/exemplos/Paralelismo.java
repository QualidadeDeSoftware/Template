package br.com.qualidade.exemplos;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

public class Paralelismo {
	
	@Test(invocationCount = 5, threadPoolSize = 5)
	public void metodoIntermitente1() throws Exception {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://selenium.polteq.com/testshop/index.php");
		double a = 5;
		System.out.println("Número gerado: " + a);
		assertThat("Assert that a is close to 5.0", a, Matchers.closeTo(5.0, 2.5));
		driver.quit();
	}
}
