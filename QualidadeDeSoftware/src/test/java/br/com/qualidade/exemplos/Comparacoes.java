package br.com.qualidade.exemplos;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import br.com.qualidadedesoftware.configuration.SeleniumWebDriver;

public class Comparacoes extends SeleniumWebDriver {

	@Test
	public void comparacoes1() throws Exception {
		String s = "This is some text.";
		assertThat("Assert s to match the exact text", s, Matchers.equalTo("Tis is some text."));
	}
	
	@Test
	public void comparacoes2() throws Exception {
		String s = "This is some text.";
		assertThat("Assert s to match the beginning of the string", s, Matchers.startsWith("Ths"));
	}
	
	@Test
	public void comparacoes3() throws Exception {
		String s = "This is some text.";
		assertThat("Assert s to match the end of the string", s, Matchers.endsWith("x."));
	}
	
	@Test
	public void comparacoes4() throws Exception {
		String s = "This is some text.";
		assertThat("Assert s to check if string contains", s, Matchers.containsString("sme"));
	}
	
	@Test
	public void comparacoes5() throws Exception {
		int n = 4;
		assertThat("Assert that n is equal to 5", n, Matchers.equalTo(5));
	}
	
	@Test
	public void comparacoes6() throws Exception {
		int n = 4;
		assertThat("Assert that n is less than 3", n, Matchers.lessThan(3));
	}
	
	@Test
	public void comparacoes7() throws Exception {
		int n = 6;
		assertThat("Assert that n is greater than 6", n, Matchers.greaterThan(6));
	}
	
	@Test
	public void comparacoes8() throws Exception {
		double a = 6.9;
		assertThat("Assert that a is close to 7.0", a, Matchers.closeTo(7.0, 0.1));
	}
	
	@Test
	public void comparacoes9() throws Exception {
		boolean b = false;
		assertThat("Assert boolean b against expected result: true", b, Matchers.equalTo(true));
	}
	
	@Test
	public void comparacoes10() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("test");
		list.add("webdriver");
		assertThat("Assert that the list contains: tedst", list, Matchers.hasItems("tedst"));
	}
	
	@Test
	public void comparacoes11() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("test");
		list.add("webdriver");
		assertThat("Assert that the list has size of 3", list, Matchers.hasSize(3));
	}
	
	@Test
	public void comparacoes12() throws Exception {
		String s = "This is some text.";
		assertThat(
				"Assert a string starting with Tis and a string ending with ext.",
				s,
				Matchers.allOf(Matchers.startsWith("Tis"), Matchers.endsWith("ext.")));
	}
	
	@Test
	public void comparacoes13() throws Exception {
		String s = "This is some text.";
		assertThat(
				"Assert a string starting with This and a string ending with et.",
				s,
				Matchers.both(Matchers.startsWith("This")).and(Matchers.endsWith("et.")));
	}
}
