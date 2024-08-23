package com.example.serverfx;
import com.example.serverfx.distance.ServerOneClient;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe che rappresenta un server che gestisce più client in parallelo.
 * Esegue un thread separato per ogni connessione client.
 */
public class MainTest extends Thread{
    private StartServerController serverController;

    /**
     * Avvia il server in un thread separato.
     * Gestisce le connessioni dei client e crea istanze di {@link ServerOneClient} per ciascuna connessione.
     */
    @Override
    public void run() {
        try {
            initServer();
        }catch (IOException e ) {
            System.out.println("ERROR");
        }

    }

    /**
     * Inizializza il server e accetta le connessioni dai client.
     *
     * @throws IOException se si verifica un errore durante l'inizializzazione del server.
     */
    public void initServer() throws IOException {
            final int PORT=8080;
            ServerSocket server=null;
            try {
                server = new ServerSocket(PORT);
                while (true) {
                    Socket socket=server.accept();
                    serverController.writeOnArea(socket.getInetAddress().getHostAddress()+"\n");
                    try {
                        new ServerOneClient(socket);
                    }catch (IOException e) {
                        socket.close();
                    }
                }
            }finally {
                server.close();
            }
        }

    /**
     * Metodo principale per avviare il server.
     *
     * @param args argomenti della riga di comando.
     */
    public static void main(String[] args) throws IOException {

        final int PORT=8080;
        ServerSocket server=null;
        try {
            server = new ServerSocket(PORT);
            while (true) {
                Socket socket=server.accept();
                try {
                    new ServerOneClient(socket);
                }catch (IOException e) {
                    socket.close();
                }
            }
        }finally {
            server.close();
        }
    }

    /**
     * Imposta il controller per l'interfaccia utente.
     *
     * @param startServerController il controller dell'interfaccia utente.
     */
    public void setController(StartServerController startServerController) {
        serverController=startServerController;
    }
}

