package com.ai.workflow.service;

import com.ai.workflow.dao.WorkFlows;

public interface WorkflowToApiService {

    WorkFlows getWorkflowFromApi(String api);

    WorkFlows save(WorkFlows workFlows);

}
