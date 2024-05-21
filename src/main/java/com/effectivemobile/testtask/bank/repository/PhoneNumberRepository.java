package com.effectivemobile.testtask.bank.repository;

import com.effectivemobile.testtask.bank.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneNumberRepository extends JpaRepository<Phone, Long> {

    Optional<Phone> findByPhone(String phone);

}
