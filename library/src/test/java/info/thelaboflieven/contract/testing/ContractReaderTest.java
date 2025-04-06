package info.thelaboflieven.contract.testing;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ContractReaderTest {

    @Test
    void readContract() throws FileNotFoundException, JsonProcessingException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        ContractReader.fromFile(new File(classloader.getResource("contractv1.json").getFile()));
    }

}