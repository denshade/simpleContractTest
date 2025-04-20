package info.thelaboflieven.contract.testing;

import com.github.tomakehurst.wiremock.WireMockServer;
import info.thelaboflieven.contract.example.client.SimpleHttpClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleCustomerConsumerContractTest {
    private static WireMockServer wireMockServer;

    @BeforeAll
    static void setup() {
        try {
            var contract = ContractReader.fromFile(new File("..\\exampleContract.json"));

            wireMockServer = new WireMockServer(options().dynamicPort());
            wireMockServer.start();

            // Configure mappings
            configureFor("localhost", wireMockServer.port());

            // Add mock responses
            for (var paragraph : contract.getContractParagraphList()) {
                wireMockServer.stubFor(get(urlEqualTo(paragraph.getExpectedUrl()))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withStatus(paragraph.getExpectedEndpointCode())
                                .withBody(paragraph.getExpectedOutput())));
            }
            } catch(Exception e) {
                throw new RuntimeException(e);
            }

    }

    @Test
    void checkCustomers() throws MalformedURLException {
        var client = new SimpleHttpClient(new URL("http://localhost:"+wireMockServer.port()));
        assertEquals("[{\"customerName\": \"name\",\"customerAddress\": \"AddressStreet 12, 47856 Aze\"}]", client.getCustomers());
    }

}
