package br.com.qualidadedesoftware.configuration;

import java.io.File;

public class SetupAmbiente extends Suporte 
{	
	public SetupAmbiente() {
		removerArquivosAntigos(diretorioImagem, "png");
		removerArquivosAntigos(diretorioPDF, "pdf");
		//removerArquivosAntigos(diretorioLog, "txt");
	}
	
	public void removerArquivosAntigos(String diretorio, String tipoArquivo) {
		File f = new File(diretorio);
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().toLowerCase().contains(tipoArquivo)) {
					files[i].delete();
				}
			}
		}
	}
}
