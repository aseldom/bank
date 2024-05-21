package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.TransferMoneyDto;
import com.effectivemobile.testtask.bank.dto.TransferMoneyReturnDto;

public interface BankAccountService {

    TransferMoneyReturnDto transferMoney(TransferMoneyDto transferMoneyDto, String userName);

}
