package com.example.threerivers.repository;

import com.example.threerivers.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findAccountByAccountNumber(String accountNumber);
}
