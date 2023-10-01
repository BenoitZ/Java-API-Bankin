package com.bank.banking.controllers;

import com.bank.banking.dto.AddressDto;
import com.bank.banking.dto.UserDto;
import com.bank.banking.services.AddressServices;
import com.bank.banking.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class addressController {

    private final AddressServices service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(service.save(addressDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{address-id}")
    public ResponseEntity<AddressDto> findById(@PathVariable("address-id")Integer addressId){
        return ResponseEntity.ok(service.findById(addressId));
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(@PathVariable("address-id")Integer addressId){
        service.delete(addressId);
        return ResponseEntity.accepted().build();
    }
}
