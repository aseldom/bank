package com.effectivemobile.testtask.bank.controller;

import com.effectivemobile.testtask.bank.dto.TransferMoneyDto;
import com.effectivemobile.testtask.bank.dto.TransferMoneyReturnDto;
import com.effectivemobile.testtask.bank.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@AllArgsConstructor
@RestController()
@RequestMapping("api/v1/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PutMapping("/transfer")
    public ResponseEntity<TransferMoneyReturnDto> transferMoney(@RequestBody TransferMoneyDto transferMoneyDto, Principal principal) {
        TransferMoneyReturnDto transferMoneyReturnDto = bankAccountService.transferMoney(transferMoneyDto, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(transferMoneyReturnDto);
    }
}
