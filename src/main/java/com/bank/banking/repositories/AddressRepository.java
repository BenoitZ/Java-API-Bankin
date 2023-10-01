package com.bank.banking.repositories;

import com.bank.banking.models.Account;
import com.bank.banking.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Integer> {
}
