package com.example.fileupload.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
@Table(name="customer_txns")
public class CustomerTxns {
    @Id
    @GeneratedValue
    @Column(name="id")
    private String id;

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private AccountInfo accountInfo;

    @Column (name="debit_amount")
    private float debit_amount;

    @Column (name = "txn_created_time")
    private Time txn_created_time;

    @Column (name = "status")
    private Time status;


    @Column(name = "reason")
    private String reason;





}
