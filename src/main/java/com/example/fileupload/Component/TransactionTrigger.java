package com.example.fileupload.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTrigger {
    private String account_no;
    private int amount;
    private int txn_id;
}
