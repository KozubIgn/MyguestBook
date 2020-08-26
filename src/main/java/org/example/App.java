package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8002), 0);
        server.createContext("/guestbook", new GuestBookHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server has stared on port 8002");
    }
}