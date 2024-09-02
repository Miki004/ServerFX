package com.example.serverfx.Server;
import com.example.serverfx.Controllers.StartServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe principale per avviare il server.
 * Estende Thread per eseguire il server in un thread separato.
 */
public class MainTest extends Thread{
    private StartServerController serverController;

    @Override
    public void run() {
        try {
            initServer();
        }catch (IOException e ) {
            System.out.println("ERROR");
        }

    }

    /**
     * Inizializza e avvia il server.
     * Ascolta sulla porta 8080 per le connessioni in entrata.
     * Per ogni connessione accettata, crea una nuova istanza di ServerOneClient.
     *
     * @throws IOException Se si verifica un errore durante l'inizializzazione del server.
     */
    public void initServer() throws IOException {
            final int PORT=8080;
            ServerSocket server=null;
            try {
                server = new ServerSocket(PORT);
                while (true) {
                    Socket socket=server.accept();
                    System.out.println(socket);
                    serverController.writeOnArea(socket.getInetAddress().getHostAddress());
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
     * Imposta il controller per comunicare con l'interfaccia utente.
     *
     * @param startServerController Il controller dell'interfaccia utente.
     */
    public void setController(StartServerController startServerController) {
        serverController=startServerController;
    }
}

