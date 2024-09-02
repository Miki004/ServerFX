package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;
import com.example.serverfx.distance.ClusterDistance;
import com.example.serverfx.distance.InvalidSizeException;

import java.io.Serializable;

/**
 * La classe ClusterSet rappresenta un insieme di cluster.
 * Supporta operazioni di aggiunta di cluster, fusione dei cluster più vicini
 * basata su una metrica di distanza, e generazione di rappresentazioni testuali
 * dei cluster presenti nell'insieme.
 * Implementa {@link Serializable} per consentire la serializzazione degli oggetti ClusterSet.
 */
class ClusterSet implements Serializable {

	private Cluster C[];
	private int lastClusterIndex=0;

	/**
	 * Costruttore che crea un ClusterSet per contenere un numero specificato di cluster.
	 *
	 * @param k Il numero massimo di cluster che questo ClusterSet può contenere.
	 */
	ClusterSet(int k){
		C=new Cluster[k];
	}

	/**
	 * Aggiunge un cluster al ClusterSet, evitando duplicati.
	 *
	 * @param c Il cluster da aggiungere al ClusterSet.
	 */
	void add(Cluster c){
		for(int j=0;j<lastClusterIndex;j++)
			if(c==C[j]) // to avoid duplicates
				return;

		C[lastClusterIndex]=c;
		lastClusterIndex++;
	}

	/**
	 * Restituisce il cluster situato alla posizione specificata nell'insieme.
	 *
	 * @param i L'indice del cluster da restituire.
	 * @return Il cluster situato alla posizione specificata.
	 */
	Cluster get(int i){
		return C[i];
	}

	/**
	 * Restituisce una rappresentazione testuale dei cluster nel ClusterSet.
	 *
	 * @return Una stringa che rappresenta i cluster contenuti nel ClusterSet.
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
	 * Restituisce una rappresentazione testuale dettagliata dei cluster nel ClusterSet,
	 * utilizzando un oggetto {@link Data} per ottenere informazioni aggiuntive sui dati nei cluster.
	 *
	 * @param data L'oggetto {@link Data} utilizzato per ottenere informazioni aggiuntive sui dati nei cluster.
	 * @return Una stringa che rappresenta i cluster con informazioni dettagliate sui dati.
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
	 * Unisce i due cluster più vicini nel ClusterSet utilizzando una metrica di distanza specificata.
	 *
	 * @param distance Un'istanza di {@link ClusterDistance} che definisce la metrica di distanza tra cluster.
	 * @param data     L'oggetto {@link Data} utilizzato per calcolare la distanza tra i cluster.
	 * @return Un nuovo ClusterSet che contiene i cluster rimanenti e il nuovo cluster risultante dalla fusione.
	 * @throws InvalidSizeException Se i cluster hanno dimensioni non valide per il calcolo della distanza.
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


