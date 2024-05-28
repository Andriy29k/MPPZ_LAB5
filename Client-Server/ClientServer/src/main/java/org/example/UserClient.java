package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port);
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true);
             InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            System.out.println("Connected to the server");

            while ((userInput = consoleReader.readLine()) != null) {
                writer.println(userInput);
                String response;
                while ((response = reader.readLine()) != null && !response.isEmpty()) {
                    System.out.println(response);
                }

                if ("bye".equalsIgnoreCase(userInput)) {
                    break;
                }
            }

            socket.close();
            System.out.println("Disconnected from the server");

        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
