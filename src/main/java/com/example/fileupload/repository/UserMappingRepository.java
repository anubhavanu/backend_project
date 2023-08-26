package com.example.fileupload.repository;

import com.example.fileupload.model.UserMapping;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserMappingRepository extends CrudRepository<UserMapping ,Long> {
    @Autowired
    EntityManager e = null;
    @Query(value = "SELECT r.role_id,r.username from usermappings r WHERE r.username = ?1 limit 1",nativeQuery = true)
    UserMapping findUserByName(String username);

}
