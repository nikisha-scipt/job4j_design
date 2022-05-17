package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes(StandardCharsets.UTF_8));
                    String str = reader.readLine();
                    if (str.contains("Bye")) {
                        serverSocket.close();
                    }
                    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                        System.out.println(line);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
