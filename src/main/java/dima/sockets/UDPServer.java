package dima.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[512];

        try (DatagramSocket socket = new DatagramSocket(7777);) {
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
            socket.receive(datagramPacket);
            System.out.println(new String(bytes));
        }
    }
}
