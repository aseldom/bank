package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.AddEmailDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;

public interface EmailService {

    UserReturnDto addEmail(String username, AddEmailDto addEmailDto);

    void deleteEmail(String username, String email);
}
