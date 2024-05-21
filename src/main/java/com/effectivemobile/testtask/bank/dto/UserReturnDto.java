package com.effectivemobile.testtask.bank.dto;

import com.effectivemobile.testtask.bank.model.BankAccount;
import com.effectivemobile.testtask.bank.model.EmailAddress;
import com.effectivemobile.testtask.bank.model.Phone;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserReturnDto {
    private String userName;
    private String fullName;
    private LocalDate birthDate;
    private Set<EmailAddress> emails;
    private Set<Phone> phones;
    private BankAccount account;
}
