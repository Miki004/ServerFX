package com.example.serverfx.distance;
import com.example.serverfx.clustering.Cluster;
import com.example.serverfx.data.Data;
import com.example.serverfx.data.Example;

import java.util.Iterator;

/**
 * Implementa la distanza tra cluster utilizzando il metodo del single-linkage.
 * <p>
 * Il single-linkage (o minima distanza) tra due cluster è definito come la distanza minima tra
 * qualsiasi coppia di punti, uno appartenente al primo cluster e l'altro al secondo cluster.
 * </p>
 */
public class SingleLinkDistance implements ClusterDistance {
	/**
	 * Calcola la distanza tra due cluster utilizzando il single-linkage.
	 *
	 * @param c1 il primo cluster.
	 * @param c2 il secondo cluster.
	 * @param d  i dati utilizzati per calcolare le distanze tra gli esempi.
	 * @return la distanza minima tra i due cluster.
	 * @throws InvalidSizeException se le dimensioni degli esempi sono diverse.
	 */
	public double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException {
		double min=Double.MAX_VALUE;
		Iterator<Integer> it2=c2.iterator();
		Iterator<Integer> it=c1.iterator();

		// Itera su tutti gli esempi del primo cluster.
		while(it.hasNext()) {
			Example e1=d.getExample(it.next());

			// Itera su tutti gli esempi del secondo cluster.
			while(it2.hasNext()) {

				// Calcola la distanza tra i due esempi.
				double distance=e1.distance(d.getExample(it2.next()));

				// Aggiorna la distanza minima se la distanza attuale è più piccola.
				if(distance<min) {
					min=distance;
				}
			}
		}
		return min;
	}

}
