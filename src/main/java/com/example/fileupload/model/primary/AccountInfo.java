package com.example.fileupload.model.primary;

import jakarta.persistence.*;

@Entity
@Table(name="account_info")
public class AccountInfo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="user_account_no",unique = true)
    private String user_account_no;

    @Column (name="balance")
    private float balance;

    @Column (name = "is_active")
    private boolean is_active;




}




