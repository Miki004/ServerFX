package com.example.serverfx.distance;
import com.example.serverfx.clustering.Cluster;
import com.example.serverfx.data.Data;
import com.example.serverfx.data.Example;

import java.util.Iterator;

/**
 * Implementa la misura di distanza tra cluster utilizzando la tecnica del single-link.
 * La distanza tra due cluster è definita come la distanza minima tra i membri dei due cluster.
 */
public class SingleLinkDistance implements ClusterDistance {

	/**
	 * Calcola la distanza tra due cluster utilizzando la misura di distanza del single-link.
	 * La distanza tra i cluster è la distanza minima tra tutti i membri dei due cluster.
	 *
	 * @param c1 Il primo cluster.
	 * @param c2 Il secondo cluster.
	 * @param d  Il dataset contenente gli esempi.
	 * @return La distanza minima tra i membri di c1 e c2.
	 * @throws InvalidSizeException Se le dimensioni degli esempi nei cluster non sono compatibili.
	 */
	public double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException {
		double min=Double.MAX_VALUE;
		Iterator<Integer> it2=c2.iterator();
		Iterator<Integer> it=c1.iterator();
		while(it.hasNext()) {
			Example e1=d.getExample(it.next());
			while(it2.hasNext()) {
				double distance=e1.distance(d.getExample(it2.next()));
				if(distance<min) {
					min=distance;
				}
			}
		}
		return min;
	}

}
