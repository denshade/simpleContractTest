package info.thelaboflieven.contract.testing;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ExampleCustomerConsumerContractTest {

    @Test
    void checkConsumerCustomerTests() throws IOException {
        var contract = ContractReader.fromFile(new File("..\\exampleContract.json"));
        for (var paragraph : contract.getFilteredParagraphs("/v1/customer")) {
            assertRequestHandledCorrectly(paragraph.getExpectedUrl(), paragraph.getExpectedOutput(), paragraph.getExpectedInput(), paragraph.getExpectedHTTPVerb(), paragraph.getExpectedEndpointCode() );
        }
    }

    void assertRequestHandledCorrectly(String url, String output, String input, String verb, int httpStatusCode) {
        //when I call with verb, url with input
        // and the httpStatus is httpStatusCode
        // Check if this application can handle output
    }

}
