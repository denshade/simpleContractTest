package info.thelaboflieven.contract.example.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SimpleHttpClient {

    private final URL baseUrl;

    public SimpleHttpClient(URL baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getCustomers() {
        try {
            // Create a URL object from the string
            URL url = new URL(baseUrl+"/v1/customers");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code from the server
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the input stream if the response is OK (HTTP 200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder content = new StringBuilder();

                    // Read the response line by line
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }

                    // Output the response content
                    System.out.println("Response Content: ");
                    System.out.println(content.toString());
                    return content.toString();
                }
            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
