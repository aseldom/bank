package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.UserCreateDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserReturnDto> saveUser(UserCreateDto user);

    List<UserReturnDto> findAllUsers();

}