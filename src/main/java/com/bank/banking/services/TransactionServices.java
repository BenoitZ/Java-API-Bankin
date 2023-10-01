package com.bank.banking.services;

import com.bank.banking.dto.TransactionDto;
import com.bank.banking.models.Transaction;

import java.util.List;

public interface TransactionServices extends AbstractServices<TransactionDto>{

    List<TransactionDto> findAllByUserId(Integer userId);

}
