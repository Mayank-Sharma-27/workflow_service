package com.ai.workflow.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkFlowsRepository extends MongoRepository<WorkFlows, String> {

    @Query("{api:'?0'}")
    WorkFlows findItemByApi(String api);

}
