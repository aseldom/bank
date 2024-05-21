package com.effectivemobile.testtask.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "emails", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmailAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public EmailAddress(String email) {
        this.email = email;
    }
}