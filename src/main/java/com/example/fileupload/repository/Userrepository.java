package com.example.fileupload.repository;

import com.example.fileupload.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Userrepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT id,username,password FROM users u WHERE u.username = ?1 limit 1",nativeQuery = true)
    User findUserByName(String username);
     
     List <User> findAll();


}
