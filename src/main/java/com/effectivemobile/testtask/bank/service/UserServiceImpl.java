package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.UserCreateDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;
import com.effectivemobile.testtask.bank.dto.UserSearchDto;
import com.effectivemobile.testtask.bank.mapper.UserMapper;
import com.effectivemobile.testtask.bank.model.User;
import com.effectivemobile.testtask.bank.repository.UserRepository;
import com.effectivemobile.testtask.bank.util.UserSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Override
    public Page<UserSearchDto> searchUsers(
            LocalDate birthDate,
            String number,
            String fullName,
            String email,
            int page,
            int size,
            String sortBy,
            String sortDir
    ) {
        Pageable pageable = PageRequest.of(page, size, sortDir.equals("asc")
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Specification<User> spec = Specification.where(null);

        if (birthDate != null) {
            spec = spec.and(UserSpecifications.hasBirthDateAfter(birthDate));
        }
        if (number != null && !number.isEmpty()) {
            spec = spec.and(UserSpecifications.hasPhone(number));
        }
        if (fullName != null && !fullName.isEmpty()) {
            spec = spec.and(UserSpecifications.hasFullNameLike(fullName));
        }
        if (email != null && !email.isEmpty()) {
            spec = spec.and(UserSpecifications.hasEmail(email));
        }
        return userRepository.findAll(spec, pageable).map(userMapper::toUserSearchDto);
    }

}