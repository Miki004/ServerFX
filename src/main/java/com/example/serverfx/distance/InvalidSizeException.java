package com.example.serverfx.distance;

/**
 * Eccezione lanciata quando si verifica un errore relativo alla dimensione durante
 * il calcolo delle distanze tra esempi o cluster.
 */
public class InvalidSizeException extends Exception {

	/**
	 * Costruttore predefinito per InvalidSizeException.
	 */
	public InvalidSizeException() {}

	/**
	 * Costruttore che accetta un messaggio di errore.
	 *
	 * @param msg Il messaggio di errore da associare all'eccezione.
	 */
	public InvalidSizeException(String msg) {
		super(msg);
	}

	/**
	 * Verifica se le dimensioni di due insiemi sono compatibili. Lancia un'eccezione se
	 * la dimensione del primo insieme è maggiore della dimensione del secondo insieme.
	 *
	 * @param d1 La dimensione del primo insieme.
	 * @param d2 La dimensione del secondo insieme.
	 * @throws InvalidSizeException Se la dimensione del primo insieme è maggiore della dimensione del secondo insieme.
	 */
	public static void VerificareDimensione(int d1,int d2) throws InvalidSizeException {
		if(d1>d2) 
			throw new InvalidSizeException("lunghezza degli esempi diversa");
		
	}

}
