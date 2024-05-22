package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.MoneyTransferDto;
import com.effectivemobile.testtask.bank.dto.MoneyTransferReturnDto;

public interface BankAccountService {

    MoneyTransferReturnDto transferMoney(MoneyTransferDto moneyTransferDto, String userName);

}
