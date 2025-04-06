package info.thelaboflieven.contract.testing;


import java.util.List;

public class Contract {
    private List<ContractParagraph> contractParagraphList;

    public Contract(List<ContractParagraph> contractParagraphList) {
        this.contractParagraphList = contractParagraphList;
    }

    public List<ContractParagraph> getContractParagraphList() {
        return contractParagraphList;
    }
}
