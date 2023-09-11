package com.example.fileupload.service;

import com.example.fileupload.model.CountryImp;
import com.example.fileupload.model.CustomerTxns;
import com.example.fileupload.repository.CountryRepository;
import com.example.fileupload.repository.CustomerTxnRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.logging.Logger;

@Service
public class TestRepo {


    @Autowired
    CustomerTxnRepository cr;

    @Autowired
    CountryRepository cip;


    private static Logger logger = Logger
            .getLogger(TestRepo.class.getName());
    public void savedata() throws JsonProcessingException {

        CustomerTxns customerTxnsDup= new CustomerTxns();
        customerTxnsDup.setId(100L);
        customerTxnsDup.setTxn_created_time(LocalTime.now());
        customerTxnsDup.setReason("Online shopping");
        customerTxnsDup.setDebit_amount(2355);
        cr.save(customerTxnsDup);


        CountryImp ci=new CountryImp();

        ci.setCountry_name("india");
        ci.setCapital("delhiii");
        cip.save(ci);
        logger.info("heeeyyy savedd******");

    }
}
