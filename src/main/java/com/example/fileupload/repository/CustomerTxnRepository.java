package com.example.fileupload.repository;

import com.example.fileupload.model.CustomerTxns;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTxnRepository extends CrudRepository< CustomerTxns, Long> {

    @Query(value="Select * from customer_txns c  where c.id= ?1 limit 1 ", nativeQuery = true)
    CustomerTxns findCustomerById(int id);
//    public Object save(CustomerTxns customerTxns);

}
