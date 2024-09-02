package com.example.serverfx.database;

/**
 * La classe EmptySetException è un'eccezione personalizzata che viene lanciata quando
 * si verifica un errore relativo a un set vuoto, come durante l'accesso o l'operazione su un set che non contiene elementi.
 * Estende la classe {@link Exception}.
 */
public class EmptySetException extends Exception{

    /**
     * Costruttore predefinito per EmptySetException.
     * Viene utilizzato per creare un'eccezione senza un messaggio di errore specifico.
     */
    public EmptySetException(){}

    /**
     * Costruttore che accetta un messaggio di errore specifico.
     * Viene utilizzato per creare un'eccezione con un messaggio che descrive la causa dell'errore.
     *
     * @param msg Il messaggio di errore che descrive la causa dell'eccezione.
     */
    public EmptySetException(String msg) {super(msg);}

    /**
     * Verifica se una condizione booleana è falsa e, in tal caso, lancia un'eccezione EmptySetException.
     * Questo metodo può essere utilizzato per controllare se un set è vuoto e gestire il caso in cui lo sia.
     *
     * @param esito La condizione booleana da verificare. Se è falsa, viene lanciata l'eccezione.
     * @throws EmptySetException Se la condizione booleana è falsa (indicando che il set è vuoto).
     */
    public static void verificaVuoto(boolean esito) throws EmptySetException {
        if(!esito)
            throw new EmptySetException("set vuoto");
    }

}
