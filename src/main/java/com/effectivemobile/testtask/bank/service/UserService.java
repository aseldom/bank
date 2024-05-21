package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.AddEmailDto;
import com.effectivemobile.testtask.bank.dto.AddPhoneDto;
import com.effectivemobile.testtask.bank.dto.UserCreateDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserReturnDto> saveUser(UserCreateDto user);

    UserReturnDto addEmail(String username, AddEmailDto addEmailDto);

    UserReturnDto addPhone(String username, AddPhoneDto addPhoneDto);

    List<UserReturnDto> findAllUsers();

}
