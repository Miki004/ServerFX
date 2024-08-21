package com.example.serverfx.Server.data;
import com.example.serverfx.Server.distance.InvalidSizeException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



public class Example  implements Iterable<Double>{
	private List<Double> example;

	public Example () {example=new LinkedList<Double>();}
	
	public void add(Double v) {
		example.add(v);
	}
	public Iterator<Double> iterator() {
		return example.iterator();//porto fuori dalla classe l'iteratore di example
	}
	Double get(int index) {
		return example.get(index);
	}

	public double distance(Example newE) throws InvalidSizeException {
		InvalidSizeException.VerificareDimensione(this.example.size(), newE.example.size());
		double distance=0;
		double diff=0;
		Iterator<Double> it=example.iterator();
		Iterator<Double> itnew=newE.iterator();
		while(it.hasNext()) {
			diff=it.next()-itnew.next();
			distance+=diff*diff;
		}
		return distance;
	}

	public String toString() {
		StringBuilder str= new StringBuilder();
		Iterator<Double> it=example.iterator();
		int i;
		i=0;
		while(it.hasNext()) {
			str.append(it.next());
			if(i!=example.size()-1) {
				str.append(",");
			}
			i++;
		}
		return str.toString();

	}
}
