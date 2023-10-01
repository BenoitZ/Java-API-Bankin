package com.bank.banking.controllers;

import com.bank.banking.dto.AccountDto;
import com.bank.banking.services.AccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class accountController {

    private final AccountServices service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(AccountDto accountDto){
        return ResponseEntity.ok(service.save(accountDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountDto> findById(@PathVariable("account-id")Integer accountId){
        return ResponseEntity.ok(service.findById(accountId));
    }

    @DeleteMapping("/{account-id}")
    public ResponseEntity<Void> delete(@PathVariable("account-id")Integer accountId){
        service.delete(accountId);
        return ResponseEntity.accepted().build();
    }
}
