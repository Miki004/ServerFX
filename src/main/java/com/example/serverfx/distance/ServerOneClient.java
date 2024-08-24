package com.example.serverfx.distance;

import com.example.serverfx.clustering.HierachicalClusterMiner;
import com.example.serverfx.clustering.InvalidDepthException;
import com.example.serverfx.data.Data;
import com.example.serverfx.data.NoDataException;

import java.io.*;
import java.net.Socket;

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

    @Override
    public void run() {
        try {
            Data data=null;
            while (true) {
                int scelta = (int) in.readObject();
                if (scelta == 0) {//potremmo ritornare qui anche quando si è verificata un eccezzione
                    int flag=0;
                    try {
                            data = new Data((String) in.readObject());
                    }catch (NoDataException e) {
                        out.writeObject("Nessun dato presente nella seguente tabella");
                        flag=1;
                    }
                    if(flag==0) {
                        out.writeObject("OK");
                    }
                } else if (scelta == 1) {
                    int k=(int) in.readObject();
                    HierachicalClusterMiner clustering;
                    try {
                        clustering= new HierachicalClusterMiner(k);
                        InvalidDepthException.VerificareDimensione(k, data);
                    }catch (InvalidDepthException e) {
                        clustering = new HierachicalClusterMiner(data.getNumberOfExamples());
                    }
                    int tipoOperazione =(int) in.readObject();
                    if(tipoOperazione==1) {
                        clustering.mine(data, new SingleLinkDistance());
                        out.writeObject("OK");
                        out.writeObject(clustering.toString(data));
                        try {
                            String nomeFile= (String) in.readObject();
                            clustering.salva(nomeFile);
                        }catch (IOException e){
                            out.writeObject("Errore durante il salvataggio !!");
                        }
                    }else if(tipoOperazione==2) {
                        clustering.mine(data, new AverageLinkDistance());
                        out.writeObject("OK");
                        out.writeObject(clustering.toString(data));
                        try {
                            String nomeFile= (String) in.readObject();
                            clustering.salva(nomeFile);
                        }catch (IOException e){
                            out.writeObject("Errore durante il salvataggio !!");
                        }
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
            throw new RuntimeException(e);
        }
    }
}
