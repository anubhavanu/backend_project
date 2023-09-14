package com.example.fileupload.service;

import com.example.fileupload.model.secondary.Exam;
import com.example.fileupload.repository.secondary.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;




    public void addExam(String exam_name, String exam_type)
    {
        Exam exams=new Exam();
        exams.setExam_name(exam_name);
        exams.setExam_type(exam_type);
        examRepository.save(exams);
    }
}
