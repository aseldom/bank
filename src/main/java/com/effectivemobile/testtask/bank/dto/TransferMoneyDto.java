package com.effectivemobile.testtask.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TransferMoneyDto {

    private String recipient;
    private BigDecimal value;

}
