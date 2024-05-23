package com.effectivemobile.testtask.bank.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class MoneyTransferDto {

    @NotEmpty
    private String recipient;

    @PositiveOrZero
    @NotNull
    private BigDecimal value;

}
