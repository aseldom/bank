package com.effectivemobile.testtask.bank.util;

import com.effectivemobile.testtask.bank.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class UserSpecifications {

    public static Specification<User> hasBirthDateAfter(LocalDate birthDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("birthDate"), birthDate);
    }

    public static Specification<User> hasPhone(String number) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("phones").get("number"), number);
    }

    public static Specification<User> hasFullNameLike(String fullName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("fullName"), fullName + "%");
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("emails").get("email"), email);
    }

}
