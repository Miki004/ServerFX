package com.example.serverfx.database;

/**
 * La classe DatabaseConnectionException è un'eccezione personalizzata che viene lanciata
 * quando si verifica un problema durante la connessione al database.
 * Estende la classe {@link Exception} e può includere un messaggio che descrive l'errore.
 */
public class DatabaseConnectionException extends  Exception{

    /**
     * Costruttore predefinito per DatabaseConnectionException.
     * Viene utilizzato per creare un'eccezione senza un messaggio di errore specifico.
     */
    public  DatabaseConnectionException() {

    }

    /**
     * Costruttore che accetta un messaggio di errore specifico.
     * Viene utilizzato per creare un'eccezione con un messaggio che descrive la causa dell'errore.
     *
     * @param msg Il messaggio di errore che descrive la causa dell'eccezione.
     */
    public  DatabaseConnectionException(String msg) {
        super(msg);
    }

}
