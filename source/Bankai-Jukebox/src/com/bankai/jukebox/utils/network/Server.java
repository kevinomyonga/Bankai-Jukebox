package com.bankai.jukebox.utils.network;

import com.bankai.jukebox.config.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    // Define the port number for the server to listen on
    public static final int PORT = 20203;
    // Define the number of threads in the thread pool
    private static final int THREAD_POOL_SIZE = 20;

    // Constructor for the Server class
    public Server() {
        try (// Create a ServerSocket to listen for incoming client connections on the specified port
             ServerSocket socket = new ServerSocket(PORT);
             // Create a thread pool with a fixed number of threads
             ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE)) {

            System.out.println(Constants.APP_NAME + " Server started. Listening on port " + PORT);

            // Continuously accept incoming client connections
            while (true) {
                // Indicate when the server accepts a new client connection
                System.out.println("Waiting for incoming client connections...");
                // Accept a client connection and submit it to the thread pool for handling
                threadPool.execute(new ClientHandler(socket.accept()));
            }
        } catch (IOException e) {
            // Handle any IO exceptions that occur during server operation
            e.printStackTrace();
        }
    }

    // Main method to start the server
    public static void main(String[] args) {
        // Create and start the server
        new Server();
    }
}
