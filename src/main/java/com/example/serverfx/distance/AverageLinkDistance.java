package com.example.serverfx.distance;
import com.example.serverfx.clustering.Cluster;
import com.example.serverfx.data.Data;
import com.example.serverfx.data.Example;

import java.util.Iterator;


public class AverageLinkDistance implements ClusterDistance{
	public double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException {
		double distance=0;
		Iterator<Integer> it2=c2.iterator();
		Iterator<Integer> it=c1.iterator();
		while(it.hasNext()) {
			Example e1=d.getExample(it.next());
			while(it2.hasNext()) {
				distance=distance+e1.distance(d.getExample(it2.next()));

			}
		}
		return distance;
		/*
		for (int i=0;i< c1.getSize();i++) {
			Example e1=d.getExample(c1.getElement(i));
			for(int j=0; j<c2.getSize();j++) {
				distance=distance+e1.distance(d.getExample(c2.getElement(j)));	
			}
		}
		distance=distance/(c1.getSize()*c2.getSize());
		*/
		
	}

}
