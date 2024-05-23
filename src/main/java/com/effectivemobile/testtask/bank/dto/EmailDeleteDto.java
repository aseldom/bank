package com.effectivemobile.testtask.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDeleteDto {

    @NotEmpty
    @Email
    private String email;
}
