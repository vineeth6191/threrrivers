package com.example.threerivers.model.dto;

import com.example.threerivers.model.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class AccountDetails {
    private Double balance;
    private List<Transaction> transactionList;
}
