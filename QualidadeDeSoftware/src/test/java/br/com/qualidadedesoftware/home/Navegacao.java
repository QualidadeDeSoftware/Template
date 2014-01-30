package br.com.qualidadedesoftware.home;

import org.junit.Test;

import br.com.qualidadedesoftware.configuration.Excel;
import br.com.qualidadedesoftware.dsl.Commons_DSL;

public class Navegacao extends Commons_DSL {
	Excel excel = new Excel();
	
	public void preRequisito(String planilha) {
		diretorioExcel = diretorioDatapool;
		diretorioExcel += planilha;
		excel.carregarPlanilhaExcel();
		nomeCasoDeTeste = abasDaPlanilha.get(0);
		descricaoCasoDeTeste = planilhaExcel.get(nomeCasoDeTeste).coluna("Descrição");
	}
	
	@Test
	public void testNavegacao_ComSucesso() throws Exception {
		abasDaPlanilha.add(0, "Plan1" );
		preRequisito("Home.xls");
		
		for (int i = 1; i < planilhaExcel.get(nomeCasoDeTeste).totalLinhas(); i++, excel.proximaLinha(planilhaExcel, nomeCasoDeTeste)) {
			selenium.open("http://www.QualidadeDeSoftware.com.br/");
			logger.info("teste 1");
			assertTrue(true);
		}
	}
	
	@Test
	public void testNavegacao_ComErro() throws Exception {
		abasDaPlanilha.add(0, "Plan2" );
		preRequisito("Home.xls");
		
		for (int i = 1; i < planilhaExcel.get(nomeCasoDeTeste).totalLinhas(); i++, excel.proximaLinha(planilhaExcel, nomeCasoDeTeste)) {
			selenium.open("http://www.QualidadeDeSoftware.com.br/");
			logger.info("teste 2");
			assertTrue(false);
		}
	}	
}
