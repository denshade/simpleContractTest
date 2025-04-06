package info.thelaboflieven.contract.testing;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ContractReaderTest {

    @Test
    void readContract() throws FileNotFoundException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        var contract = ContractReader.fromFile(new File(classloader.getResource("contractv1.json").getFile()));
        var contractParagraph = contract.getContractParagraphList().get(0);
        assertEquals(contractParagraph.getExpectedHTTPVerb(), "GET");
        assertEquals(contractParagraph.getExpectedInput(), "");
        assertEquals(contractParagraph.getExpectedOutput(), "CustomerData");
        assertEquals(contractParagraph.getExpectedState(), "optionalStatename");
        assertEquals(contractParagraph.getExpectedEndpointCode(), "200");
    }

}