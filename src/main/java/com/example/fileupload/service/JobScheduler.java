package com.example.fileupload.service;

import com.example.fileupload.Component.JobCreate;
import com.example.fileupload.repository.JobScheduleRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobScheduler implements JobScheduleRepository {
    @Autowired
    JobScheduleRepository jobScheduleRepository;
    @Autowired
    private Scheduler scheduler;


    @Override
    public String findJobByName(String jobName) {
        String jn = jobScheduleRepository.findJobByName("jobName");
        if (jn == null)
            return "true";
        return "false";
    }

    public String scheduling(String jobName, String triggerName, int time) throws SchedulerException {
        if(findJobByName(jobName).equals("false"))
        {
            JobDetail jobDetail = JobBuilder.newJob(JobCreate.class)
                    .withIdentity(jobName)
                    .build();
            SimpleTrigger st = TriggerBuilder.newTrigger()
                    .withIdentity(triggerName)

                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(time)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(jobDetail, st);
            return ("job created successfully");
        }
        else
            return ("job was already present/not created successfully");
    }

    @Override
    public <S extends JobScheduler> S save(S entity) {
        return null;
    }

    @Override
    public <S extends JobScheduler> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<JobScheduler> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<JobScheduler> findAll() {
        return null;
    }

    @Override
    public Iterable<JobScheduler> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(JobScheduler entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends JobScheduler> entities) {

    }

    @Override
    public void deleteAll() {

    }
}