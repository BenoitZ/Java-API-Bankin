package com.bank.banking.services.impl;

import com.bank.banking.dto.ContactDto;
import com.bank.banking.models.Contact;
import com.bank.banking.repositories.ContactRepository;
import com.bank.banking.services.ContactServices;
import com.bank.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServicesImpl implements ContactServices {

    private final ContactRepository contactRepository;
    private final ObjectsValidator<ContactDto> validator;

    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);
        return contactRepository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll()
                .stream()
                .map(u -> ContactDto.fromEntity(u))
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return contactRepository.findById(id)
                .map(u -> ContactDto.fromEntity(u))
                .orElseThrow(() -> new EntityNotFoundException("No User find with this provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        contactRepository.deleteById(id);
    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return contactRepository.findAllByUserId(userId)
                .stream()
                .map(u -> ContactDto.fromEntity(u))
                .collect(Collectors.toList());
    }
}
