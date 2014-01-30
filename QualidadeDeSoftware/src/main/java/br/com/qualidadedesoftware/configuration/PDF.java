package br.com.qualidadedesoftware.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.log4j.FileAppender;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDF extends Suporte {
	Document doc = null;
	OutputStream os = null;
	String arquivoPDF = diretorioPDF + spd.format(data) + " - ";
	
	public String gerarPDF(String nomeCasodeTeste, String descricao) {
		incluirNomeEDescricao(nomeCasodeTeste, descricao);
		incluirLogs();
		incluirImagens();
		
		if (doc != null) {
			doc.close();
		}
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				logger.info(e.fillInStackTrace());
			}
		}
		
		return arquivoPDF;
	}

	public void incluirNomeEDescricao(String nomeCasodeTeste, String descricao) {
		try {
			arquivoPDF += nomeCasodeTeste + ".pdf";
			doc = new Document(PageSize.A4);
			os = new FileOutputStream(arquivoPDF);
			PdfWriter.getInstance(doc, os);
			doc.open();

			Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
			Paragraph p = new Paragraph(nomeCasodeTeste, f);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(3);
			doc.add(p);

			Font f2 = new Font(FontFamily.COURIER, 9, Font.NORMAL);
			Paragraph p2 = new Paragraph(descricao, f2);
			p2.setAlignment(Element.ALIGN_CENTER);
			p2.setSpacingAfter(30);
			doc.add(p2);
		} catch (Exception e) {
			logger.info(e.fillInStackTrace());
		}
	}
	
	public void incluirLogs() {
		try {
			// Adiciona o título ao PDF
			Font f = new Font(FontFamily.COURIER, 6, Font.NORMAL);
			Paragraph p2 = new Paragraph("", f);
			p2.setAlignment(Element.ALIGN_LEFT);
			p2.setSpacingAfter(20);
			
			FileAppender fileAppender = (FileAppender) logger.getAppender(token);

			BufferedReader br = new BufferedReader(new FileReader(fileAppender.getFile()));
			while(br.ready()){  
				p2.add(br.readLine());
				p2.add(Chunk.NEWLINE);
			}
			
			br.close();
			if (!p2.isEmpty()) {
				doc.add(p2);
			}
			logger.removeAppender(token);
			appender.close();			
		} catch (Exception e) {
			logger.info(e.fillInStackTrace());
		}
	}
	
	public void incluirImagens() {
		try {
			Vector<String> arquivos = listarArquivos();
			if (!arquivos.isEmpty()) {
				for (int i = 0; i < arquivos.size(); i++) {
					Image img = Image.getInstance(arquivos.elementAt(i));
					img.scaleToFit(PageSize.A4.getWidth() - 30, (PageSize.A4.getHeight() / 2) - 50);
					img.setAlignment(Element.ALIGN_CENTER);			
					img.setBorderWidth(5f);
			        img.setBorder(Image.BOX);
			        img.setBorderColor(BaseColor.BLACK);
			        img.setSpacingBefore(200);
			        img.setSpacingAfter(200);
					doc.add(img);
				}
			}
		} catch (Exception e) {
			logger.info(e.fillInStackTrace());
		}
	}

	private Vector listarArquivos() {
		File f = new File(diretorioImagem);
		Vector<String> arquivos = new Vector<String>();
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().toLowerCase().contains("png")) {
					arquivos.add(files[i].toPath().toString());
				}
			}
		}
		return arquivos;
	}
}
