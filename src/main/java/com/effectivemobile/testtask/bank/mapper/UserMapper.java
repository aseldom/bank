package com.effectivemobile.testtask.bank.mapper;

import com.effectivemobile.testtask.bank.dto.UserCreateDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;
import com.effectivemobile.testtask.bank.model.EmailAddress;
import com.effectivemobile.testtask.bank.model.Phone;
import com.effectivemobile.testtask.bank.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserReturnDto toUserReturnDto(User user);

    List<UserReturnDto> toUserReturnDtos(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emails", ignore = true)
    @Mapping(target = "phones", ignore = true)
    User toUserFirst(UserCreateDto userCreateDto);

    default User toUser(UserCreateDto userCreateDto) {
        User user = toUserFirst(userCreateDto);
        user.getAccount().setBalance(
                user.getAccount().getInitialDeposit()
        );
        toEmails(userCreateDto, user);
        toPhones(userCreateDto, user);
        return user;
    }

    default void toPhones(UserCreateDto userCreateDto, User user) {
        Set<Phone> phones = userCreateDto.getPhones();
        phones.forEach(phone -> phone.setUser(user));
        user.setPhones(phones);
    }

    default void toEmails(UserCreateDto userCreateDto, User user) {
        Set<EmailAddress> emails = userCreateDto.getEmails();
        emails.forEach(email -> email.setUser(user));
        user.setEmails(emails);
    }

}
