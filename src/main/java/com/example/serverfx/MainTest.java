package com.example.serverfx;

import com.example.serverfx.distance.ServerOneClient;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainTest {
        public void initServer() throws IOException {
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


}

