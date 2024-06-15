package com.ai.workflow.controllers;

import com.ai.workflow.service.WorkflowToApiService;
import io.camunda.zeebe.client.api.command.CancelProcessInstanceCommandStep1;
import io.camunda.zeebe.client.api.response.ActivateJobsResponse;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workflows")
@RequiredArgsConstructor
public class WorkflowController {

    @Autowired
    private final ZeebeClient zeebeClient;

    @Autowired
    private final WorkflowToApiService workflowToApiService;

    @GetMapping
    public ResponseEntity<List<String>> getAllWorkflows() {
        // This is a dummy list for example purposes, replace with actual method to fetch workflows
        Map<String, Object> variables = new HashMap<>();
        workflowToApiService.getWorkflowFromApi("test");


//        ProcessInstanceEvent t = zeebeClient.newCreateInstanceCommand().bpmnProcessId("retrieve-weather")
//                .latestVersion()
//                .send().join();
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getWorkflow(@PathVariable String id) {
        // This is a dummy value for example purposes, replace with actual method to fetch workflow
        String workflow = "workflow";
        return new ResponseEntity<>(workflow, HttpStatus.OK);
    }

    @GetMapping("/get-current-weather")
    public ResponseEntity<String> getcurrentWeatger(@PathVariable String city) {
        // This is a dummy value for example purposes, replace with actual method to fetch workflow
        String workflow = "workflow";
        return new ResponseEntity<>(workflow, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createWorkflow(@RequestBody String workflow) {
        // This is a dummy value for example purposes, replace with actual method to create and save workflow
        return new ResponseEntity<>("Workflow created.", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorkflow(@PathVariable String id, @RequestBody String workflow) {
        // This is a dummy value for example purposes, replace with actual method to update workflow
        return new ResponseEntity<>("Workflow updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkflow(@PathVariable String id) {
        // This is a dummy value for example purposes, replace with actual method to delete workflow
        return new ResponseEntity<>("Workflow deleted.", HttpStatus.OK);
    }
}
