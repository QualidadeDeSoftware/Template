package br.com.qualidadedesoftware.configuration;

public aspect Aspectos extends Suporte 
{	
	SetupAmbiente setup =  new SetupAmbiente();
	Log log = new Log();
	
	// Print da tela após validações de dados com sucesso
	pointcut screenshot(): call (public * validar*(..));
	after() : screenshot() {
		captureScreenshot("");
	}
	
	// Print da tela por erro de dados atuais divergentes dos dados esperados
	pointcut asserts(): call (public * assert*(..));
	after() throwing (Exception e): asserts() {
		captureScreenshot("ERRO assertEquals - ");
		if (e.fillInStackTrace().getMessage().contains("junit")) {
			logger.info(e.fillInStackTrace().getMessage());	
		}
		enviarRelatorio();
	}

	// Print da tela por erro de timeout
	pointcut falhas() : call (* *.fail(..));
	before() : falhas() {
		captureScreenshot("ERRO fail - ");
		enviarRelatorio();
	}
    
	// Inicializa no setup do Selenium
	pointcut inicializar() : execution (* setUp());
	before() : inicializar() {
		setup.startSeleniumServer();
		setup.removerArquivosAntigos();
		log.inicializarlog4j();
		if (Boolean.parseBoolean(prop.getProperty("prop.bd.habilitar"))) {
			bd.iniciarBancoDeDados();
		}
	}
	
	// Finaliza no setup do Selenium
	pointcut finalizar() : execution (* tearDown());
	before() : finalizar() {
		log.finalizarLog4j();
		if (Boolean.parseBoolean(prop.getProperty("prop.bd.habilitar"))) {
			bd.encerrarBancoDeDados();
		}
	}
}
