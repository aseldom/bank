package com.effectivemobile.testtask.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneChangeDto {

    private String oldPhoneNumber;

    private String newPhoneNumber;
}