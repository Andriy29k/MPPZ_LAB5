package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class UserServer {
    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new UserServerThread(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized void addUser(User user) {
        users.add(user);
    }

    public static synchronized void removeUser(String id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public static synchronized List<User> getUsers() {
        return new ArrayList<>(users);
    }
}

