package com.example.fileupload.repository.primary;

import com.example.fileupload.model.primary.CustomerTxns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
CustomerTxnRepository extends JpaRepository<CustomerTxns, Long> {

//    @Query(value="Select * from customer_txns c  where c.id= ? limit 1 ", nativeQuery = true)
//    CustomerTxns findCustomerById(Long id);
//    public Object save(CustomerTxns customerTxns);

}
