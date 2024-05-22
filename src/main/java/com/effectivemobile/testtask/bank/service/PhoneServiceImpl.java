package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.PhoneAddDto;
import com.effectivemobile.testtask.bank.dto.PhoneChangeDto;
import com.effectivemobile.testtask.bank.dto.PhoneDeleteDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;
import com.effectivemobile.testtask.bank.mapper.UserMapper;
import com.effectivemobile.testtask.bank.model.Phone;
import com.effectivemobile.testtask.bank.model.User;
import com.effectivemobile.testtask.bank.repository.PhoneRepository;
import com.effectivemobile.testtask.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
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
    public UserReturnDto addPhone(String userName, PhoneAddDto phoneAddDto) {
        User user = checkUser(userName);
        Phone phone = new Phone(phoneAddDto.getNumber());
        phone.setUser(user);
        phoneRepository.save(phone);
        return userMapper.toUserReturnDto(user);
    }

    @Transactional
    @Override
    public void deletePhone(String userName, PhoneDeleteDto phoneDeleteDto) {
        User user = checkUser(userName);
        Phone deletePhone = new Phone(phoneDeleteDto.getNumber());
        Set<Phone> phones = user.getPhones();
        if (phones.size() < 2) {
            throw new IllegalArgumentException("Cannot delete the last phone");
        }
        if (!phones.contains(deletePhone)) {
            throw new IllegalArgumentException("Nothing to delete");
        }
        phones.remove(deletePhone);
        phoneRepository.delete(deletePhone);
    }

    @Transactional
    @Override
    public UserReturnDto changePhone(String userName, PhoneChangeDto phoneChangeDto) {
        User user = checkUser(userName);
        Phone oldPhone = new Phone(phoneChangeDto.getOldPhoneNumber());
        Phone newPhone = new Phone(phoneChangeDto.getNewPhoneNumber());
        Set<Phone> phones = user.getPhones();
        if (!phones.remove(oldPhone)) {
            throw new IllegalArgumentException("Phone to change not found");
        }
        newPhone.setUser(user);
        phones.add(newPhone);
        phoneRepository.save(newPhone);
        return userMapper.toUserReturnDto(user);

    }

    private User checkUser(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

}
