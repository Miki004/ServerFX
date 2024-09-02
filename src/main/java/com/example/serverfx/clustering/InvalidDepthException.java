package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;

/**
 * La classe InvalidDepthException viene utilizzata per segnalare un'eccezione quando
 * viene specificata una profondità non valida per un dendrogramma o un cluster set.
 * Estende la classe {@link Exception} per fornire informazioni dettagliate sull'errore.
 */
public class InvalidDepthException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore predefinito per InvalidDepthException.
	 */
	public InvalidDepthException() {}

	/**
	 * Costruttore che accetta un messaggio di errore specifico.
	 *
	 * @param msg Il messaggio di errore che descrive la causa dell'eccezione.
	 */
	public InvalidDepthException(String msg) {
		super(msg);
		
	}

	/**
	 * Verifica se la profondità specificata è valida rispetto al numero di esempi nel dataset.
	 * Se la profondità è maggiore del numero di esempi o è negativa, lancia un'eccezione InvalidDepthException.
	 *
	 * @param k    La profondità da verificare.
	 * @param data L'oggetto {@link Data} che contiene il numero di esempi.
	 * @throws InvalidDepthException Se la profondità specificata non è valida.
	 */
	public static void VerificareDimensione(int k, Data data) throws InvalidDepthException {
		if(k>data.getNumberOfExamples() || k<0)
			throw new InvalidDepthException("dimensione inserita non valida");
	}
	

}
