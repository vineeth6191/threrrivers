package com.example.threerivers.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Document(collection = "account")
public class Account {
    @Id
    private String id;
    @Indexed
    private String accountNumber;
    private Instant lastUpdatedTimeStamp;
    private Double balance;
    private List<Transaction> transactions;
}
