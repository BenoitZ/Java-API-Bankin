package com.bank.banking.services.impl;

import com.bank.banking.dto.TransactionDto;
import com.bank.banking.models.Transaction;
import com.bank.banking.models.TransactionType;
import com.bank.banking.repositories.TransactionRepository;
import com.bank.banking.services.TransactionServices;
import com.bank.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServicesImpl implements TransactionServices {

    private final TransactionRepository transactionRepository;
    private final ObjectsValidator<TransactionDto> validator;

    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        //Determine le type de transaction par le mutiply postifif ou negatif
        BigDecimal transactionMultiplier = BigDecimal.valueOf(getTransactionMultiplier(transaction.getType()));
        //Recalcule le montant de la transaction
        BigDecimal amount = transaction.getAmount().multiply(transactionMultiplier);
        //Set le montant de l atransaction
        transaction.setAmount(amount);
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(u -> TransactionDto.fromEntity(u))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return transactionRepository.findById(id)
                .map(u -> TransactionDto.fromEntity(u))
                .orElseThrow(() -> new EntityNotFoundException("No Transaction find with this provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return transactionRepository.findAllByUserId(userId)
                .stream()
                .map(u -> TransactionDto.fromEntity(u))
                .collect(Collectors.toList());
    }

    private Integer getTransactionMultiplier(TransactionType type){
        return TransactionType.TRANSFERT == type ? -1 : 1;
    }
}
