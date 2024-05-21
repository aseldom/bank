package com.effectivemobile.testtask.bank.repository;

import com.effectivemobile.testtask.bank.model.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailAddress, Long> {

}
