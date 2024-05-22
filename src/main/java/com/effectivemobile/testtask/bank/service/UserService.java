package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.UserCreateDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;
import com.effectivemobile.testtask.bank.dto.UserSearchDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Optional;

public interface UserService {

    Optional<UserReturnDto> saveUser(UserCreateDto user);

    Page<UserSearchDto> searchUsers(
            LocalDate birthDate, String number, String fullName, String email, int page, int size, String sortBy, String sortDir
    );

}