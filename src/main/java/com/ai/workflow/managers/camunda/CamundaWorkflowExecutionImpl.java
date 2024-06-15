package com.ai.workflow.managers.camunda;

import com.ai.workflow.managers.WorkflowExecutionInterface;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class CamundaWorkflowExecutionImpl implements WorkflowExecutionInterface {

    private final ZeebeClient zeebeClient;

    @Override
    public Map<String, Object> startWorkflow(String workflowName) {
        ProcessInstanceEvent processInstanceEvent = zeebeClient.newCreateInstanceCommand().bpmnProcessId("retrieve-weather")
                .latestVersion()
                .send().join();
        return new TreeMap<>();
    }
}
