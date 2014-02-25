package br.com.qualidadedesoftware.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class Excel extends Suporte {
	// Configurações do Excel
	String FILE_XLS = "";
	String ENCODING_FILE_XLS = "Cp1252";
	Workbook workbook = null;
	WorkbookSettings ws = null;
	File fileXLS = null;
	Sheet sheet = null;
	String abaPlanilha = "";
	int linhas = 0;
	int colunas = 0;
	int linhaAtual = 1;
	
	public Excel() {
		super();
	}

	public Excel(String aba, String arquivoExcel) {
		super();
		FILE_XLS = arquivoExcel;
		abaPlanilha = aba;

		if (!FILE_XLS.equalsIgnoreCase("null")) {
			coluna("carga");
		}
	}

	public String coluna(String coluna) {
		if ("carga".equalsIgnoreCase(coluna)) {
			fileXLS = new File(FILE_XLS);

			if ("null".equalsIgnoreCase(FILE_XLS)) {
				logger.error("Informe o diretório e o nome do arquivo .xls na variável \"FILE_XLS\". Exemplo: \"C:/datapool.xls\"");
				if (fileXLS.exists()) {
					logger.error("Não existe o arquivo .xml dentro do diretório informado!");
				}
			}

			try {
				ws = new WorkbookSettings();
				ws.setEncoding(ENCODING_FILE_XLS);
				workbook = Workbook.getWorkbook(fileXLS, ws);
			} catch (Exception e) {}

			boolean contains = true;
			for (int i = 0; i < workbook.getSheetNames().length; i++) {
				if (workbook.getSheetNames()[i].equalsIgnoreCase(abaPlanilha
						.toLowerCase())) {
					contains = false;
					break;
				}
			}
			if (contains) {
				logger.error("Não existe a aba (" + abaPlanilha
						+ ") no arquivo informado!");
			}

			sheet = workbook.getSheet(abaPlanilha);
			linhas = sheet.getRows();
			colunas = sheet.getColumns();
		}

		if ("carga".equalsIgnoreCase(coluna))
			return null;

		for (int i = 0; i < colunas; i++) {
			Cell colunasXML = sheet.getCell(i, 0);
			if (coluna.equalsIgnoreCase(colunasXML.getContents()))
				return sheet.getCell(i, linhaAtual).getContents();
		}
		logger.error("A coluna \"" + coluna + "\" não exite no arquivo excel!");
		return null;
	}

	public void recalculo(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFFormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			HSSFFormulaEvaluator[] evaluators = { evaluator };
			String[] workbookNames = { fileName.substring(fileName.lastIndexOf(separator)).replace(separator, "") };
			HSSFFormulaEvaluator.setupEnvironment(workbookNames, evaluators);
			evaluator.evaluateAll();
			boolean teste = false;

			for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
				HSSFSheet sheet = wb.getSheetAt(sheetNum);
				for (Row r : sheet) {
					for (org.apache.poi.ss.usermodel.Cell c : r) {
						if (c.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA) {
							evaluator.evaluateFormulaCell(c);
							teste = true;
						}
					}
				}
			}

			if (teste) {
				FileOutputStream out = new FileOutputStream(fileName);
				wb.write(out);
				out.close();
			}
		} catch (Exception e) {
			logger.info("Não conseguiu atualizar a planilha excel");
			logger.error(e.fillInStackTrace());
		}
	}
	
	public void proximaLinha() {
		linhaAtual++;
	}

	public int totalLinhas() {
		return linhas;
	}

	public void setLinhaAtual(int linhaAtual) {
		this.linhaAtual = linhaAtual;
	}

	public int getLinhaAtual() {
		return linhaAtual;
	}

	public boolean existeColuna(String coluna) {
		for (int i = 0; i < colunas; i++) {
			Cell colunasXML = sheet.getCell(i, 0);
			if (coluna.equalsIgnoreCase(colunasXML.getContents()))
				return true;
		}
		return false;
	}
	
    public void proximaLinha(Map<String, Excel> planilha, String aba) {
    	planilha.get( aba ).setLinhaAtual(planilha.get( aba ).getLinhaAtual() + 1);
	}
	
	// Carrega massa de dados a partir da planilha excel
	public void carregarPlanilhaExcel() {
		recalculo(diretorioExcel);
		for (int i = 0; i < abasDaPlanilha.size(); i++) {
			planilhaExcel.put(abasDaPlanilha.get(i).toString(), new Excel(abasDaPlanilha.get(i).toString(), diretorioExcel));
		}
	}
}
