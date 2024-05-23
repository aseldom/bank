package com.effectivemobile.testtask.bank.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDeleteDto {

    @NotEmpty
    private String number;
}
