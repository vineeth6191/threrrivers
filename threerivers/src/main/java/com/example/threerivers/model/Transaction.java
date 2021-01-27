package com.example.threerivers.model;

import lombok.Data;

import java.time.Instant;

@Data
public class Transaction {

    private Instant transactionTs;
    private TransactionType transactionType;
    private Double amount;
}
