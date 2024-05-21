package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.AddEmailDto;
import com.effectivemobile.testtask.bank.dto.AddPhoneDto;
import com.effectivemobile.testtask.bank.dto.UserCreateDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;
import com.effectivemobile.testtask.bank.mapper.UserMapper;
import com.effectivemobile.testtask.bank.model.EmailAddress;
import com.effectivemobile.testtask.bank.model.Phone;
import com.effectivemobile.testtask.bank.model.User;
import com.effectivemobile.testtask.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public Optional<UserReturnDto> saveUser(UserCreateDto userCreateDto) {
        User user = userMapper.toUser(userCreateDto);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        User savedUser = userRepository.save(user);
        return Optional.of(userMapper.toUserReturnDto(savedUser));
    }

    @Transactional
    @Override
    public UserReturnDto addPhone(String username, AddPhoneDto addPhoneDto) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Phone phone = new Phone(addPhoneDto.getPhone());
        phone.setUser(user);
        user.getPhones().add(phone);

        return userMapper.toUserReturnDto(userRepository.save(user));
    }

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
    public List<UserReturnDto> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return userMapper.toUserReturnDtos(allUsers);
    }
}