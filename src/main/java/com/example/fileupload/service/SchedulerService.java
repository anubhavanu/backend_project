package com.example.fileupload.service;

import Component.CountryJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

@Service

public class SchedulerService {

    public SchedulerService()

    {


    }
    public void schedule() throws SchedulerException {
          JobDetail jobDetail= JobBuilder.newJob(CountryJob.class)
                  .withIdentity("MyJobDetail")
                  .build();
          SimpleTrigger st=TriggerBuilder.newTrigger()
                  .withIdentity("Simpletrigger")

                  .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever())
                .build();
          Scheduler sd=new StdSchedulerFactory().getScheduler();
          sd.scheduleJob(jobDetail,st);
          sd.start();


    }
}
