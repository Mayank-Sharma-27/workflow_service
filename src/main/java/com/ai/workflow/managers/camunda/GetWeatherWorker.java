package com.ai.workflow.managers.camunda;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Log4j2
public class GetWeatherWorker {

    @Autowired
    private RestTemplate restTemplate;

    @JobWorker
    public void getWeather() {
        String url = "https://world.openfoodfacts.org/api/v0/product/737628064502.json";
        String response = restTemplate.getForObject(url, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(response, Map.class);

            System.out.println("Ingredients Text: " + map.get("ingredients_text"));
            System.out.println("Ingredients Original Tags: " + map.get("ingredients_original_tags"));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

}