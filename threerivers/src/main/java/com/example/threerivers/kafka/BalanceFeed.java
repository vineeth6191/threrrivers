package com.example.threerivers.kafka;

import lombok.Data;

import java.time.Instant;

@Data
public class BalanceFeed {
    private String accountNumber;
    private Instant lastUpdateTimestamp;
    private  Double balance;
}
