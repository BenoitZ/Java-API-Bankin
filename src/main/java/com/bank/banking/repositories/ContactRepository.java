package com.bank.banking.repositories;

import com.bank.banking.dto.ContactDto;
import com.bank.banking.models.Contact;
import com.bank.banking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository  extends JpaRepository<Contact, Integer> {

    List<Contact> findAllByUserId(Integer userId);

}
