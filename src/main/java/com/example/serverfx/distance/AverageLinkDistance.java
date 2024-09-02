package com.example.serverfx.distance;
import com.example.serverfx.clustering.Cluster;
import com.example.serverfx.data.Data;
import com.example.serverfx.data.Example;

import java.util.Iterator;

/**
 * Implementa la distanza tra cluster utilizzando il metodo della distanza media (Average Linkage).
 * La distanza tra due cluster è calcolata come la media delle distanze tra tutti i membri dei due cluster.
 */
public class AverageLinkDistance implements ClusterDistance{

	/**
	 * Calcola la distanza media tra due cluster.
	 *
	 * @param c1 Il primo cluster.
	 * @param c2 Il secondo cluster.
	 * @param d  L'oggetto {@link Data} che contiene gli esempi utilizzati per il calcolo delle distanze.
	 * @return La distanza media tra i due cluster.
	 * @throws InvalidSizeException Se si verifica un errore di dimensione durante il calcolo della distanza.
	 */
	public double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException {
		double distance=0;
		Iterator<Integer> it2=c2.iterator();
		Iterator<Integer> it=c1.iterator();
		while(it.hasNext()) {
			Example e1=d.getExample(it.next());
			while(it2.hasNext()) {
				distance=distance+e1.distance(d.getExample(it2.next()));

			}
		}
		return distance;
	}

}
