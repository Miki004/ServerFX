package com.example.serverfx.data;
import com.example.serverfx.distance.InvalidSizeException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



/**
 * La classe Example rappresenta un esempio con un insieme di valori numerici.
 * Implementa l'interfaccia {@link Iterable<Double>} per consentire l'iterazione sui valori numerici.
 */
public class Example  implements Iterable<Double>{
	private List<Double> example;

	/**
	 * Costruisce un oggetto Example vuoto.
	 */
	public Example () {example=new LinkedList<Double>();}

	/**
	 * Aggiunge un valore numerico all'esempio.
	 *
	 * @param v il valore numerico da aggiungere.
	 */
	public void add(Double v) {
		example.add(v);
	}
	/**
	 * Restituisce un iteratore sui valori numerici dell'esempio.
	 *
	 * @return un iteratore sui valori numerici.
	 */
	public Iterator<Double> iterator() {
		return example.iterator();//porto fuori dalla classe l'iteratore di example
	}

	/**
	 * Restituisce il valore numerico all'indice specificato.
	 *
	 * @param index l'indice del valore da restituire.
	 * @return il valore numerico all'indice specificato.
	 */
	Double get(int index) {
		return example.get(index);
	}

	/**
	 * Calcola la distanza euclidea tra questo esempio e un altro esempio.
	 *
	 * @param newE l'altro esempio con cui calcolare la distanza.
	 * @return la distanza euclidea tra i due esempi.
	 * @throws InvalidSizeException se le dimensioni degli esempi non sono compatibili per il calcolo della distanza.
	 */
	public double distance(Example newE) throws InvalidSizeException {
		InvalidSizeException.VerificareDimensione(this.example.size(), newE.example.size());
		double distance=0;
		double diff=0;
		Iterator<Double> it=example.iterator();
		Iterator<Double> itnew=newE.iterator();
		while(it.hasNext()) {
			diff=it.next()-itnew.next();
			distance+=diff*diff;
		}
		return distance;
	}

	/**
	 * Restituisce una rappresentazione in formato stringa dei valori numerici dell'esempio.
	 *
	 * @return una stringa che rappresenta i valori numerici dell'esempio, separati da virgole.
	 */
	public String toString() {
		StringBuilder str= new StringBuilder();
		Iterator<Double> it=example.iterator();
		int i;
		i=0;
		while(it.hasNext()) {
			str.append(it.next());
			if(i!=example.size()-1) {
				str.append(",");
			}
			i++;
		}
		return str.toString();

	}
}
