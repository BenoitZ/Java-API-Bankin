package com.bank.banking.services.impl;

import com.bank.banking.dto.AccountDto;
import com.bank.banking.exceptions.OperationNonPermittedException;
import com.bank.banking.models.Account;
import com.bank.banking.repositories.AccountRepository;
import com.bank.banking.services.AccountServices;
import com.bank.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServicesImpl implements AccountServices {

    private final AccountRepository accountRepository;
    private final ObjectsValidator<AccountDto> validator;


    @Override
    public Integer save(AccountDto dto) {
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        // check if account already exist
        boolean userHasAlreadyAnAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent();
        if(userHasAlreadyAnAccount) {
            throw new OperationNonPermittedException(
                    "the selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        // generate random IBAN when creating new account else do not update the IBAN
        if (dto.getId() == null) {
            account.setIban(generateRandomIban());
        }
        return accountRepository.save(account).getId();
    }
    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(u -> AccountDto.fromEntity(u))
                .collect(Collectors.toList());
    }
    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(u -> AccountDto.fromEntity(u))
                .orElseThrow(() -> new EntityNotFoundException("No Account find with this provided ID : " + id));
    }
    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    private String generateRandomIban(){
        //generate an iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        //check if iban already exists with repository method findByIban (accountRepository)
        boolean ibanExists = accountRepository.findByIban(iban).isPresent();

        //if exists -> generate new random iban, restart method (recursive)
        if (ibanExists) {
            generateRandomIban();
        }

        //if not exist -> return generated iban
        return iban;
    }
}
