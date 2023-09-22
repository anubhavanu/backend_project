package com.example.fileupload.service;

import com.example.fileupload.model.primary.CountryImp;
import com.example.fileupload.model.primary.SecondaryCountryImp;
import com.example.fileupload.repository.primary.CountryRepository;
import com.example.fileupload.repository.primary.SecondaryCountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestAsync {
    @Autowired
    CountryRepository cr;
    @Autowired
    SecondaryCountryRepository scr;
    Logger logger = LoggerFactory.getLogger(TestAsync.class);

    public void sample1() throws InterruptedException {

        while(true){
            Thread.sleep(1000);
            logger.info("sample1**********************");
        }
    }



    @Transactional
    public void saveMultipleData2(){
        logger.info("*******saveMultipleData*******");
        CountryImp cou1= new CountryImp();
        cou1.setCountry_name("name4455");
        cou1.setCapital("capita44455");

        cr.save(cou1);
//        CountryImp cou3 = cr.getById("name1112");


        if(1==11){
            throw new RuntimeException();
        }



    }

    @Transactional(transactionManager = "chainedTransactionManager")
    public void saveMultipleData1(){
        logger.info("*******saveMultipleData2222*******");

        SecondaryCountryImp cou2= new SecondaryCountryImp();
        cou2.setCountry_name("name7");
        cou2.setCapital("capital7");

        scr.save(cou2);
        saveMultipleData2();


    }
}
