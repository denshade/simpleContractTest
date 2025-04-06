package info.thelaboflieven.contract.testing;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

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
        for (var paragraph : contract.getFilteredParagraphs("/v1/customer")) {
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

    ExampleReponse handleResponse(String url, String output, String input, String verb, String httpStatusCode, String state) {
        //handle input, with verb in your application
        return new ExampleReponse(output, httpStatusCode);
    }

}
