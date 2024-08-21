package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class Cluster implements Iterable<Integer>, Cloneable, Serializable {
    private Set<Integer> clusteredData = new TreeSet<>();
    //add the index of a sample to the cluster, il tree set è un set ordinato
    void addData(int id) {
        clusteredData.add(id);
    }

    public int getSize() {
        return clusteredData.size();
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.print("clone non supportato");
        }
        return o;
    }

    // crea un nuovo cluster che è la fusione dei due cluster pre-esistenti
    Cluster mergeCluster(Cluster c) {

        Cluster newC = new Cluster();
        for (Integer e : clusteredData) {
            newC.addData(e);
        }
        for (Integer e : c.clusteredData) {
            newC.addData(e);
        }
        return newC;

    }


    public String toString() {
        int i = 0;
        Iterator<Integer> it = clusteredData.iterator();
        StringBuilder str = new StringBuilder();
        while (it.hasNext()) {
            str.append(it.next());
        if (i != clusteredData.size() - 1) {
            str.append(",");
            i++;
        }
    }
        return str.toString();
    }

    String toString(Data data) {
        StringBuilder str = new StringBuilder();
        for (Integer e : clusteredData)
            str.append("<" + "[").append(data.getExample(e)).append("]").append(">");

        return str.toString();

    }

    @Override
    public Iterator<Integer> iterator() {
        return clusteredData.iterator();
    }


}
