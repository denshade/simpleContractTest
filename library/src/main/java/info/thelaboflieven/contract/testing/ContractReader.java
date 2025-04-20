package info.thelaboflieven.contract.testing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ContractReader {

    public static Contract fromFile(File file) throws FileNotFoundException {
        var inputStream = new FileInputStream(file);
        var objectMapper = new ObjectMapper();
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
            TypeReference<List<HashMap<String,String>>> typeRef
                    = new TypeReference<>() {
            };
            var list = objectMapper.readValue(resultStringBuilder.toString(), typeRef);
            var paragraphs = list.stream().map(e -> new ContractParagraph(
                    e.get("expectedInput"),
                    e.get("expectedOutput"),
                    e.get("expectedEndpointUrlSuffix"),
                    e.get("expectedEndpointVerb"),
                    e.get("expectedState"),
                    Integer.parseInt(e.get("expectedEndpointCode"))
                    )).collect(Collectors.toList());
            return new Contract(paragraphs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
