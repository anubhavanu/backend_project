package com.example.fileupload.service;

import com.example.fileupload.Component.CountryJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

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
                .withIntervalInSeconds(300)
                .repeatForever())
                .build();

        Scheduler scheduler= schedulerFactoryBean.getScheduler();

        scheduler.scheduleJob(jobDetail,st);
        scheduler.start();


    }
}
