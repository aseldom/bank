package com.effectivemobile.testtask.bank.dto;

import com.effectivemobile.testtask.bank.model.BankAccount;
import com.effectivemobile.testtask.bank.model.EmailAddress;
import com.effectivemobile.testtask.bank.model.Phone;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserCreateDto {
    private String userName;
    private String password;
    private String fullName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private Set<EmailAddress> emails;
    private Set<Phone> phones;
    private BankAccount account;
}
