package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.AddEmailDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;
import com.effectivemobile.testtask.bank.mapper.UserMapper;
import com.effectivemobile.testtask.bank.model.EmailAddress;
import com.effectivemobile.testtask.bank.model.User;
import com.effectivemobile.testtask.bank.repository.EmailRepository;
import com.effectivemobile.testtask.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserReturnDto addEmail(String username, AddEmailDto addEmailDto) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        EmailAddress email = new EmailAddress(addEmailDto.getEmail());
        email.setUser(user);
        user.getEmails().add(email);
        return userMapper.toUserReturnDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteEmail(String username, String email) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Set<EmailAddress> emails = user.getEmails();
        if (emails.size() < 2) {
            throw new IllegalArgumentException("Cannot delete the last email");
        }
        EmailAddress deleteEmail = emails.stream()
                .filter(e -> e.getEmail().equals(email))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Nothing to delete"));
        emails.remove(deleteEmail);
        emailRepository.delete(deleteEmail);
    }
}
