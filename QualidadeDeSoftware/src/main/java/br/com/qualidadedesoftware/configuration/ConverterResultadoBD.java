package br.com.qualidadedesoftware.configuration;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

public class ConverterResultadoBD 
{
	public Map <Integer, HashMap> convert(ResultSet rs) throws Exception {
		Map <Integer, HashMap> linha = new HashMap<Integer, HashMap>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int numColumns = rsmd.getColumnCount();		
		for (int j = 0; rs.next(); j++) {
			HashMap<String, String> coluna = new HashMap<String, String>();
			for (int i = 1; i < (numColumns + 1); i++) {
				String column_name = rsmd.getColumnName(i);

				if (rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
					String ARRAY = rs.getArray(column_name) + "";
					coluna.put(column_name, ARRAY.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
					String BIGINT = rs.getInt(column_name) + "";
					coluna.put(column_name, BIGINT.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
					String BOOLEAN = rs.getBoolean(column_name) + "";
					coluna.put(column_name, BOOLEAN.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {					
					String BLOB = rs.getBlob(column_name) + "";
					coluna.put(column_name, BLOB.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
					String DOUBLE = rs.getDouble(column_name) + "";
					coluna.put(column_name, DOUBLE.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
					String FLOAT = rs.getFloat(column_name) + "";
					coluna.put(column_name, FLOAT.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
					String INTEGER = rs.getInt(column_name) + "";
					coluna.put(column_name, INTEGER.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
					String NVARCHAR = rs.getNString(column_name) + "";
					coluna.put(column_name, NVARCHAR.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
					String VARCHAR = rs.getString(column_name) + "";
					coluna.put(column_name, VARCHAR.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.TINYINT) {
					String TINYINT = rs.getInt(column_name) + "";
					coluna.put(column_name, TINYINT.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
					String SMALLINT = rs.getInt(column_name) + "";
					coluna.put(column_name, SMALLINT.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
					String DATE = rs.getDate(column_name) + "";
					coluna.put(column_name, DATE.replace("null", ""));
				} else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
					String TIMESTAMP = rs.getTimestamp(column_name) + "";
					coluna.put(column_name,  TIMESTAMP.replace("null", ""));
				} else {
					String outros = rs.getObject(column_name) + "";
					coluna.put(column_name, outros.replace("null", ""));
				}
			}
			linha.put(j, coluna);
		}
		return linha;
	}
}
