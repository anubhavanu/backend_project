package com.example.fileupload.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
@Table(name="account_info")
public class AccountInfo {
    @Id
    @GeneratedValue
    @Column(name="id")
    private String id;

    @Column(name="user_account_no",unique = true)
    private String user_account_no;


    @Column (name="balance")
    private float balance;

    @Column (name = "is_active")
    private boolean is_active;



}
