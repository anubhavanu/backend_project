package com.example.fileupload.dto;


import lombok.Data;

@Data
public class Job {
    String jobName;
    String cronExpression;
    String groupName;
    String className;
}
