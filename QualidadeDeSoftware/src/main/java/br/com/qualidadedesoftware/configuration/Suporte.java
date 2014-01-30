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

public class Suporte extends SeleniumTestCase 
{
	// Configurações do projeto
	protected static Properties prop = new Propriedades().getProp();
	protected static String separator = File.separator;
	protected static Date data = data = new Date();
	protected static SimpleDateFormat spd = new SimpleDateFormat("dd MM yyyy hh mm ss ");
	
	// Configurações do teste
	protected static String nomeCasoDeTeste = "";
	protected static String descricaoCasoDeTeste = "";
	
	// Configações dos diretórios
	protected static String diretorioProjeto = new File("pom.xml").getAbsolutePath().replace("pom.xml", "");
	protected static String diretorioDatapool = diretorioProjeto + prop.getProperty("prop.file.excel").replace(".", separator);
	protected static String diretorioExcel = diretorioDatapool;
	protected static String diretorioImagem = diretorioProjeto + prop.getProperty("prop.file.img").replace(".", separator);
	protected static String diretorioPDF = diretorioProjeto + prop.getProperty("prop.file.pdf").replace(".", separator);
	protected static String diretorioLog = diretorioProjeto + prop.getProperty("prop.file.logs").replace(".", separator);
	
	// Configurações do Excel
	protected static Map<String, Excel> planilhaExcel = new HashMap<String, Excel>();
	public static ArrayList <String> abasDaPlanilha = new <String> ArrayList();
	
	// Configurações do log4j
	protected static Logger logger;
	protected static String token;
	protected static FileAppender appender;
}
