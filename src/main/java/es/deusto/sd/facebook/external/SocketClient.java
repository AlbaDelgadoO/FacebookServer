package es.deusto.sd.facebook.external;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    private String stravaServerIP;
    private int stravaServerPort;

    public SocketClient(String serverIP, int serverPort) {
        this.stravaServerIP = serverIP;
        this.stravaServerPort = serverPort;
    }

    public boolean validateWithStravaServer(String email, String password) {
        try (Socket socket = new Socket(stravaServerIP, stravaServerPort);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            // Send email and password to StravaServer
            out.writeUTF(email + "#" + password);
            System.out.println(" - Sending credentials to StravaServer: " + email);

            // Receive validation result
            String response = in.readUTF();
            System.out.println(" - Response from StravaServer: " + response);

            return "OK".equals(response);
        } catch (IOException e) {
            System.err.println("# StravaSocketClient: Error: " + e.getMessage());
            return false;
        }
    }
}
