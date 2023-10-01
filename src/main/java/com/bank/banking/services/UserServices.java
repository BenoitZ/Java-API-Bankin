package com.bank.banking.services;

import com.bank.banking.dto.UserDto;

public interface UserServices extends AbstractServices<UserDto>{

    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);
}
