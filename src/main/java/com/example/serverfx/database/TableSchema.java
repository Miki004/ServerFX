package com.example.serverfx.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * La classe {@code TableSchema} gestisce la struttura di una tabella in un database,
 * recuperando le informazioni sulle colonne e i loro tipi.
 */
public class TableSchema {
	private DbAccess db;
	/**
	 * La classe interna {@code Column} rappresenta una colonna nella tabella.
	 */
	public class Column{
		private String name;
		private String type;

		/**
		 * Costruisce un'istanza di {@code Column} con il nome e il tipo della colonna.
		 *
		 * @param name il nome della colonna.
		 * @param type il tipo della colonna.
		 */
		Column(String name,String type){
			this.name=name;
			this.type=type;
		}
		/**
		 * Restituisce il nome della colonna.
		 *
		 * @return il nome della colonna.
		 */
		public String getColumnName(){
			return name;
		}

		/**
		 * Verifica se il tipo della colonna è numerico.
		 *
		 * @return {@code true} se il tipo della colonna è "number", altrimenti {@code false}.
		 */
		public boolean isNumber(){
			return type.equals("number");
		}

		/**
		 * Restituisce una rappresentazione testuale della colonna.
		 *
		 * @return una stringa che rappresenta la colonna nel formato "nome:tipo".
		 */
		public String toString(){
			return name+":"+type;
		}
	}


	List<Column> tableSchema=new ArrayList<Column>();

	/**
	 * Costruisce un'istanza di {@code TableSchema} per una tabella specificata.
	 *
	 * @param db l'oggetto {@link DbAccess} utilizzato per connettersi al database.
	 * @param tableName il nome della tabella di cui recuperare la struttura.
	 * @throws SQLException se si verifica un errore durante l'accesso alle informazioni della tabella.
	 * @throws DatabaseConnectionException se si verifica un errore durante la connessione al database.
	 */
	public TableSchema(DbAccess db, String tableName) throws SQLException, DatabaseConnectionException{
		this.db=db;
		HashMap<String,String> mapSQL_JAVATypes=new HashMap<String, String>();
		//http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
		mapSQL_JAVATypes.put("CHAR","string");
		mapSQL_JAVATypes.put("VARCHAR","string");
		mapSQL_JAVATypes.put("LONGVARCHAR","string");
		mapSQL_JAVATypes.put("BIT","string");
		mapSQL_JAVATypes.put("SHORT","number");
		mapSQL_JAVATypes.put("INT","number");
		mapSQL_JAVATypes.put("LONG","number");
		mapSQL_JAVATypes.put("FLOAT","number");
		mapSQL_JAVATypes.put("DOUBLE","number");

		 Connection con=db.getConnection();
		 DatabaseMetaData meta = con.getMetaData();
	     ResultSet res = meta.getColumns(null, null, tableName, null);
		   
	     while (res.next()) {
	         
	         if(mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
	        		 tableSchema.add(new Column(
	        				 res.getString("COLUMN_NAME"),
	        				 mapSQL_JAVATypes.get(res.getString("TYPE_NAME")))
	        				 );
	      }
	      res.close();
	    }

	/**
	 * Restituisce il numero di colonne nella tabella.
	 *
	 * @return il numero di colonne.
	 */
	public int getNumberOfAttributes(){
			return tableSchema.size();
		}

	/**
	 * Restituisce la colonna alla posizione specificata.
	 *
	 * @param index l'indice della colonna da recuperare.
	 * @return l'oggetto {@code Column} alla posizione specificata.
	 */
	public Column getColumn(int index){
			return tableSchema.get(index);
		}
	}

		     


