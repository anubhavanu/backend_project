package com.example.fileupload.service;

import com.example.fileupload.configuration.RedisConfig;
import io.github.bucket4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class SubscriberInitializeService {
    @Autowired
    RedisConfig redisConfig;

    private Map<String,Bucket> user_bucket_map= new HashMap<>();
    public Bucket subscriber_Initialize(String subscription,String user_name) {
        if (user_bucket_map!=null && user_bucket_map.get(user_name) != null  )
            return user_bucket_map.get(user_name);
        else {
            Bucket b= subs(subscription);
            user_bucket_map.put(user_name,b);
            return b;
        }
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
//
        Bucket bucket= Bucket4j.builder()
                .addLimit(limit).build();
//       LettuceBasedProxyManager proxyManager=redisConfig.set_proxy_mgr();
//        BucketConfiguration bconfig=redisConfig.bconfig(refillCount, minutescount, bucketSize);
        byte[] key = new byte[0];
//        Bucket bucket=proxyManager.builder().build(key,bconfig);
        return bucket;

    }
}
