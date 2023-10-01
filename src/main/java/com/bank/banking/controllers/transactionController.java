package com.bank.banking.controllers;

import com.bank.banking.dto.ContactDto;
import com.bank.banking.dto.TransactionDto;
import com.bank.banking.dto.UserDto;
import com.bank.banking.services.TransactionServices;
import com.bank.banking.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class transactionController {

    private final TransactionServices service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(TransactionDto transactionDto){
        return ResponseEntity.ok(service.save(transactionDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("transaction-id")Integer transactionId){
        return ResponseEntity.ok(service.findById(transactionId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<TransactionDto>> findAllByUserId(@PathVariable("user-id")Integer userId){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void> delete(@PathVariable("transaction-id")Integer transactionId){
        service.delete(transactionId);
        return ResponseEntity.accepted().build();
    }
}
