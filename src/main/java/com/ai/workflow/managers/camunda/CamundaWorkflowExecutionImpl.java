package com.ai.workflow.managers.camunda;

import com.ai.workflow.managers.WorkflowExecutionInterface;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CamundaWorkflowExecutionImpl implements WorkflowExecutionInterface {

    private final ZeebeClient zeebeClient;

    @Override
    public Map<String, Object> startWorkflow(String workflowName) {
        ProcessInstanceResult processInstanceResult = zeebeClient.newCreateInstanceCommand().bpmnProcessId("retrieve-weather")
                .latestVersion()
                .withResult()
                .send().join();
        // Retrieve and return the workflow output variables
        return processInstanceResult.getVariablesAsMap();
    }
}
