package com.example.fileupload.service;

import com.example.fileupload.Component.CountryJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    @Autowired
    private Scheduler scheduler;

    public void StartSchedulerService() throws SchedulerException {
        System.out.println("******StartSchedulerService");
        scheduler.start();
    }
    public void SchedulerService(){

    }
    public void schedule() throws SchedulerException {
          JobDetail jobDetail= JobBuilder.newJob(CountryJob.class)
                  .withIdentity("MyJobDetail")
                  .build();
          SimpleTrigger st=TriggerBuilder.newTrigger()
                  .withIdentity("Simpletrigger")

                  .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever())
                .build();

        scheduler.scheduleJob(jobDetail,st);


    }
}
