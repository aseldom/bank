package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.AddPhoneDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;
import com.effectivemobile.testtask.bank.mapper.UserMapper;
import com.effectivemobile.testtask.bank.model.Phone;
import com.effectivemobile.testtask.bank.model.User;
import com.effectivemobile.testtask.bank.repository.PhoneRepository;
import com.effectivemobile.testtask.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@AllArgsConstructor
@Service
public class PhoneServiceImpl implements PhoneService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final UserMapper userMapper;

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
    public void deletePhone(String username, String phone) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Set<Phone> phones = user.getPhones();
        if (phones.size() < 2) {
            throw new IllegalArgumentException("Cannot delete the last phone");
        }
        Phone deletePhone = phones.stream()
                .filter(p -> p.getPhone().equals(phone))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Nothing to delete"));
        phoneRepository.delete(deletePhone);
    }
}
