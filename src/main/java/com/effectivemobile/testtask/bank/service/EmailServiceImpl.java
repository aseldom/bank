package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.EmailAddDto;
import com.effectivemobile.testtask.bank.dto.EmailChangeDto;
import com.effectivemobile.testtask.bank.dto.EmailDeleteDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;
import com.effectivemobile.testtask.bank.mapper.UserMapper;
import com.effectivemobile.testtask.bank.model.EmailAddress;
import com.effectivemobile.testtask.bank.model.User;
import com.effectivemobile.testtask.bank.repository.EmailRepository;
import com.effectivemobile.testtask.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserReturnDto addEmail(String userName, EmailAddDto emailAddDto) {
        User user = checkUser(userName);
        EmailAddress email = new EmailAddress(emailAddDto.getEmail());
        email.setUser(user);
        emailRepository.save(email);
        return userMapper.toUserReturnDto(user);
    }

    @Transactional
    @Override
    public void deleteEmail(String userName, EmailDeleteDto emailDeleteDto) {
        User user = checkUser(userName);
        EmailAddress deleteEmail = new EmailAddress(emailDeleteDto.getEmail());
        Set<EmailAddress> emails = user.getEmails();
        if (emails.size() < 2) {
            throw new IllegalArgumentException("Cannot delete the last email");
        }
        if (!emails.contains(deleteEmail)) {
            throw new IllegalArgumentException("Nothing to delete");
        }
        emails.remove(deleteEmail);
        emailRepository.delete(deleteEmail);
    }

    @Transactional
    @Override
    public UserReturnDto changeEmail(String userName, EmailChangeDto emailChangeDto) {
        User user = checkUser(userName);
        EmailAddress oldEmail = new EmailAddress(emailChangeDto.getOldEmail());
        EmailAddress newEmail = new EmailAddress(emailChangeDto.getNewEmail());
        Set<EmailAddress> emails = user.getEmails();
        if (!emails.remove(oldEmail)) {
            throw new IllegalArgumentException("Email to change not found");
        }
        newEmail.setUser(user);
        emails.add(newEmail);
        emailRepository.save(newEmail);
        return userMapper.toUserReturnDto(user);
    }

    private User checkUser(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
