package dima.sockets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private int port;
    private ExecutorService pool;
    private boolean stopped;

    public HttpServer(int port, int poolSize) {
        this.port = port;
        pool = Executors.newFixedThreadPool(poolSize);
    }

    public void run() {
        try {
            var server = new ServerSocket(port);
            while (!stopped) {
                Socket socket = server.accept();
                pool.submit(() -> processSocket(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processSocket(Socket socket) {
        try (socket;
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());) {

            System.out.println("request: " + new String(inputStream.readNBytes(100)));

            byte[] body = Files.readAllBytes(Path.of("/Users/ssoraka/IdeaProjects/servletTest/src/main/resources/example.html"));
            var headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length)
                    .getBytes(StandardCharsets.UTF_8);
            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            outputStream.write(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
