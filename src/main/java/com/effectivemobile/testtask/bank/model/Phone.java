package com.effectivemobile.testtask.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phones", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"phone"})
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Phone(String phone) {
        this.phone = phone;
    }
}