package com.example.fileupload.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name="customer_txns")
public class CustomerTxns {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


//    private ZAccountInfo ZAccountInfo;

//    @Column (name="debit_amount")
//    private float debit_amount;

    @Column (name="debit_amount")
    private float debit_amount;

    @Column (name = "txn_created_time")
    private LocalTime txn_created_time;

    @Column (name = "status")
    private String status;


    @Column(name = "reason")
    private String reason;

    @Column (name = "account_id")
    private Long account_id;




}
