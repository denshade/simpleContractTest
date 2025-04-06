package info.thelaboflieven.contract.testing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class ContractReader {

    public static Contract fromFile(File file) throws JsonProcessingException, FileNotFoundException {
        var inputStream = new FileInputStream(file);
        var objectMapper = new ObjectMapper();
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
            objectMapper.readValue(resultStringBuilder.toString(), Contract.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
