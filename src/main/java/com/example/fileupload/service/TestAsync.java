package com.example.fileupload.service;

import com.example.fileupload.model.CountryImp;
import com.example.fileupload.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestAsync {
    @Autowired
    CountryRepository cr;
    Logger logger = LoggerFactory.getLogger(TestAsync.class);

    public void sample1() throws InterruptedException {

        while(true){
            Thread.sleep(1000);
            logger.info("sample1**********************");
        }
    }



    @Transactional()
    public void saveMultipleData1(){
        logger.info("*******saveMultipleData*******");
        CountryImp cou1= new CountryImp();
        cou1.setCountry_name("name44");
        cou1.setCapital("capita444");

        cr.saveAndFlush(cou1);
        CountryImp cou3 = cr.getById("name1112");



        if(1==2){
            throw new RuntimeException();
        }


        saveMultipleData2();

    }

    @Transactional()
    public void saveMultipleData2(){
        logger.info("*******saveMultipleData2222*******");

        CountryImp cou2= new CountryImp();
        cou2.setCountry_name("name2");
        cou2.setCapital("capital2");

        cr.save(cou2);


    }
}
