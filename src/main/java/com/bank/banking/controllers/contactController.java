package com.bank.banking.controllers;

import com.bank.banking.dto.ContactDto;
import com.bank.banking.dto.UserDto;
import com.bank.banking.services.ContactServices;
import com.bank.banking.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class contactController {

    private final ContactServices service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(ContactDto contactDto){
        return ResponseEntity.ok(service.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<ContactDto> findById(@PathVariable("user-id")Integer contactId){
        return ResponseEntity.ok(service.findById(contactId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<ContactDto>> findAllByUserId(@PathVariable("user-id")Integer userId){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @DeleteMapping("/{contact-id}")
    public ResponseEntity<Void> delete(@PathVariable("contact-id")Integer contactId){
        service.delete(contactId);
        return ResponseEntity.accepted().build();
    }
}
