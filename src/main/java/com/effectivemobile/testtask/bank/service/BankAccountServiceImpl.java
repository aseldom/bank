package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.MoneyTransferDto;
import com.effectivemobile.testtask.bank.dto.MoneyTransferReturnDto;
import com.effectivemobile.testtask.bank.model.BankAccount;
import com.effectivemobile.testtask.bank.model.User;
import com.effectivemobile.testtask.bank.repository.BankAccountRepository;
import com.effectivemobile.testtask.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class BankAccountServiceImpl implements BankAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;

    @Transactional
    @Override
    public MoneyTransferReturnDto transferMoney(MoneyTransferDto moneyTransferDto, String userName) {
        User fromUser = getUserByUserName(userName, "User not found");
        User toUser = getUserByUserName(moneyTransferDto.getRecipient(), "Recipient not found");

        if (fromUser.equals(toUser)) {
            throw new IllegalArgumentException("The sender is the recipient");
        }

        BigDecimal moneyTransfer = moneyTransferDto.getValue();

        BankAccount fromAccount = getBankAccountByUserId(fromUser.getId());
        BankAccount toAccount = getBankAccountByUserId(toUser.getId());

        if (fromAccount.getBalance().compareTo(moneyTransfer) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(moneyTransfer));
        toAccount.setBalance(toAccount.getBalance().add(moneyTransfer));

        bankAccountRepository.save(fromAccount);
        bankAccountRepository.save(toAccount);

        LOGGER.info("Transferred {} from user {} to user {}", moneyTransfer, fromUser.getUserName(), toUser.getUserName());
        return new MoneyTransferReturnDto(fromAccount.getBalance());
    }

    private User getUserByUserName(String userName, String errorMessage) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    LOGGER.error(errorMessage + ": {}", userName);
                    return new IllegalArgumentException(errorMessage);
                });
    }

    private BankAccount getBankAccountByUserId(Long userId) {
        return bankAccountRepository.findById(userId)
                .orElseThrow(() -> {
                    LOGGER.error("Account not found for user ID: {}", userId);
                    return new IllegalArgumentException("Account not found");
                });
    }
}