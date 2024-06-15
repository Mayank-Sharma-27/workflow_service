package com.ai.workflow.managers;

import com.ai.workflow.dao.WorkFlows;
import com.ai.workflow.dao.WorkFlowsRepository;
import com.ai.workflow.service.WorkflowToApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkflowExecutionFactory {

    private final WorkflowToApiService workflowToApiService;

    private final WorkflowExecutionInterface camundaWorkflowExecutionImpl;

    private final WorkflowExecutionInterface conductorWorkflowExecutionImpl;

    private final WorkFlowsRepository workFlowsRepository;

    public WorkflowExecutionInterface getWorkflowExecutor(String apiEndPoint) {
        workFlowsRepository.save(new WorkFlows("retrieve-food-data", "Conductor"));

        WorkFlows workflow = workflowToApiService.getWorkflowFromApi(apiEndPoint);

        if (workflow == null || workflow.getWorkflow() == null) {
            throw new IllegalArgumentException("Invalid workflow");
        }

        return switch (workflow.getWorkflow()) {
            case "Camunda" -> camundaWorkflowExecutionImpl;
            case "Conductor" -> conductorWorkflowExecutionImpl;
            default -> throw new IllegalArgumentException("Unknown workflow: " + workflow.getWorkflow());
        };
    }

}
