package com.ai.workflow.controllers;

import com.ai.workflow.managers.WorkflowExecutionFactory;
import com.ai.workflow.managers.WorkflowExecutionInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FoodControllerController {

    private final WorkflowExecutionFactory workflowExecutionFactory;

    @GetMapping("/retrieve-food-data")
    public ResponseEntity<List<Map<String, Object>>> getFoodData() {
        WorkflowExecutionInterface workflowExecutionInterface = workflowExecutionFactory.getWorkflowExecutor("retrieve-food-data");


        Map<String, Object> workflowResponse = workflowExecutionInterface.startWorkflow("retrieve-food-data");
        List<Map<String, Object>> response = new ArrayList<>();
        response.add(workflowResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
