package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * La classe Cluster rappresenta un insieme di dati raggruppati, con funzionalità per aggiungere,
 * fondere e iterare sui dati raggruppati. Utilizza un TreeSet per mantenere i dati ordinati.
 * La classe è serializzabile, clonabile e supporta l'iterazione.
 */
public class Cluster implements Iterable<Integer>, Cloneable, Serializable {
    private Set<Integer> clusteredData = new TreeSet<>();
    /**
     * Aggiunge l'indice di un campione al cluster.
     *
     * @param id l'indice del campione da aggiungere al cluster.
     */
    void addData(int id) {
        clusteredData.add(id);
    }

    /**
     * Restituisce la dimensione del cluster, ovvero il numero di campioni contenuti.
     *
     * @return la dimensione del cluster.
     */
    public int getSize() {
        return clusteredData.size();
    }

    /**
     * Crea e restituisce una copia di questo cluster.
     *
     * @return un clone di questo cluster.
     */
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.print("clone non supportato");
        }
        return o;
    }

    /**
     * Crea un nuovo cluster che è la fusione di questo cluster con un altro cluster specificato.
     *
     * @param c il cluster da fondere con questo cluster.
     * @return un nuovo cluster che rappresenta la fusione dei due cluster.
     */
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

    /**
     * Restituisce una rappresentazione in formato stringa del cluster.
     * Gli indici dei campioni nel cluster sono separati da una virgola.
     *
     * @return una stringa che rappresenta il cluster.
     */
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

    /**
     * Restituisce una rappresentazione in formato stringa del cluster,
     * utilizzando un oggetto Data per ottenere la rappresentazione dei campioni.
     *
     * @param data l'oggetto Data utilizzato per ottenere la rappresentazione dei campioni.
     * @return una stringa che rappresenta il cluster con le informazioni dei campioni.
     */
    String toString(Data data) {
        StringBuilder str = new StringBuilder();
        for (Integer e : clusteredData)
            str.append("<" + "[").append(data.getExample(e)).append("]").append(">");

        return str.toString();

    }

    /**
     * Restituisce un iteratore per iterare sugli indici dei campioni contenuti nel cluster.
     *
     * @return un iteratore sugli indici dei campioni nel cluster.
     */
    @Override
    public Iterator<Integer> iterator() {
        return clusteredData.iterator();
    }

}
