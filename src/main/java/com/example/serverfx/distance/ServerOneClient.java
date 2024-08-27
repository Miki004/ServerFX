package com.example.serverfx.distance;

import com.example.serverfx.clustering.HierachicalClusterMiner;
import com.example.serverfx.clustering.InvalidDepthException;
import com.example.serverfx.data.Data;
import com.example.serverfx.data.NoDataException;
import com.example.serverfx.database.DatabaseConnectionException;
import com.example.serverfx.database.DbAccess;

import java.io.*;
import java.net.Socket;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerOneClient extends Thread{

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

   public ServerOneClient(Socket socket) throws IOException {
        this.socket=socket;
        out=new ObjectOutputStream(this.socket.getOutputStream());
        in =new ObjectInputStream(this.socket.getInputStream());
        this.start();//viene creato un thread separato,alla fine di ciò verrà invocato il metodo run
    }

    public List<String> getTables() {
        List<String> listaTabelle= new ArrayList<>();
        try {
            DbAccess db = new DbAccess();
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

    @Override
    public void run() {
       HierachicalClusterMiner tempcluster = null;
        try {
            List<String> list= getTables();
            out.writeObject(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Data data=null;
            boolean saveble=false;
            while (true) {
                int scelta = (Integer) in.readObject();
                if(scelta==0) {
                    try {
                        data = new Data((String) in.readObject());
                        out.writeObject("OK");
                    } catch (NoDataException e) {
                        out.writeObject("Nessun dato presente nella tabella ");
                    }
                } else if (scelta == 1) {
                    HierachicalClusterMiner clustering;
                    try {
                        int k=(int) in.readObject();
                        clustering= new HierachicalClusterMiner(k);
                        InvalidDepthException.VerificareDimensione(k, data);
                    }catch (InvalidDepthException e) {
                        clustering = new HierachicalClusterMiner(data.getNumberOfExamples());
                    }
                    int tipoOperazione =(int) in.readObject();
                    if(tipoOperazione==1) {
                        clustering.mine(data, new SingleLinkDistance());
                        tempcluster=clustering;
                        saveble=true;
                        out.writeObject("OK");
                        out.writeObject(clustering.toString(data));

                    }else if(tipoOperazione==2) {
                        clustering.mine(data, new AverageLinkDistance());
                        tempcluster=clustering;
                        saveble=true;
                        out.writeObject("OK");
                        out.writeObject(clustering.toString(data));

                    }
                } else if (saveble && scelta==3) {
                    try {
                        String nomeFile= (String) in.readObject();
                        tempcluster.salva(nomeFile);
                    }catch (IOException e){
                        out.writeObject("Errore durante il salvataggio !!");
                    }
                } else if (scelta == 2) {
                    String nomeFile= (String) in.readObject();
                    HierachicalClusterMiner clustering=null;
                    int flag=0;
                    try {
                        clustering = HierachicalClusterMiner.loaHierachicalClusterMiner(nomeFile);
                    }catch (FileNotFoundException e) {
                        out.writeObject("il file specificato non è stato trovato");
                        flag=1;
                    }
                    if(flag==0) {
                        out.writeObject("OK");
                        out.writeObject(clustering.toString(data));
                    }
                }
            }
        }catch(ClassNotFoundException | IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket non chiusa");
            }
        } catch (InvalidSizeException e) {
            try {
                out.writeObject(e.getMessage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
