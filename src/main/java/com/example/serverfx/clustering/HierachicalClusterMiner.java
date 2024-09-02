package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;
import com.example.serverfx.distance.ClusterDistance;
import com.example.serverfx.distance.InvalidSizeException;

import java.io.*;

/**
 * La classe HierachicalClusterMiner implementa un algoritmo di clustering gerarchico.
 * Consente di eseguire il clustering gerarchico su un insieme di dati, rappresentato da un dendrogramma.
 * Questa classe supporta la serializzazione per salvare e caricare l'oggetto HierachicalClusterMiner su/da un file.
 */
public class HierachicalClusterMiner implements Serializable {
	private Dendrogram dendrogram;

	/**
	 * Costruttore che crea un HierachicalClusterMiner con una profondità specificata per il dendrogramma.
	 *
	 * @param depth La profondità del dendrogramma, ovvero il numero di livelli.
	 */
	public HierachicalClusterMiner(int depth) {
		dendrogram= new Dendrogram(depth);
	}

	/**
	 * Restituisce una rappresentazione testuale del dendrogramma associato a questo miner.
	 *
	 * @return Una stringa che rappresenta i livelli e i ClusterSet nel dendrogramma.
	 */
	public String toString() {
		return dendrogram.toString();
	}

	/**
	 * Restituisce una rappresentazione testuale dettagliata del dendrogramma associato a questo miner,
	 * utilizzando un oggetto {@link Data} per ottenere informazioni aggiuntive sui dati nei cluster.
	 *
	 * @param data L'oggetto {@link Data} utilizzato per ottenere informazioni aggiuntive sui dati nei cluster.
	 * @return Una stringa che rappresenta i livelli e i ClusterSet nel dendrogramma con informazioni dettagliate sui dati.
	 */
	public String toString(Data data) {
		return dendrogram.toString(data);
	}

	/**
	 * Esegue l'algoritmo di clustering gerarchico sui dati forniti, utilizzando la metrica di distanza specificata.
	 * Ogni esempio nel dataset viene inizialmente assegnato a un cluster separato, e successivamente
	 * i cluster vengono fusi iterativamente in base alla distanza minima fino a formare un dendrogramma completo.
	 *
	 * @param data     Il dataset su cui eseguire il clustering.
	 * @param distance Un'istanza di {@link ClusterDistance} che definisce la metrica di distanza tra i cluster.
	 * @throws InvalidSizeException Se i cluster hanno dimensioni non valide per il calcolo della distanza.
	 */
	public void mine(Data data, ClusterDistance distance) throws InvalidSizeException {
		//inizializzo il clusterSet , dove ogni cluster contiene un esempio distinto
		ClusterSet startClusterSet= new ClusterSet(data.getNumberOfExamples());
		for(int i=0; i<data.getNumberOfExamples(); i++) {
			Cluster iesimo_cluster=new Cluster();
			iesimo_cluster.addData(i);
			startClusterSet.add(iesimo_cluster);
		}
		//nel livello 0 del dendrogramma verrà collocato il clusterSet iniziale con all'interno dei singoli cluster ogni esempio distinto
		dendrogram.setClusterSet(startClusterSet, 0);
		//comincio a fondere i cluster con una certa distance(oggetto di una classe che implementa l'interfaccia ClusterDistance.
		for(int j=0; j<dendrogram.getDepth()-1; j++) {
			ClusterSet newclusterSet=dendrogram.getClusterSet(j).mergeClosestClusters(distance, data);
			dendrogram.setClusterSet(newclusterSet, j+1);
		}
	}

	/**
	 * Carica un oggetto HierachicalClusterMiner da un file specificato.
	 *
	 * @param fileName Il nome del file da cui caricare l'oggetto.
	 * @return L'oggetto HierachicalClusterMiner caricato dal file.
	 * @throws FileNotFoundException Se il file specificato non viene trovato.
	 * @throws IOException           Se si verifica un errore di I/O durante la lettura del file.
	 * @throws ClassNotFoundException Se la classe dell'oggetto serializzato non viene trovata.
	 */
	public static HierachicalClusterMiner loaHierachicalClusterMiner(String fileName) throws FileNotFoundException,IOException,ClassNotFoundException {
			System.out.println(fileName);
			ObjectInputStream inStream= new ObjectInputStream(new FileInputStream(fileName));
			HierachicalClusterMiner clusterMiner=(HierachicalClusterMiner) inStream.readObject();
			inStream.close();
			return clusterMiner;
	}

	/**
	 * Salva l'oggetto HierachicalClusterMiner corrente su un file specificato.
	 *
	 * @param fileName Il nome del file su cui salvare l'oggetto.
	 * @throws FileNotFoundException Se il file specificato non può essere creato o aperto.
	 * @throws IOException           Se si verifica un errore di I/O durante la scrittura del file.
	 */
	public void salva(String fileName) throws FileNotFoundException, IOException {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(this);
			out.close();
	}
}
