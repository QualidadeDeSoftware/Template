package br.com.qualidadedesoftware.home;

import java.util.ArrayList;

import org.junit.Test;

import br.com.qualidadedesoftware.configuration.Excel;
import br.com.qualidadedesoftware.dsl.Commons_DSL;

public class Navegacao extends Commons_DSL {
	public void preRequisito(String planilha) {
		diretorioExcel = diretorioDatapool;
		diretorioExcel += planilha;
		abasDaPlanilha = new <String> ArrayList();
		abasDaPlanilha.add( "Plan1" );
		abasDaPlanilha.add( "Plan2" );
		abasDaPlanilha.add( "Plan3" );
		new Excel().carregarPlanilhaExcel();
	}
	
	@Test
	public void testNavegacao_ComSucesso() throws Exception {
		preRequisito("Home.xls");
	
		for (int i = 1; i < planilhaExcel.get( "Plan1" ).totalLinhas(); i++) {
			selenium.open("http://www.QualidadeDeSoftware.com.br/");
			assertTrue(true);
		}
	}
	
	@Test
	public void testNavegacao_ComErro() throws Exception {
		preRequisito("Home.xls");
	
		for (int i = 1; i < planilhaExcel.get( "Plan1" ).totalLinhas(); i++) {
			selenium.open("http://www.QualidadeDeSoftware.com.br/");
			assertTrue(false);
		}
	}
}
