package com.example.fileupload.repository;
//import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fileupload.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

//    void saveAll(List<Tutorial> tutorials);

//    List<Tutorial> findAll();
}
