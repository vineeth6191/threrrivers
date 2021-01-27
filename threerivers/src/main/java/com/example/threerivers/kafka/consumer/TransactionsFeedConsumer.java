package com.example.threerivers.kafka.consumer;

import com.example.threerivers.kafka.BalanceFeed;
import com.example.threerivers.kafka.TransactionsFeed;
import com.example.threerivers.model.Account;
import com.example.threerivers.model.Transaction;
import com.example.threerivers.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionsFeedConsumer {

    @Autowired
    private AccountRepository accountRepository;

    @KafkaListener(topics = "transactions_topic", groupId = "group_id")
    public void consumeMessage(TransactionsFeed message) {

        System.out.println(message);
        Account account = accountRepository.findAccountByAccountNumber(message.getAccountNumber()).isPresent() ?
                accountRepository.findAccountByAccountNumber(message.getAccountNumber()).get() : null;

        if (account == null)
            return;
        Transaction transaction = new Transaction();
        transaction.setTransactionTs(message.getTransactionTs());
        transaction.setTransactionType(message.getType());
        transaction.setAmount(message.getAmount());

        List<Transaction> transactions = account.getTransactions();
        if(transactions == null)
            transactions = Collections.singletonList(transaction);
        else
            transactions.add(transaction);

        account.setTransactions(transactions);
        //Save the updated transaction Info to MongoDB.
        accountRepository.save(account);
    }
}
