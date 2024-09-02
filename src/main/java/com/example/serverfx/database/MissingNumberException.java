package com.example.serverfx.database;

/**
 * La classe MissingNumberException è un'eccezione personalizzata che viene lanciata
 * quando si verifica un errore relativo alla mancanza di attributi numerici, come nel caso in cui
 * una tabella contenga attributi non numerici quando ci si aspetterebbe solo numeri.
 * Estende la classe {@link Exception}.
 */
public class MissingNumberException extends  Exception{

    /**
     * Costruttore predefinito per MissingNumberException.
     * Viene utilizzato per creare un'eccezione senza un messaggio di errore specifico.
     */
    public  MissingNumberException() {}

    /**
     * Costruttore che accetta un messaggio di errore specifico.
     * Viene utilizzato per creare un'eccezione con un messaggio che descrive la causa dell'errore.
     *
     * @param msg Il messaggio di errore che descrive la causa dell'eccezione.
     */
    public  MissingNumberException(String msg) {super(msg);}

    /**
     * Verifica se un oggetto è una stringa e, in tal caso, lancia un'eccezione MissingNumberException.
     * Questo metodo può essere utilizzato per controllare se una tabella contiene attributi non numerici
     * quando ci si aspetterebbe solo numeri.
     *
     * @param e L'oggetto da verificare. Se è una stringa, viene lanciata un'eccezione.
     * @throws MissingNumberException Se l'oggetto è una stringa (indicando che la tabella presenta attributi non numerici).
     */
    public static void verificaIntero(Object e) throws  MissingNumberException{
        if( e instanceof String)
            throw new MissingNumberException("la tabella presenta attributi non numerici");
    }
}

