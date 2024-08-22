package com.example.serverfx;
import com.example.serverfx.distance.ServerOneClient;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainTest extends Thread{
    private String ip;
    @Override
    public void run() {
        try {
            initServer();
        }catch (IOException e ) {
            System.out.println("ERROR");
        }

    }

    public void initServer() throws IOException {
            final int PORT=8080;
            ServerSocket server=null;
            try {
                server = new ServerSocket(PORT);
                while (true) {
                    Socket socket=server.accept();
                    ip=socket.getInetAddress().getHostAddress();
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

    public String getIp() {
        return ip;
    }
}

