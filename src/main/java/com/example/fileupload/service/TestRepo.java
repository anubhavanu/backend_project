package com.example.fileupload.service;

import com.example.fileupload.model.AccountInfoDup;
import com.example.fileupload.repository.AccountInfoRepositoryDup;
import com.example.fileupload.repository.CustomerTxnRepositoryDup;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TestRepo {



//    @Autowired
//    AccountInfoDup accountInfo1;
    @Autowired
    AccountInfoRepositoryDup accountInfoRepository1;

//    @Autowired
//    CustomerTxnsDup customerTxnsDup;

    @Autowired
    CustomerTxnRepositoryDup customerTxnRepositoryDup;
    private static Logger logger = Logger
            .getLogger(TestRepo.class.getName());
    public void savedata() throws JsonProcessingException {

        AccountInfoDup accountInfo1 =new AccountInfoDup();
//        CustomerTxnsDup customerTxnsDup=new CustomerTxnsDup();

//        customerTxnsDup.setStatus("Succsess");
//
        logger.info("helllooooooo world---- TestRepoTestRepoTestRepo");
        AccountInfoDup accountInfo2 = accountInfoRepository1.getById(1L);
        accountInfo1.set_active(true);
        accountInfo1.setUser_account_no("ICICI32323");
        accountInfo1.setBalance(100000);
        accountInfo1.setId(100L);
        accountInfoRepository1.save(accountInfo1);

//        customerTxnsDup.setAccount_id("ICICI32323");
//        customerTxnsDup.setTxn_created_time(LocalTime.now());
//        customerTxnsDup.setReason("Online shopping");
//        customerTxnsDup.setDebit_amount(2355);
//        customerTxnRepositoryDup.save(customerTxnsDup);

    }
}
