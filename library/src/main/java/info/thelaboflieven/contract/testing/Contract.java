package info.thelaboflieven.contract.testing;


import java.util.List;
import java.util.stream.Collectors;

public class Contract {
    private List<ContractParagraph> contractParagraphList;

    public Contract(List<ContractParagraph> contractParagraphList) {
        this.contractParagraphList = contractParagraphList;
    }

    public List<ContractParagraph> getContractParagraphList() {
        return contractParagraphList;
    }

    public List<ContractParagraph> getFilteredParagraphs(String url) {
        return contractParagraphList.stream().filter(e -> e.getExpectedUrl().equals(url)).collect(Collectors.toList());
    }
}
