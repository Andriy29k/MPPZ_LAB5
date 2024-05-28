package org.example;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class UserServerThread extends Thread {
    private Socket socket;

    public UserServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            String command;
            while ((command = reader.readLine()) != null) {
                if (command.startsWith("ADD")) {
                    String[] parts = command.split(" ");
                    if (parts.length == 3) {
                        String id = parts[1];
                        String name = parts[2];
                        UserServer.addUser(new User(id, name));
                        writer.println("User added: " + id + " " + name);
                    } else {
                        writer.println("Invalid ADD command");
                    }
                } else if (command.startsWith("REMOVE")) {
                    String[] parts = command.split(" ");
                    if (parts.length == 2) {
                        String id = parts[1];
                        UserServer.removeUser(id);
                        writer.println("User removed: " + id);
                    } else {
                        writer.println("Invalid REMOVE command");
                    }
                } else if (command.equals("LIST")) {
                    List<User> users = UserServer.getUsers();
                    for (User user : users) {
                        writer.println(user);
                    }
                } else {
                    writer.println("Unknown command");
                }

                if ("bye".equalsIgnoreCase(command)) {
                    break;
                }
            }

            socket.close();
            System.out.println("Client disconnected");
        } catch (IOException e) {
            System.out.println("UserServerThread exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
