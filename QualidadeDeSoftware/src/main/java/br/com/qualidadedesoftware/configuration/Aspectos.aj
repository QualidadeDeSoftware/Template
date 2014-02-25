package br.com.qualidadedesoftware.configuration;

public aspect Aspectos extends Suporte 
{	
	// Print da tela após validações de dados com sucesso
	pointcut screenshot(): call (public * validar*(..));
	after() : screenshot() {
		captureScreenshot("");
	}
	
	// Print da tela por erro de dados atuais divergentes dos dados esperados
	pointcut asserts(): call (public * assert*(..));
	after() throwing (AssertionError e): asserts() {
		captureScreenshot("ERRO assert - ");
		enviarRelatorio();
	}

	// Print da tela por erro de timeout
	pointcut falhas() : call (* *.fail(..));
	before() : falhas() {
		captureScreenshot("ERRO fail - ");
		enviarRelatorio();
	}

	// Inicializa o log4j no setup do Selenium
	pointcut setup() : execution (* setUp());
	before() : setup() {
		new Log().log4j();
		new SetupAmbiente();
	}
	
	public void captureScreenshot(String msg) {
		int count = 1;
		selenium.captureEntirePageScreenshot(diretorioImagem + count
				+ " - " + msg + spd.format(data) + ".png", "background=#FFFFFF");
		count++;
	}

	public String gerarPDF() {
		String nomeCasodeTeste = diretorioExcel.substring(diretorioExcel.lastIndexOf(separator)).replace(separator, "").replace("_", " ").replace(".xls", "");
		String descricao = planilhaExcel.get(abasDaPlanilha.get(0)).coluna("Descrição");
		return new PDF().gerarPDF();
	}
	
	public void enviarRelatorio() {
		String[] destinatarios = prop.getProperty("prop.email.destinatarios").split(",");
		String assunto = prop.getProperty("prop.email.assunto");
		String anexo = gerarPDF();
		new Email().enviaEmail(destinatarios, assunto, anexo);
	}
}
