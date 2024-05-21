package com.effectivemobile.testtask.bank.repository;

import com.effectivemobile.testtask.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

}