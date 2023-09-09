package com.example.fileupload.service;

import com.example.fileupload.Component.MessageReciever;
import com.example.fileupload.Component.MessageSender;
import com.example.fileupload.model.AccountInfo;
import com.example.fileupload.model.CustomerTxns;
import com.example.fileupload.repository.AccountInfoRepository;
import com.example.fileupload.repository.CustomerTxnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String s="{ \"account_no\" :"+ userAccountNo+", \"debit_amount\" :"+ amount+"}";
        System.out.println(messageSender.sendMessage(s));
        return customerTxns.getId();
    }

    public String transactionStatus(int transactionId)
    {
        CustomerTxns c=new CustomerTxns();

       CustomerTxns customerTxns= customerTxnRepository.findCustomerById(transactionId);
       if (customerTxns==null)
       {
           return ("invalid transaction id");
       }
       else {
           customerTxns.setId(transactionId);
           if (customerTxns.getDebit_amount()>customerTxns.getAccountInfo().getBalance()) {
               String res=messageReciever.recieveMessage(customerTxns);
               customerTxns.setStatus("Failed");
               return ("transaction failed due to insufficiant balance "+res);
           }
           else if (!customerTxns.getAccountInfo().is_active()) {
               String res=messageReciever.recieveMessage(customerTxns);
               customerTxns.setStatus("Failed");
               return ("transaction failed due to inactive user "+res);
           }
           else
           {
               customerTxns.getAccountInfo().setBalance(customerTxns.getAccountInfo().getBalance()-customerTxns.getDebit_amount());
               accountInfoRepository.save(customerTxns.getAccountInfo());
               customerTxns.setStatus("Success");
               customerTxnRepository.save(customerTxns);
                String res=messageReciever.recieveMessage(customerTxns);
               return ("transaction successful "+res);
           }
       }


    }
}
