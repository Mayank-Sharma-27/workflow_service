package com.ai.workflow;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import io.orkes.conductor.client.ApiClient;
import io.orkes.conductor.client.TaskClient;
import io.orkes.conductor.client.WorkflowClient;
import io.orkes.conductor.client.http.OrkesTaskClient;
import io.orkes.conductor.client.http.OrkesWorkflowClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Deployment(resources = "classpath:sample.bpmn")
@SpringBootApplication()
public class WorkflowApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WorkflowApplication.class, args);
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        System.out.println("Active profiles: " + Arrays.toString(activeProfiles));
    }

    @Bean
    public ApiClient apiClient() {

        String conductorServer = "http://localhost:7070/api";


        // If authentication is disabled key and secret can be null
        return new ApiClient(Objects.requireNonNull(conductorServer), "", "");
    }

    @Bean
    public TaskClient getTaskClient(ApiClient apiClient) {
        return new OrkesTaskClient(apiClient);
    }

    @Bean
    public WorkflowClient getWorkflowClient(ApiClient apiClient) {
        return new OrkesWorkflowClient(apiClient);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
