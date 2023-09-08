package com.example.fileupload.controller;

import com.example.fileupload.dto.JobRequest;
import org.quartz.SchedulerException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.batch.core.Job;
@RestController
public class BatchController {

    @Autowired
    JobLauncher jbLauncher;

    @Autowired
    Job dboProcessJob;

    @GetMapping ("/startDboUpload")
    public String resumeJob() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jbLauncher.run(dboProcessJob, jobParameters);

        return "Batch job has been invoked";

    }


}
