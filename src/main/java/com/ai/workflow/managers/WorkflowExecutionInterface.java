package com.ai.workflow.managers;

import java.util.Map;

public interface WorkflowExecutionInterface {

    Map<String, Object> startWorkflow(String workflowName);

}
