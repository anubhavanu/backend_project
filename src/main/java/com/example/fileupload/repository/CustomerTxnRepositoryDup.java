package com.example.fileupload.repository;

import com.example.fileupload.model.CustomerTxnsDup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
CustomerTxnRepositoryDup extends JpaRepository<CustomerTxnsDup, Long> {

//    @Query(value="Select * from customer_txns1_dup c  where c.id= ? limit 1 ", nativeQuery = true)
//    CustomerTxnsDup findCustomerById(int id);
//    public Object save(CustomerTxns customerTxns);

}
