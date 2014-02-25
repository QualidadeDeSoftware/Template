package br.com.qualidadedesoftware.configuration;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

public class SetupAmbiente extends Suporte {
	
	public void removerArquivosAntigos() {
		removerArquivosAntigos(diretorioImagem, "png");
		removerArquivosAntigos(diretorioPDF, "pdf");
		removerArquivosAntigos(diretorioLog, "txt");
	}
	
	public void removerArquivosAntigos(String diretorio, String tipoArquivo) {
		File f = new File(diretorio);
		if (!f.exists()) {
			new File(diretorio).mkdirs();
		}
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().toLowerCase().contains(tipoArquivo)) {
					if (files[i].getName().toLowerCase().contains("log4j")) {
						if(verificaData(spd.format(files[i].lastModified()))){
							files[i].delete();
						}
					} else {
						files[i].delete();	
					}
				}
			}
		}
	}
	
	public boolean verificaData(String arquivo) {
		try{        	
        	Calendar dataDoSistema = Calendar.getInstance();
        	Calendar dataDoArquivo = Calendar.getInstance();
        	
        	dataDoSistema.add(Calendar.DATE, -1);
        	dataDoArquivo.setTime(spd.parse(arquivo +""));
 
        	if(dataDoSistema.after(dataDoArquivo)){
        		return true;
        	}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
		return false;
	}
	
	public void startSeleniumServer() {
        try {
            URL yahoo = new URL("http://localhost:4444/selenium-server/driver/?cmd=getLogMessages");
            URLConnection yc = yahoo.openConnection();
            new BufferedReader(new InputStreamReader(yc.getInputStream()));
		} catch (Exception e) {
			BufferedReader conteudoPrompt = runCommandConsole();
			String resultado = "";
			try {
				while ((resultado = conteudoPrompt.readLine()) != null) {
					if (resultado.contains("Started org.openqa.jetty.jetty.")) {
						break;
					}
				}
			} catch (Exception ex) {}
		}
    }
	
	public BufferedReader runCommandConsole() {
		BufferedReader commandResult = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			StringBuilder comando = new StringBuilder();
			comando.append("java -jar " + diretorioSeleniumRC + prop.getProperty("prop.selenium.server.version"));
			comando.append(" -port " + prop.getProperty("prop.selenium.server.port"));
			if (prop.getProperty("prop.selenium.server.firefox.profile") != null) {
				if (!prop.getProperty("prop.selenium.server.firefox.profile").isEmpty()) {
					comando.append(" -firefoxProfileTemplate " + prop.getProperty("prop.selenium.server.firefox.profile").replace("#", "\\"));
				}
			}
			
			InputStream input = runtime.exec(comando.toString()).getInputStream();
			BufferedInputStream buffer = new BufferedInputStream(input);
			commandResult = new BufferedReader(new InputStreamReader(buffer));
		} catch (Exception e) {}
		return commandResult;
	}	
}
