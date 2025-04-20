package info.thelaboflieven.contract.example.server;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static void main(String[] args) {
        startHttpThread(8000);
    }

    public static void startHttpThread(int port) {
        try {
            // Create an HTTP server listening on port 8000
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            // Define a context that will handle requests to the root path ("/")
            server.createContext("/v1/customers", new MyHandler());

            // Start the server
            server.start();
            System.out.println("Server is listening on port 8000...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "[{\"customerName\": \"name\",\"customerAddress\": \"AddressStreet 12, 47856 Aze\"}]";

            // Set the response headers and status code
            exchange.sendResponseHeaders(200, response.length());

            // Write the response body
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
