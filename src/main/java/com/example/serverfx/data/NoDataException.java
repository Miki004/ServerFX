package com.example.serverfx.data;

/**
 * L'eccezione NoDataException viene sollevata quando non ci sono dati disponibili
 * o quando si verifica un problema relativo ai dati.
 * Questa classe estende {@link Exception}.
 */
public class NoDataException  extends Exception{
    /**
     * Costruisce un'eccezione NoDataException senza un messaggio di errore.
     */
    public NoDataException() {}

    /**
     * Costruisce un'eccezione NoDataException con un messaggio di errore specificato.
     *
     * @param msg il messaggio di errore da associare all'eccezione.
     */
    public NoDataException(String msg) {super(msg);}
}
