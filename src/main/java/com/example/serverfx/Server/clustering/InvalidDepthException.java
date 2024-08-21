package com.example.serverfx.Server.clustering;

import com.example.serverfx.Server.data.Data;

public class InvalidDepthException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidDepthException() {}
	
	public InvalidDepthException(String msg) {
		super(msg);
		
	}

	/**
	 *
	 * @param k
	 * @param data
	 * @throws InvalidDepthException
	 */
	public static void VerificareDimensione(int k, Data data) throws InvalidDepthException {
		if(k>data.getNumberOfExamples())
			throw new InvalidDepthException("dimensione inserita non valida,superiore al numero di esempi");
	}
	

}
