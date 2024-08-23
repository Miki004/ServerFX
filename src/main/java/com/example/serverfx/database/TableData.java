package com.example.serverfx.database;

import com.example.serverfx.data.Example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe TableData gestisce l'accesso ai dati da una tabella di un database e
 * fornisce metodi per ottenere i dati sotto forma di una lista di oggetti {@link Example}.
 */
public class TableData {
    private DbAccess db;
    /**
     * Costruisce un'istanza di TableData con un oggetto {@link DbAccess} per gestire l'accesso al database.
     *
     * @param db l'oggetto {@link DbAccess} utilizzato per connettersi al database.
     */
    public TableData(DbAccess db) {
        this.db=db;
    }

    /**
     * Recupera i dati distinti dalla tabella specificata e li converte in una lista di oggetti {@link Example}.
     *
     * @param table il nome della tabella da cui recuperare i dati.
     * @return una lista di oggetti {@link Example} rappresentanti il dataset recuperato dalla tabella.
     * @throws SQLException se si verifica un errore durante l'esecuzione delle query SQL.
     * @throws EmptySetException se il risultato della query è vuoto.
     * @throws MissingNumberException se il risultato della query contiene attributi non numerici.
     */
    public List<Example> getDistinctTransazioni(String table) throws SQLException,EmptySetException,MissingNumberException{
        List<Example> esempi=new ArrayList<>();
        Statement s;
        //tutto il codice sql potrebbe generare eccezzioni Sqlexception
        try {
            s = db.getConnection().createStatement();
            ResultSet puntatoreTabella = s.executeQuery("Select * from " + table);
            //EmptySetException.verificaVuoto(puntatoreTabella.next());

            while (puntatoreTabella.next()) {

                Example iesimoEsempio = new Example();
                Double data=puntatoreTabella.getDouble("X1");
                MissingNumberException.verificaIntero(data);
                iesimoEsempio.add(data);
                data=puntatoreTabella.getDouble("X2");
                MissingNumberException.verificaIntero(data);
                iesimoEsempio.add(data);
                data=puntatoreTabella.getDouble("X3");
                MissingNumberException.verificaIntero(data);
                iesimoEsempio.add(data);
                esempi.add(iesimoEsempio);

            }
            puntatoreTabella.close();//chiude anche il resultset
            ResultSet r=s.executeQuery("Select * from " + table);
            EmptySetException.verificaVuoto(r.next());
            r.close();
            s.close();
        }catch (DatabaseConnectionException e) {
                System.out.println(e.getMessage());
        }
        return esempi;
    }
}
