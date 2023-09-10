package com.example.fileupload.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="account_info_dup")
public class AccountInfoDup {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="user_account_no",unique = true)
    private String user_account_no;


    @Column (name="balance")
    private float balance;

    @Column (name = "is_active")
    private boolean is_active;



}
