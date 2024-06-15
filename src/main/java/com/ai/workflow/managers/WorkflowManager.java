package com.ai.workflow.managers;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WorkflowManager {

    @Autowired
    private ZeebeClient client;

    // Generic Start method, for our Workflows

    /**
     *  .startWorkflow('foo','1', { 'foo': 'bar', 'bar': 'baz'})
     *
     * @param workflowName
     * @param workflowVersion
     * @param variables
     */
    public void startWorkflow(String workflowName, String workflowVersion, Map<String,Object> variables) {
        log.info("Staring workflow: {}", workflowName);

        String bpmnProcessId = workflowName; //TODO: Load this mapping from DB

        variables.put("startTime", System.currentTimeMillis());

        client.newCreateInstanceCommand()
                .bpmnProcessId(bpmnProcessId)
                .latestVersion()
                .variables(variables)
                .send()
                .join();

    }

    public void cancelWorkflow(String workflowInstanceName) {
        log.info("Cancelling workflow: {}", workflowInstanceName);
        long processInstanceKey = 0L;
        //TODO: load from Database .. mapping workflowInstanceName to processInstanceKey

        client.newCancelInstanceCommand(processInstanceKey)
                .send()
                .join();
    }



}
