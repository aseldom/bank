package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.MoneyTransferDto;
import com.effectivemobile.testtask.bank.dto.MoneyTransferReturnDto;
import com.effectivemobile.testtask.bank.dto.UserCreateDto;
import com.effectivemobile.testtask.bank.model.BankAccount;
import com.effectivemobile.testtask.bank.model.EmailAddress;
import com.effectivemobile.testtask.bank.model.Phone;
import com.effectivemobile.testtask.bank.repository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BankAccountTransferMoneyTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Test
    @Transactional
    public void transferMoneySuccessful() {
        UserCreateDto sender = getUser();
        userService.saveUser(sender);
        BankAccount senderAccount = sender.getAccount();

        UserCreateDto recipient = getUser();
        recipient.setEmails(Set.of(new EmailAddress("email2@email.com")));
        recipient.setPhones(Set.of(new Phone("222-222-333")));
        recipient.setUserName("recipient");
        userService.saveUser(recipient);
        BankAccount recipientAccount = recipient.getAccount();

        MoneyTransferDto moneyTransferDto = new MoneyTransferDto();
        moneyTransferDto.setRecipient("recipient");
        moneyTransferDto.setValue(BigDecimal.valueOf(100));

        MoneyTransferReturnDto result = bankAccountService.transferMoney(moneyTransferDto, "sender");

        assertThat(BigDecimal.valueOf(900)).isEqualTo(result.getBalance());
        assertThat(BigDecimal.valueOf(900)).isEqualTo(bankAccountRepository.findById(senderAccount.getId()).get().getBalance());
        assertThat(BigDecimal.valueOf(1100)).isEqualTo(bankAccountRepository.findById(recipientAccount.getId()).get().getBalance());

    }

    @Test
    @Transactional
    public void transferMoneyUserNotFound() {
        MoneyTransferDto moneyTransferDto = new MoneyTransferDto();
        moneyTransferDto.setRecipient("recipient");
        moneyTransferDto.setValue(BigDecimal.valueOf(100));

        assertThrows(IllegalArgumentException.class,
                () -> bankAccountService.transferMoney(moneyTransferDto, "sender"),
                "User not found");

    }

    @Test
    @Transactional
    public void transferMoneyRecipientNotFound() {
        UserCreateDto sender = getUser();
        userService.saveUser(sender);

        MoneyTransferDto moneyTransferDto = new MoneyTransferDto();
        moneyTransferDto.setRecipient("recipient");
        moneyTransferDto.setValue(BigDecimal.valueOf(100));

        assertThrows(IllegalArgumentException.class,
                () -> bankAccountService.transferMoney(moneyTransferDto, "sender"),
                "Recipient not found");
    }

    @Test
    @Transactional
    public void transferMoneySenderIsRecipient() {
        UserCreateDto sender = getUser();
        userService.saveUser(sender);

        MoneyTransferDto moneyTransferDto = new MoneyTransferDto();
        moneyTransferDto.setRecipient("sender");
        moneyTransferDto.setValue(BigDecimal.valueOf(100));

        assertThrows(IllegalArgumentException.class,
                () -> bankAccountService.transferMoney(moneyTransferDto, "sender"),
                "The sender is the recipient");
    }

    @Test
    @Transactional
    public void transferMoneyInsufficientBalance() {
        UserCreateDto sender = getUser();
        userService.saveUser(sender);

        UserCreateDto recipient = getUser();
        recipient.setEmails(Set.of(new EmailAddress("email2@email.com")));
        recipient.setPhones(Set.of(new Phone("222-222-333")));
        recipient.setUserName("recipient");
        userService.saveUser(recipient);

        MoneyTransferDto moneyTransferDto = new MoneyTransferDto();
        moneyTransferDto.setRecipient("recipient");
        moneyTransferDto.setValue(BigDecimal.valueOf(2000));

        assertThrows(IllegalArgumentException.class,
                () -> bankAccountService.transferMoney(moneyTransferDto, "sender"),
                "Insufficient balance");
    }

    private static UserCreateDto getUser() {
        BankAccount senderAccount = new BankAccount();
        senderAccount.setBalance(BigDecimal.valueOf(1000));
        senderAccount.setInitialDeposit(BigDecimal.valueOf(1000));
        UserCreateDto user = new UserCreateDto();
        user.setUserName("sender");
        user.setPassword("password");
        user.setFullName("Sender Fullname");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setEmails(Set.of(new EmailAddress("email1@email.com")));
        user.setPhones(Set.of(new Phone("111-222-333")));
        user.setAccount(senderAccount);
        return user;
    }
}
