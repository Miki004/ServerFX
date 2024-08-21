package com.example.serverfx.Server.database;

public class MissingNumberException extends  Exception{
    public  MissingNumberException() {}
    public  MissingNumberException(String msg) {super(msg);}

    public static void verificaIntero(Object e) throws  MissingNumberException{
        if( e instanceof String)
            throw new MissingNumberException("la tabella presenta attributi non numerici");
    }
}

