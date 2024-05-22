package com.effectivemobile.testtask.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailChangeDto {

    private String oldEmail;

    private String newEmail;
}