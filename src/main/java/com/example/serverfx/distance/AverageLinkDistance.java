package com.example.serverfx.distance;
import com.example.serverfx.clustering.Cluster;
import com.example.serverfx.data.Data;
import com.example.serverfx.data.Example;

import java.util.Iterator;

/**
 * Implementa la misura di distanza tra due cluster utilizzando la tecnica dell'Average Linkage.
 * La distanza media (Average Linkage) tra due cluster è calcolata come la media delle distanze tra tutti i
 * possibili accoppiamenti di esempi tra i due cluster.
 */
public class AverageLinkDistance implements ClusterDistance{

	/**
	 * Calcola la distanza tra due cluster utilizzando la tecnica dell'Average Linkage.
	 *
	 * @param c1 il primo cluster.
	 * @param c2 il secondo cluster.
	 * @param d il dataset contenente gli esempi.
	 * @return la distanza media tra i due cluster.
	 * @throws InvalidSizeException se le dimensioni dei cluster non sono valide.
	 */
	public double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException {
		double distance=0;
		Iterator<Integer> it2=c2.iterator();
		Iterator<Integer> it=c1.iterator();

		// Itera attraverso tutti gli esempi del primo cluster
		while(it.hasNext()) {
			Example e1=d.getExample(it.next());
			// Itera attraverso tutti gli esempi del secondo cluster
			while(it2.hasNext()) {
				distance=distance+e1.distance(d.getExample(it2.next()));

			}
		}
		return distance;
		/*
		for (int i=0;i< c1.getSize();i++) {
			Example e1=d.getExample(c1.getElement(i));
			for(int j=0; j<c2.getSize();j++) {
				distance=distance+e1.distance(d.getExample(c2.getElement(j)));	
			}
		}
		distance=distance/(c1.getSize()*c2.getSize());
		*/
		
	}

}
