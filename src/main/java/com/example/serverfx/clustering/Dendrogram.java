package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;

import java.io.Serializable;

/**
 * La classe Dendrogram rappresenta una struttura ad albero che memorizza
 * un insieme di cluster a vari livelli di un dendrogramma. Ogni livello
 * del dendrogramma contiene un {@link ClusterSet} che rappresenta lo stato
 * dei cluster a quel livello. Implementa {@link Serializable} per consentire
 * la serializzazione degli oggetti Dendrogram.
 */
class Dendrogram implements Serializable {
	private ClusterSet[] tree;

	/**
	 * Costruttore che crea un Dendrogram con una profondità specificata.
	 *
	 * @param depth Il numero di livelli del dendrogramma, ovvero la profondità.
	 */
	Dendrogram(int depth) {
		tree=new ClusterSet[depth];
	}

	/**
	 * Imposta un {@link ClusterSet} in un livello specificato del dendrogramma.
	 *
	 * @param c     Il ClusterSet da inserire nel dendrogramma.
	 * @param level Il livello del dendrogramma in cui inserire il ClusterSet.
	 */
	void setClusterSet(ClusterSet c, int level) {
		tree[level]=c;
	}

	/**
	 * Restituisce il {@link ClusterSet} presente a un livello specificato del dendrogramma.
	 *
	 * @param level Il livello del dendrogramma di cui si desidera ottenere il ClusterSet.
	 * @return Il ClusterSet presente al livello specificato.
	 */
	ClusterSet getClusterSet(int level) {
		return tree[level];
	}

	/**
	 * Restituisce la profondità del dendrogramma, ovvero il numero di livelli che contiene.
	 *
	 * @return La profondità del dendrogramma.
	 */
	int getDepth() {
		return tree.length;
	}

	/**
	 * Restituisce una rappresentazione testuale del dendrogramma,
	 * con i vari livelli e i rispettivi {@link ClusterSet}.
	 *
	 * @return Una stringa che rappresenta i livelli e i ClusterSet nel dendrogramma.
	 */
	public String toString() {
		StringBuilder v= new StringBuilder();
		for (int i=0;i<tree.length;i++)
			v.append("level").append(i).append(":\n").append(tree[i]).append("\n");
		return v.toString();
	}

	/**
	 * Restituisce una rappresentazione testuale dettagliata del dendrogramma,
	 * utilizzando un oggetto {@link Data} per ottenere informazioni aggiuntive sui dati nei cluster.
	 *
	 * @param data L'oggetto {@link Data} utilizzato per ottenere informazioni aggiuntive sui dati nei cluster.
	 * @return Una stringa che rappresenta i livelli e i ClusterSet nel dendrogramma con informazioni dettagliate sui dati.
	 */
	String toString(Data data) {
		StringBuilder v= new StringBuilder();
		for (int i=0;i<tree.length;i++)
			v.append("level").append(i).append(":\n").append(tree[i].toString(data)).append("\n");
		return v.toString();
	}


}
