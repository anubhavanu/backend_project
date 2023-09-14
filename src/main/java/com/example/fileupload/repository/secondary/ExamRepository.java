package com.example.fileupload.repository.secondary;

import com.example.fileupload.model.secondary.Exam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends CrudRepository<Exam,Long> {
}
