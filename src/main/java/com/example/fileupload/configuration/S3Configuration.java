package com.example.fileupload.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Configuration {
    @Value("${AWS_ACCESS_KEY_ID}")
    private String accessKey;
    @Value("${AWS_SECRET_ACCESS_KEY}")
    private String secretKey;
    @Bean("s3c")
    public S3Client  s3Client(){
        AwsCredentialsProvider credentialsProvider =  EnvironmentVariableCredentialsProvider.create();
        S3Client s3Client=S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.AP_SOUTH_1).build();
        return s3Client;
    }
}

//AWS_ACCESS_KEY_ID=AKIAVUQ57EQQRAA3676N;AWS_SECRET_ACCESS_KEY=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