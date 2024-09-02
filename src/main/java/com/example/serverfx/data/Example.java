package com.example.serverfx.data;
import com.example.serverfx.distance.InvalidSizeException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * La classe Example rappresenta un esempio individuale costituito da un insieme di valori numerici.
 * Ogni valore numerico è memorizzato come un {@link Double} in una lista.
 * La classe supporta il calcolo della distanza euclidea tra due esempi e fornisce un iteratore per scorrere i valori.
 */
public class Example  implements Iterable<Double>{
	private List<Double> example;

	/**
	 * Costruttore che inizializza un esempio vuoto.
	 * I valori numerici possono essere aggiunti successivamente usando il metodo {@link #add(Double)}.
	 */
	public Example () {example=new LinkedList<Double>();}

	/**
	 * Aggiunge un valore numerico all'esempio.
	 *
	 * @param v Il valore numerico da aggiungere all'esempio.
	 */
	public void add(Double v) {
		example.add(v);
	}

	/**
	 * Restituisce un iteratore per scorrere i valori numerici dell'esempio.
	 *
	 * @return Un iteratore di {@link Double} per l'esempio.
	 */
	public Iterator<Double> iterator() {
		return example.iterator();//porto fuori dalla classe l'iteratore di example
	}

	/**
	 * Restituisce il valore numerico all'indice specificato.
	 *
	 * @param index L'indice del valore da restituire.
	 * @return Il valore numerico all'indice specificato.
	 */
	Double get(int index) {
		return example.get(index);
	}

	/**
	 * Calcola la distanza euclidea tra questo esempio e un altro esempio fornito.
	 * La distanza è calcolata come la somma delle differenze al quadrato tra i corrispondenti valori numerici.
	 * Lancia un'eccezione se i due esempi non hanno lo stesso numero di valori.
	 *
	 * @param newE L'altro esempio con cui calcolare la distanza.
	 * @return La distanza euclidea tra i due esempi.
	 * @throws InvalidSizeException Se i due esempi non hanno lo stesso numero di valori.
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
	 * Restituisce una rappresentazione testuale dell'esempio, con i valori separati da virgole.
	 *
	 * @return Una stringa che rappresenta i valori numerici dell'esempio.
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
