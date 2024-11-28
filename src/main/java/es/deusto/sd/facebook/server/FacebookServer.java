package es.deusto.sd.facebook.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FacebookServer {
    private static final int SERVER_PORT = 8081;
    
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Facebook server started, waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from: " + clientSocket.getInetAddress());
                new FacebookService(clientSocket); // New thread for each client connection
            }
        } catch (IOException e) {
            System.err.println("Error starting Facebook server: " + e.getMessage());
        }
    }
}

class FacebookService extends Thread {

    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;

    public FacebookService(Socket socket) {
        this.clientSocket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.err.println("Error setting up service: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            // Read the email and password sent by Strava
            String credentials = in.readUTF();
            System.out.println("Received credentials: " + credentials);  // Log the credentials

            String[] parts = credentials.split("#");
            String email = parts[0];
            String password = parts[1];
            
            // Perform authentication logic here
            boolean isAuthenticated = authenticateWithFacebook(email, password);
            
            // Send the response back to Strava
            if (isAuthenticated) {
                out.writeUTF("OK#User authenticated");
            } else {
                out.writeUTF("ERR#Invalid credentials");
            }
        } catch (IOException e) {
            System.err.println("Error processing authentication: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    private boolean authenticateWithFacebook(String email, String password) {
        return true;
    }
}