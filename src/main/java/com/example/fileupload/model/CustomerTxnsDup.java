package com.example.fileupload.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
@Data
@Entity
@Table(name="customer_txns_dup")
public class CustomerTxnsDup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="account_id", nullable=false)
    private String  account_id;

    @Column (name="debit_amount")
    private float debit_amount;

    @Column (name = "txn_created_time")
    private LocalTime txn_created_time;

    @Column (name = "status")
    private String status;


    @Column(name = "reason")
    private String reason;



}

