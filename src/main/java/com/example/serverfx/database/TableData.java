package com.example.serverfx.database;

import com.example.serverfx.data.Example;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe TableData gestisce l'accesso ai dati in una tabella di un database.
 * Fornisce metodi per estrarre dati dalla tabella e convertirli in oggetti di tipo {@link Example}.
 */
public class TableData {
    private DbAccess db;

    /**
     * Costruttore della classe TableData.
     * Inizializza un'istanza di TableData con un oggetto DbAccess per accedere al database.
     *
     * @param db L'oggetto {@link DbAccess} utilizzato per accedere al database.
     */
    public TableData(DbAccess db) {
        this.db=db;
    }

    /**
     * Estrae i dati distinti dalla tabella specificata e li converte in una lista di oggetti {@link Example}.
     *
     * Il metodo esegue una query SQL per recuperare tutti i dati dalla tabella e li converte in oggetti {@code Example}.
     * Ogni riga della tabella diventa un oggetto {@code Example} e i valori delle colonne sono aggiunti come attributi numerici.
     *
     * @param table Il nome della tabella da cui estrarre i dati.
     * @return Una lista di oggetti {@code Example} che rappresentano i dati estratti dalla tabella.
     * @throws SQLException Se si verifica un errore durante l'esecuzione della query SQL o durante l'accesso ai dati del database.
     * @throws EmptySetException Se il risultato della query è vuoto (nessun dato trovato nella tabella).
     * @throws MissingNumberException Se la tabella contiene attributi non numerici (ovvero, se i dati recuperati non sono numeri).
     */
    public List<Example> getDistinctTransazioni(String table) throws SQLException,EmptySetException,MissingNumberException{
        List<Example> esempi=new ArrayList<>();
        Statement s;
        try {
            s = db.getConnection().createStatement();
            ResultSet puntatoreTabella = s.executeQuery("Select * from " + table);
            int numColonne= puntatoreTabella.getMetaData().getColumnCount();
            while (puntatoreTabella.next()) {
                Example iesimoEsempio = new Example();
                for(int i=1; i<= numColonne; i++) {
                    Double data=puntatoreTabella.getDouble(i);
                    MissingNumberException.verificaIntero(data);
                    iesimoEsempio.add(data);
                }
                esempi.add(iesimoEsempio);
            }
            puntatoreTabella.close();//chiude anche il resultset
            ResultSet r=s.executeQuery("Select * from " + table);
            EmptySetException.verificaVuoto(r.next());
            s.close();
        }catch (DatabaseConnectionException e) {
                System.out.println(e.getMessage());
        }
        return esempi;
    }
}
