package com.bank.banking.services.impl;

import com.bank.banking.dto.AccountDto;
import com.bank.banking.dto.UserDto;
import com.bank.banking.models.User;
import com.bank.banking.repositories.UserRepository;
import com.bank.banking.services.AccountServices;
import com.bank.banking.services.UserServices;
import com.bank.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final AccountServices accountServices;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(u -> UserDto.fromEntity(u))
                .collect(Collectors.toList());
    }
    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(u -> UserDto.fromEntity(u))
                .orElseThrow(() -> new EntityNotFoundException("No User find with this provided ID : " + id));
    }
    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Integer validateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(true);
        //create a bank account
        AccountDto account = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountServices.save(account);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }
}
