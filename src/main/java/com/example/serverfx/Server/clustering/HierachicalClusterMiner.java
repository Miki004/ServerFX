package com.example.serverfx.Server.clustering;

import com.example.serverfx.Server.data.Data;
import com.example.serverfx.Server.distance.ClusterDistance;
import com.example.serverfx.Server.distance.InvalidSizeException;

import java.io.*;

public class HierachicalClusterMiner implements Serializable {
	private Dendrogram dendrogram;

	public HierachicalClusterMiner(int depth) {
		dendrogram= new Dendrogram(depth);
	}

	public String toString() {
		return dendrogram.toString();
	}

	public String toString(Data data) {
		return dendrogram.toString(data);
	}

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

	public static HierachicalClusterMiner loaHierachicalClusterMiner(String fileName) throws FileNotFoundException,IOException,ClassNotFoundException {
			System.out.println(fileName);
			ObjectInputStream inStream= new ObjectInputStream(new FileInputStream(fileName));
			HierachicalClusterMiner clusterMiner=(HierachicalClusterMiner) inStream.readObject();
			inStream.close();
			return clusterMiner;
	}

	public void salva(String fileName) throws FileNotFoundException, IOException {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(this);
			out.close();
	}
}
