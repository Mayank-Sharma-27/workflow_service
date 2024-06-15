package com.ai.workflow.managers.conductor;

import com.ai.workflow.managers.WorkflowExecutionInterface;
import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;
import com.netflix.conductor.common.run.Workflow;
import io.orkes.conductor.client.WorkflowClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class ConductorWorkflowExecutionImpl implements WorkflowExecutionInterface {

    private final WorkflowClient workflowClient;

    @Override
    public Map<String,Object> startWorkflow(String workflowName) {
        StartWorkflowRequest request = new StartWorkflowRequest();
        request.setName("retrieve_food_data");
        String workflowId = workflowClient.startWorkflow(request);

        Workflow workflow;
        while (true) {
            workflow = workflowClient.getWorkflow(workflowId, true);
            if (workflow.getStatus().isTerminal()) {
                break;
            }
            try {
                Thread.sleep(1000); // Wait for 1 second before polling again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted", e);
            }
        }

        if (workflow.getStatus() == Workflow.WorkflowStatus.COMPLETED) {
            log.info("Workflow completed successfully with output: {}", workflow.getOutput());
            return workflow.getOutput();
        } else {
            log.error("Workflow failed with status: {}", workflow.getStatus());
            throw new RuntimeException("Workflow execution failed with status: " + workflow.getStatus());
        }
    }

}
