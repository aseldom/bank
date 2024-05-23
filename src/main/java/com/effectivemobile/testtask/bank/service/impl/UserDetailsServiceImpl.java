package com.effectivemobile.testtask.bank.service.impl;

import com.effectivemobile.testtask.bank.model.User;
import com.effectivemobile.testtask.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userName: " + userName));
        return new org.springframework.security.core.userdetails
                .User(user.getUserName(), user.getPassword(), new ArrayList<>());

    }
}