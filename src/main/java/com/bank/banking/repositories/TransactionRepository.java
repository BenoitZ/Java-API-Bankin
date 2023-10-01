package com.bank.banking.repositories;

import com.bank.banking.models.Address;
import com.bank.banking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserId(Integer userId);
}
