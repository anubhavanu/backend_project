package com.example.fileupload.service;


import com.example.fileupload.model.CustomerTxns;
import com.example.fileupload.repository.AccountInfoRepository;
import com.example.fileupload.repository.CustomerTxnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferAmount {
    @Autowired
    CustomerTxnRepository customerTxnRepository;
    @Autowired
    AccountInfoRepository accountInfoRepository;

//    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public String transferAmount(String accountNo, float debitAmount, Long internalTxnId) {
        Optional<CustomerTxns> customerTxns = customerTxnRepository.findById(internalTxnId);
        System.out.println(customerTxns);


        if (!customerTxns.isEmpty()) {

            CustomerTxns ct = customerTxns.get();
            ct.setStatus("Success");
            customerTxnRepository.save(ct);

            return ("Success");
        }
        return "Failure";
    }




}
