package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;

/**
 * L'eccezione InvalidDepthException viene sollevata quando si verifica un errore relativo
 * alla profondità non valida in relazione ai dati forniti. Questa eccezione estende {@link Exception}.
 */
public class InvalidDepthException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Costruisce un'eccezione InvalidDepthException senza messaggio di errore.
	 */
	public InvalidDepthException() {}

	/**
	 * Costruisce un'eccezione InvalidDepthException con un messaggio di errore specificato.
	 *
	 * @param msg il messaggio di errore da associare all'eccezione.
	 */
	public InvalidDepthException(String msg) {
		super(msg);
		
	}

	/**
	 * Verifica se la profondità specificata è valida rispetto al numero di esempi nei dati.
	 * Se la profondità è maggiore del numero di esempi, viene sollevata un'eccezione InvalidDepthException.
	 *
	 * @param k la profondità da verificare.
	 * @param data l'oggetto Data utilizzato per ottenere il numero di esempi.
	 * @throws InvalidDepthException se la profondità specificata è maggiore del numero di esempi nei dati.
	 */
	public static void VerificareDimensione(int k, Data data) throws InvalidDepthException {
		if(k>data.getNumberOfExamples())
			throw new InvalidDepthException("dimensione inserita non valida,superiore al numero di esempi");
	}
	

}
