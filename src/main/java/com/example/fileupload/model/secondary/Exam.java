package com.example.fileupload.model.secondary;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Examination")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="exam_id")
    private int exam_id;

    @Column(name="exam_name")
    private String exam_name;

    @Column (name="exam_type")
    private String exam_type;
}
