package com.example.serverfx.distance;

import com.example.serverfx.clustering.Cluster;
import com.example.serverfx.data.Data;

/**
 * Interfaccia per calcolare la distanza tra due cluster.
 * La distanza è calcolata in base a un criterio specifico definito dalle classi che implementano questa interfaccia.
 */
public interface ClusterDistance {


	/**
	 * Calcola la distanza tra due cluster.
	 *
	 * @param c1 Il primo cluster.
	 * @param c2 Il secondo cluster.
	 * @param d  L'oggetto {@link Data} che contiene gli esempi utilizzati per il calcolo delle distanze.
	 * @return La distanza tra i due cluster, calcolata secondo il criterio specificato dalla classe che implementa questa interfaccia.
	 * @throws InvalidSizeException Se si verifica un errore di dimensione durante il calcolo della distanza.
	 */
	double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException;
}

