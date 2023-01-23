package dima.sockets;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPSocket {
    public static void main(String[] args) throws IOException {
        InetAddress address = Inet4Address.getByName("localhost");
        try (DatagramSocket socket = new DatagramSocket();
        ) {
            byte[] bytes = "hello".getBytes(StandardCharsets.UTF_8);
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, address, 7777);
            socket.send(datagramPacket);
        }
    }
}
