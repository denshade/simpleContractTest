package info.thelaboflieven.contract.testing;

import com.github.tomakehurst.wiremock.WireMockServer;

import java.io.File;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ContractWireMock {

    private static WireMockServer wireMockServer;

    public static int mockContracts() {
        try{
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
            return wireMockServer.port();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
