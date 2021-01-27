package com.example.threerivers.service;

import com.example.threerivers.model.Account;
import com.example.threerivers.model.Transaction;
import com.example.threerivers.model.TransactionType;
import com.example.threerivers.model.dto.AccountDetails;
import com.example.threerivers.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountDetails getAccountDetails(String accountNumber, Instant from,
                                            Instant to, TransactionType transactionType) {

        Account account = accountRepository.findAccountByAccountNumber(accountNumber).isPresent() ?
                accountRepository.findAccountByAccountNumber(accountNumber).get() : null;

        if (account == null)
            return null;

        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setBalance(account.getBalance());
        List<Transaction> transactions = account.getTransactions();

        if (from != null)
            transactions = transactions.stream().filter(transaction -> transaction.getTransactionTs()
                    .isAfter(from)).collect(Collectors.toList());
        if (to != null)
            transactions = transactions.stream().filter(transaction -> transaction.getTransactionTs()
                    .isBefore(to)).collect(Collectors.toList());
        if (transactionType != null)
            transactions = transactions.stream().filter(transaction -> transactionType.equals(transaction
                    .getTransactionType())).collect(Collectors.toList());

        accountDetails.setTransactionList(transactions);

        return accountDetails;
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
