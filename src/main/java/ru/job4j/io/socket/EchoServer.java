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
                    String any = "";
                    while (reader.ready()) {
                        String line = reader.readLine();
                        if (line.contains("/?msg=Hello")) {
                            any = "Hello, dear friend";
                            break;
                        } else if (line.contains("/?msg=Exit")) {
                            any = "Exit";
                            serverSocket.close();
                        } else {
                            any = "What";
                        }
                    }
                    if (serverSocket.isClosed()) {
                        out.write("HTTP/1.1 100 Server is closed \r\n\r\n".getBytes(StandardCharsets.UTF_8));
                    } else {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes(StandardCharsets.UTF_8));
                        out.write(any.getBytes(StandardCharsets.UTF_8));
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
