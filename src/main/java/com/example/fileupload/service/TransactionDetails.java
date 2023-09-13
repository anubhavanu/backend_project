package com.example.fileupload.service;

import com.example.fileupload.Component.MessageReciever;
import com.example.fileupload.Component.MessageSender;
import com.example.fileupload.Component.TransactionTrigger;
import com.example.fileupload.model.primary.AccountInfo;
import com.example.fileupload.model.primary.CustomerTxns;
import com.example.fileupload.repository.primary.AccountInfoRepository;
import com.example.fileupload.repository.primary.CustomerTxnRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class TransactionDetails {
//    @Autowired
//    CustomerTxns customerTxns;

//    @Autowired
//    AccountInfo accountInfo;

    @Autowired
    MessageSender messageSender;

    @Autowired
    MessageReciever messageReciever;

    @Autowired
    CustomerTxnRepository customerTxnRepository;

    @Autowired
    AccountInfoRepository accountInfoRepository;

//    @Autowired
//    SqsClient sqsClient;
    public Long  newTransaction(String userAccountNo, int amount) {
        CustomerTxns customerTxns =new CustomerTxns();
        AccountInfo ZAccountInfo =accountInfoRepository.findAccountByAccountNo(userAccountNo);
//        customerTxns.setZAccountInfo(ZAccountInfo);
        customerTxns.setDebit_amount(amount);
        LocalTime obj=  LocalTime.now();
        customerTxns.setTxn_created_time(obj);
        customerTxns.setReason("OnlineShopping");
        customerTxns.setStatus("Transaction request accepted");
        customerTxnRepository.save(customerTxns);
        TransactionTrigger t=new TransactionTrigger(userAccountNo,amount,customerTxns.getId());
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr="";

        // Try block to check for exceptions
        try {

            // Getting organisation object as a json string
             jsonStr = Obj.writeValueAsString(t);

            // Displaying JSON String on console
            System.out.println(jsonStr);
        }

        // Catch block to handle exceptions
        catch (IOException e) {

            // Display exception along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
//        String s="{ \"account_no\" :"+ userAccountNo+", \"debit_amount\" :"+ amount+"}";
        System.out.println(messageSender.sendMessage(jsonStr));
        return customerTxns.getId();
    }

    public String transactionStatus(Long transactionId)
    {


       Optional<CustomerTxns> customerTxns= customerTxnRepository.findById(transactionId);


               return ("transaction status is "+customerTxns.get().getStatus());




    }
}
