package com.example.fileupload.repository;

import com.example.fileupload.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.fileupload.model.Roles;


@Repository
public interface Rolerepository extends CrudRepository<Roles, Long> {
    @Query(value = "SELECT id,rolename FROM roles u WHERE u.rolename = ?1 limit 1",nativeQuery = true)
    User findUserByName(String username);



}
