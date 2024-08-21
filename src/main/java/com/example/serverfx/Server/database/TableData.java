package com.example.serverfx.Server.database;

import com.example.serverfx.Server.data.Example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
