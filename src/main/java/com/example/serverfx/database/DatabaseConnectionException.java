package com.example.serverfx.database;

/**
 * L'eccezione DatabaseConnectionException viene sollevata quando si verifica un problema
 * durante la connessione a un database.
 * Questa classe estende {@link Exception}.
 */
public class DatabaseConnectionException extends  Exception{

    /**
     * Costruisce un'eccezione DatabaseConnectionException senza un messaggio di errore.
     */
    public  DatabaseConnectionException() {}

    /**
     * Costruisce un'eccezione DatabaseConnectionException con un messaggio di errore specificato.
     *
     * @param msg il messaggio di errore da associare all'eccezione.
     */
    public  DatabaseConnectionException(String msg) {
        super(msg);
    }

}
