package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;

import java.io.Serializable;

class Dendrogram implements Serializable {
	private ClusterSet[] tree;

	Dendrogram(int depth) {
		tree=new ClusterSet[depth];
	}
	
	void setClusterSet(ClusterSet c, int level) {
		tree[level]=c;
	}
	
	ClusterSet getClusterSet(int level) {
		return tree[level];
	}
	
	int getDepth() {
		return tree.length;
	}
	
	public String toString() {
		StringBuilder v= new StringBuilder();
		for (int i=0;i<tree.length;i++)
			v.append("level").append(i).append(":\n").append(tree[i]).append("\n");
		return v.toString();
	}

	String toString(Data data) {
		StringBuilder v= new StringBuilder();
		for (int i=0;i<tree.length;i++)
			v.append("level").append(i).append(":\n").append(tree[i].toString(data)).append("\n");
		return v.toString();
	}


}
