package com.effectivemobile.testtask.bank.repository;

import com.effectivemobile.testtask.bank.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}