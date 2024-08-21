package com.example.serverfx.distance;

public class InvalidSizeException extends Exception {

	private static final long serialVersionUID = 1L;
	public InvalidSizeException() {}
	public InvalidSizeException(String msg) {
		super(msg);
	}
	 
	public static void VerificareDimensione(int d1,int d2) throws InvalidSizeException {
		if(d1>d2) 
			throw new InvalidSizeException("lunghezza degli esempi diversa");
		
	}

}
