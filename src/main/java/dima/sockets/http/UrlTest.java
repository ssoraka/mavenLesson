package dima.sockets.http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class UrlTest {
    public static void main(String[] args) throws IOException {

        get();
//        post();
        file();

    }

    public static void get() throws IOException {
        URL url = new URL("https://google.com");

        URLConnection urlConnection = url.openConnection();
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();

        System.out.println(new DataInputStream(urlConnection.getInputStream()).readUTF());
    }

    public static void post() throws IOException {
        URL url = new URL("https://google.com");

        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoOutput(true);
        try(DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());) {
            outputStream.writeUTF("{}");
        }

        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();

        System.out.println(new DataInputStream(urlConnection.getInputStream()).readUTF());
    }

    public static void file() throws IOException {
        URL url = new URL("file:///Users/ssoraka/IdeaProjects/servletTest/src/main/java/dima/sockets/http/UrlTest.java");

        URLConnection urlConnection = url.openConnection();
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();

        System.out.println(new String(new DataInputStream(urlConnection.getInputStream()).readAllBytes()));
    }
}
