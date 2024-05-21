package com.effectivemobile.testtask.bank.repository;

import com.effectivemobile.testtask.bank.model.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailAddressRepository extends JpaRepository<EmailAddress, Long> {

    Optional<EmailAddress> findByEmail(String email);

}
