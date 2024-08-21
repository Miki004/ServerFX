package com.example.serverfx.Server.clustering;

import com.example.serverfx.Server.data.Data;
import com.example.serverfx.Server.distance.ClusterDistance;
import com.example.serverfx.Server.distance.InvalidSizeException;

import java.io.Serializable;
import java.util.Iterator;


class ClusterSet implements Serializable {

	private Cluster C[];
	private int lastClusterIndex=0;

	ClusterSet(int k){
		C=new Cluster[k];
	}

	void add(Cluster c){
		for(int j=0;j<lastClusterIndex;j++)
			if(c==C[j]) // to avoid duplicates
				return;

		C[lastClusterIndex]=c;
		lastClusterIndex++;
	}

	Cluster get(int i){
		return C[i];
	}

	public String toString(){
		StringBuilder str= new StringBuilder();
		for(int i=0;i<C.length;i++){
			if (C[i]!=null){
				str.append("cluster").append(i).append(":").append(C[i]).append("\n");

			}
		}
		return str.toString();

	}

	public String toString(Data data){
		String str="";
		for(int i=0;i<C.length;i++){
			if (C[i]!=null){
				str+="cluster"+i+":"+C[i].toString(data)+"\n";

			}
		}
		return str;

	}

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


