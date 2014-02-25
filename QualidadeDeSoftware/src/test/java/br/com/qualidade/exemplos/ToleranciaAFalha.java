package br.com.qualidade.exemplos;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import br.com.qualidadedesoftware.configuration.Resiliencia;
import br.com.qualidadedesoftware.configuration.SeleniumWebDriver;

public class ToleranciaAFalha extends SeleniumWebDriver {
	
	@Test(retryAnalyzer = Resiliencia.class)
	public void metodoIntermitente() throws Exception {		
		double a = ( 5 * Math.random() );
		System.out.println("Número gerado: " + a);
		assertThat("Assert that a is close to 5.0", a, Matchers.closeTo(5.0, 2.5));
	}
}
