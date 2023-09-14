package com.example.fileupload.service;

import com.example.fileupload.Component.MessageReciever;
import com.example.fileupload.model.CustomerTxns;
import com.example.fileupload.repository.CountryTableRepository;
import com.example.fileupload.repository.CustomerTxnRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.logging.Logger;

@Service


public class TestRepo {


    @Autowired
    CustomerTxnRepository cr;

    @Autowired
    CountryTableRepository cip;

    @Autowired
    CountryService countryServices;

    @Autowired
    MessageReciever mr;

//   @Autowired
//    PrimaryJpaConfiguration primaryJpaConfigurations;

    private static Logger logger = Logger
            .getLogger(TestRepo.class.getName());

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void devide()
    {
        try {
            int c=5/1;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


@Transactional(isolation = Isolation.REPEATABLE_READ)
public void savedata() throws JsonProcessingException {
        CustomerTxns customerTxnsDup= new CustomerTxns();
        customerTxnsDup.setId(14L);
        customerTxnsDup.setTxn_created_time(LocalTime.now());
        customerTxnsDup.setReason("online-advertise");
        customerTxnsDup.setDebit_amount(100);
        cr.save(customerTxnsDup);


        countryServices.fetchCountryTableData();
        devide();
    }
//    public void startTxnQueuelistner() throws JsonProcessingException, InterruptedException {
//
//        while(true){
//            Thread.sleep(10000);
//            logger.info("**********running startTxnQueuelistner ");
//            mr.recieveMessage();
//        }
//
//    }
}
