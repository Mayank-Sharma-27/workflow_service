package com.ai.workflow;

import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.util.logging.Slf4j;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerResponseTranslationUtils {

    @SneakyThrows
    public static Map<String, Object> getWorkerResponse(String response){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(response, Map.class);
        Map<String, Object> productMap = (Map<String, Object>) map.get("product");

        // Extract the needed data
        String ingredientsText = (String) productMap.get("ingredients_text");
        List ingredientsOriginalTags = (List) productMap.get("ingredients_original_tags");


        // Prepare the variables to complete the job
        Map<String, Object> variables = new HashMap<>();
        variables.put("ingredients_text", ingredientsText);
        variables.put("ingredients_original_tags", ingredientsOriginalTags);
        return variables;
    }

}
