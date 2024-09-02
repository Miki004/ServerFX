package com.example.serverfx.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * La classe TableSchema gestisce la raccolta delle informazioni sullo schema di una tabella
 * in un database, come i nomi delle colonne e i loro tipi di dati.
 * Fornisce metodi per ottenere il numero di colonne e accedere alle informazioni di ciascuna colonna.
 */
public class TableSchema {
	private DbAccess db;

	/**
	 * Classe interna che rappresenta una colonna di una tabella nel database.
	 * Contiene informazioni sul nome e sul tipo di dati della colonna.
	 */
	public class Column{
		private String name;
		private String type;

		/**
		 * Costruttore della classe Column.
		 *
		 * @param name Il nome della colonna.
		 * @param type Il tipo di dati della colonna.
		 */
		Column(String name,String type){
			this.name=name;
			this.type=type;
		}

		/**
		 * Restituisce il nome della colonna.
		 *
		 * @return Il nome della colonna.
		 */
		public String getColumnName(){
			return name;
		}

		/**
		 * Verifica se il tipo di dati della colonna è numerico.
		 *
		 * @return {@code true} se il tipo di dati è "number", altrimenti {@code false}.
		 */
		public boolean isNumber(){
			return type.equals("number");
		}

		/**
		 * Restituisce una rappresentazione testuale della colonna nel formato "nome:tipo".
		 *
		 * @return Una stringa che rappresenta la colonna.
		 */
		public String toString(){
			return name+":"+type;
		}
	}
	List<Column> tableSchema=new ArrayList<Column>();

	/**
	 * Costruttore della classe TableSchema.
	 * Inizializza l'istanza di TableSchema e recupera le informazioni sullo schema della tabella specificata.
	 *
	 * @param db L'oggetto {@link DbAccess} utilizzato per accedere al database.
	 * @param tableName Il nome della tabella di cui ottenere lo schema.
	 * @throws SQLException Se si verifica un errore durante l'accesso al database o durante la lettura dei metadati.
	 * @throws DatabaseConnectionException Se si verifica un errore nella connessione al database.
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
	 * @return Il numero di colonne.
	 */
	public int getNumberOfAttributes(){
			return tableSchema.size();
		}

	/**
	 * Restituisce la colonna all'indice specificato.
	 *
	 * @param index L'indice della colonna.
	 * @return L'oggetto {@link Column} che rappresenta la colonna all'indice specificato.
	 */
	public Column getColumn(int index){
			return tableSchema.get(index);
		}
	}

		     


