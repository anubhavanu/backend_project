package com.example.fileupload.dto;


import lombok.Data;

@Data
public class JobRequest {
    String jobName;
    String cronExpression;
    String groupName;
    String className;
    String triggerName;
}
