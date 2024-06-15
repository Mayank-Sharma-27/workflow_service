package com.ai.workflow.managers.camunda;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Log4j2
@RequiredArgsConstructor
public class GetWeatherWorker {

    private final RestTemplate restTemplate;

    @JobWorker(type = "getWeather", timeout = 10000L)
    @SneakyThrows
    public void getWeather(JobClient client, ActivatedJob job) {
        Thread.sleep(1000);
        String url = "https://world.openfoodfacts.org/api/v0/product/737628064502.json";
        log.info("Fetching data from: " + url);
        String response = restTemplate.getForObject(url, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(response, Map.class);
            Map<String, Object> productMap = (Map<String, Object>) map.get("product");

            // Extract the needed data
            String ingredientsText = (String) productMap.get("ingredients_text");
            List ingredientsOriginalTags = (List) productMap.get("ingredients_original_tags");

            // Log the extracted data
            log.info("Ingredients Text: " + ingredientsText);
            log.info("Ingredients Original Tags: " + ingredientsOriginalTags);

            // Prepare the variables to complete the job
            Map<String, Object> variables = new HashMap<>();
            variables.put("ingredients_text", ingredientsText);
            variables.put("ingredients_original_tags", ingredientsOriginalTags);

            client.newCompleteCommand(job.getKey())
                    .variables(variables)
                    .send()
                    .join();

            log.info("Job completed successfully with variables: " + variables);

        } catch (Exception e) {
            log.info("Job failed due to  : {}", e.getMessage());
        }
    }

}