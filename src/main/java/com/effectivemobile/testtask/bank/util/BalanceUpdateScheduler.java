package com.effectivemobile.testtask.bank.util;

import com.effectivemobile.testtask.bank.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@AllArgsConstructor
@Component
public class BalanceUpdateScheduler {

    private final BankAccountRepository bankAccountRepository;

    @Scheduled(fixedRate = 60000) // every minute
    @Transactional
    public void updateBalances() {
        bankAccountRepository.findAll().forEach((account) -> {
            BigDecimal maxBalance = account.getInitialDeposit().multiply(new BigDecimal("2.07"));
            BigDecimal balance = account.getBalance();
            if (maxBalance.compareTo(balance) >= 0) {
                BigDecimal newBalance = balance.multiply(new BigDecimal("1.05"));
                if (newBalance.compareTo(maxBalance) > 0) {
                    newBalance = maxBalance;
                }
                account.setBalance(newBalance);
                bankAccountRepository.save(account);
            }
        });
    }
}