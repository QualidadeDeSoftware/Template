package br.com.qualidadedesoftware.configuration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

public class Suporte extends SeleniumRC 
{
	// Configurações do projeto
	protected static Properties prop = new Propriedades().getProp();
	protected static String separator = File.separator;
	protected static Date data = data = new Date();
	protected static SimpleDateFormat spd = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss ");
	
	// Configurações do teste
	protected static String nomeCasoDeTeste = "";
	protected static String descricaoCasoDeTeste = "";
	
	// Configações dos diretórios
	protected static String diretorioProjeto = new File("pom.xml").getAbsolutePath().replace("pom.xml", "");
	protected static String diretorioDatapool = diretorioProjeto + prop.getProperty("prop.file.excel").replace("#", separator);
	protected static String diretorioExcel = diretorioDatapool;
	protected static String diretorioImagem = diretorioProjeto + prop.getProperty("prop.file.img").replace("#", separator);
	protected static String diretorioPDF = diretorioProjeto + prop.getProperty("prop.file.pdf").replace("#", separator);
	protected static String diretorioLog = diretorioProjeto + prop.getProperty("prop.file.logs").replace("#", separator);
	protected static String diretorioSeleniumRC = diretorioProjeto + prop.getProperty("prop.file.selenium.rc").replace("#", separator);
	
	// Configurações do Excel
	protected static Map<String, Excel> planilhaExcel = new HashMap<String, Excel>();
	public static ArrayList <String> abasDaPlanilha = new <String> ArrayList();
	public static Map<String, String> dados = new HashMap<String, String>();
	
	// Configurações do log4j
	protected static Logger logger;
	protected static String token;
	protected static FileAppender appender;
	
	// Configurações do Banco de Dados SQL Server
	protected static BD bd = new BD();
	
	public void captureScreenshot(String msg) {
		int count = 1;
		try {
			Thread.sleep(1000);
			selenium.captureEntirePageScreenshot(diretorioImagem + count + " - " + msg + spd.format(data) + ".png", "background=#FFFFFF");
		} catch (Exception e) {}
		count++;
	}

	public void enviarRelatorio() {
		String[] destinatarios = prop.getProperty("prop.email.destinatarios").split(",");
		String assunto = prop.getProperty("prop.email.assunto");
		String anexo = new PDF().gerarPDF();
		new Email().enviaEmail(destinatarios, assunto, anexo);
	}
}
