package com.effectivemobile.testtask.bank.controller;

import com.effectivemobile.testtask.bank.dto.*;
import com.effectivemobile.testtask.bank.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserReturnDto> saveUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserReturnDto userReturnDto = userService.saveUser(userCreateDto).get(); // exception
        return ResponseEntity.status(HttpStatus.CREATED).body(userReturnDto);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserReturnDto>> findAllUsers() {
//        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/add-phone")
    public ResponseEntity<UserReturnDto> addPhone(@RequestBody AddPhoneDto addPhoneDto, Principal principal) {
        UserReturnDto updatedUser = userService.addPhone(principal.getName(), addPhoneDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/add-email")
    public ResponseEntity<UserReturnDto> addEmail(@RequestBody AddEmailDto addEmailDto, Principal principal) {
        UserReturnDto updatedUser = userService.addEmail(principal.getName(), addEmailDto);
        return ResponseEntity.ok(updatedUser);
    }

}