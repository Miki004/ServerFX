package com.example.serverfx.Server;

import com.example.serverfx.clustering.HierachicalClusterMiner;
import com.example.serverfx.clustering.InvalidDepthException;
import com.example.serverfx.data.Data;
import com.example.serverfx.data.NoDataException;
import com.example.serverfx.database.DatabaseConnectionException;
import com.example.serverfx.database.DbAccess;
import com.example.serverfx.distance.AverageLinkDistance;
import com.example.serverfx.distance.InvalidSizeException;
import com.example.serverfx.distance.SingleLinkDistance;

import java.io.*;
import java.net.Socket;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestisce la comunicazione tra un server e un client attraverso una connessione socket.
 * Questa classe gestisce le richieste del client, inclusa la connessione al database,
 * l'esecuzione di operazioni di clustering, e il salvataggio e il caricamento dei risultati.
 */
public class ServerOneClient extends Thread{

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String stringconnection;

    /**
     * Costruttore che inizializza il socket e i flussi di input e output.
     *
     * @param socket Il socket tramite cui il server comunica con il client.
     * @throws IOException Se si verifica un errore durante la creazione degli stream.
     */
   public ServerOneClient(Socket socket) throws IOException {
        this.socket=socket;
        out=new ObjectOutputStream(this.socket.getOutputStream());
        in =new ObjectInputStream(this.socket.getInputStream());
        this.start();//viene creato un thread separato,alla fine di ciò verrà invocato il metodo run
    }

    /**
     * Recupera l'elenco delle tabelle presenti nel database.
     *
     * @return Una lista di nomi di tabelle.
     */
    public List<String> getTables() {
        List<String> listaTabelle= new ArrayList<>();
        try {
            DbAccess db = new DbAccess(stringconnection);
            DatabaseMetaData metaData =db.getConnection().getMetaData();
            ResultSet tables= metaData.getTables(null,null, "%", new String[] {"TABLE"} );
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                listaTabelle.add(tableName);
            }
            tables.close();
            db.closeConnection();
            return listaTabelle;
        } catch (SQLException | DatabaseConnectionException e) {
            return listaTabelle;
        }
    }
    /**
     * Imposta la connessione al database leggendo le informazioni inviate dal client.
     *
     * @throws IOException Se si verifica un errore durante la lettura dei dati dal client.
     * @throws DatabaseConnectionException Se si verifica un errore durante la connessione al database.
     * @throws ClassNotFoundException Se si verifica un errore nella lettura dei dati del database.
     */
    public void setDataBase() throws IOException, DatabaseConnectionException, ClassNotFoundException {
           String Server= (String) in.readObject();
           String Database = (String) in.readObject();
           Integer Port = (Integer) in.readObject();
           String User_ID= (String) in.readObject();
           String password= (String) in.readObject();
           stringconnection= Server + ":" + Port + "/" + Database
                   + "?user=" + User_ID + "&password=" + password + "&serverTimezone=UTC";
    }

    /**
     * Gestisce le comunicazioni e le richieste del client in un thread separato.
     */
    @Override
    public void run() {
       boolean connectionAlive=true;
       DbAccess db=null;
       HierachicalClusterMiner tempcluster = null;
        try {
            setDataBase();
            db = new DbAccess(stringconnection);
            db.closeConnection();
            out.writeObject("OK");
        } catch (IOException | DatabaseConnectionException | ClassNotFoundException | SQLException e) {
            try {
                out.writeObject("NO");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            connectionAlive=false;
        }

        if (connectionAlive) {
            try {
                out.writeObject(getTables());
            } catch (IOException e) {
                System.out.println("Errore durante l'invio delle tabelle");
                connectionAlive = false;
            }
        }

            Data data=null;
            boolean saveble=false;
            while (connectionAlive) {
                try {

                    int scelta = (Integer) in.readObject();
                    if (scelta == 0) {
                        try {
                            data = new Data(db, (String) in.readObject());
                            out.writeObject("OK");
                        } catch (NoDataException e) {
                            out.writeObject("Nessun dato presente nella tabella ");
                        }
                    } else if (scelta == 1) {
                        HierachicalClusterMiner clustering;
                        try {
                            int k = (int) in.readObject();
                            InvalidDepthException.VerificareDimensione(k, data);
                            clustering = new HierachicalClusterMiner(k);
                        } catch (InvalidDepthException e) {
                            clustering = new HierachicalClusterMiner(data.getNumberOfExamples());
                        }
                        int tipoOperazione = (int) in.readObject();
                        if (tipoOperazione == 1) {
                            clustering.mine(data, new SingleLinkDistance());
                            tempcluster = clustering;
                            saveble = true;
                            out.writeObject("OK");
                            out.writeObject(clustering.toString(data));

                        } else if (tipoOperazione == 2) {
                            clustering.mine(data, new AverageLinkDistance());
                            tempcluster = clustering;
                            saveble = true;
                            out.writeObject("OK");
                            out.writeObject(clustering.toString(data));

                        }
                    } else if (saveble && scelta == 3) {
                        try {
                            String nomeFile = (String) in.readObject();
                            tempcluster.salva(nomeFile);
                        } catch (IOException e) {
                            out.writeObject("Errore durante il salvataggio !!");
                        }
                    } else if (scelta == 2) {
                        String nomeFile = (String) in.readObject();
                        HierachicalClusterMiner clustering = null;
                        int flag = 0;
                        try {
                            clustering = HierachicalClusterMiner.loaHierachicalClusterMiner(nomeFile);
                        } catch (FileNotFoundException e) {
                            out.writeObject("il file specificato non è stato trovato");
                            flag = 1;
                        }
                        if (flag == 0) {
                            out.writeObject("OK");
                            try {
                                out.writeObject(clustering.toString(data));
                            } catch (IndexOutOfBoundsException e) {
                                out.writeObject("il file specificato potrebbe esserre stato creato con un altra tabella");
                            }

                        }
                    }
                }catch (ClassNotFoundException | IOException | InvalidSizeException e) {
                    connectionAlive=false;
                }
            }
        }
}
