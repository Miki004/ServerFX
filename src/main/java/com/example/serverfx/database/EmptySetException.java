package com.example.serverfx.database;

/**
 * L'eccezione EmptySetException viene sollevata quando si verifica una condizione in cui
 * un insieme di dati è vuoto o non contiene elementi necessari.
 * Questa classe estende {@link Exception}.
 */
public class EmptySetException extends Exception{

    /**
     * Costruisce un'eccezione EmptySetException senza un messaggio di errore.
     */
    public EmptySetException(){}

    /**
     * Costruisce un'eccezione EmptySetException con un messaggio di errore specificato.
     *
     * @param msg il messaggio di errore da associare all'eccezione.
     */
    public EmptySetException(String msg) {super(msg);}

    /**
     * Verifica se un dato insieme è vuoto e, in caso affermativo, lancia un'eccezione EmptySetException.
     *
     * @param esito valore booleano che indica se l'insieme è vuoto (false) o non è vuoto (true).
     * @throws EmptySetException Se l'insieme è vuoto (esito è false).
     */
    public static void verificaVuoto(boolean esito) throws EmptySetException {
        if(!esito)
            throw new EmptySetException("set vuoto");
    }

}
