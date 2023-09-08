package com.example.fileupload.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SQSConfiguration {
    @Value("${AWS_ACCESS_KEY_ID}")
    private String accessKey;
    @Value("${AWS_SECRET_ACCESS_KEY}")
    private String secretKey;


    @Bean
    public  SqsClient getSQSClient() {
//        AwsCredentialsProvider credentialsProvider =
//                ProfileCredentialsProvider.create("<Profile>");


        AwsCredentialsProvider credentialsProvider =  EnvironmentVariableCredentialsProvider.create();

        SqsClient sqsClient = SqsClient
                .builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.AP_SOUTH_1).build();

        return sqsClient;
    }

}
