package com.example.serverfx.clustering;

import com.example.serverfx.data.Data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * La classe Cluster rappresenta un gruppo di dati raggruppati, identificati tramite interi.
 * Questa classe supporta operazioni di aggiunta di dati, fusione di cluster, clonazione e
 * iterazione sugli elementi del cluster. Implementa {@link Iterable} per consentire l'iterazione
 * sugli identificatori dei dati raggruppati, {@link Cloneable} per supportare la clonazione
 * e {@link Serializable} per consentire la serializzazione degli oggetti Cluster.
 */
public class Cluster implements Iterable<Integer>, Cloneable, Serializable {
    private Set<Integer> clusteredData = new TreeSet<>();

    /**
     * Aggiunge un identificatore di dato al cluster.
     *
     * @param id L'identificatore del dato da aggiungere al cluster.
     */
    void addData(int id) {
        clusteredData.add(id);
    }

    /**
     * Restituisce la dimensione del cluster, ovvero il numero di dati contenuti.
     *
     * @return Il numero di elementi nel cluster.
     */
    public int getSize() {
        return clusteredData.size();
    }

    /**
     * Crea e restituisce una copia del cluster corrente.
     *
     * @return Una copia del cluster corrente.
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
     * Crea un nuovo cluster che è la fusione del cluster corrente con un altro cluster specificato.
     *
     * @param c Il cluster da fondere con il cluster corrente.
     * @return Un nuovo cluster che contiene gli elementi di entrambi i cluster.
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
     * Restituisce una rappresentazione testuale degli identificatori dei dati nel cluster.
     *
     * @return Una stringa contenente gli identificatori dei dati nel cluster, separati da virgole.
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
     * Restituisce una rappresentazione testuale dettagliata degli identificatori dei dati
     * nel cluster, utilizzando un oggetto {@link Data} per ottenere informazioni aggiuntive.
     *
     * @param data L'oggetto {@link Data} utilizzato per ottenere informazioni aggiuntive sui dati.
     * @return Una stringa contenente le informazioni dettagliate dei dati nel cluster.
     */
    String toString(Data data) {
        StringBuilder str = new StringBuilder();
        for (Integer e : clusteredData)
            str.append("<" + "[").append(data.getExample(e)).append("]").append(">");

        return str.toString();

    }

    /**
     * Restituisce un iteratore sugli identificatori dei dati nel cluster.
     *
     * @return Un iteratore sugli identificatori dei dati nel cluster.
     */
    @Override
    public Iterator<Integer> iterator() {
        return clusteredData.iterator();
    }

}
