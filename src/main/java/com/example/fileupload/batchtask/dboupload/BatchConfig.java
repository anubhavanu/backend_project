package com.example.fileupload.batchtask.dboupload;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class BatchConfig {
    @Bean
    public Job dboProcessJob( JobRepository jbRepository, PlatformTransactionManager transactionManager, Step orderStep1 ) {
        return new JobBuilder("processJob", jbRepository)
//                .incrementer(new RunIdIncrementer())  //  not needed for spring boot 3
                .listener(listener())
                .flow(orderStep1)
                .end()
                .build();
    }
    @Bean
    public Step orderStep1(JobRepository jbRepository,PlatformTransactionManager transactionManager) {
        return new StepBuilder("orderStep1", jbRepository)
                .<String, String> chunk(1,transactionManager)
                .reader(new Reader())
                .processor(new Processor())
                .writer(new Writer())
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new BatchListener();
    }


}