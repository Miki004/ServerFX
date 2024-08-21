package com.example.serverfx.Server.distance;
import com.example.serverfx.Server.clustering.Cluster;
import com.example.serverfx.Server.data.Data;
import com.example.serverfx.Server.data.Example;

import java.util.Iterator;



public class SingleLinkDistance implements ClusterDistance {
	public double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException {
		double min=Double.MAX_VALUE;
		Iterator<Integer> it2=c2.iterator();
		Iterator<Integer> it=c1.iterator();
		while(it.hasNext()) {
			Example e1=d.getExample(it.next());
			while(it2.hasNext()) {
				double distance=e1.distance(d.getExample(it2.next()));
				if(distance<min) {
					min=distance;
				}
			}
		}
		return min;
	}

}
