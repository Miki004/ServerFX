package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;

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
		if(k>data.getNumberOfExamples() || k<0)
			throw new InvalidDepthException("dimensione inserita non valida");
	}
	

}
