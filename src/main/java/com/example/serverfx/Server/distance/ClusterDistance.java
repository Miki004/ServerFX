package com.example.serverfx.Server.distance;

import com.example.serverfx.Server.clustering.Cluster;
import com.example.serverfx.Server.data.Data;

public interface ClusterDistance {
	//visibilità public
	double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException;
}

