package com.example.serverfx.distance;

import com.example.serverfx.clustering.Cluster;
import com.example.serverfx.data.Data;

/**
 * Interfaccia per la misura della distanza tra due cluster.
 * <p>
 * Questa interfaccia definisce un metodo per calcolare la distanza tra due cluster utilizzando un criterio specifico
 * di distanza. La distanza può essere utilizzata in algoritmi di clustering per determinare quanto sono simili o diversi
 * i cluster tra loro.
 * </p>
 */
public interface ClusterDistance {
	/**
	 * Calcola la distanza tra due cluster.
	 * <p>
	 * La distanza viene calcolata in base al criterio specifico implementato dalla classe che
	 * implementa questa interfaccia. Il metodo può utilizzare il dataset fornito per calcolare la distanza
	 * tra gli elementi dei due cluster.
	 * </p>
	 *
	 * @param c1 il primo cluster.
	 * @param c2 il secondo cluster.
	 * @param d il dataset contenente gli esempi da cui calcolare la distanza tra i cluster.
	 * @return la distanza calcolata tra i due cluster.
	 * @throws InvalidSizeException se le dimensioni dei cluster non sono valide o se si verifica un problema
	 *                               con i dati.
	 */
	double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException;
}

