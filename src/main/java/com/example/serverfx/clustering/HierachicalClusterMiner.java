package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;
import com.example.serverfx.distance.ClusterDistance;
import com.example.serverfx.distance.InvalidSizeException;

import java.io.*;

/**
 * La classe HierachicalClusterMiner esegue un clustering gerarchico sui dati utilizzando un dendrogramma.
 * Permette di inizializzare il clustering, eseguire il mining dei cluster e salvare o caricare l'oggetto
 * da e verso un file.
 * La classe è serializzabile.
 */
public class HierachicalClusterMiner implements Serializable {
	private Dendrogram dendrogram;

	/**
	 * Costruisce un HierachicalClusterMiner con una profondità specificata per il dendrogramma.
	 *
	 * @param depth la profondità del dendrogramma.
	 */
	public HierachicalClusterMiner(int depth) {
		dendrogram= new Dendrogram(depth);
	}

	/**
	 * Restituisce una rappresentazione in formato stringa del dendrogramma.
	 *
	 * @return una stringa che rappresenta il dendrogramma.
	 */
	public String toString() {
		return dendrogram.toString();
	}

	/**
	 * Restituisce una rappresentazione in formato stringa del dendrogramma,
	 * utilizzando un oggetto Data per ottenere la rappresentazione dei campioni.
	 *
	 * @param data l'oggetto Data utilizzato per ottenere la rappresentazione dei campioni.
	 * @return una stringa che rappresenta il dendrogramma con le informazioni dei campioni.
	 */
	public String toString(Data data) {
		return dendrogram.toString(data);
	}

	/**
	 * Esegue il mining dei cluster sui dati utilizzando una distanza specificata per fondere i cluster.
	 *
	 * @param data i dati sui quali eseguire il mining dei cluster.
	 * @param distance l'oggetto ClusterDistance utilizzato per calcolare la distanza tra i cluster.
	 * @throws InvalidSizeException se il calcolo della distanza tra i cluster fallisce a causa di dimensioni non valide.
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
	 * Carica un oggetto HierachicalClusterMiner da un file.
	 *
	 * @param fileName il nome del file da cui caricare l'oggetto.
	 * @return l'oggetto HierachicalClusterMiner caricato dal file.
	 * @throws FileNotFoundException se il file specificato non viene trovato.
	 * @throws IOException se si verifica un errore durante la lettura del file.
	 * @throws ClassNotFoundException se la classe dell'oggetto non viene trovata durante il caricamento.
	 */
	public static HierachicalClusterMiner loaHierachicalClusterMiner(String fileName) throws FileNotFoundException,IOException,ClassNotFoundException {
			System.out.println(fileName);
			ObjectInputStream inStream= new ObjectInputStream(new FileInputStream(fileName));
			HierachicalClusterMiner clusterMiner=(HierachicalClusterMiner) inStream.readObject();
			inStream.close();
			return clusterMiner;
	}

	/**
	 * Salva l'oggetto HierachicalClusterMiner in un file.
	 *
	 * @param fileName il nome del file in cui salvare l'oggetto.
	 * @throws FileNotFoundException se il file non può essere creato o aperto.
	 * @throws IOException se si verifica un errore durante la scrittura del file.
	 */
	public void salva(String fileName) throws FileNotFoundException, IOException {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(this);
			out.close();
	}
}
