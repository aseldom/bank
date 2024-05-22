package com.effectivemobile.testtask.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phones", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"number"})
})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "number")
@Getter
@Setter
@ToString
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Phone(String number) {
        this.number = number;
    }
}