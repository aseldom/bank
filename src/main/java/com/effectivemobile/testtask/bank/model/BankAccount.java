package com.effectivemobile.testtask.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@org.hibernate.annotations.Check(constraints = "balance >= 0 AND initial_deposit >= 0")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.valueOf(0);

    @Column(nullable = false)
    private BigDecimal initialDeposit = BigDecimal.valueOf(0);
}