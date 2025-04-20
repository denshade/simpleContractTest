package info.thelaboflieven.contract.testing;

import info.thelaboflieven.contract.example.server.SimpleHttpServer;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleCustomerProviderContractTest {

    private class ExampleReponse {
        private final String output;
        private final String httpStatusCode;

        ExampleReponse(String output, String httpStatusCode) {
            this.output = output;
            this.httpStatusCode = httpStatusCode;
        }
    }

    @Test
    void checkConsumerCustomerTests() throws IOException {
        var contract = ContractReader.fromFile(new File("..\\exampleContract.json"));
        for (var paragraph : contract.getFilteredParagraphs("/v1/customers")) {
            assertRequestHandledCorrectly(paragraph.getExpectedUrl(), paragraph.getExpectedOutput(), paragraph.getExpectedInput(), paragraph.getExpectedHTTPVerb(), paragraph.getExpectedEndpointCode(), paragraph.getExpectedState());
        }
    }

    void assertRequestHandledCorrectly(String url, String output, String input, String verb, String httpStatusCode, String state) {
        if ("oneCustomerAvailable".equals(state)) {
            assertCustomerRequest(url, output, input, verb, httpStatusCode, state);
        }
    }

    void assertCustomerRequest(String url, String output, String input, String verb, String httpStatusCode, String state) {
        // Setup state
        var expectedRespectedResponseBody = output;
        var response = handleResponse(url, output, input, verb, httpStatusCode, state);
        assertEquals(expectedRespectedResponseBody, response.output);
        assertEquals(httpStatusCode, response.httpStatusCode);
    }

    ExampleReponse handleResponse(String urlSuffix, String output, String input, String verb, String httpStatusCode, String state) {
        try{
            ExecutorService executor = Executors.newCachedThreadPool();
            executor.submit(SimpleHttpServer::startHttpThread);
            URL url = new URL("http://localhost:8000" + urlSuffix);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(verb);

            int responseCode = connection.getResponseCode();
            assertEquals(200, responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = getContent(in);
            in.close();
            connection.disconnect();
            executor.shutdown();
            return new ExampleReponse(content.toString(), httpStatusCode);
        } catch (Exception exception) {
            return null;
        }
    }

    private StringBuilder getContent(BufferedReader in) throws IOException {
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        return content;
    }

}
