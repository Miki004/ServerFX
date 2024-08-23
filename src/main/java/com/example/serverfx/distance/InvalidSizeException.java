package com.example.serverfx.distance;

/**
 * Eccezione lanciata per indicare che si è verificato un problema con le dimensioni dei dati.
 * <p>
 * Questa eccezione viene sollevata quando ci sono discrepanze nelle dimensioni degli oggetti con cui si lavora,
 * come ad esempio quando si confrontano o si elaborano esempi di dimensioni diverse.
 * </p>
 */
public class InvalidSizeException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * Crea un'eccezione senza messaggio specifico.
	 */
	public InvalidSizeException() {}
	/**
	 * Crea un'eccezione con un messaggio specifico.
	 *
	 * @param msg il messaggio che descrive l'eccezione.
	 */
	public InvalidSizeException(String msg) {
		super(msg);
	}

	/**
	 * Verifica se due dimensioni sono valide e lancia un'eccezione se la dimensione del primo parametro è maggiore
	 * della dimensione del secondo parametro.
	 * <p>
	 * Questo metodo è utile per garantire che le dimensioni degli esempi o dei cluster siano coerenti prima di
	 * eseguire operazioni che richiedono una corrispondenza di dimensioni.
	 * </p>
	 *
	 * @param d1 la dimensione del primo oggetto.
	 * @param d2 la dimensione del secondo oggetto.
	 * @throws InvalidSizeException se la dimensione del primo oggetto è maggiore di quella del secondo oggetto.
	 */
	public static void VerificareDimensione(int d1,int d2) throws InvalidSizeException {
		if(d1>d2) 
			throw new InvalidSizeException("lunghezza degli esempi diversa");
		
	}

}
