package dima.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerSocket {
    public static void main(String[] args) throws IOException {
        try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(7777);
            Socket socket = serverSocket.accept();
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            while (true) {
                String s = inputStream.readUTF();
                System.out.println(s);
                outputStream.writeUTF("server: " + s);
                if (s.equals("stop")) break;
            }
        }

    }
}
