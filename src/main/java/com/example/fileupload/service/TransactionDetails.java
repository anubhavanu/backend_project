package com.example.fileupload.service;

import com.example.fileupload.Component.MessageReciever;
import com.example.fileupload.Component.MessageSender;
import com.example.fileupload.Component.TransactionTrigger;
import com.example.fileupload.model.AccountInfo;
import com.example.fileupload.model.CustomerTxns;
import com.example.fileupload.repository.AccountInfoRepository;
import com.example.fileupload.repository.CustomerTxnRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;

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
    public int  newTransaction(String userAccountNo, int amount) {
        CustomerTxns customerTxns =new CustomerTxns();
        AccountInfo accountInfo=accountInfoRepository.findAccountByAccountNo(userAccountNo);
        customerTxns.setAccountInfo(accountInfo);
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

    public String transactionStatus(int transactionId)
    {


       CustomerTxns customerTxns= customerTxnRepository.findCustomerById(transactionId);

               return ("transaction status is "+customerTxns.getStatus());




    }
}
