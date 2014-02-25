package br.com.qualidadedesoftware.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class BD extends Suporte 
{
	static Connection con = null;
	static Statement stmt = null;

	public Map <Integer, HashMap> consultarProcedure(StringBuilder parametros, boolean commit) throws Exception {
		logger.info(parametros.toString());
		try {
			return new ConverterResultadoBD().convert(stmt.executeQuery(parametros.toString()));			
		} catch (Exception e) {
			fail("Não conseguiu executar a procedure no banco de dados.");
		}
		return null;
	}
	
	public Map <Integer, HashMap> consultarTabela(StringBuilder query, boolean commit) {
		logger.info(query);
		try {
			return  new ConverterResultadoBD().convert(stmt.executeQuery(query.toString()));
		} catch (Exception e) {
			fail("Não conseguiu executar a query no banco de dados.");
		}
		return null;
	}
	
	public void iniciarBancoDeDados() {
		try {
			Class.forName(prop.getProperty("prop.bd.driver"));
			con = DriverManager.getConnection(
					prop.getProperty("prop.bd.string.conexao") + "://" + 
					prop.getProperty("prop.bd.host") + ":" + 
					prop.getProperty("prop.bd.porta") + ";DtabaseName=" + 
					prop.getProperty("prop.bd.database"),
					prop.getProperty("prop.bd.usuario"),
					prop.getProperty("prop.bd.senha"));
			stmt = (Statement) con.createStatement();
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
			fail("Não conseguiu conectar no banco de dados.");
		}
	}
	
	public void encerrarBancoDeDados() {
		try {
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
			fail("Não conseguiu encerrar o banco de dados.");
		}
	}
}
