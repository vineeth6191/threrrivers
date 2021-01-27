package com.example.threerivers.kafka.consumer;

import com.example.threerivers.kafka.BalanceFeed;
import com.example.threerivers.model.Account;
import com.example.threerivers.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BalanceFeedConsumer {
    @Autowired
    private AccountRepository accountRepository;

    @KafkaListener(topics = "balance_topic", groupId = "group_id")
    public void consumeMessage(BalanceFeed message) {

        System.out.println(message);
        Account account = accountRepository.findAccountByAccountNumber(message.getAccountNumber()).isPresent() ?
                accountRepository.findAccountByAccountNumber(message.getAccountNumber()).get() : null;

        if (account == null)
            return;

        account.setBalance(message.getBalance());
        account.setLastUpdatedTimeStamp(message.getLastUpdateTimestamp());

        // save the updated balance to mongo DB.
        accountRepository.save(account);
    }
}
