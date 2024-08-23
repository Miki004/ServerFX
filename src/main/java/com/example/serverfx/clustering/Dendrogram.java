package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;

import java.io.Serializable;

/**
 * La classe Dendrogram rappresenta una struttura ad albero di cluster a diversi livelli di profondità.
 * È utilizzata per mantenere e visualizzare l'evoluzione dei cluster attraverso i vari livelli dell'albero.
 * La classe è serializzabile.
 */
class Dendrogram implements Serializable {
	private ClusterSet[] tree;

	/**
	 * Costruisce un Dendrogram con una profondità specificata.
	 *
	 * @param depth il numero di livelli dell'albero del dendrogramma.
	 */
	Dendrogram(int depth) {
		tree=new ClusterSet[depth];
	}

	/**
	 * Imposta il ClusterSet per un livello specificato del dendrogramma.
	 *
	 * @param c il ClusterSet da impostare.
	 * @param level il livello dell'albero al quale impostare il ClusterSet.
	 */
	void setClusterSet(ClusterSet c, int level) {
		tree[level]=c;
	}

	/**
	 * Restituisce il ClusterSet per un livello specificato del dendrogramma.
	 *
	 * @param level il livello dell'albero di cui restituire il ClusterSet.
	 * @return il ClusterSet al livello specificato.
	 */
	ClusterSet getClusterSet(int level) {
		return tree[level];
	}

	/**
	 * Restituisce la profondità del dendrogramma, ovvero il numero di livelli.
	 *
	 * @return la profondità del dendrogramma.
	 */
	int getDepth() {
		return tree.length;
	}

	/**
	 * Restituisce una rappresentazione in formato stringa dell'intero dendrogramma.
	 *
	 * @return una stringa che rappresenta i cluster a tutti i livelli del dendrogramma.
	 */
	public String toString() {
		StringBuilder v= new StringBuilder();
		for (int i=0;i<tree.length;i++)
			v.append("level").append(i).append(":\n").append(tree[i]).append("\n");
		return v.toString();
	}

	/**
	 * Restituisce una rappresentazione in formato stringa dell'intero dendrogramma,
	 * utilizzando un oggetto Data per ottenere la rappresentazione dei campioni a ciascun livello.
	 *
	 * @param data l'oggetto Data utilizzato per ottenere la rappresentazione dei campioni.
	 * @return una stringa che rappresenta i cluster a tutti i livelli del dendrogramma con le informazioni dei campioni.
	 */
	String toString(Data data) {
		StringBuilder v= new StringBuilder();
		for (int i=0;i<tree.length;i++)
			v.append("level").append(i).append(":\n").append(tree[i].toString(data)).append("\n");
		return v.toString();
	}


}
