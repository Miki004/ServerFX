package com.example.serverfx.database;

/**
 * L'eccezione MissingNumberException viene sollevata quando si riscontra un problema
 * legato alla mancanza di valori numerici in un contesto dove sono previsti numeri.
 * Questa classe estende {@link Exception}.
 */
public class MissingNumberException extends  Exception{
    /**
     * Costruisce un'eccezione MissingNumberException senza un messaggio di errore.
     */
    public  MissingNumberException() {}

    /**
     * Costruisce un'eccezione MissingNumberException con un messaggio di errore specificato.
     *
     * @param msg il messaggio di errore da associare all'eccezione.
     */
    public  MissingNumberException(String msg) {super(msg);}

    /**
     * Verifica se un oggetto è una stringa e, in tal caso, lancia un'eccezione MissingNumberException.
     * Questa verifica è utilizzata per rilevare attributi non numerici in una tabella.
     *
     * @param e l'oggetto da verificare.
     * @throws MissingNumberException Se l'oggetto è una stringa, indicando che la tabella presenta attributi non numerici.
     */
    public static void verificaIntero(Object e) throws  MissingNumberException{
        if( e instanceof String)
            throw new MissingNumberException("la tabella presenta attributi non numerici");
    }
}

