package com.effectivemobile.testtask.bank.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDeleteDto {
    @Email
    private String email;
}