package com.ai.workflow.service;

import com.ai.workflow.dao.WorkFlows;
import com.ai.workflow.dao.WorkFlowsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkflowToApiServiceImpl implements WorkflowToApiService {

    private final WorkFlowsRepository workFlowsRepository;
    @Override
    public WorkFlows getWorkflowFromApi(String api) {
        return workFlowsRepository.findItemByApi(api);
    }

    @Override
    public WorkFlows save(WorkFlows workFlows) {
        return workFlowsRepository.save(workFlows);
    }
}
