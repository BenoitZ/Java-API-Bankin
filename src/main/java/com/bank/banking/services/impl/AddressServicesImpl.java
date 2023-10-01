package com.bank.banking.services.impl;

import com.bank.banking.dto.AddressDto;
import com.bank.banking.models.Address;
import com.bank.banking.repositories.AddressRepository;
import com.bank.banking.services.AddressServices;
import com.bank.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServicesImpl implements AddressServices {

    private final AddressRepository addressRepository;
    private final ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return addressRepository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(u -> AddressDto.fromEntity(u))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(u -> AddressDto.fromEntity(u))
                .orElseThrow(() -> new EntityNotFoundException("No User find with this provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        addressRepository.deleteById(id);
    }

}
