package com.example.threerivers.kafka;

import com.example.threerivers.model.TransactionType;
import lombok.Data;

import java.time.Instant;

@Data
public class TransactionsFeed {
    private String accountNumber;
    private Instant transactionTs;
    private TransactionType type;
    private Double amount;
}
