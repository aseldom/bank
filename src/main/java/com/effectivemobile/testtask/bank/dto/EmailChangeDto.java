package com.effectivemobile.testtask.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailChangeDto {

    @NotEmpty
    @Email
    private String oldEmail;

    @NotEmpty
    @Email
    private String newEmail;
}