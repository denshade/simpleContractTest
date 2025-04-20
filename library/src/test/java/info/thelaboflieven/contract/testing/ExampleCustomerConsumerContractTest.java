package info.thelaboflieven.contract.testing;

import info.thelaboflieven.contract.example.client.CustomerServiceClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleCustomerConsumerContractTest {
    private static int wireMockPort;
    @BeforeAll
    static void setup() {
        wireMockPort = ContractWireMock.mockContracts();
    }

    @Test
    void checkCustomers() throws MalformedURLException {
        var client = new CustomerServiceClient(new URL("http://localhost:"+wireMockPort));
        assertEquals("[{\"customerName\": \"name\",\"customerAddress\": \"AddressStreet 12, 47856 Aze\"}]", client.getCustomers());
    }

}
