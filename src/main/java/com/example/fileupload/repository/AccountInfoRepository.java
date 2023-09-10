package com.example.fileupload.repository;

import com.example.fileupload.model.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {
    @Query(value = "Select * from account_info i where i.user_account_no= ?1 limit 1", nativeQuery = true)
     AccountInfo findAccountByAccountNo(String account_no);

//    public Object save(AccountInfo accountInfo);
}
