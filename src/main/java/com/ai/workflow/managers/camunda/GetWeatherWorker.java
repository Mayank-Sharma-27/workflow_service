package com.ai.workflow.managers.camunda;

import com.ai.workflow.WorkerResponseTranslationUtils;
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
            Map<String, Object> variables = WorkerResponseTranslationUtils.getWorkerResponse(response);
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