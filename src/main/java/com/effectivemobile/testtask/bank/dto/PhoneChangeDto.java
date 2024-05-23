package com.effectivemobile.testtask.bank.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneChangeDto {

    @NotEmpty
    private String oldPhoneNumber;

    @NotEmpty
    private String newPhoneNumber;
}