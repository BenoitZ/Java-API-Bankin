package com.bank.banking.services;

import com.bank.banking.dto.ContactDto;

import java.util.List;

public interface ContactServices extends AbstractServices<ContactDto> {

    List<ContactDto> findAllByUserId(Integer userId);
}
