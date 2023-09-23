package com.example.fileupload.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class SubscriberInitializeService {
    private Bucket buckets;
    public Bucket subscriber_Initialize(String subscription)
    {

        return subs(subscription);

    }

    public Bucket subs(String plan)
    {
        int refillCount;
        int minutescount;
        int bucketSize;

        switch (plan){
            case "simple":refillCount=2;
                minutescount=1;
                bucketSize=4;
            case "premium":refillCount=10;
                minutescount=1;
                bucketSize=10;

            default:refillCount=5;
                minutescount=1;
                bucketSize=5;

        }
        Refill refill=Refill.of(refillCount, Duration.ofMinutes(minutescount));
        Bandwidth limit = Bandwidth.classic(bucketSize, refill);

        buckets= Bucket4j.builder()
                .addLimit(limit).build();
        return buckets;

    }
}
