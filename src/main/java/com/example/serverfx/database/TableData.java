package com.example.serverfx.database;

import com.example.serverfx.data.Example;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableData {
    private DbAccess db;
    public TableData(DbAccess db) {
        this.db=db;
    }

    /**
     * @param table rappresenta il nome della tabella
     *
     * @return esempi rappresenta una lista di {@code Example} rappresentano il dataset
     * @throws SQLException oggetto connection potrebbe aver fallito
     * @throws EmptySetException resulset vuoto
     * @throws MissingNumberException resultset presenta attributi numerici
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
