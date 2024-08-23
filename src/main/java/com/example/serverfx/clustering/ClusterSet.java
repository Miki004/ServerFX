package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;
import com.example.serverfx.distance.ClusterDistance;
import com.example.serverfx.distance.InvalidSizeException;

import java.io.Serializable;

/**
 * La classe ClusterSet gestisce un insieme di cluster, permettendo di aggiungere cluster,
 * accedere ai cluster esistenti, e unire i cluster più vicini basandosi su una misura di distanza.
 * La classe è serializzabile.
 */
class ClusterSet implements Serializable {

	private Cluster C[];
	private int lastClusterIndex=0;

	/**
	 * Costruisce un ClusterSet con una capacità specificata.
	 *
	 * @param k il numero massimo di cluster che questo ClusterSet può contenere.
	 */
	ClusterSet(int k){
		C=new Cluster[k];
	}

	/**
	 * Aggiunge un cluster al ClusterSet. Evita di aggiungere cluster duplicati.
	 *
	 * @param c il cluster da aggiungere.
	 */
	void add(Cluster c){
		for(int j=0;j<lastClusterIndex;j++)
			if(c==C[j]) // to avoid duplicates
				return;

		C[lastClusterIndex]=c;
		lastClusterIndex++;
	}

	/**
	 * Restituisce il cluster all'indice specificato.
	 *
	 * @param i l'indice del cluster da restituire.
	 * @return il cluster all'indice specificato.
	 */
	Cluster get(int i){
		return C[i];
	}

	/**
	 * Restituisce una rappresentazione in formato stringa di tutti i cluster nel ClusterSet.
	 *
	 * @return una stringa che rappresenta i cluster nel ClusterSet.
	 */
	public String toString(){
		StringBuilder str= new StringBuilder();
		for(int i=0;i<C.length;i++){
			if (C[i]!=null){
				str.append("cluster").append(i).append(":").append(C[i]).append("\n");

			}
		}
		return str.toString();

	}

	/**
	 * Restituisce una rappresentazione in formato stringa di tutti i cluster nel ClusterSet,
	 * utilizzando un oggetto Data per ottenere la rappresentazione dei campioni.
	 *
	 * @param data l'oggetto Data utilizzato per ottenere la rappresentazione dei campioni.
	 * @return una stringa che rappresenta i cluster nel ClusterSet con le informazioni dei campioni.
	 */
	public String toString(Data data){
		String str="";
		for(int i=0;i<C.length;i++){
			if (C[i]!=null){
				str+="cluster"+i+":"+C[i].toString(data)+"\n";

			}
		}
		return str;

	}

	/**
	 * Unisce i due cluster più vicini basandosi su una misura di distanza specificata e restituisce
	 * un nuovo ClusterSet con il cluster unito e i cluster rimanenti.
	 *
	 * @param distance l'oggetto ClusterDistance utilizzato per calcolare la distanza tra i cluster.
	 * @param data l'oggetto Data utilizzato per calcolare la distanza tra i cluster.
	 * @return un nuovo ClusterSet con i cluster uniti.
	 * @throws InvalidSizeException se il calcolo della distanza tra i cluster fallisce a causa di dimensioni non valide.
	 */
	ClusterSet mergeClosestClusters(ClusterDistance distance, Data data) throws InvalidSizeException {
		Cluster mergedCluster= new Cluster();
		ClusterSet mergedClusterSet= new ClusterSet(C.length-1);
		double distanza=0.0;
		double min=Double.MAX_VALUE;
		int c1=0;
		int c2=0;
		int k=0;
		int j=0;
		while(j<C.length) {
			k=j+1;
			while(k<C.length) {
				distanza=distance.distance(C[j], C[k], data);
				if(distanza<min) {
					c1=j;
					c2=k;
					min=distanza;
				}
				k++;
			}
			j++;
		}
		//i cluster in posizione c1 e c2 rappresentano i cluster più vicini.
		mergedCluster=C[c1].mergeCluster(C[c2]);
		for(int z=0; z<C.length; z++) {
			if(z==c1) {
				mergedClusterSet.add(mergedCluster);
			}
			if(z!=c1 && z!=c2) {
				mergedClusterSet.add(C[z]);
			}
		}
		return mergedClusterSet;
	}

}


