package com.ai.workflow.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("workflows")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkFlows {

    @Id
    private String api;
    private String workflow;

}