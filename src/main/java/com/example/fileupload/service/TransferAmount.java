package com.example.fileupload.service;


import com.example.fileupload.model.CustomerTxns;
import com.example.fileupload.repository.AccountInfoRepository;
import com.example.fileupload.repository.CustomerTxnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferAmount {
    @Autowired
    CustomerTxnRepository customerTxnRepository;
    @Autowired
    AccountInfoRepository accountInfoRepository;

//    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public String transferAmount(String accountNo, float debitAmount, int internalTxnId){
        CustomerTxns customerTxns= customerTxnRepository.findCustomerById(internalTxnId);
        System.out.println(customerTxns);

        try {
                        if (customerTxns==null)
            {

                return ("Failure");
            }
            else {
                customerTxns.setId(internalTxnId);
                if (customerTxns.getDebit_amount()>customerTxns.getAccountInfo().getBalance()) {

                    customerTxns.setStatus("Failure");

                    customerTxnRepository.save(customerTxns);
                    return ("Failure");
                }
                else if (!customerTxns.getAccountInfo().is_active()) {

                    customerTxns.setStatus("Failure");
                    customerTxnRepository.save(customerTxns);
                    return ("Failure");
                }



            // hit api of bank
            // hit api of npci to check bank has contacted or not
            //hit another api to verify everything is ok or not

            //do nothing with our db status if above apis are giving pending status
            // update our db with status success if above apis are fine
            customerTxns.getAccountInfo().setBalance(customerTxns.getAccountInfo().getBalance()-customerTxns.getDebit_amount());
               accountInfoRepository.save(customerTxns.getAccountInfo());
               customerTxns.setStatus("Success");
              System.out.println("**********saving *******   "+customerTxns);

               customerTxnRepository.save(customerTxns);



        }}
        catch (Exception e){
            // update our db with status failure if above apis are fine

        }
        if (customerTxns.getStatus().equals("Success"))
            return ("Success");
        else if (customerTxns.getStatus().equals("Failure"))
            return ("Failure");
        else
            return ("Retry");
    }


}
