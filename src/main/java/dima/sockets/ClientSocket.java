package dima.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    public static void main(String[] args) throws IOException {
        InetAddress address = Inet4Address.getByName("localhost");
        try (
                Socket socket = new Socket(address, 7777);
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);
        ) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                outputStream.writeUTF("client: " + s);
                System.out.println(inputStream.readUTF());
            }
        }
    }
}
