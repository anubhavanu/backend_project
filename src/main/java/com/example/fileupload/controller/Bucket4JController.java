package com.example.fileupload.controller;

import com.example.fileupload.service.OfficeService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class Bucket4JController {
    @Autowired
    OfficeService officeServices;

//    @Autowired
//    Subscriber subscribers;
    private Bucket buckets;
    @GetMapping("/bucket_creation")
    public Bucket generateToken()
    {
        Refill refill=Refill.of(5, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(5, refill);

        buckets= Bucket4j.builder()
                .addLimit(limit).build();
        return buckets;

    }
    public Bucket subscriber_Initialize(String subscription)
    {

        if (subscription.equals("simple"))
        {
           return simpleSubscriber();
        }
        else if (subscription.equals("premium"))
        {
            return premiumSubscriber();
        }
        else

            return vipSubscriber();

    }

    public Bucket simpleSubscriber()
    {
        Refill refill=Refill.of(2, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(4, refill);

        buckets= Bucket4j.builder()
                .addLimit(limit).build();
        return buckets;

    }

    public Bucket vipSubscriber()
    {
        Refill refill=Refill.of(5, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(5, refill);

        buckets= Bucket4j.builder()
                .addLimit(limit).build();
        return buckets;

    }
    public Bucket premiumSubscriber()
    {
        Refill refill=Refill.of(10, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(10, refill);

        buckets= Bucket4j.builder()
                .addLimit(limit).build();
        return buckets;

    }
    @GetMapping("/rate_limiter_consumption/{office_Id}")
    public ResponseEntity<String> consumption(@PathVariable int office_Id)
    {

        if (buckets.tryConsume(1)){
            System.out.println("================API TOKEN CONSUMED SUCCESSFULLY================");
            return new ResponseEntity<>("Office is found" +officeServices.find_office(office_Id) , HttpStatus.OK);
        }
        System.out.println("================= TOO MANY HITS ===============");
        return new ResponseEntity<>("TOO MANY HITS!!!!!! PLEASE TRY AFTER SOMETIME!!!!",HttpStatus.TOO_MANY_REQUESTS);
    }
}
