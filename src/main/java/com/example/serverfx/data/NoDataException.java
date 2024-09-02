package com.example.serverfx.data;

/**
 * La classe NoDataException è un'eccezione personalizzata che viene lanciata
 * quando non è possibile trovare dati o quando un'operazione sui dati fallisce
 * a causa dell'assenza di dati.
 * Estende la classe {@link Exception}.
 */
public class NoDataException  extends Exception{
    /**
     * Costruttore predefinito per NoDataException.
     * Viene utilizzato per creare un'eccezione senza un messaggio di errore specifico.
     */
    public NoDataException() {}

    /**
     * Costruttore che accetta un messaggio di errore specifico.
     * Viene utilizzato per creare un'eccezione con un messaggio che descrive la causa dell'errore.
     *
     * @param msg Il messaggio di errore che descrive la causa dell'eccezione.
     */
    public NoDataException(String msg) {super(msg);}
}
