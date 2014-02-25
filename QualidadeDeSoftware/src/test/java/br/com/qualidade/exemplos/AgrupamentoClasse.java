package br.com.qualidade.exemplos;

import org.testng.annotations.Test;
 
@Test(groups= "selenium-test")
public class AgrupamentoClasse {
	
	// http://www.mkyong.com/unittest/testng-groups-example/
 
	public void runSelenium() {
		System.out.println("runSelenium()");
	}
 
	public void runSelenium1() {
		System.out.println("runSelenium()1");
	}
}
