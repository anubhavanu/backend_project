package com.example.fileupload.model.infosys;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private int employee_id;

    @Column(name="employee_name")
    private String employee_name;

    @Column (name="employee_status")
    private String employee_status;

    @Column(name="salary")
    private int salary;


}

