package com.example.serverfx.Server.database;

import java.sql.ResultSet;

public class EmptySetException extends Exception{
    public EmptySetException(){}
    public EmptySetException(String msg) {super(msg);}

    public static void verificaVuoto(boolean esito) throws EmptySetException {
        if(!esito)
            throw new EmptySetException("set vuoto");
    }

}
