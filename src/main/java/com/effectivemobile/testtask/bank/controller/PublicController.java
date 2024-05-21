package com.effectivemobile.testtask.bank.controller;

import com.effectivemobile.testtask.bank.dto.UserCreateDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;
import com.effectivemobile.testtask.bank.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("api/v1/public")
@RestController
public class PublicController {

    private final UserService userService;

    @PostMapping("/users/create")
    public ResponseEntity<UserReturnDto> saveUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserReturnDto userReturnDto = userService.saveUser(userCreateDto).get(); // exception
        return ResponseEntity.status(HttpStatus.CREATED).body(userReturnDto);
    }

}
