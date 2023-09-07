package com.example.fileupload.service;

import com.example.fileupload.Component.JobCreate;


import com.example.fileupload.dto.JobRequest;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class JobScheduler  {
//    @Autowired
//    JobScheduleRepository jobScheduleRepository;
    @Autowired
    private Scheduler scheduler;


    public JobDetail findJobByName(String jobName, String jobGroup) throws SchedulerException {

        try {
            JobDetail jd  = scheduler.getJobDetail(new JobKey(jobName, jobGroup));
            return jd;
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public String scheduling(JobRequest jobRequest) throws SchedulerException {
        if(findJobByName(jobRequest.getJobName(), jobRequest.getGroupName())==null)
        {
            JobDetail jobDetail = JobBuilder.newJob(JobCreate.class)
                    .withIdentity(jobRequest.getJobName(),jobRequest.getGroupName())
                    .build();

//            CronTrigger st = newTrigger()
//                    .withIdentity(jobRequest.getTriggerName())
//
//                    .withSchedule(CronScheduleBuilder.cronSchedule(jobRequest.getCronExpression())).build();

            CronTrigger st =newTrigger()
                    .withIdentity(jobRequest.getTriggerName(), jobRequest.getGroupName())
                    .withSchedule(cronSchedule("0/10 * * * * ?"))
                    .forJob(jobRequest.getJobName(),jobRequest.getGroupName())

                    .build();

            scheduler.scheduleJob(jobDetail, st);
            return ("job created successfully");
        }
        else
            return ("job was already present/not created successfully");
    }


    public String pause(JobRequest jobRequest) throws SchedulerException {
        try {
            JobDetail jd=findJobByName(jobRequest.getJobName(), jobRequest.getGroupName());
            if(jd!=null)
            {
                scheduler.pauseJob(new JobKey(jobRequest.getJobName(),jobRequest.getGroupName()));
                return ("job paused successfully");
            }
            else
                return ("Job not found");
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public String resume(JobRequest jobRequest)  throws SchedulerException {
        try {
            JobDetail jd=findJobByName(jobRequest.getJobName(), jobRequest.getGroupName());
            if(jd!=null)
            {
                scheduler.resumeJob(new JobKey(jobRequest.getJobName(),jobRequest.getGroupName()));
                return ("job resumed successfully");
            }
            else
                return ("Job not found");
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public String updateJob(JobRequest jobRequest) throws SchedulerException{
        try {
            JobDetail jd=findJobByName(jobRequest.getJobName(), jobRequest.getGroupName());
            if (jd!=null)
            {
                CronTrigger st =newTrigger()
                        .withIdentity(jobRequest.getTriggerName(), jobRequest.getGroupName())
                        .withSchedule(cronSchedule(jobRequest.getCronExpression()))
                        .forJob(jobRequest.getJobName(),jobRequest.getGroupName())

                        .build();

                scheduler.rescheduleJob(new TriggerKey(jobRequest.getTriggerName()),st);
                return ("job updated successfully");
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return ("job not found");
    }
}