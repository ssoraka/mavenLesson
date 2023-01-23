package dima.sockets.server;

public class ServerRunner {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(9000, 50);
        httpServer.run();
    }
}
