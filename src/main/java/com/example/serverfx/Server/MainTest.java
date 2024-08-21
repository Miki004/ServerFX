package com.example.serverfx.Server;

import com.example.serverfx.Server.distance.*;

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
}

