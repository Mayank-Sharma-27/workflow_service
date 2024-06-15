package com.ai.workflow.managers;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaskManager {

//    @Autowired
//    private ZeebeClient client;
//
//
//    public void completeTask(String taskId) {
//        log.info("Completing task: {}", taskId);
//        client.newCompleteCommand(Long.parseLong(taskId)).send().join();
//
//    }
//
//    public void assignTask(String taskId, String assignee) {
//        log.info("Assigning task: {} to {}", taskId, assignee);
//        client.newSetVariablesCommand(Long.parseLong(taskId)).variables("{\"assignee\": \"" + assignee + "\"}").send().join();
//    }
//
//    public void cancelTask(String taskId) {
//        log.info("Cancelling task: {}", taskId);
//        client.newCancelInstanceCommand(Long.parseLong(taskId)).send().join();
//    }
//
//    public void updateTask(String taskId) {
//        log.info("Updating task: {}", taskId);
//
//    }
//
//    public void deleteTask(String taskId) {
//        log.info("Deleting task: {}", taskId);
//    }
//
//    public void getTask(String taskId) {
//        log.info("Getting task: {}", taskId);
//        client.newActivateJobsCommand().jobType("task").maxJobsToActivate(1).workerName("worker").send().join();
//    }
//
//    public void getTasks() {
//        log.info("Getting tasks");
//
//    }
//    public void getTaskHistory(String taskId) {
//        log.info("Getting task history: {}", taskId);
//    }
//

}
