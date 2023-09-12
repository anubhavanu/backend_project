package com.example.fileupload.Component;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class JobCreate implements Job {

    Logger logger=  LoggerFactory.getLogger(JobCreate.class);
    @Autowired
    MessageReciever messageReciever;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        try {
//            logger.info("******inside Jobcreate class********");
////            messageReciever.recieveMessage();
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("The job was created successfully");
    }
}
