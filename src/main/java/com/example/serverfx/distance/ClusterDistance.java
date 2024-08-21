package com.example.serverfx.distance;

import com.example.serverfx.clustering.Cluster;
import com.example.serverfx.data.Data;

public interface ClusterDistance {
	//visibilità public
	double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException;
}

