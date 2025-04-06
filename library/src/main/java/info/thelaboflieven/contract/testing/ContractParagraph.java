package info.thelaboflieven.contract.testing;

public class ContractParagraph {
    private final String expectedInput;
    private final String expectedOutput;
    private final String expectedUrl;
    private final String expectedHTTPVerb;
    private final String expectedState;
    private final String expectedEndpointCode;

    public ContractParagraph(String expectedInput, String expectedOutput, String expectedUrl, String expectedHTTPVerb, String expectedState, String expectedEndpointCode) {
        this.expectedInput = expectedInput;
        this.expectedOutput = expectedOutput;
        this.expectedUrl = expectedUrl;
        this.expectedHTTPVerb = expectedHTTPVerb;
        this.expectedState = expectedState;
        this.expectedEndpointCode = expectedEndpointCode;
    }


    public String getExpectedInput() {
        return expectedInput;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public String getExpectedUrl() {
        return expectedUrl;
    }

    public String getExpectedHTTPVerb() {
        return expectedHTTPVerb;
    }

    public String getExpectedState() {
        return expectedState;
    }


    public String getExpectedEndpointCode() {
        return expectedEndpointCode;
    }
}
