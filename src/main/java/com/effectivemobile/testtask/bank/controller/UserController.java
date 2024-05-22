package com.effectivemobile.testtask.bank.controller;

import com.effectivemobile.testtask.bank.dto.*;
import com.effectivemobile.testtask.bank.service.EmailService;
import com.effectivemobile.testtask.bank.service.PhoneService;
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
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final PhoneService phoneService;
    private final EmailService emailService;

    @PostMapping("/create")
    public ResponseEntity<UserReturnDto> saveUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserReturnDto userReturnDto = userService.saveUser(userCreateDto).get(); // exception
        return ResponseEntity.status(HttpStatus.CREATED).body(userReturnDto);
    }

    @GetMapping
    public ResponseEntity<List<UserReturnDto>> findAllUsers() {
//        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/add-phone")
    public ResponseEntity<UserReturnDto> addPhone(@RequestBody PhoneAddDto phoneAddDto, Principal principal) {
        UserReturnDto updatedUser = phoneService.addPhone(principal.getName(), phoneAddDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/change-phone")
    public ResponseEntity<UserReturnDto> changePhone(@RequestBody PhoneChangeDto phoneChangeDto, Principal principal) {
        UserReturnDto updatedUser = phoneService.changePhone(principal.getName(), phoneChangeDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete-phone")
    public ResponseEntity<String> deletePhone(@RequestBody PhoneDeleteDto phoneDeleteDto, Principal principal) {
        try {
            phoneService.deletePhone(principal.getName(), phoneDeleteDto);
            return ResponseEntity.ok("Phone deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-email")
    public ResponseEntity<UserReturnDto> addEmail(@RequestBody EmailAddDto emailAddDto, Principal principal) {
        UserReturnDto updatedUser = emailService.addEmail(principal.getName(), emailAddDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/change-email")
    public ResponseEntity<UserReturnDto> changePhone(@RequestBody EmailChangeDto emailChangeDto, Principal principal) {
        UserReturnDto updatedUser = emailService.changeEmail(principal.getName(), emailChangeDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete-email")
    public ResponseEntity<String> deleteEmail(@RequestBody EmailDeleteDto deleteEmailDto, Principal principal) {
        try {
            emailService.deleteEmail(principal.getName(), deleteEmailDto);
            return ResponseEntity.ok("Email deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}