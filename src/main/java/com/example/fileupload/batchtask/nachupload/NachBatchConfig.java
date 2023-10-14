package com.example.fileupload.batchtask.nachupload;


import com.example.fileupload.model.secondary.BulkUploadRow;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class NachBatchConfig {

    @Bean
    public ItemReader<JsonNode> itemReader() {
        return new Reader();
    }

    @Bean
    public ItemProcessor<JsonNode, BulkUploadRow> itemProcessor() {
        return new Processor();
    }

    @Bean
    public ItemWriter<BulkUploadRow> itemWriter() {
        return new Writer();
    }


    @Bean
    protected Step processNachStep(JobRepository jobRepository,@Qualifier("primaryTransactionManager")  PlatformTransactionManager transactionManager, ItemReader<JsonNode> reader,
                                ItemProcessor<JsonNode, BulkUploadRow> processor, ItemWriter<BulkUploadRow> writer) {
        return new StepBuilder("processNachStep", jobRepository)
                .<JsonNode, BulkUploadRow> chunk(2, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


    @Bean
    public Job nachProcessJob(@Qualifier("jbRepository") JobRepository jbRepository, @Qualifier("primaryTransactionManager") PlatformTransactionManager transactionManager, Step processNachStep ) {
        return new JobBuilder("processNachJob", jbRepository)
                .listener(nachBatchListener())
                .flow(processNachStep)
                .end()
                .build();
    }


    @Bean
    public JobExecutionListener nachBatchListener() {
        return new NachBatchListener();
    }


}