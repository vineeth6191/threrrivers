package com.example.threerivers.resource;

import com.example.threerivers.exception.InvalidAccountException;
import com.example.threerivers.model.Account;
import com.example.threerivers.model.TransactionType;
import com.example.threerivers.model.dto.AccountDetails;
import com.example.threerivers.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @GetMapping("{accountNumber}")
    public AccountDetails getAccountDetails(@PathVariable String accountNumber,
                                            @RequestParam(required = false) Instant from,
                                            @RequestParam(required = false) Instant to,
                                            @RequestParam(required = false) TransactionType transactionType) {
        AccountDetails accountDetails = accountService.
                getAccountDetails(accountNumber, from, to, transactionType);
        if (accountDetails == null)
            throw new InvalidAccountException("Account not found with given accountNumber");

        return accountDetails;
    }

    @PostMapping("/save")
    public void saveAccount(@RequestBody Account account) {
        accountService.saveAccount(account);
    }
}
